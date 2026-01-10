package dev.sojunx.ecommerce.authorize.api.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "ratings")
@Getter
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(nullable = false)
    private int ratingValue;
}
