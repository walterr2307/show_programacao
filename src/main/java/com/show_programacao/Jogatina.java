package com.show_programacao;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Jogatina extends Application {
    private float largura = Main.getLargura(), altura = Main.getAltura();
    private String[] perguntas, resp_corretas, audios;
    private String[][] respostas;


    public void start(Stage stage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, largura, altura);


        stage.setScene(scene);
        stage.show();
    }
}
