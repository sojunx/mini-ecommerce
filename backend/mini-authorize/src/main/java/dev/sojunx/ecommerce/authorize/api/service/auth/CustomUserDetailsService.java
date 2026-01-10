package dev.sojunx.ecommerce.authorize.api.service.auth;

import dev.sojunx.ecommerce.authorize.api.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
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

        return result.get();
    }
}
