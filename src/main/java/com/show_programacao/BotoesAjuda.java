package com.show_programacao;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;

public class BotoesAjuda {
    private float largura = Main.getLargura(), altura = Main.getAltura();
    private String[] resp_corretas;
    private String[][] respostas;
    private Pane root;
    private ImageView img_cartas = new ImageView(), img_amigos = new ImageView(), img_convidados = new ImageView();
    private ImageView[] imgs = {img_cartas, img_amigos, img_convidados};
    private Image img_cartas_azul, img_amigos_azul, img_convidados_azul, img_cartas_cinza, img_amigos_cinza, img_convidados_cinza;
    private Image[] imgs_azul = {img_cartas_azul, img_amigos_azul, img_convidados_azul},
            imgs_cinza = {img_cartas_cinza, img_amigos_cinza, img_convidados_cinza};
    private Button cartas = new Button(), amigos = new Button(), convidados = new Button();

    public BotoesAjuda(Pane root, String[] resp_corretas, String[][] respostas) {
        this.root = root;
        this.resp_corretas = resp_corretas;
        this.respostas = respostas;

        colocarImagens();
        colocarBotoes();
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
    }
}
