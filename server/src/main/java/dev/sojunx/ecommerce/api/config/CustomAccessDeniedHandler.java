package dev.sojunx.ecommerce.api.config;

import dev.sojunx.ecommerce.api.dto.helper.ApiError;
import dev.sojunx.ecommerce.api.dto.helper.ApiResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private final ObjectMapper mapper;

    @Override
    public void handle(
            @NonNull HttpServletRequest req,
            @NonNull HttpServletResponse res,
            @NonNull AccessDeniedException ex
    ) throws IOException, ServletException {
        res.setContentType("application/json;charset=UTF-8");
        res.setStatus(HttpServletResponse.SC_FORBIDDEN);

        var error = new ApiError(HttpStatus.FORBIDDEN.name(), ex.getMessage());
        var data = ApiResponse.error("You dont have permissions to access this resource", error);

        var out = res.getOutputStream();
        mapper.writeValue(out, data);
        out.flush();
    }
}
