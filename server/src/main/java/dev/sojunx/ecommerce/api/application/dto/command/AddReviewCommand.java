package dev.sojunx.ecommerce.api.application.dto.command;

import lombok.Data;

@Data
public class AddReviewCommand {
    private int rating; // In 10

    private String title;
    private String comment;
}
