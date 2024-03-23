package br.fiap.projeto.identificacao.adapter.controller.rest.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponseDTO {
    private String userId;
    private String nome;
    private String email;
    private String matricula;
}
