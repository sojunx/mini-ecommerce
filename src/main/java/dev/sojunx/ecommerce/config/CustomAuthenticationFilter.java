package dev.sojunx.ecommerce.config;

import dev.sojunx.ecommerce.utils.CookieUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomAuthenticationFilter extends OncePerRequestFilter {
    private final CustomUserDetailsService service;
    private final CookieUtil cookieUtil;

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    record EndpointMatcher(String pattern, HttpMethod method) { }

    private final List<EndpointMatcher> whitelist = List.of(
            new EndpointMatcher("/api/auth", HttpMethod.POST),
            new EndpointMatcher("/api/products", HttpMethod.GET),
            new EndpointMatcher("/v3/api-docs", HttpMethod.GET),
            new EndpointMatcher("/swagger-ui", HttpMethod.GET),
            new EndpointMatcher("/swagger-ui.html", HttpMethod.GET)
    );

    @Override
    protected boolean shouldNotFilter(HttpServletRequest req) {
        var path = req.getServletPath();
        var method = req.getMethod();

        return whitelist.stream().anyMatch(e -> {
            var match = pathMatcher.match(e.pattern(), path);
            var methodMatch = e.method() == null || e.method().name().equalsIgnoreCase(method);
            return match && methodMatch;
        });
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
        final Optional<Cookie> cookie = cookieUtil.extractCookie(req, "session_id");
        if (cookie.isEmpty()) {
            chain.doFilter(req, res);
            return;
        }

        try {
            var session_id = cookie.get().getValue();
            if (session_id.isBlank()) throw new RuntimeException("Invalid session id");

            var userDetails = service.loadUserByUsername(session_id);

            var token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            token.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
            SecurityContextHolder.getContext().setAuthentication(token);

        } catch (Exception e) {
            log.error("Error while authenticating user", e);
        }

        chain.doFilter(req, res);
    }
}
