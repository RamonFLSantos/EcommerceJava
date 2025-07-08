package br.sistema.persistencia;

import br.sistema.conta.Usuario;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaUsuario {
    private static final String CAMINHO_ARQUIVO = "usuarios.json";

    public static void salvarUsuarios(List<Usuario> lista) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(CAMINHO_ARQUIVO), lista);
        } catch (IOException e) {
            System.out.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }

    public static List<Usuario> carregarUsuarios() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        File arquivo = new File(CAMINHO_ARQUIVO);
        if (!arquivo.exists()) return new ArrayList<>();

        try {
            return mapper.readValue(arquivo, new TypeReference<List<Usuario>>() {});
        } catch (IOException e) {
            System.out.println("Erro ao carregar usuários: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
