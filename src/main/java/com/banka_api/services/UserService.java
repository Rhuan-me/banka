package com.banka_api.services;

import com.banka_api.dtos.RegisterUserRequestDto;
import com.banka_api.dtos.UserResponseDto;
import com.banka_api.models.User;
import com.banka_api.models.UserStatus; // Supondo que o enum está em models
import com.banka_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Futuramente, você injetaria aqui outros serviços, como o de onboarding externo
    // @Autowired private OnboardingProviderService onboardingProvider;

    public UserResponseDto registerNewUser(RegisterUserRequestDto requestDto) {
        // Lógica para verificar se o email já existe (será adicionada no repository)
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new IllegalStateException("Email já cadastrado."); // Vamos melhorar isso com exceptions customizadas
        }

        User newUser = new User();
        newUser.setFirstName(requestDto.getFirstName());
        newUser.setLastName(requestDto.getLastName());
        newUser.setEmail(requestDto.getEmail());
        newUser.setCountryOfOrigin(requestDto.getCountryOfOrigin());
        // O status inicial é definido pelo @PrePersist no model

        User savedUser = userRepository.save(newUser);

        // Futuramente, aqui você chamaria:
        // onboardingProvider.startVerification(savedUser.getId(), documentImage, faceImage);

        // Converte o User (model) para UserResponseDto para retornar à API
        return convertToDto(savedUser);
    }

    private UserResponseDto convertToDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setEmail(user.getEmail());
        dto.setStatus(user.getStatus().name());
        return dto;
    }
}