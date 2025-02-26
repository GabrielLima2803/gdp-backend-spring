package com.lima.GerenciamentoDePlanos.user.application.usecases;

import com.lima.GerenciamentoDePlanos.user.application.dto.LoginRequest;
import com.lima.GerenciamentoDePlanos.user.application.dto.LoginResponse;
import com.lima.GerenciamentoDePlanos.user.domain.models.Role;
import com.lima.GerenciamentoDePlanos.user.domain.models.User;
import com.lima.GerenciamentoDePlanos.user.domain.repositories.RoleRepository;
import com.lima.GerenciamentoDePlanos.user.domain.repositories.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserUseCase {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JwtEncoder jwtEncoder;

    public UserUseCase(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, RoleRepository roleRepository, JwtEncoder jwtEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.jwtEncoder = jwtEncoder;
    }



    public User registerUser(User user) {
        userRepository.findByUsername(user.getUsername())
                .ifPresent(existingUser -> {
                    throw new IllegalArgumentException("Username '" + user.getUsername() + "' já está em uso");
                });

        userRepository.findByEmail(user.getEmail())
                .ifPresent(existingUser -> {
                    throw new IllegalArgumentException("Email '" + user.getEmail() + "' já está em uso");
                });

        Role basicRole = roleRepository.findByName(Role.Values.BASIC.name())
                .orElseThrow(() -> new IllegalArgumentException("Role '" + Role.Values.BASIC.name() + "' not found"));

        System.out.println("Senha do Usuario: " + user.getPassword());
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password é obrigatório");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(basicRole));
        return userRepository.save(user);
    }

    public LoginResponse authenticateUser(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.username())
                .orElseThrow(() -> new BadCredentialsException("Credenciais inválidas"));
        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new BadCredentialsException("Credenciais inválidas");
        }
        return generateTokenResponse(user);
    }

    private LoginResponse generateTokenResponse(User user) {
       var now = Instant.now();
       var expiresIn = 300L;

       String scopes = user.getRoles().stream()
               .map(Role::getName)
               .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("spring")
                .subject(user.getEmail())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("scopes", scopes)
                .build();

        String token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return new LoginResponse(token, expiresIn);
    }
}

