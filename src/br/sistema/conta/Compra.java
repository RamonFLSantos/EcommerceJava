package br.sistema.conta;

import java.time.LocalDate;
import java.util.List;

public class Compra {
    private static int contador = 1;
    private int codigo;
    private LocalDate data;
    private String status;
    private List<ItemCarrinho> itens;

    public Compra() {
    }

    public Compra(List<ItemCarrinho> itens) {
        this.codigo = contador++;
        this.data = LocalDate.now();
        this.status = "Aguardando Envio";
        this.itens = itens;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
        if (codigo >= contador) {
            contador = codigo + 1;
        }
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ItemCarrinho> getItens() {
        return itens;
    }

    public void setItens(List<ItemCarrinho> itens) {
        this.itens = itens;
    }

    public void cancelar() {
        this.status = "Cancelada";
    }

    public void marcarComoEntregue() {
        this.status = "Entregue";
    }

    public boolean podeCancelar() {
        return status.equals("Aguardando Envio");
    }
    
    public static void atualizarContador(List<Compra> compras) {
        for (Compra c : compras) {
            if (c.getCodigo() >= contador) {
                contador = c.getCodigo() + 1;
            }
        }
    }

}
