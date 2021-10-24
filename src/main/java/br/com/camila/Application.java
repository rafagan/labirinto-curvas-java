package br.com.camila;

import br.com.camila.example.bezier.Bezier3PointsGame;
import br.com.camila.example.bezier.Bezier4PointsGame;
import br.com.camila.labyrinth.LabyrinthGame;
import br.com.camila.window.Window;


public class Application {
    public static void main(String[] args) {
        Window.getInstance().run(new LabyrinthGame());
    }
}

