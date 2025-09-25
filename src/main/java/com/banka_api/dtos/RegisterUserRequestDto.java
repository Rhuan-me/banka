package com.banka_api.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data // Lombok para getters e setters
public class RegisterUserRequestDto {

    @NotBlank(message = "O primeiro nome é obrigatório")
    private String firstName;

    @NotBlank(message = "O sobrenome é obrigatório")
    private String lastName;

    @Email(message = "O email deve ser válido")
    @NotBlank(message = "O email é obrigatório")
    private String email;

    @NotBlank(message = "O país de origem é obrigatório")
    private String countryOfOrigin;

    // Note que não pedimos senha aqui, pois o onboarding pode ser via link mágico, biometria, etc.
    // Também não recebemos ID, status ou datas, pois isso é controlado pelo sistema.
}