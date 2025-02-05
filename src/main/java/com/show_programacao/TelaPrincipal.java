package com.show_programacao;

import javafx.animation.PauseTransition;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.text.DecimalFormat;

public class TelaPrincipal {
    private int dinheiro = Jogatina.getDinheiro();
    private int indice_atual = 0;
    private float largura = Main.getLargura(), altura = Main.getAltura();
    private String[] audios, perguntas, resp_corretas;
    private String[][] respostas;
    private Pane root;
    private Button caixa_pergunta = new Button(), perder = new Button("PERDER\n(R$ 250)");
    private Button parar = new Button("PARAR\n(R$ 500)"), ganhar = new Button("GANHAR\n(R$ 1000)");
    private Button[] caixas = {perder, parar, ganhar};
    private Button[] caixas_respostas = {new Button(), new Button(), new Button(), new Button()};
    private OrganizadorAudios org = OrganizadorAudios.instanciar(null);

    public TelaPrincipal(String[] audios, String[] perguntas, String[] resp_corretas, String[][] respostas, Pane root) {
        this.audios = audios;
        this.perguntas = perguntas;
        this.resp_corretas = resp_corretas;
        this.respostas = respostas;
        this.root = root;

        for (int i = 0; i < 4; i++)
            ajustarCaixaResposta(i);

        ajustarCaixaPergunta();
        adicionarBlocoDinheiro();
    }

    private void ajustarCaixaPergunta() {
        caixa_pergunta.setPrefSize(largura * 0.6f, altura * 0.3f);
        caixa_pergunta.setLayoutX(largura / 30f);
        caixa_pergunta.setLayoutY(largura / 30f);
        caixa_pergunta.setFont(Font.font(largura / 60f));
        caixa_pergunta.setWrapText(true);
        caixa_pergunta.setStyle("-fx-background-color: #2E2C3E; -fx-text-fill: white; -fx-border-color: white; -fx-border-width: 2px;");
        caixa_pergunta.setText(perguntas[indice_atual]);

        root.getChildren().add(caixa_pergunta);
        org.reproduzirAudio(audios[indice_atual]);
        Jogatina.setRespostaCorreta(resp_corretas[indice_atual]);
        ++indice_atual;
    }

    private void ajustarCaixaResposta(int i) {
        caixas_respostas[i].setPrefSize(largura * 0.6f, altura * 0.12f);
        caixas_respostas[i].setLayoutX(largura / 30f);
        caixas_respostas[i].setLayoutY(largura * (0.3f + i * 0.11f));
        caixas_respostas[i].setFont(Font.font(largura / 60f));
        caixas_respostas[i].setWrapText(true);
        caixas_respostas[i].setStyle("-fx-background-color: #2E2C3E; -fx-text-fill: white; -fx-border-color: white; -fx-border-width: 2px;");
        caixas_respostas[i].setOnAction(event -> reescreverCaixas(i));
        caixas_respostas[i].setText(respostas[i][indice_atual]);
        root.getChildren().add(caixas_respostas[i]);
    }

    public void reescreverCaixas(int i) {
        if (Jogatina.respostaCorreta(respostas[i][indice_atual - 1])) {
            caixas_respostas[i].setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-border-color: white; -fx-border-width: 2px;");
        } else {
            caixas_respostas[i].setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-border-color: white; -fx-border-width: 2px;");
        }

        if (indice_atual >= 10) {
            PauseTransition delay1 = new PauseTransition(Duration.millis(500));
            delay1.setOnFinished(e -> {
                Jogatina.getStage().close();
                dinheiro *= 2;
            });

            delay1.play();
            return;
        }

        org.reproduzirAudio(audios[indice_atual]);

        PauseTransition delay2 = new PauseTransition(Duration.millis(500));
        delay2.setOnFinished(e -> {
            for (int j = 0; j < 4; j++) {
                caixas_respostas[j].setStyle("-fx-background-color: #2E2C3E; -fx-text-fill: white; -fx-border-color: white; -fx-border-width: 2px;");
                caixas_respostas[j].setText(respostas[j][indice_atual]);
            }

            caixa_pergunta.setText(perguntas[indice_atual]);
            atualizarValorDinheiro();
            Jogatina.setRespostaCorreta(resp_corretas[indice_atual]);
            ++indice_atual;
        });
        delay2.play();
    }

    private void adicionarBlocoDinheiro() {
        Rectangle ret = new Rectangle();

        for (int i = 0; i < 3; i++) {
            caixas[i].setPrefSize(largura / 9.5f, altura / 7.5f);
            caixas[i].setLayoutX(largura * (0.675f + i * 0.1f));
            caixas[i].setLayoutY(altura * 0.75f);
            caixas[i].setFont(Font.font(largura / 75f));
            caixas[i].setTextAlignment(TextAlignment.CENTER);
            caixas[i].setStyle("-fx-background-color: #FFC81C; -fx-text-fill: black;");
            root.getChildren().add(caixas[i]);
        }

        ret.setWidth(largura * (2.9f / 9.5f));
        ret.setHeight(altura / 7.4f);
        ret.setLayoutX(largura * 0.675f);
        ret.setLayoutY(altura * 0.75f);
        ret.setFill(Color.TRANSPARENT);
        ret.setStroke(Color.web("#2E2C3E"));
        ret.setStrokeWidth(5);
        root.getChildren().add(ret);
    }

    private void atualizarValorDinheiro() {
        DecimalFormat formato = new DecimalFormat("#,###");
        String num;

        Jogatina.aumentarValorDinheiro();
        dinheiro = Jogatina.getDinheiro();

        if (dinheiro == 500000) {
            perder.setText("PERDER\n(R$ 0)");
        } else {
            num = formato.format(dinheiro / 2f);
            perder.setText(String.format("PERDER\n(R$ %s)", num));
        }

        num = formato.format(dinheiro);
        parar.setText(String.format("PARAR\n(R$ %s)", num));
        num = formato.format(dinheiro * 2L);
        ganhar.setText(String.format("GANHAR\n(R$ %s)", num));
    }
}
