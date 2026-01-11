package dev.sojunx.ecommerce.api.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "reviews")
@Getter
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", referencedColumnName = "id")
    private Review review;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String feedbackText;
}
