package com.show_programacao;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class LeitorJson {
    private int num_perguntas;
    private String[] perguntas, resp_corretas, temas;
    private String[][] respostas;

    public LeitorJson(String[] temas) {
        String json_str = getJsonStr();

        this.temas = temas;
        this.num_perguntas = definirNumeroPerguntas(json_str);

        perguntas = new String[num_perguntas];
        resp_corretas = new String[num_perguntas];
        respostas = new String[4][num_perguntas];

        gerarPerguntas(json_str);
        embaralharPerguntas();
    }

    private String getJsonStr() {
        String json_str = null;
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("perguntas.json")) {
            if (inputStream != null) {
                json_str = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            } else {
                System.err.println("Recurso não encontrado: perguntas.json");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json_str;
    }

    private int definirNumeroPerguntas(String json_str) {
        int num_perguntas = 0;
        Gson gson = new Gson();
        Type list_type = new TypeToken<List<Map<String, String>>>() {
        }.getType();
        List<Map<String, String>> lista_mapas = gson.fromJson(json_str, list_type);

        // Itera sobre o mapa de perguntas e conta quantas são do tema desejado
        for (Map<String, String> mapa : lista_mapas) {
            if (getTemaEncontrado(mapa.get("tema")))
                ++num_perguntas; // Incrementa o contador para cada pergunta encontrada
        }

        return num_perguntas; // Retorna o número total de perguntas encontradas
    }

    private boolean getTemaEncontrado(String tema) {
        for (int i = 0; i < temas.length; ++i) {
            if (temas[i].equals(tema))
                return true;
        }

        return false;
    }

    private void gerarPerguntas(String json_str) {
        int i = 0;
        Gson gson = new Gson();
        Type list_type = new TypeToken<List<Map<String, String>>>() {
        }.getType();
        List<Map<String, String>> lista_mapas = gson.fromJson(json_str, list_type);

        // Itera sobre a lista de mapas para preencher os arrays de perguntas e
        // respostas
        for (Map<String, String> mapa : lista_mapas) {
            if (getTemaEncontrado(mapa.get("tema"))) { // Verifica se o tema da pergunta é relevante
                perguntas[i] = mapa.get("pergunta"); // Armazena a pergunta
                respostas[0][i] = mapa.get("0"); // Armazena a primeira resposta
                respostas[1][i] = mapa.get("1"); // Armazena a segunda resposta
                respostas[2][i] = mapa.get("2"); // Armazena a terceira resposta
                respostas[3][i] = mapa.get("3"); // Armazena a quarta resposta
                resp_corretas[i] = respostas[0][i]; // Define a primeira resposta como a correta por padrão
                ++i;
            }
        }
    }

    private void embaralharPerguntas() {
        int i, j;

        for (i = num_perguntas - 1; i > 0; i--) {
            j = (int) (Math.random() * (i + 1)); // Gera um índice aleatório para troca

            embaralharRespostas(respostas, i); // Embaralha as respostas para a pergunta atual

            trocarValores(perguntas, i, j); // Troca a posição das perguntas
            trocarValores(resp_corretas, i, j); // Troca a posição das respostas corretas
            trocarValores(respostas[0], i, j); // Troca as respostas nas quatro opções
            trocarValores(respostas[1], i, j);
            trocarValores(respostas[2], i, j);
            trocarValores(respostas[3], i, j);
        }
    }

    private void trocarValores(String vetor[], int i, int j) {
        String copia;

        copia = vetor[i]; // Armazena temporariamente o valor do índice i
        vetor[i] = vetor[j]; // Substitui o valor do índice i pelo valor do índice j
        vetor[j] = copia; // Atribui ao índice j o valor armazenado temporariamente
    }

    private void embaralharRespostas(String[][] respostas, int indice) {
        int i, j;
        String copia;

        for (i = 0; i < 4; i++) {
            j = (int) (Math.random() * (i + 1)); // Gera um índice aleatório para troca
            copia = respostas[i][indice]; // Armazena temporariamente a resposta
            respostas[i][indice] = respostas[j][indice]; // Troca as respostas
            respostas[j][indice] = copia; // Substitui pela resposta armazenada
        }
    }

    public String[] getPerguntas() {
        String[] perguntas = new String[10];

        System.arraycopy(this.perguntas, 0, perguntas, 0, 10);

        return perguntas;
    }

    public String[] getRespostasCorretas() {
        String[] resp_corretas = new String[10];

        System.arraycopy(this.resp_corretas, 0, resp_corretas, 0, 10);

        return resp_corretas;
    }

    public String[][] getRespostas() {
        String[][] respostas = new String[4][10];

        for (int i = 0; i < 4; i++) {
            System.arraycopy(this.respostas[i], 0, respostas[i], 0, 10);
        }
        return respostas;
    }
}