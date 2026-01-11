package dev.sojunx.ecommerce.api.config;

import dev.sojunx.ecommerce.api.dto.response.ApiError;
import dev.sojunx.ecommerce.api.dto.response.ApiResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper mapper;

    @Override
    public void commence(
            @NonNull HttpServletRequest req,
            @NonNull HttpServletResponse res,
            AuthenticationException ex
    ) throws IOException, ServletException {
        res.setContentType("application/json;charset=UTF-8");
        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        var error = new ApiError(HttpStatus.UNAUTHORIZED.name(), ex.getMessage());
        var data = ApiResponse.error("Please sign in before using this resource", error);
        
        var out = res.getOutputStream();
        mapper.writeValue(out, data);
        out.flush();
    }
}
