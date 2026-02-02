package dev.sojunx.ecommerce.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
public class CookieUtil {
    private String build(String name, String data) {
        return ResponseCookie.from(name, data)
                .path("/")
                .httpOnly(true)
                .maxAge(900)
                .build()
                .toString();
    }

    public Optional<Cookie> extractCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return Optional.empty();

        return Arrays.stream(cookies).filter(cookie -> cookie.getName().equals(name)).findFirst();
    }

    public void setCookie(String name, String data, HttpServletResponse res) {
        var cookie = build(name, data);
        res.addHeader(HttpHeaders.SET_COOKIE, cookie);
    }
}
