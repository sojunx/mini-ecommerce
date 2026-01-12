package dev.sojunx.ecommerce.api.domain.entities.product;

import dev.sojunx.ecommerce.api.domain.entities.cart.CartItem;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_variants")
@Data
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @OneToMany(mappedBy = "variant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItem = new ArrayList<>();

    // NOTES: This is unique code, human-readable
    // staff can scan qr-code to know what type of product
    @Column(unique = true, nullable = false)
    private String sku;

    @Column(nullable = false)
    private String size;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private double price;

    private String imageUrl;

    // Need to add warehouse to handle this
    @Column(nullable = false)
    private long stockQuantity;
}
