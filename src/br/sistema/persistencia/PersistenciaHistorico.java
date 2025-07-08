package br.sistema.persistencia;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.sistema.conta.Compra;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;

public class PersistenciaHistorico {
    private static final String CAMINHO_ARQUIVO = "historico.json";

    public static void salvarHistorico(List<Compra> compras) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(CAMINHO_ARQUIVO), compras);
        } catch (IOException e) {
            System.out.println("Erro ao salvar histórico: " + e.getMessage());
        }
    }

    public static List<Compra> carregarHistorico() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        File arquivo = new File(CAMINHO_ARQUIVO);
        if (!arquivo.exists()) return new ArrayList<>();

        try {
            return mapper.readValue(arquivo, new TypeReference<List<Compra>>() {});
        } catch (IOException e) {
            System.out.println("Erro ao carregar histórico: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}