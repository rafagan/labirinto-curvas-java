package br.com.camila;

import br.com.camila.example.line.PrimitiveGame;
import br.com.camila.glfw.Window;


public class Application {
    public static void main(String[] args) {
        Window.getInstance().run(new PrimitiveGame());
    }
}

