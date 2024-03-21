package br.fiap.projeto.identificacao.adapter.controller.rest.response;

import br.fiap.projeto.identificacao.entity.Colaborador;
import br.fiap.projeto.identificacao.entity.vo.Email;
import lombok.*;

import java.util.Optional;

@Builder
@Data
@NoArgsConstructor @AllArgsConstructor
public class ColaboradorResponseDTO {

    private String codigo;
    private String nome;
    private String matricula;
    private String email;

    public static ColaboradorResponseDTO fromColaborador(Colaborador colaborador) {

        if (colaborador == null) {
            return null;
        }
        return new ColaboradorResponseDTO(
                colaborador.getCodigo(),
                colaborador.getNome(),
                colaborador.getMatricula(),
                Optional.ofNullable(colaborador.getEmail()).map(Email::getEndereco).orElse(null)
        );
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getEmail() {
        return email;
    }
}
