package br.sistema.persistencia;

import br.sistema.conta.ItemCarrinho;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PersistenciaCarrinho {
    private static final String CAMINHO_ARQUIVO = "carrinho.json";

    public static void salvarCarrinho(ArrayList<ItemCarrinho> itens) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(CAMINHO_ARQUIVO), itens);
        } catch (IOException e) {
            System.out.println("Erro ao salvar carrinho: " + e.getMessage());
        }
    }

    public static ArrayList<ItemCarrinho> carregarCarrinho() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        File arquivo = new File(CAMINHO_ARQUIVO);
        try {
            if (!arquivo.exists()) return new ArrayList<>();
            return mapper.readValue(arquivo, new TypeReference<ArrayList<ItemCarrinho>>() {});
        } catch (IOException e) {
            System.out.println("Erro ao carregar carrinho: " + e.getMessage());
            return new ArrayList<>();
        }
    }

}
