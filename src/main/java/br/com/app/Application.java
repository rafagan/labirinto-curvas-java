package br.com.app;

import br.com.app.labyrinth.LabyrinthGame;
import br.com.app.window.Window;


public class Application {
    public static void main(String[] args) {
        Window.getInstance().run(new LabyrinthGame());
    }
}