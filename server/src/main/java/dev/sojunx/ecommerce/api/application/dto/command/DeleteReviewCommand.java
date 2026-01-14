package dev.sojunx.ecommerce.api.application.dto.command;

import lombok.Data;

import java.util.UUID;

@Data
public class DeleteReviewCommand {
    private UUID reviewId;
}
