package com.show_programacao;

public class Main {
    private static float largura = 700f, altura = largura * 0.75f;

    public static float getLargura() {
        return largura;
    }

    public static float getAltura() {
        return altura;
    }

    public static void ComecarJogo(String voz, String[] temas) {
        System.out.println(voz);
    }

    public static void main(String[] args) {
        TelaInicial.main(args);
    }
}