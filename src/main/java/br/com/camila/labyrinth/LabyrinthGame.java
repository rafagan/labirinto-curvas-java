package br.com.camila.labyrinth;

import br.com.camila.game.Global;
import br.com.camila.game.IGame;
import br.com.camila.game.World;
import br.com.camila.labyrinth.io.LabyrinthReader;
import br.com.camila.labyrinth.model.Labyrinth;
import br.com.camila.labyrinth.model.LabyrinthCurve;
import br.com.camila.labyrinth.model.LabyrinthPoint;
import br.com.camila.labyrinth.model.Vehicle;
import br.com.camila.primitive.*;
import org.joml.Vector2f;

import java.io.FileNotFoundException;
import java.util.*;

public class LabyrinthGame implements IGame {
    List<IBezier> curves = new ArrayList<>();
    List<Vehicle> enemies = new ArrayList<>();
    Vehicle player;

    @Override
    public void init() {
        try {
            Labyrinth level = LabyrinthReader.read("src/main/resources/levels/level1.json");
            for(LabyrinthCurve curve: level.getCurves()) {
                LabyrinthPoint p1 = level.getPoints().get(curve.getP1());
                LabyrinthPoint p2 = level.getPoints().get(curve.getP2());
                LabyrinthPoint p3 = level.getPoints().get(curve.getP3());
                LabyrinthPoint p4;

                curve.setStartPoint(p1);
                p1.getCurves().add(curve);

                IBezier primitive;
                if(curve.hasFourPoints()) {
                    p4 = level.getPoints().get(curve.getP4());
                    curve.setEndPoint(p4);
                    p4.getCurves().add(curve);
                    primitive = new Bezier4P(p1.getVector(), p2.getVector(), p3.getVector(), p4.getVector());
                } else {
                    curve.setEndPoint(p3);
                    p3.getCurves().add(curve);
                    primitive = new Bezier3P(p1.getVector(), p2.getVector(), p3.getVector());
                }

                curve.setBezier(primitive);
                curves.add(primitive);
            }

            LabyrinthCurve curve = level.getCurves().get(
                    new Random().nextInt(curves.size())
            );
            player = new Vehicle(false, true, curve);

            Set<LabyrinthCurve> chooseCurves = new HashSet<>();
            chooseCurves.add(curve);

            for(int i = 0; i < 0; i++) {
                while(true) {
                    if(chooseCurves.size() == enemies.size() - 1) break;
                    if(chooseCurves.size() == level.getCurves().size()) break;

                    curve = level.getCurves().get(new Random().nextInt(curves.size()));
                    if(!chooseCurves.contains(curve)) {
                        chooseCurves.add(curve);
                        break;
                    }
                }

                boolean forward = i < 5;
                enemies.add(new Vehicle(true, forward, curve));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void terminate() {

    }

    @Override
    public void update() {
        if(Global.gameOver) return;

        float deltaTime = 0.0005f;

        for(Vehicle enemy: enemies) {
            enemy.update(deltaTime);

            if(player.hasCollision(enemy)) {
                Global.gameOver = true;
            }
        }
        player.update(deltaTime);
    }

    @Override
    public void draw() {
        for(IDrawable curve: curves) {
            curve.draw();
        }

        for(Vehicle enemy: enemies) {
            enemy.draw();
        }
        player.draw();
    }
}
