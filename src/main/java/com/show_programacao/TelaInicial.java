package com.show_programacao;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;

public class TelaInicial extends Application {
    private final float largura = Main.getLargura(), altura = Main.getAltura();
    private String voz;
    private String[] temas;
    private Regras regras = new Regras();

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Show da Programação");
        Button btnComecar = new Button("Começar");
        Button btnRegras = new Button("Regras");

        btnComecar.setStyle("-fx-font-size: 10pt; -fx-padding: 10px 20px;");
        btnRegras.setStyle("-fx-font-size: 10pt; -fx-padding: 10px 20px;");

        btnRegras.setOnAction(e -> Platform.runLater(() -> regras.start(new Stage())));
        btnComecar.setOnAction(e -> {
            primaryStage.close();
            escolhervoz();
        });

        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(btnComecar, btnRegras);

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void escolhervoz() {
        Stage primaryStage = new Stage();
        ToggleGroup group = new ToggleGroup();
        RadioButton kermitButton = new RadioButton("Kermit  ");
        RadioButton faustaoButton = new RadioButton("Faustão");
        RadioButton lulaButton = new RadioButton("Lula      ");

        kermitButton.setToggleGroup(group);
        faustaoButton.setToggleGroup(group);
        lulaButton.setToggleGroup(group);

        VBox buttonBox = new VBox(5, kermitButton, faustaoButton, lulaButton);
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
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Escolha teu personagem");
        primaryStage.show();
    }

    private void perguntarTemas() {
        Stage stage = new Stage();
        String[] topicos = {"POO", "Banco de Dados", "Programação Básica",
                "Criação de Jogos", "Testes de Software", "Organização de Projetos"};

        List<HBox> checkboxContainers = new ArrayList<>();
        for (String topico : topicos) {
            CheckBox checkBox = new CheckBox();
            Label label = new Label(topico);
            label.setStyle("-fx-font-size: 14px;");
            label.setMinWidth(200);
            label.setMaxWidth(Double.MAX_VALUE);

            HBox container = new HBox(10, checkBox, label);
            container.setAlignment(Pos.CENTER);
            checkboxContainers.add(container);
        }

        Button confirmar = new Button("Confirmar");
        confirmar.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        confirmar.setOnAction(e -> {
            boolean peloMenosUmSelecionado = checkboxContainers.stream()
                    .map(hbox -> (CheckBox) hbox.getChildren().getFirst())
                    .anyMatch(CheckBox::isSelected);
            if (!peloMenosUmSelecionado) {
                new Alert(Alert.AlertType.WARNING, "Selecione pelo menos um tópico!", ButtonType.OK).showAndWait();
            } else {
                temas = checkboxContainers.stream()
                        .filter(hbox -> ((CheckBox) hbox.getChildren().getFirst()).isSelected())
                        .map(hbox -> ((Label) hbox.getChildren().get(1)).getText())
                        .toList().toArray(new String[0]);

                stage.close();
                gerarTelaStart();
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(checkboxContainers);
        layout.getChildren().add(confirmar);
        layout.setStyle("-fx-padding: 20;");
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 400, 300);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Escolha os Tópicos:");
        stage.show();
    }

    private void gerarTelaStart() {
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

        Button titulo = new Button("Show da Programação");
        titulo.setLayoutX(0f);
        titulo.setLayoutY(0f);
        titulo.setPrefSize(largura, altura * 0.4f);
        titulo.setFont(Font.font(largura / 12f));
        titulo.setStyle("-fx-background-color: #2E2C3E; -fx-text-fill: #FFC812; -fx-border-color: white; -fx-border-width: 2px;");

        Button comecarJogo = new Button("Começar Jogo");
        comecarJogo.setPrefSize(largura * 0.4f, altura * 0.1f);
        comecarJogo.setFont(Font.font(largura / 20f));
        comecarJogo.setLayoutX((largura - comecarJogo.getPrefWidth()) / 2f);
        comecarJogo.setLayoutY(altura * 0.6f);

        comecarJogo.setOnAction(event -> {
            stage.close();
            Main.ComecarJogo(temas);
        });

        root.getChildren().addAll(titulo, comecarJogo);
        stage.setTitle(" ");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        org.reproduzirAudio(audio);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
