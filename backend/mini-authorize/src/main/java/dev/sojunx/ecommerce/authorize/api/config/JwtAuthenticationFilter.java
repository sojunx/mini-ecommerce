package dev.sojunx.ecommerce.authorize.api.config;

import dev.sojunx.ecommerce.authorize.api.service.CookieService;
import dev.sojunx.ecommerce.authorize.api.service.CustomUserDetailsService;
import dev.sojunx.ecommerce.authorize.api.service.JwtService;
import dev.sojunx.ecommerce.authorize.api.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;
    private final TokenService tokenService;
    private final CookieService cookieService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest req,
            @NonNull HttpServletResponse res,
            @NonNull FilterChain chain
    ) throws ServletException, IOException {

        if (isAuthEndpoint(req)) {
            chain.doFilter(req, res);
            return;
        }

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

        } catch (Exception e) {
            log.error("Could not set user authentication in security context", e);
        }

        chain.doFilter(req, res);
    }

    private void authenticate(HttpServletRequest req, String token, String refreshToken, String email) {
        var userDetails = userDetailsService.loadUserByUsername(email);
        boolean isTokenValid = tokenService.validate(refreshToken);

        if (jwtService.isTokenValid(token, userDetails) && isTokenValid) {
            var authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
    }

    private boolean isAuthEndpoint(HttpServletRequest req) {
        return req.getServletPath().contains("/api/auth");
    }

    private String extractBearerToken(HttpServletRequest req) {
        String header = req.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer "))
            return null;

        String token = header.substring(7).trim();
        return token.isEmpty() ? null : token;
    }
}
