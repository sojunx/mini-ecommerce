package dev.sojunx.ecommerce.api.application.dto.core;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pagination {
    private long page;
    private long limit;
    private long total;
    private long totalPages;
}
