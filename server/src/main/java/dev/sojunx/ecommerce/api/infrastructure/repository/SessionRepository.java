package dev.sojunx.ecommerce.api.infrastructure.repository;

import dev.sojunx.ecommerce.api.domain.entities.Session;
import dev.sojunx.ecommerce.api.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findByToken(String token);

    List<Session> findAllByUser(User user);
}
