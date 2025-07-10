package br.sistema.conta;

import br.sistema.persistencia.PersistenciaItemProduto;
import br.sistema.persistencia.PersistenciaProduto;

import java.util.ArrayList;
import java.util.List;

public class Loja {
    private List<Fornecedor> fornecedores = new ArrayList<>();
    private List<Produto> produtos;
    private List<ItemFornecedorProduto> estoque;

    public Loja() {
        this.produtos = PersistenciaProduto.carregarProdutos();
        this.estoque = PersistenciaItemProduto.carregarEstoque();
    }

    public void adicionarFornecedor(Fornecedor f) {
        if (!fornecedores.contains(f)) {
            fornecedores.add(f);
        }
    }

    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void adicionarProduto(Produto p) {
        if (!produtos.contains(p)) {
            produtos.add(p);
            PersistenciaProduto.salvarProdutos(new ArrayList<>(produtos)); 
        }
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void adicionarItem(ItemFornecedorProduto item) {
        estoque.add(item);
        salvarEstoque();
    }

    public List<ItemFornecedorProduto> listarItens() {
        return estoque;
    }

    public List<ItemFornecedorProduto> buscarPorNome(String nome) {
        List<ItemFornecedorProduto> encontrados = new ArrayList<>();
        for (ItemFornecedorProduto item : estoque) {
        	if (item.getProduto().getNome().toLowerCase().contains(nome.toLowerCase())) {
        	    encontrados.add(item);
        	}
        }
        return encontrados;
    }

    public List<ItemFornecedorProduto> buscarPorFornecedor(Fornecedor f) {
        List<ItemFornecedorProduto> encontrados = new ArrayList<>();
        for (ItemFornecedorProduto item : estoque) {
            if (item.getFornecedor().equals(f)) {
                encontrados.add(item);
            }
        }
        return encontrados;
    }

    public ItemFornecedorProduto buscarPorCodigo(int codigo) {
        for (ItemFornecedorProduto item : estoque) {
            if (item.getProduto().getCodigo() == codigo) {
                return item;
            }
        }
        return null;
    }

    public boolean removerItem(int codigo) {
        ItemFornecedorProduto item = buscarPorCodigo(codigo);
        if (item != null) {
            estoque.remove(item);
            salvarEstoque();
            return true;
        }
        return false;
    }

    private void salvarEstoque() {
        PersistenciaItemProduto.salvarEstoque(new ArrayList<>(estoque));
    }
}
