package br.com.camila;

import br.com.camila.example.curve.CurveGame;
import br.com.camila.window.Window;


public class Application {
    public static void main(String[] args) {
        Window.getInstance().run(new CurveGame());
    }
}

