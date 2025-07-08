package br.sistema.conta;

import java.util.List;

public class Produto {
    private static int contador = 1;
    private int codigo;
    private String nome;

    public Produto() {
        
    }

    public Produto(String nome) {
        this.codigo = contador++;
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
        if (codigo >= contador) {
            contador = codigo + 1;
        }
    }

    public static void atualizarContador(List<Produto> produtos) {
        for (Produto p : produtos) {
            if (p.getCodigo() >= contador) {
                contador = p.getCodigo() + 1;
            }
        }
    }

    public Object getFornecedor() {
        return null;
    }
}
