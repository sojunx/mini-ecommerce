package dev.sojunx.ecommerce.api.service.core;

import dev.sojunx.ecommerce.api.dto.command.SignUpCommand;
import dev.sojunx.ecommerce.api.dto.query.UserDetails;
import dev.sojunx.ecommerce.api.mapper.UserMapper;
import dev.sojunx.ecommerce.api.repository.UserRepository;
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

    public void save(SignUpCommand command) {
        // Validate password - confirm password

        // Save user
        repo.save(mapper.toEntity(command));
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
