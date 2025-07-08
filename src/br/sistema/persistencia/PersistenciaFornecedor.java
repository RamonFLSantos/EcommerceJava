package br.sistema.persistencia;

import br.sistema.conta.Fornecedor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaFornecedor {
    private static final String CAMINHO_ARQUIVO = "fornecedores.json";

    public static void salvarFornecedores(List<Fornecedor> lista) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(CAMINHO_ARQUIVO), lista);
        } catch (IOException e) {
            System.out.println("Erro ao salvar fornecedores: " + e.getMessage());
        }
    }

    public static List<Fornecedor> carregarFornecedores() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        File arquivo = new File(CAMINHO_ARQUIVO);
        if (!arquivo.exists()) return new ArrayList<>();

        try {
            return mapper.readValue(arquivo, new TypeReference<List<Fornecedor>>() {});
        } catch (IOException e) {
            System.out.println("Erro ao carregar fornecedores: " + e.getMessage());
            return new ArrayList<>();
        }
    }

}
