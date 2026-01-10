package dev.sojunx.ecommerce.authorize.api.repository;

import dev.sojunx.ecommerce.authorize.api.domain.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
