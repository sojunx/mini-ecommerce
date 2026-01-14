package dev.sojunx.ecommerce.api.application.service.user;

import dev.sojunx.ecommerce.api.application.dto.command.SignUpCommand;
import dev.sojunx.ecommerce.api.application.dto.query.UserDetails;
import dev.sojunx.ecommerce.api.application.mapper.UserMapper;
import dev.sojunx.ecommerce.api.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository repo;
    private final UserMapper mapper;

    public UserDetails save(SignUpCommand command) {
        var savedUser = repo.save(mapper.toEntity(command));

        return mapper.toDto(savedUser);
    }

    public UserDetails findById(UUID id) {
        var result = repo.findById(id);
        if (result.isEmpty())
            throw new UsernameNotFoundException("User not found with id: " + id);

        return mapper.toDto(result.get());
    }

    public UserDetails findByEmail(String email) {
        var result = repo.findByEmail(email);
        if (result.isEmpty())
            throw new UsernameNotFoundException("User not found with email: " + email);

        return mapper.toDto(result.get());
    }

    public List<UserDetails> findAll() {
        return repo.findAll().stream().map(mapper::toDto).toList();
    }
}
