package br.sistema.conta;

public class Usuario {
    private String login;
    private String senha;
    private String cpf;
    private String endereco;
    private String cep;
    private String email;

    public Usuario() {
    }

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario(String login, String senha, String cpf, String endereco, String cep, String email) {
        this.login = login;
        this.senha = senha;
        this.cpf = cpf;
        this.endereco = endereco;
        this.cep = cep;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCep() {
        return cep;
    }

    public String getEmail() {
        return email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Usuario u) {
            return this.login != null && this.login.equals(u.getLogin());
        }
        return false;
    }
}
