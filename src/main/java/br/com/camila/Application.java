package br.com.camila;

import br.com.camila.example.curve.LineGame;
import br.com.camila.example.curve.SineGame;
import br.com.camila.window.Window;


public class Application {
    public static void main(String[] args) {
        Window.getInstance().run(new LineGame());
    }
}

