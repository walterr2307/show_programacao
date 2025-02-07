package com.show_programacao;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Jogatina extends Application {
    private static int dinheiro = 500;
    private static String resp_correta;
    private static Stage stage;
    private float largura = Main.getLargura(), altura = Main.getAltura();
    private String[] perguntas, resp_corretas, audios;
    private String[][] respostas;

    public Jogatina(String[] audios, String[] perguntas, String[] resp_corretas, String[][] respostas) {
        this.audios = audios;
        this.perguntas = perguntas;
        this.resp_corretas = resp_corretas;
        this.respostas = respostas;
    }

    public static int getDinheiro() {
        return dinheiro;
    }

    public static void aumentarValorDinheiro() {
        if(dinheiro == 1000 || dinheiro == 10000 || dinheiro == 100000)
            dinheiro = (int) (dinheiro * 2.5f);
        else
            dinheiro *= 2;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setRespostaCorreta(String resp_correta) {
        Jogatina.resp_correta = resp_correta;
    }

    public void start(Stage stage) {
        Jogatina.stage = stage;
        Pane root = new Pane();
        Scene scene = new Scene(root, largura, altura);
        new TelaPrincipal(audios, perguntas, resp_corretas, respostas, root);
        new BotoesAjuda(root, resp_corretas, respostas);

        root.setBackground(new Background(new BackgroundFill(
                Color.web("#880209"), null, null
        )));

        stage.setScene(scene);
        stage.show();
    }

    private boolean verificarSeAcabou() {
        return false;
    }

    public static boolean respostaCorreta(String resposta) {
        if (resposta.equals(resp_correta))
            return true;
        return false;
    }
}
