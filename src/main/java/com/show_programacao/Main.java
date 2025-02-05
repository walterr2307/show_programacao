package com.show_programacao;

import javafx.stage.Stage;
import javafx.application.Platform;

public class Main {
    private static float largura = 700f, altura = largura * 0.75f;

    public static float getLargura() {
        return largura;
    }

    public static float getAltura() {
        return altura;
    }

    public static void ComecarJogo(String[] temas) {
        LeitorJson leitor = new LeitorJson(temas);
        OrganizadorAudios org = OrganizadorAudios.instanciar(null);
        String[] audios = org.getAudios(), perguntas = leitor.getPerguntas(), resp_corretas = leitor.getRespostasCorretas();
        String[][] respostas = leitor.getRespostas();

        Platform.runLater(() -> {
            try {
                Jogatina jogatina = new Jogatina(audios, perguntas, resp_corretas, respostas);
                Stage stage = new Stage();
                jogatina.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void finalizarJogo() {

    }

    public static void main(String[] args) {
        TelaInicial.main(args);
    }
}