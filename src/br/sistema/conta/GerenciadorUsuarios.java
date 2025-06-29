package br.sistema.conta;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorUsuarios {
    private List<Usuario> usuarios = new ArrayList<>();

    public GerenciadorUsuarios() {
        // Conta padrão para testes
        usuarios.add(new Usuario("user", "1234"));
    }

    public boolean cadastrarUsuario(String login, String senha) {
        Usuario novo = new Usuario(login, senha);
        if (!usuarios.contains(novo)) {
            usuarios.add(novo);
            return true;
        }
        return false; // Já existe
    }

    public boolean realizarLogin(String login, String senha) {
        for (Usuario u : usuarios) {
            if (u.getLogin().equals(login) && u.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }
}
