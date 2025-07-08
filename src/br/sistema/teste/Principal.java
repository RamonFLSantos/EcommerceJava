package br.sistema.teste;

import br.sistema.conta.*;
import br.sistema.exception.CepInvalidoException;
import br.sistema.exception.CnpjInvalidoException;
import br.sistema.exception.CpfInvalidoException;
import br.sistema.exception.EntradaInvalidaException;
import br.sistema.menu.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Loja loja = inicializarLojaComProdutos();
        HistoricoCompras historico = new HistoricoCompras();
        GerenciadorUsuarios gerenciadorUsuarios = new GerenciadorUsuarios();
        GerenciadorFornecedores gerenciadorFornecedores = new GerenciadorFornecedores();

        if (historico.listarCompras().isEmpty()) {
            Compra compraEntregue1 = new Compra(new ArrayList<>());
            compraEntregue1.marcarComoEntregue();
            historico.registrarCompra(compraEntregue1);

            Compra compraEntregue2 = new Compra(new ArrayList<>());
            compraEntregue2.marcarComoEntregue();
            historico.registrarCompra(compraEntregue2);
        }


        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Login como Admin");
            System.out.println("2 - Login como Usuário");
            System.out.println("3 - Cadastrar Novo Usuário");
            System.out.println("4 - Cadastrar Fornecedor");
            System.out.println("5 - Login como Fornecedor");
            System.out.println("0 - Sair do sistema");
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
                    if (realizarLoginUsuario(scanner, gerenciadorUsuarios)) {
                        MenuUsuario menuUsuario = new MenuUsuario(loja, historico);
                        menuUsuario.exibirMenu();
                    } else {
                        System.out.println("Usuário ou senha incorretos.\n");
                    }
                }
                case "3" -> {
                	try {
                	    System.out.print("Novo login: ");
                	    String novoLogin = scanner.nextLine();

                	    System.out.print("Nova senha: ");
                	    String novaSenha = scanner.nextLine();

                	    System.out.print("CPF (somente números): ");
                	    String cpf = scanner.nextLine();
                	    if (!cpf.matches("\\d{11}")) {
                	        throw new CpfInvalidoException("CPF deve conter exatamente 11 dígitos numéricos.");
                	    }

                	    System.out.print("Endereço: ");
                	    String endereco = scanner.nextLine();

                	    System.out.print("CEP (somente números): ");
                	    String cep = scanner.nextLine();
                	    if (!cep.matches("\\d{8}")) {
                	        throw new CepInvalidoException("CEP deve conter exatamente 8 dígitos numéricos.");
                	    }

                	    System.out.print("Email: ");
                	    String email = scanner.nextLine();

                	    Usuario novo = new Usuario(novoLogin, novaSenha, cpf, endereco, cep, email);
                	    boolean cadastrado = gerenciadorUsuarios.cadastrarUsuario(novo);

                	    if (cadastrado) {
                	        System.out.println("Usuário cadastrado com sucesso!");
                	    } else {
                	        System.out.println("Usuário já existe.");
                	    }

                	} catch (CpfInvalidoException | CepInvalidoException e) {
                	    System.out.println("Erro ao cadastrar: " + e.getMessage());
                	}

                }
                case "4" -> {
                    try {
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();

                        System.out.print("CNPJ (somente números): ");
                        String cnpj = scanner.nextLine();
                        if (!cnpj.matches("\\d{14}")) {
                            throw new CnpjInvalidoException("CNPJ deve conter exatamente 14 dígitos numéricos.");
                        }

                        System.out.print("Senha: ");
                        String senha = scanner.nextLine();

                        System.out.print("Endereço: ");
                        String endereco = scanner.nextLine();

                        System.out.print("CEP (somente números): ");
                        String cep = scanner.nextLine();
                        if (!cep.matches("\\d{8}")) {
                            throw new CepInvalidoException("CEP deve conter exatamente 8 dígitos numéricos.");
                        }

                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        if (!email.contains("@") || !email.contains(".")) {
                            throw new EntradaInvalidaException("Email inválido.");
                        }

                        boolean sucesso = gerenciadorFornecedores.cadastrarFornecedor(nome, cnpj, senha, endereco, cep, email);
                        if (sucesso) {
                            Fornecedor novo = new Fornecedor(nome, cnpj);
                            loja.adicionarFornecedor(novo);
                            System.out.println("Fornecedor cadastrado com sucesso!");
                        } else {
                            System.out.println("Fornecedor já existe.");
                        }

                    } catch (CnpjInvalidoException | CepInvalidoException | EntradaInvalidaException e) {
                        System.out.println("Erro ao cadastrar fornecedor: " + e.getMessage());
                    }
                }

                case "5" -> {
                    System.out.print("CNPJ: ");
                    String cnpj = scanner.nextLine();
                    System.out.print("Senha: ");
                    String senha = scanner.nextLine();

                    if (gerenciadorFornecedores.realizarLogin(cnpj, senha)) {
                        System.out.println("Login do fornecedor realizado com sucesso!");
                    } else {
                        System.out.println("CNPJ ou senha incorretos.");
                    }
                }
                case "0" -> {
                    System.out.println("Encerrando o sistema...");
                    return;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private static boolean realizarLoginUsuario(Scanner sc, GerenciadorUsuarios gUsuarios) {
        System.out.print("Usuário: ");
        String user = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();
        return gUsuarios.realizarLogin(user, senha);
    }

    private static Loja inicializarLojaComProdutos() {
        Loja loja = new Loja();
        Fornecedor fornecedor = new Fornecedor("Fornecedor Padrão", "12345678000199");
        loja.adicionarFornecedor(fornecedor);

        if (loja.getProdutos().isEmpty()) {
            Produto p1 = new Produto("Mouse Gamer");
            loja.adicionarProduto(p1);
            loja.adicionarItem(new ItemFornecedorProduto(p1, fornecedor, 120.00, 10));

            Produto p2 = new Produto("Teclado Mecânico");
            loja.adicionarProduto(p2);
            loja.adicionarItem(new ItemFornecedorProduto(p2, fornecedor, 250.00, 5));
        }

        return loja;
    }
}
