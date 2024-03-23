package br.fiap.projeto.identificacao.adapter.controller.rest.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginRequestDTO {
    private String matricula;
    private String senha;
}
