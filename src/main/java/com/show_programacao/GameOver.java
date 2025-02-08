package com.show_programacao;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.text.DecimalFormat;

public class GameOver extends Application {

    private boolean perdeu;
    private int premio;

    public GameOver(int premio, boolean perdeu) {
        String caminho;
        OrganizadorAudios org = OrganizadorAudios.instanciar(null);
        this.perdeu = perdeu;
        this.premio = premio;

        if (perdeu)
            caminho = org.getAudio("_perdeu");
        else
            caminho = org.getAudio("_ganhou");

        org.reproduzirAudio(caminho);
    }

    @Override
    public void start(Stage stage) {
        DecimalFormat formato = new DecimalFormat("#,###");
        String texto, num = formato.format(premio);

        if (perdeu)
            texto = "GAME OVER";
        else
            texto = "PARABÉNS!";

        // Mensagem de parabéns
        Text parabensText = new Text(texto);
        parabensText.setFont(Font.font("Arial", 50));
        parabensText.setFill(Color.GOLD);

        // Exibir o valor do prêmio
        Text premioText = new Text("Você ganhou: R$ " + num);
        premioText.setFont(Font.font("Arial", 30));
        premioText.setFill(Color.WHITE);

        // Botão para sair
        Button sairButton = new Button("Sair");
        sairButton.setFont(Font.font("Arial", 20));
        sairButton.setStyle("-fx-background-color: gold; -fx-text-fill: black;");
        sairButton.setOnAction(e -> stage.close());

        // Layout da tela
        VBox layout = new VBox(20, parabensText, premioText, sairButton);
        layout.setStyle("-fx-background-color: navy; -fx-padding: 50; -fx-alignment: center;");

        // Criar e exibir a cena
        Scene scene = new Scene(layout, 500, 300);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Game Over - Show do Milhão");
        stage.show();
    }
}

