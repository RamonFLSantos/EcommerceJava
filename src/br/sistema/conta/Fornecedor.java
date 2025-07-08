package br.sistema.conta;

public class Fornecedor {
    private String nome;
    private String cnpj;
    private String senha;
    private String endereco;
    private String cep;
    private String email;

    public Fornecedor() {
    }

    public Fornecedor(String nome, String cnpj) {
        this.nome = nome;
        this.cnpj = cnpj;
    }

    public Fornecedor(String nome, String cnpj, String senha, String endereco, String cep, String email) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.senha = senha;
        this.endereco = endereco;
        this.cep = cep;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getSenha() {
        return senha;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCep() {
        return cep;
    }

    public String getEmail() {
        return email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Fornecedor f) {
            return this.cnpj != null && this.cnpj.equals(f.cnpj);
        }
        return false;
    }
}
