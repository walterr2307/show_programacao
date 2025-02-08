package com.show_programacao;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BotoesAjuda2 {
    private final float largura = Main.getLargura(), altura = Main.getAltura();
    private String letra;
    private String[] resp_corretas, letras = new String[3];
    private String[][] respostas;
    private TelaPrincipal tela;

    public BotoesAjuda2(String[] resp_corretas, String[][] respostas, Button amigos, Button convidados, TelaPrincipal tela) {
        this.resp_corretas = resp_corretas;
        this.respostas = respostas;
        this.tela = tela;

        amigos.setOnAction(e -> eventoBotaoAmigos(amigos));
        convidados.setOnAction(e -> eventoBotaoConvidados(convidados));
    }

    private void definirLetras() {
        int indice = tela.getIndiceAtual() - 1, j = 0;
        String[] letras = {"A", "B", "C", "D"};

        for (int i = 0; i < 4; i++) {
            if (!resp_corretas[indice].equals(respostas[i][indice])) {
                this.letras[j] = letras[i];
                ++j;
            } else {
                letra = letras[i];
            }
        }
    }

    private void embaralharLetras() {
        for (int i = 2; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            String copia = letras[i];
            letras[i] = letras[j];
            letras[j] = copia;
        }
    }

    private void eventoBotaoAmigos(Button btn) {
        Platform.runLater(() -> {
            try {
                float largura = this.largura / 1.5f, altura = this.altura / 4f;
                Pane root = new Pane();
                Scene scene = new Scene(root, largura, altura);
                Stage stage = new Stage();
                Button[] botoes = {new Button(), new Button(), new Button()};

                btn.setDisable(true);
                btn.setOpacity(0.5f);
                definirLetras();
                embaralharLetras();

                for (int i = 0; i < 3; i++) {
                    int sorteio = (int) (Math.random() * 4);

                    botoes[i].setPrefSize(largura / 4f, largura / 4f);
                    botoes[i].setLayoutX(largura * (0.0625f + i * 0.3125f));
                    botoes[i].setLayoutY(altura * 0.04f);
                    root.getChildren().add(botoes[i]);

                    if (sorteio < 3)
                        botoes[i].setText(letra);
                    else
                        botoes[i].setText(letras[i]);
                }

                stage.setResizable(false);
                stage.setScene(scene);
                stage.setTitle("Opinião dos amigos:");
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void eventoBotaoConvidados(Button btn) {
        Platform.runLater(() -> {
            try {
                float largura = this.largura / 1.5f, altura = this.altura / 1.5f;
                Pane root = new Pane();
                Scene scene = new Scene(root, largura, altura);
                Stage stage = new Stage();
                Button[][] botoes = new Button[4][3];

                btn.setDisable(true);
                btn.setOpacity(0.5f);
                definirLetras();

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 3; j++) {
                        int sorteio = (int) (Math.random() * 4);

                        botoes[i][j] = new Button();
                        botoes[i][j].setPrefSize(largura / 5f, largura / 5f);
                        botoes[i][j].setLayoutX(largura * (0.04f + i * 0.24f));
                        botoes[i][j].setLayoutY(largura * (0.04f + j * 0.24f));
                        root.getChildren().add(botoes[i][j]);

                        if (sorteio < 3)
                            botoes[i][j].setText(letra);
                        else
                            botoes[i][j].setText(letras[(int) (Math.random() * 3)]);
                    }
                }
                stage.setResizable(false);
                stage.setScene(scene);
                stage.setTitle("Opinião dos convidados:");
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
