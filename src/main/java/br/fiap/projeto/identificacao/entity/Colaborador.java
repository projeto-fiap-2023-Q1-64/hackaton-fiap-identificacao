package br.fiap.projeto.identificacao.entity;

import br.fiap.projeto.identificacao.entity.vo.Email;
import br.fiap.projeto.identificacao.usecase.exception.EntradaInvalidaException;

import java.time.LocalDateTime;

public class Colaborador {

    public static final String MATRICULA_AUSENTE = "Informe a matricula!";
    public static final String SENHA_AUSENTE = "Informe uma senha!";
    public static final String EMAIL_AUSENTE = "Informe o e-mail!";
    public static final String NOME_AUSENTE = "Informe o nome!";
    public static final String ENTIDADE_NAO_ENCONTRADA = "Colaborador não encontrado!";
    public static final String MATRICULA_DUPLICADA = "Esta matricula já está em uso!";
    public static final String EMAIL_DUPLICADO = "Esse e-mail já está cadastrado!";
    public static final String CODIGO_AUSENTE = "Informe o código do colaborador!";
    public static final String USUARIO_JA_EXCLUIDO = "Este colaborador já está excluido! Não foi possível atualiza-lo.";

    private String codigo;
    private String nome;
    private Email email;
    private String matricula;
    private String senha;
    private LocalDateTime dataExclusao;

    public Colaborador(String codigo, String nome, String matricula, String email, String senha) throws EntradaInvalidaException {
        this.codigo = codigo;
        this.nome = nome;
        this.matricula = matricula;
        this.senha = senha;
        validaCodigo();
        validaEmail(email);
        validaNome();
        validaMatricula();
        validaSenha();
        this.email = Email.fromString(email);
    }

    public Colaborador(String codigo, String nome, String matricula, String email, String senha, LocalDateTime dataExclusao) throws EntradaInvalidaException {
        this.codigo = codigo;
        this.nome = nome;
        this.matricula = matricula;
        this.senha = senha;
        validaCodigo();
        validaEmail(email);
        validaNome();
        validaMatricula();
        validaSenha();
        this.email = Email.fromString(email);
        this.dataExclusao = dataExclusao;
    }

    public Colaborador(String nome, String matricula, String email, String senha) throws EntradaInvalidaException {
        this.nome = nome;
        this.matricula = matricula;
        this.senha = senha;
        validaEmail(email);
        validaNome();
        validaMatricula();
        validaSenha();
        this.email = Email.fromString(email);
    }

    public void adicionaDataDeExclusao() throws EntradaInvalidaException {
        if (this.dataExclusao != null) {
            throw new EntradaInvalidaException(USUARIO_JA_EXCLUIDO);
        }
        this.dataExclusao = LocalDateTime.now();
    }

    public Colaborador anonimizarDadosParaExclusao() throws EntradaInvalidaException {
        nome = "anônimo";
        email = Email.fromString("anonimo@anonimo.anon");
        return this;
    }

    private void validaCodigo() throws EntradaInvalidaException {
        if (codigo == null) {
            throw new EntradaInvalidaException(CODIGO_AUSENTE);
        }
    }

    private void validaEmail(String email) throws EntradaInvalidaException {
        if (email == null) {
            throw new EntradaInvalidaException(EMAIL_AUSENTE);
        }
        Email.fromString(email).validar();
    }

    private void validaNome() throws EntradaInvalidaException {
        if (nome == null || nome.length() == 0) {
            throw new EntradaInvalidaException(NOME_AUSENTE);
        }
    }

    private void validaMatricula() throws EntradaInvalidaException {
        if (matricula == null || matricula.length() == 0) {
            throw new EntradaInvalidaException(MATRICULA_AUSENTE);
        }
    }

    private void validaSenha() throws EntradaInvalidaException {
        if (senha == null || senha.length() == 0) {
            throw new EntradaInvalidaException(SENHA_AUSENTE);
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public Email getEmail() {
        return email;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDateTime getDataExclusao() {
        return dataExclusao;
    }
}
