package dev.sojunx.ecommerce.api.dto.helper;

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
