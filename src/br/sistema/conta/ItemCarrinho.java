package br.sistema.conta;

public class ItemCarrinho {
    private ItemFornecedorProduto item;
    private int quantidade;

    public ItemCarrinho() {
    }

    public ItemCarrinho(ItemFornecedorProduto item, int quantidade) {
        this.item = item;
        this.quantidade = quantidade;
    }

    public ItemFornecedorProduto getItem() {
        return item;
    }

    public void setItem(ItemFornecedorProduto item) {
        this.item = item;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
