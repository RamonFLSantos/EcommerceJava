package br.sistema.conta;

import br.sistema.persistencia.PersistenciaFornecedor;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorFornecedores {
    private List<Fornecedor> fornecedores;

    public GerenciadorFornecedores() {
        this.fornecedores = PersistenciaFornecedor.carregarFornecedores();
    }

    public boolean cadastrarFornecedor(String nome, String cnpj, String senha, String endereco, String cep, String email) {
        Fornecedor novo = new Fornecedor(nome, cnpj, senha, endereco, cep, email);
        if (!fornecedores.contains(novo)) {
            fornecedores.add(novo);
            PersistenciaFornecedor.salvarFornecedores(fornecedores);
            return true;
        }
        return false;
    }

    public boolean realizarLogin(String cnpj, String senha) {
        for (Fornecedor f : fornecedores) {
            if (f.getCnpj().equals(cnpj) && f.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }

    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void adicionarFornecedor(Fornecedor fornecedor) {
        fornecedores.add(fornecedor);
        PersistenciaFornecedor.salvarFornecedores(fornecedores);
    }

    public void removerFornecedor(Fornecedor fornecedor) {
        fornecedores.remove(fornecedor);
        PersistenciaFornecedor.salvarFornecedores(fornecedores);
    }
}
