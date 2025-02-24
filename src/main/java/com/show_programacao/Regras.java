package com.show_programacao;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Regras extends Application {

    public void start(Stage primaryStage) {
        String regrasTexto = "\u0002Regras do Show da Programação\u0002\n\n"
                + "1. \u0002Objetivo do Jogo\u0002\n"
                + "   Responder corretamente às 10 perguntas aleatórias sobre computação para alcançar o prêmio de 1 milhão de reais fictícios.\n\n"
                + "2. \u0002Estrutura do Jogo\u0002\n"
                + "   - Cada pergunta oferece 4 opções de resposta (A, B, C ou D), com apenas uma correta.\n"
                + "   - A cada resposta correta, o participante acumula um prêmio fictício maior, até chegar ao valor máximo de 1 milhão de reais.\n\n"
                + "3. \u0002Botões de Ajuda\u0002\n"
                + "   - O jogador pode usar 3 ajudas diferentes durante o jogo, cada uma apenas uma vez:\n"
                + "     🎴 Cartas: Escolha uma carta virtual que pode eliminar uma, duas, três ou nenhuma alternativa errada.\n"
                + "     👥 Amigos (3): Três amigos virtuais opinam sobre a resposta correta.\n"
                + "     🎤 Convidados (12): Um painel fictício de 12 convidados vota na alternativa que consideram correta.\n\n"
                + "4. \u0002Toque Humorístico\u0002\n"
                + "   - Para deixar o jogo mais divertido, você pode escolher entre as vozes de Kermit, Faustão ou Lula, dubladas por Walter Cavalcante, Gustavo Lameu e Thiago Guilherme, estudantes de Ciência da Computação na UECE e desenvolvedores do jogo.\n\n"
                + "5. \u0002Premiação\u0002\n"
                + "   - Se errar uma pergunta, o jogador leva metade do prêmio acumulado até o momento, exceto na última pergunta, onde perderá tudo em caso de erro.\n\n"
                + "6. \u0002Finalização do Jogo\u0002\n"
                + "   - O jogo termina quando o participante responde corretamente às 10 perguntas ou erra uma delas.\n"
                + "   - O objetivo é conquistar o prêmio máximo de 1 milhão de reais fictícios.";

        TextArea textArea = new TextArea(regrasTexto);
        textArea.setWrapText(true);
        textArea.setEditable(false);
        textArea.setStyle("-fx-font-size: 14px; -fx-font-family: 'Arial'; -fx-background-color: #f4f4f4; -fx-text-fill: #333;");

        StackPane root = new StackPane(textArea);
        root.setPadding(new Insets(20));
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(root, 600, 500);

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }
}
