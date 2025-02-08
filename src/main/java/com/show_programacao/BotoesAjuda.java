package com.show_programacao;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;

public class BotoesAjuda {
    private final float largura = Main.getLargura(), altura = Main.getAltura();
    private String[] resp_corretas;
    private String[][] respostas;
    private TelaPrincipal tela;
    private Pane root;
    private ImageView img_cartas = new ImageView(), img_amigos = new ImageView(), img_convidados = new ImageView();
    private ImageView[] imgs = {img_cartas, img_amigos, img_convidados};
    private Image[] imgs_azul = new Image[3], imgs_cinza = new Image[3];
    private Button cartas = new Button(), amigos = new Button(), convidados = new Button();

    public BotoesAjuda(Pane root, String[] resp_corretas, String[][] respostas, TelaPrincipal tela) {
        this.root = root;
        this.resp_corretas = resp_corretas;
        this.respostas = respostas;
        this.tela = tela;

        colocarImagens();
        colocarBotoes();
        new BotoesAjuda2(resp_corretas, respostas, amigos, convidados, tela);
    }

    private void colocarImagens() {
        for (int i = 0; i < 3; i++) {
            String caminho1 = String.format("/imagens/botao%d.jpg", i);
            String caminho2 = String.format("/imagens/botao%d_click.jpg", i);

            URL url1 = getClass().getResource(caminho1);
            URL url2 = getClass().getResource(caminho2);

            if (url1 == null || url2 == null) {
                System.out.println("Erro ao carregar imagem: " + caminho1 + " ou " + caminho2);
            } else {
                imgs_azul[i] = new Image(url1.toExternalForm());
                imgs_cinza[i] = new Image(url2.toExternalForm());
                imgs[i].setImage(imgs_azul[i]);
                imgs[i].setLayoutX(largura * (0.67f + i * 0.095f));
                imgs[i].setLayoutY(altura * 0.45f);
                imgs[i].setFitWidth(largura / 10f);
                imgs[i].setFitHeight(largura / 10f);
                root.getChildren().add(imgs[i]);
            }
        }
    }

    private void colocarBotoes() {
        Button[] botoes = {cartas, amigos, convidados};

        for (int i = 0; i < 3; i++) {
            final int index = i;

            botoes[i].setPrefSize(largura / 10f, largura / 10f);
            botoes[i].setLayoutX(largura * (0.67f + i * 0.095f));
            botoes[i].setLayoutY(altura * 0.45f);
            botoes[i].setOpacity(0f);
            root.getChildren().add(botoes[i]);

            botoes[i].setOnMouseEntered(event -> imgs[index].setImage(imgs_cinza[index]));
            botoes[i].setOnMouseExited(event -> imgs[index].setImage(imgs_azul[index]));
        }

        botoes[0].setOnAction(event -> eventoCartas());
    }

    private void eventoCartas() {
        Platform.runLater(() -> {
            try {
                int copia_indice;
                int[] indices = {0, 1, 2, 3};
                float largura = this.largura / 1.5f, altura = this.largura / 1.5f;
                Pane root = new Pane();
                Scene scene = new Scene(root, largura, altura * 0.26f);
                Stage stage = new Stage();
                Button[] botoes = {new Button("*"), new Button("*"), new Button("*"), new Button("*")};

                cartas.setDisable(true);
                cartas.setOpacity(0.5f);

                for (int i = 3; i > 0; i--) {
                    int j = (int) (Math.random() * (i + 1));
                    copia_indice = indices[i];
                    indices[i] = indices[j];
                    indices[j] = copia_indice;
                }

                for (int i = 0; i < 4; i++) {
                    final int index = i;
                    botoes[i].setPrefSize(largura * 0.2f, largura * 0.2f);
                    botoes[i].setLayoutY(altura * 0.03f);
                    botoes[i].setFont(Font.font(largura / 60f));
                    botoes[i].setWrapText(true);
                    botoes[indices[i]].setLayoutX(largura * (0.04f + i * 0.24f));
                    botoes[i].setOnAction(event -> eventoBotaoCarta(index, botoes, stage));
                    root.getChildren().add(botoes[i]);
                }

                Jogatina.getStage().getScene().getRoot().setDisable(true);

                stage.setScene(scene);
                stage.setTitle("Escolha uma carta: ");
                stage.show();
                stage.setResizable(false);
                stage.setOnCloseRequest(Event::consume);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void eventoBotaoCarta(int index, Button[] botoes, Stage stage) {
        int indice_atual = tela.getIndiceAtual(), j = 0;
        Button copia;
        Button[] caixas_resposta = tela.getCaixasRespostas(), caixas_excluidas = new Button[3];

        for (int i = 0; i < 4; i++) {
            botoes[i].setDisable(true);
            if (!respostas[i][indice_atual - 1].equals(resp_corretas[indice_atual - 1])) {
                caixas_excluidas[j] = caixas_resposta[i];
                ++j;
            }
        }

        for (int i = 2; i > 0; i--) {
            j = (int) (Math.random() * (i + 1));
            copia = caixas_excluidas[i];
            caixas_excluidas[i] = caixas_excluidas[j];
            caixas_excluidas[j] = copia;
        }

        switch (index) {
            case 0:
                botoes[index].setText("Não eliminou nenhuma resposta");
                break;
            case 1:
                botoes[index].setText("Eliminou uma resposta");
                caixas_excluidas[0].setDisable(true);
                caixas_excluidas[0].setVisible(false);
                break;
            case 2:
                botoes[index].setText("Eliminou duas respostas");
                for (int i = 0; i < 2; i++) {
                    caixas_excluidas[i].setDisable(true);
                    caixas_excluidas[i].setVisible(false);
                }
                break;
            default:
                botoes[index].setText("Eliminou três respostas");
                for (int i = 0; i < 3; i++) {
                    caixas_excluidas[i].setDisable(true);
                    caixas_excluidas[i].setVisible(false);
                }
        }

        PauseTransition delay1 = new PauseTransition(Duration.millis(1500));
        delay1.setOnFinished(e -> {
            Jogatina.getStage().getScene().getRoot().setDisable(false);
            stage.close();
        });

        delay1.play();
    }
}
