package com.show_programacao;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.CheckBox;

public class TelaInicial extends Application {
    private final float largura = Main.getLargura(), altura = Main.getAltura();
    private String voz;
    private String[] temas;

    public void start(Stage primaryStage) {
        ToggleGroup group = new ToggleGroup();
        RadioButton kermitButton = new RadioButton("Kermit  ");
        RadioButton faustaoButton = new RadioButton("Faustão");

        kermitButton.setToggleGroup(group);
        faustaoButton.setToggleGroup(group);

        // Criando um VBox separado para alinhar os botões ao centro
        VBox buttonBox = new VBox(5, kermitButton, faustaoButton);
        buttonBox.setAlignment(Pos.CENTER);

        Button escolherButton = new Button("Escolher");
        escolherButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        escolherButton.setOnAction(e -> {
            RadioButton selected = (RadioButton) group.getSelectedToggle();
            if (selected != null) {
                voz = selected.getText().trim();
                primaryStage.close();
                perguntarTemas();
            }
        });

        VBox vbox = new VBox(10, buttonBox, escolherButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPrefSize(300, 200);

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Escolha teu personagem");
        primaryStage.show();
    }

    private void perguntarTemas() {
        Stage temaStage = new Stage();

        // Criando CheckBoxes para cada tema
        CheckBox testesSoftware = new CheckBox("Testes de Software              ");
        CheckBox criacaoJogos = new CheckBox("Criação de Jogos                 ");
        CheckBox gerenciamentoProjetos = new CheckBox("Gerenciamento de Projetos");

        // Lista para armazenar os temas escolhidos
        List<String> temasEscolhidos = new ArrayList<>();

        // Botão para confirmar a escolha
        Button confirmarButton = new Button("Confirmar");
        confirmarButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");

        confirmarButton.setOnAction(e -> {
            temasEscolhidos.clear(); // Limpa antes de adicionar os temas escolhidos

            if (testesSoftware.isSelected()) temasEscolhidos.add("Testes de Software");
            if (criacaoJogos.isSelected()) temasEscolhidos.add("Criação de Jogos");
            if (gerenciamentoProjetos.isSelected()) temasEscolhidos.add("Gerenciamento de Projetos");

            // Verifica se pelo menos um tema foi escolhido
            if (!temasEscolhidos.isEmpty()) {
                String[] temasArray = temasEscolhidos.toArray(new String[0]);

                for (int i = 0; i < temasArray.length; i++)
                    temasArray[i] = temasArray[i].trim();

                temas = temasArray;
                temaStage.close(); // Fecha a janela
                gerarTelaStart();
            }
        });

        // Criando um VBox para alinhar os componentes
        VBox vbox = new VBox(10, testesSoftware, criacaoJogos, gerenciamentoProjetos, confirmarButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPrefSize(300, 200);

        Scene scene = new Scene(vbox);
        temaStage.setScene(scene);
        temaStage.setTitle("Escolha um tema");
        temaStage.show();
    }

    private void gerarTelaStart() {
        ajustarStrings();

        OrganizadorAudios org = OrganizadorAudios.instanciar(voz);
        String audio = org.getAudio("_intro");
        Pane root = new Pane();
        Scene scene = new Scene(root, largura, altura);
        Stage stage = new Stage();

        root.setBackground(new Background(new BackgroundFill(
                Color.web("#880209"), null, null
        )));

        root.setBorder(new Border(new BorderStroke(
                Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2)
        )));

        // Botão título
        Button titulo = new Button("Show da Programação");
        titulo.setLayoutX(0f);
        titulo.setLayoutY(0f);
        titulo.setPrefSize(largura, altura * 0.4f);
        titulo.setFont(Font.font(largura / 12f));
        titulo.setStyle("-fx-background-color: #2E2C3E; -fx-text-fill: #FFC812; -fx-border-color: white; -fx-border-width: 2px;");

        // Botão "Começar Jogo"
        Button comecarJogo = new Button("Começar Jogo");
        comecarJogo.setPrefSize(largura * 0.4f, altura * 0.1f);
        comecarJogo.setFont(Font.font(largura / 20f));
        comecarJogo.setLayoutX((largura - comecarJogo.getPrefWidth()) / 2f);
        comecarJogo.setLayoutY(altura * 0.6f);

        comecarJogo.setOnAction(event -> {
            stage.close();
            Main.ComecarJogo(temas);
        });

        // Adicionando os botões à tela
        root.getChildren().addAll(titulo, comecarJogo);

        stage.setTitle(" ");
        stage.setScene(scene);
        stage.show();
        org.reproduzirAudio(audio);
    }

    private void ajustarStrings() {
        switch (voz) {
            case "Kermit":
                voz = "kermit";
                break;
            default:
                voz = "faustao";
        }

        for (int i = 0; i < temas.length; i++) {
            switch (temas[i]) {
                case "Testes de Software":
                    temas[i] = "teste_software";
                    break;
                case "Criação de Jogos":
                    temas[i] = "criacao_jogos";
                    break;
                default:
                    temas[i] = "gerenciamento_projetos";
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
