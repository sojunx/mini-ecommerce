package dev.sojunx.ecommerce.api.application.service.product;

import dev.sojunx.ecommerce.api.application.dto.command.AddReviewCommand;
import dev.sojunx.ecommerce.api.application.dto.query.ReviewDetails;
import dev.sojunx.ecommerce.api.application.mapper.ReviewMapper;
import dev.sojunx.ecommerce.api.domain.entities.user.User;
import dev.sojunx.ecommerce.api.infrastructure.repository.ProductRepository;
import dev.sojunx.ecommerce.api.infrastructure.repository.ReviewRepository;
import dev.sojunx.ecommerce.api.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository repo;
    private final ReviewMapper mapper;
    private final ProductRepository productRepo;
    private final UserRepository userRepo;

    @Transactional(readOnly = true)
    public List<ReviewDetails> findAllByProductId(UUID id) {
        var product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        var reviews = repo.findAllByProduct(product);

        return reviews.stream().map(mapper::toDto).toList();
    }

    @Transactional
    public void addReview(User user, UUID id, AddReviewCommand command) {
        var product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        var review = mapper.toEntity(command);
        review.setProduct(product);
        review.setUser(user);

        product.getReviews().add(review);

        productRepo.save(product);
    }

    @Transactional
    public void deleteReview(UUID id) {
        repo.deleteById(id);
    }
}
