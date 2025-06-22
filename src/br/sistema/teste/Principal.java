package br.sistema.teste;

import br.sistema.conta.*;
import br.sistema.menu.Menu;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Loja loja = inicializarLojaComProdutos();

        System.out.println("Escolha o tipo de login:");
        System.out.println("1 - Admin");
        System.out.println("2 - Usuário");
        System.out.print("Opção: ");
        String tipo = scanner.nextLine();

        switch (tipo) {
            case "1" -> {
                while (!Login.realizarLogin()) {
                    System.out.println("Usuário ou senha incorretos. Tente novamente.\n");
                }
                Menu menu = new Menu(loja);
                menu.exibirMenu();
            }
            case "2" -> {
                System.out.println("Área do usuário ainda em desenvolvimento.");
            }
            default -> System.out.println("Opção inválida.");
        }
    }

    private static Loja inicializarLojaComProdutos() {
        Loja loja = new Loja();

        Fornecedor fornecedor = new Fornecedor("Tecnologia LTDA", "12345678000199");
        loja.adicionarFornecedor(fornecedor);

        Produto p1 = new Produto("Mouse Gamer");
        loja.adicionarProduto(p1);
        loja.adicionarItem(new ItemFornecedorProduto(p1, fornecedor, 120.00, 10));

        Produto p2 = new Produto("Teclado Mecânico");
        loja.adicionarProduto(p2);
        loja.adicionarItem(new ItemFornecedorProduto(p2, fornecedor, 250.00, 5));

        return loja;
    }
}


