package br.fiap.projeto.identificacao.entity;

public class LoginColaborador {
    private String matricula;
    private String senha;

    public LoginColaborador(String matricula, String senha) {
        this.matricula = matricula;
        this.senha = senha;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getSenha() {
        return senha;
    }
}
