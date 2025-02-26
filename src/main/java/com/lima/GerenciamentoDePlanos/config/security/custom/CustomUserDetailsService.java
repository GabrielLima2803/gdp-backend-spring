package com.lima.GerenciamentoDePlanos.config.security.custom;

import com.lima.GerenciamentoDePlanos.user.domain.models.User;
import com.lima.GerenciamentoDePlanos.user.domain.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Buscando usuário com email: {}", username);
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> {
                    log.error("Usuário com email {} não encontrado", username);
                    return new UsernameNotFoundException("Usuário não encontrado");
                });
        return new CustomUserDetails(user);
    }
}