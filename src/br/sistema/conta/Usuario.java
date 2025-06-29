package br.sistema.conta;

public class Usuario {
    private String login;
    private String senha;

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Usuario u) {
            return this.login.equals(u.getLogin());
        }
        return false;
    }
}
