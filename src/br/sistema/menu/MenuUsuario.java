package br.sistema.menu;

import br.sistema.conta.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuUsuario {
    private Loja loja;
    private HistoricoCompras historico;
    private Scanner scanner;
    private Carrinho carrinho;

    public MenuUsuario(Loja loja, HistoricoCompras historico) {
        this.loja = loja;
        this.historico = historico;
        this.scanner = new Scanner(System.in);
        this.carrinho = new Carrinho();
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n--- Menu do Usuário ---");
            System.out.println("1. Ver todos os produtos");
            System.out.println("2. Buscar produto por nome");
            System.out.println("3. Buscar produto por código");
            System.out.println("4. Buscar produto por fornecedor");
            System.out.println("5. Adicionar produto ao carrinho");
            System.out.println("6. Ver carrinho");
            System.out.println("7. Finalizar compra");
            System.out.println("8. Ver histórico de compras");
            System.out.println("9. Buscar compra por código");
            System.out.println("10. Buscar compra por data");
            System.out.println("11. Cancelar Compra");
            System.out.println("12. Remover item do carrinho");
            System.out.println("13. Aumentar quantidade de item no carrinho");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, insira um número válido.");
                scanner.next();
            }

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> listarProdutos();
                case 2 -> buscarPorNome();
                case 3 -> buscarPorCodigo();
                case 4 -> buscarPorFornecedor();
                case 5 -> adicionarAoCarrinho();
                case 6 -> carrinho.exibirCarrinho();
                case 7 -> carrinho.finalizarCompra(historico, loja);
                case 8 -> verHistorico();
                case 9 -> buscarCompraPorCodigo();
                case 10 -> buscarCompraPorData();
                case 11 -> cancelarCompra();
                case 12 -> removerItemCarrinho();
                case 13 -> aumentarQuantidadeCarrinho();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void listarProdutos() {
        for (ItemFornecedorProduto item : loja.listarItens()) {
        	String status = item.getQuantidade() == 0 ? "Indisponível" : item.getQuantidade() + " unidades";
        	System.out.println(item.getProduto().getCodigo() + " - " + item.getProduto().getNome() +
        	    " | Preço: R$" + item.getPreco() +
        	    " | Quantidade: " + status +
        	    " | Fornecedor: " + item.getFornecedor().getNome());
        }
    }

    private void buscarPorNome() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        List<ItemFornecedorProduto> encontrados = loja.buscarPorNome(nome);
        for (ItemFornecedorProduto item : encontrados) {
            System.out.println(item.getProduto().getCodigo() + " - " + item.getProduto().getNome() +
                " | Preço: R$" + item.getPreco() +
                " | Quantidade: " + item.getQuantidade() +
                " | Fornecedor: " + item.getFornecedor().getNome());
        }
        if (encontrados.isEmpty()) {
            System.out.println("Nenhum produto encontrado.");
        }
    }

    private void buscarPorCodigo() {
        try {
            System.out.print("Código: ");
            int codigo = Integer.parseInt(scanner.nextLine());

            ItemFornecedorProduto item = loja.buscarPorCodigo(codigo);
            if (item != null) {
                System.out.println(item.getProduto().getNome() + " | R$" + item.getPreco() +
                        " | Qtd: " + item.getQuantidade() +
                        " | Fornecedor: " + item.getFornecedor().getNome());
            } else {
                System.out.println("Produto não encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Código inválido. Digite apenas números.");
        }
    }


    private void buscarPorFornecedor() {
        System.out.print("Nome do fornecedor: ");
        String nome = scanner.nextLine();
        List<Fornecedor> lista = loja.getFornecedores();
        for (Fornecedor f : lista) {
            if (f.getNome().equalsIgnoreCase(nome)) {
                List<ItemFornecedorProduto> itens = loja.buscarPorFornecedor(f);
                for (ItemFornecedorProduto item : itens) {
                    System.out.println(item.getProduto().getCodigo() + " - " + item.getProduto().getNome() +
                        " | Preço: R$" + item.getPreco() +
                        " | Quantidade: " + item.getQuantidade());
                }
                return;
            }
        }
        System.out.println("Fornecedor não encontrado.");
    }

    private void adicionarAoCarrinho() {
        System.out.print("Código do produto: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();

        ItemFornecedorProduto item = loja.buscarPorCodigo(codigo);
        if (item == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Quantidade desejada: ");
        int qtd = scanner.nextInt();
        scanner.nextLine();

        if (qtd <= 0) {
            System.out.println("Quantidade inválida.");
            return;
        }

        if (qtd > item.getQuantidade()) {
            System.out.println("Quantidade indisponível em estoque.");
            return;
        }

        carrinho.adicionarProduto(item, qtd);
    }

    private void verHistorico() {
        List<Compra> lista = historico.listarCompras();
        if (lista.isEmpty()) {
            System.out.println("Nenhuma compra registrada.");
        } else {
        	for (Compra c : lista) {
        	    System.out.println("Compra #" + c.getCodigo() + " | Data: " + c.getData() + " | Status: " + c.getStatus());
        	}
        }
    }

    private void buscarCompraPorCodigo() {
        System.out.print("Código da compra: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();
        Compra c = historico.buscarPorCodigo(codigo);
        if (c != null) {
        	System.out.println("Compra #" + c.getCodigo() + " | Data: " + c.getData() + " | Status: " + c.getStatus());
        } else {
            System.out.println("Compra não encontrada.");
        }
    }

    private void buscarCompraPorData() {
        System.out.print("Data da compra (AAAA-MM-DD): ");
        String data = scanner.nextLine();
        List<Compra> lista = historico.buscarPorData(data);
        if (lista.isEmpty()) {
            System.out.println("Nenhuma compra nesta data.");
        } else {
            for (Compra c : lista) {
            	System.out.println("Compra #" + c.getCodigo() + " | Data: " + c.getData() + " | Status: " + c.getStatus());
            }
        }
    }
    
    private void cancelarCompra() {
        List<Compra> compras = historico.listarCompras();
        List<Compra> pendentes = new ArrayList<>();
        for (Compra c : compras) {
            if (c.podeCancelar()) {
                pendentes.add(c);
            }
        }

        if (pendentes.isEmpty()) {
            System.out.println("Nenhuma compra disponível para cancelamento.");
            return;
        }

        System.out.println("Compras pendentes:");
        for (Compra c : pendentes) {
            System.out.println("Código: " + c.getCodigo() + " | Data: " + c.getData());
        }

        System.out.print("Digite o código da compra a cancelar: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();

        Compra compra = historico.buscarPorCodigo(codigo);
        if (compra != null && compra.podeCancelar()) {
            for (ItemCarrinho ic : compra.getItens()) {
                ItemFornecedorProduto itemEstoque = loja.buscarPorCodigo(ic.getItem().getProduto().getCodigo());
                if (itemEstoque != null) {
                    itemEstoque.setQuantidade(itemEstoque.getQuantidade() + ic.getQuantidade());
                }
            }

            compra.cancelar();
            System.out.println("Compra cancelada com sucesso. Estoque restaurado.");
        } else {
            System.out.println("Compra não encontrada ou não pode mais ser cancelada.");
        }
    }

    private void removerItemCarrinho() {
        System.out.print("Código do produto a remover: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();
        carrinho.removerItem(codigo);
    }

    private void aumentarQuantidadeCarrinho() {
        System.out.print("Código do produto: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Quantidade a adicionar: ");
        int qtd = scanner.nextInt();
        scanner.nextLine();
        carrinho.aumentarQuantidade(codigo, qtd, loja);
    }

}
