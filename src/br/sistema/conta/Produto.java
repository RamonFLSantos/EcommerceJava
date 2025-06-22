package br.sistema.conta;

public class Produto {
    private static int contador = 1;
    private int codigo;
    private String nome;

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

	public Object getFornecedor() {
		return null;
	}
}
