package dev.sojunx.ecommerce.config;

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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomAuthenticationFilter extends OncePerRequestFilter {
    private final CustomUserDetailsService service;

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    record EndpointMatcher(String pattern, HttpMethod method) { }

    private final List<EndpointMatcher> whitelist = List.of(
            new EndpointMatcher("/api/users/login", HttpMethod.POST),
            new EndpointMatcher("/api/users/register", HttpMethod.POST),
            new EndpointMatcher("/api/products", HttpMethod.GET)
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
        final Optional<Cookie> cookie = extractCookie(req, "session_id");
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

            log.info("Authenticating user with session id: {}", cookie.get().getValue());
        } catch (Exception e) {
            log.error("Error while authenticating user", e);
        }

        chain.doFilter(req, res);
    }

    private Optional<Cookie> extractCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return Optional.empty();

        return Arrays.stream(cookies).filter(cookie -> cookie.getName().equals(name)).findFirst();
    }
}
