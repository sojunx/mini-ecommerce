package dev.sojunx.ecommerce.api.application.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
public class CookieService {

    public Optional<Cookie> extractCookie(HttpServletRequest req, String name) {
        Cookie[] cookies = req.getCookies();
        if (cookies == null) return Optional.empty();

        return Arrays.stream(cookies)
                .filter(c -> c.getName().equals(name))
                .findFirst();
    }

    public void setCookie(HttpServletResponse res, String name, String value, long expiration) {
        var cookie = ResponseCookie.from(name, value)
                .httpOnly(true)
                .path("/")
                .maxAge(expiration)
                .build();

        res.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

    public void clearCookie(HttpServletResponse res, String name) {
        setCookie(res, name, "", 0);
    }
}
