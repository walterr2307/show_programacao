package com.show_programacao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Map;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class OrganizadorAudios {
    private static OrganizadorAudios instancia;
    private String voz;

    private OrganizadorAudios(String voz) {
        this.voz = voz;
    }

    public static OrganizadorAudios instanciar(String voz) {
        if (instancia == null)
            instancia = new OrganizadorAudios(voz);

        return instancia;
    }

    private String[] carregarCaminhos(String tipo_arq) {
        String caminho = String.format("audios/%s/%s%s.json", voz, voz, tipo_arq);

        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(caminho)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("Recurso não encontrado: " + caminho);
            }

            InputStreamReader reader = new InputStreamReader(inputStream);
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, String>>() {
            }.getType();
            Map<String, String> map = gson.fromJson(reader, type);

            return map.values().toArray(new String[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public String[] getAudios() {
        int j;
        String copia;
        String[] audios = carregarCaminhos(null), aux_audios = new String[10];

        for (int i = audios.length - 1; i > 0; i--) {
            j = (int) (Math.random() * (i + 1));
            copia = audios[i];
            audios[i] = audios[j];
            audios[j] = copia;
        }

        System.arraycopy(audios, 0, aux_audios, 0, 10);

        return aux_audios;
    }

    public String getAudio(String tipo_arq) {
        int i;
        String audio;
        String[] audios = carregarCaminhos(tipo_arq);

        i = (int) (Math.random() * audios.length);
        audio = audios[i];

        return audio;
    }

    public void reproduzirAudio(String caminho) {
        String recurso = String.format("/audios/%s/%s", voz, caminho);
        InputStream is = getClass().getResourceAsStream(recurso);

        if (is == null) {
            System.err.println("Arquivo de áudio não encontrado: " + recurso);
            return;
        }

        try {
            AdvancedPlayer player = new AdvancedPlayer(is);
            Thread thread = new Thread(() -> {
                try {
                    player.play();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            });

            thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
