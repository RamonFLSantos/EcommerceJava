package br.sistema.persistencia;

import br.sistema.conta.ItemFornecedorProduto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PersistenciaItemProduto {
    private static final String CAMINHO_ARQUIVO = "estoque.json";

    public static void salvarEstoque(ArrayList<ItemFornecedorProduto> estoque) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(CAMINHO_ARQUIVO), estoque);
        } catch (IOException e) {
            System.out.println("Erro ao salvar estoque: " + e.getMessage());
        }
    }

    public static ArrayList<ItemFornecedorProduto> carregarEstoque() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        File arquivo = new File(CAMINHO_ARQUIVO);
        try {
            if (!arquivo.exists()) return new ArrayList<>();
            return mapper.readValue(arquivo, new TypeReference<ArrayList<ItemFornecedorProduto>>() {});
        } catch (IOException e) {
            System.out.println("Erro ao carregar estoque: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
