package br.sistema.conta;

public class ItemCarrinho {
    private ItemFornecedorProduto item;
    private int quantidade;

    public ItemCarrinho(ItemFornecedorProduto item, int quantidade) {
        this.item = item;
        this.quantidade = quantidade;
    }

    public ItemFornecedorProduto getItem() {
        return item;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
