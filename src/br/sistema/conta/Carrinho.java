package br.sistema.conta;

import br.sistema.persistencia.PersistenciaCarrinho;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private List<ItemCarrinho> itens;

    public Carrinho() {
        this.itens = PersistenciaCarrinho.carregarCarrinho();
    }

    public void adicionarProduto(ItemFornecedorProduto item, int quantidadeDesejada) {
        if (quantidadeDesejada <= item.getQuantidade()) {
            itens.add(new ItemCarrinho(item, quantidadeDesejada));
            salvar();
            System.out.println("Produto adicionado ao carrinho!");
        } else {
            System.out.println("Quantidade indisponível em estoque.");
        }
    }

    public void exibirCarrinho() {
        if (itens.isEmpty()) {
            System.out.println("Carrinho vazio.");
            return;
        }

        System.out.println("--- Seu Carrinho ---");
        double total = 0;
        for (ItemCarrinho ic : itens) {
            double subtotal = ic.getQuantidade() * ic.getItem().getPreco();
            total += subtotal;
            System.out.println("Produto: " + ic.getItem().getProduto().getNome() +
                    " | Preço Unitário: R$" + ic.getItem().getPreco() +
                    " | Quantidade: " + ic.getQuantidade() +
                    " | Subtotal: R$" + subtotal);
        }

        double icms = total * 0.17;
        double totalComIcms = total + icms;

        System.out.println("------------------------------");
        System.out.println("Total: R$" + total);
        System.out.println("ICMS (17%): R$" + icms);
        System.out.println("Total com ICMS: R$" + totalComIcms);
    }

    public void finalizarCompra(HistoricoCompras historico, Loja loja) {
        if (itens.isEmpty()) {
            System.out.println("Carrinho vazio. Nada para comprar.");
            return;
        }

        for (ItemCarrinho ic : itens) {
            ItemFornecedorProduto estoqueItem = loja.buscarPorCodigo(ic.getItem().getProduto().getCodigo());
            if (estoqueItem != null) {
                int novaQuantidade = estoqueItem.getQuantidade() - ic.getQuantidade();
                estoqueItem.setQuantidade(novaQuantidade);
            }
        }

        Compra compra = new Compra(new ArrayList<>(itens));
        historico.registrarCompra(compra);
        itens.clear();
        salvar();
        System.out.println("Compra finalizada com sucesso! Total: R$" + calcularTotal());
    }

    public void removerItem(int codigoProduto) {
        for (ItemCarrinho ic : new ArrayList<>(itens)) {
            if (ic.getItem().getProduto().getCodigo() == codigoProduto) {
                itens.remove(ic);
                salvar();
                System.out.println("Item removido do carrinho.");
                return;
            }
        }
        System.out.println("Produto não encontrado no carrinho.");
    }

    public void aumentarQuantidade(int codigoProduto, int quantidade, Loja loja) {
        for (int i = 0; i < itens.size(); i++) {
            ItemCarrinho ic = itens.get(i);
            if (ic.getItem().getProduto().getCodigo() == codigoProduto) {
                int estoqueDisponivel = loja.buscarPorCodigo(codigoProduto).getQuantidade();
                int novaQuantidade = ic.getQuantidade() + quantidade;

                if (novaQuantidade > estoqueDisponivel) {
                    System.out.println("Quantidade excede o estoque disponível.");
                } else {
                    itens.set(i, new ItemCarrinho(ic.getItem(), novaQuantidade));
                    salvar();
                    System.out.println("Quantidade atualizada.");
                }
                return;
            }
        }
        System.out.println("Produto não está no carrinho.");
    }

    public boolean isEmpty() {
        return itens.isEmpty();
    }

    public double calcularTotal() {
        double total = 0;
        for (ItemCarrinho ic : itens) {
            total += ic.getQuantidade() * ic.getItem().getPreco();
        }
        return total;
    }

    private void salvar() {
        PersistenciaCarrinho.salvarCarrinho(new ArrayList<>(itens));
    }
}
