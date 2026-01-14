package dev.sojunx.ecommerce.api.infrastructure.config;

import dev.sojunx.ecommerce.api.application.service.helper.CookieService;
import dev.sojunx.ecommerce.api.application.service.helper.CustomUserDetailsService;
import dev.sojunx.ecommerce.api.application.service.helper.JwtService;
import dev.sojunx.ecommerce.api.application.service.user.SessionService;
import dev.sojunx.ecommerce.api.domain.entities.user.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
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
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;
    private final SessionService sessionService;
    private final CookieService cookieService;

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    record EndpointMatcher(String pattern, HttpMethod method) { }

    private final List<EndpointMatcher> whiteList = List.of(
            new EndpointMatcher("/api/auth/**", null),
            new EndpointMatcher("/h2-console/**", null),
            new EndpointMatcher("/v3/api-docs/**", null),
            new EndpointMatcher("/swagger-ui/**", null),
            new EndpointMatcher("/api/reviews/**", HttpMethod.GET),
            new EndpointMatcher("/api/products/**", HttpMethod.GET)
    );

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest req) {
        var path = req.getServletPath();
        var method = req.getMethod();

        return whiteList.stream().anyMatch(endpoint -> {
            var matchPath = pathMatcher.match(endpoint.pattern(), path);

            var matchMethod = endpoint.method() == null || endpoint.method().name().equalsIgnoreCase(method);

            return matchPath && matchMethod;
        });
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest req,
            @NonNull HttpServletResponse res,
            @NonNull FilterChain chain
    ) throws ServletException, IOException {

        final String token = extractBearerToken(req);
        final Optional<Cookie> cookie = cookieService.extractCookie(req, "refresh_token");
        if (token == null || cookie.isEmpty()) {
            chain.doFilter(req, res);
            return;
        }

        try {
            final String email = jwtService.extractUsername(token);

            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null)
                authenticate(req, token, cookie.get().getValue(), email);

            // FIXME: Bug here, user have token but not return error
            log.info("User authenticated with token: {}", token);
        } catch (Exception e) {
            log.error("Could not set user authentication in security context", e);
        }

        chain.doFilter(req, res);
    }

    private void authenticate(HttpServletRequest req, String token, String refreshToken, String email) {
        var userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(email);
        boolean isTokenValid = sessionService.validate(refreshToken);

        if (jwtService.isTokenValid(token, userDetails) && isTokenValid) {
            var authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
    }

    private String extractBearerToken(HttpServletRequest req) {
        String header = req.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer "))
            return null;

        String token = header.substring(7).trim();
        return token.isEmpty() ? null : token;
    }
}
