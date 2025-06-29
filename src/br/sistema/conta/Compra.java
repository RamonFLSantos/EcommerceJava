package br.sistema.conta;

import java.time.LocalDate;

public class Compra {
    private static int contador = 1;
    private int codigo;
    private LocalDate data;

    public Compra() {
        this.codigo = contador++;
        this.data = LocalDate.now();
    }

    public int getCodigo() {
        return codigo;
    }

    public LocalDate getData() {
        return data;
    }
}
