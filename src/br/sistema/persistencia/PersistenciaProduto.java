package br.sistema.persistencia;

import br.sistema.conta.Produto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PersistenciaProduto {
    private static final String CAMINHO_ARQUIVO = "produtos.json";

    public static void salvarProdutos(ArrayList<Produto> listaProdutos) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(CAMINHO_ARQUIVO), listaProdutos);
        } catch (IOException e) {
            System.out.println("Erro ao salvar produtos: " + e.getMessage());
        }
    }

    public static ArrayList<Produto> carregarProdutos() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        File arquivo = new File(CAMINHO_ARQUIVO);
        try {
            if (!arquivo.exists()) return new ArrayList<>();
            ArrayList<Produto> lista = mapper.readValue(arquivo, new TypeReference<>() {});
            Produto.atualizarContador(lista);
            return lista;
        } catch (IOException e) {
            System.out.println("Erro ao carregar produtos: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
