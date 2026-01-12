package dev.sojunx.ecommerce.api.repository;

import dev.sojunx.ecommerce.api.domain.entities.user.RefreshToken;
import dev.sojunx.ecommerce.api.domain.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    List<RefreshToken> findAllByUser(User user);
}
