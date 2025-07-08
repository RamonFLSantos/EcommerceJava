package br.sistema.conta;

import br.sistema.persistencia.PersistenciaUsuario;
import java.util.List;

public class GerenciadorUsuarios {
    private List<Usuario> usuarios;

    public GerenciadorUsuarios() {
        usuarios = PersistenciaUsuario.carregarUsuarios();

        // Garante o usuário padrão
        Usuario padrao = new Usuario("user", "1234");
        if (!usuarios.contains(padrao)) {
            usuarios.add(padrao);
            PersistenciaUsuario.salvarUsuarios(usuarios);
        }
    }

    public boolean cadastrarUsuario(String login, String senha) {
        return cadastrarUsuario(new Usuario(login, senha));
    }

    public boolean cadastrarUsuario(Usuario usuario) {
        if (!usuarios.contains(usuario)) {
            usuarios.add(usuario);
            PersistenciaUsuario.salvarUsuarios(usuarios);
            return true;
        }
        return false;
    }

    public boolean realizarLogin(String login, String senha) {
        for (Usuario u : usuarios) {
            if (u.getLogin().equals(login) && u.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}
