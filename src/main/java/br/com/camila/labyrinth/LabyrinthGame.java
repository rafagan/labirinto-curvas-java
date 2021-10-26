package br.com.camila.labyrinth;

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

                Vector2f v1 = new Vector2f(World.xFromRatio(p1.getX()), World.yFromRatio(p1.getY()));
                Vector2f v2 = new Vector2f(World.xFromRatio(p2.getX()), World.yFromRatio(p2.getY()));
                Vector2f v3 = new Vector2f(World.xFromRatio(p3.getX()), World.yFromRatio(p3.getY()));

                curve.setStartPoint(p1);
                p1.getCurves().add(curve);

                IBezier primitive;
                if(curve.hasFourPoints()) {
                    p4 = level.getPoints().get(curve.getP4());
                    Vector2f v4 = new Vector2f(World.xFromRatio(p4.getX()), World.yFromRatio(p4.getY()));
                    primitive = new Bezier4P(v1, v2, v3, v4);
                    curve.setEndPoint(p4);
                    p4.getCurves().add(curve);
                } else {
                    primitive = new Bezier3P(v1, v2, v3);
                    curve.setEndPoint(p3);
                    p3.getCurves().add(curve);
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

//            for(int i = 0; i < 10; i++) {
//                while(true) {
//                    if(chooseCurves.size() == enemies.size() - 1) break;
//                    if(chooseCurves.size() == level.getCurves().size()) break;
//
//                    curve = level.getCurves().get(new Random().nextInt(curves.size()));
//                    if(!chooseCurves.contains(curve)) {
//                        chooseCurves.add(curve);
//                        break;
//                    }
//                }
//
//                boolean forward = i < 5;
//                enemies.add(new Vehicle(true, forward, curve));
//            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void terminate() {

    }

    @Override
    public void update() {
        float deltaTime = 0.001f;

        for(Vehicle enemy: enemies) {
            enemy.update(deltaTime);
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
