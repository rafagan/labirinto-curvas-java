package br.com.camila;

import br.com.camila.example.line.PrimitiveGame;
import br.com.camila.glfw.Window;


public class Application {
    private static final Window WINDOW = Window.getInstance();

    public static void main(String[] args) {
        WINDOW.run(new PrimitiveGame());
    }
}

