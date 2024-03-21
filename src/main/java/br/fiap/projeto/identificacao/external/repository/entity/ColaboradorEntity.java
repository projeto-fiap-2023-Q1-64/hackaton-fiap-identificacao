package br.fiap.projeto.identificacao.external.repository.entity;

import br.fiap.projeto.identificacao.entity.Colaborador;
import br.fiap.projeto.identificacao.entity.vo.Email;
import br.fiap.projeto.identificacao.usecase.exception.EntradaInvalidaException;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDateTime;


@Entity @Getter
@Table(name = "colaboradores", uniqueConstraints =
        @UniqueConstraint(name = "UN_MATR_COLABORADOR", columnNames = {"matricula"}))
public class ColaboradorEntity {

    @Id
    private String codigo;
    private String nome;
    private String matricula;
    private String email;
    private String senha;
    private LocalDateTime dataExclusao;

    public ColaboradorEntity() {
    }

    public ColaboradorEntity(String codigo, String nome, String matricula, String email, String senha, LocalDateTime dataExclusao) {
        this.codigo = codigo;
        this.nome = nome;
        this.matricula = matricula;
        this.email = email;
        this.senha = senha;
        this.dataExclusao = dataExclusao;
    }

    public ColaboradorEntity(String codigo, String nome, String matricula, Email email, String senha, LocalDateTime dataExclusao) {
        this(codigo, nome, matricula, email.getEndereco(), senha, dataExclusao);
    }

    public static ColaboradorEntity fromColaborador(Colaborador colaborador) {
        return new ColaboradorEntity(colaborador.getCodigo(), colaborador.getNome(), colaborador.getMatricula(), colaborador.getEmail(), colaborador.getSenha(), colaborador.getDataExclusao());
    }

    public Colaborador toColaborador() {
        try {
            return new Colaborador(codigo, nome, matricula, email, senha, dataExclusao);
        } catch (EntradaInvalidaException e) {
            throw new RuntimeException(e);
        }
    }
}
