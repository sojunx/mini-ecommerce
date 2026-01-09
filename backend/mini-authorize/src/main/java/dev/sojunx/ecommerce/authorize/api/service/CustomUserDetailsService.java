package dev.sojunx.ecommerce.authorize.api.service;

import dev.sojunx.ecommerce.authorize.api.model.CustomUserDetails;
import dev.sojunx.ecommerce.authorize.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(@NonNull String email) throws UsernameNotFoundException {
        var result = repo.findByEmail(email);
        if (result.isEmpty())
            throw new UsernameNotFoundException("User not found with email: " + email);

        return new CustomUserDetails(result.get());
    }
}
