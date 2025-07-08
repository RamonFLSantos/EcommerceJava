package br.sistema.conta;

import br.sistema.persistencia.PersistenciaProduto;
import java.util.ArrayList;

public class GerenciadorDeProdutos {
    private ArrayList<Produto> listaProdutos;

    public GerenciadorDeProdutos() {
        this.listaProdutos = PersistenciaProduto.carregarProdutos();
    }

    public void adicionarProduto(Produto produto) {
        listaProdutos.add(produto);
        PersistenciaProduto.salvarProdutos(listaProdutos);
    }

    public void removerProduto(Produto produto) {
        listaProdutos.remove(produto);
        PersistenciaProduto.salvarProdutos(listaProdutos);
    }

    public ArrayList<Produto> getListaProdutos() {
        return listaProdutos;
    }
}
