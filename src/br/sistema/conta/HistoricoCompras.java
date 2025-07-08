package br.sistema.conta;

import br.sistema.persistencia.PersistenciaCompra;

import java.util.ArrayList;
import java.util.List;

public class HistoricoCompras {
    private List<Compra> compras;

    public HistoricoCompras() {
        this.compras = PersistenciaCompra.carregarCompras();
    }

    public void registrarCompra(Compra compra) {
        compras.add(compra);
        salvar();
    }

    public List<Compra> listarCompras() {
        return compras;
    }

    public Compra buscarPorCodigo(int codigo) {
        for (Compra c : compras) {
            if (c.getCodigo() == codigo) return c;
        }
        return null;
    }

    public List<Compra> buscarPorData(String data) {
        List<Compra> resultado = new ArrayList<>();
        for (Compra c : compras) {
            if (c.getData().toString().equals(data)) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    private void salvar() {
        PersistenciaCompra.salvarCompras(new ArrayList<>(compras));
    }
}
