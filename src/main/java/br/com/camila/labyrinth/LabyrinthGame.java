package br.com.camila.labyrinth;

import br.com.camila.game.IGame;
import br.com.camila.game.World;
import br.com.camila.labyrinth.io.LabyrinthReader;
import br.com.camila.labyrinth.model.Labyrinth;
import br.com.camila.labyrinth.model.LabyrinthCurve;
import br.com.camila.labyrinth.model.LabyrinthPoint;
import br.com.camila.primitive.Bezier3P;
import br.com.camila.primitive.Bezier4P;
import br.com.camila.primitive.IPrimitive;
import org.joml.Vector2f;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class LabyrinthGame implements IGame {
    List<IPrimitive> curves = new ArrayList<>();

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

                IPrimitive primitive;
                if(curve.hasFourPoints()) {
                    p4 = level.getPoints().get(curve.getP4());
                    Vector2f v4 = new Vector2f(World.xFromRatio(p4.getX()), World.yFromRatio(p4.getY()));
                    primitive = new Bezier4P(v1, v2, v3, v4);
                } else {
                    primitive = new Bezier3P(v1, v2, v3);
                }

                curves.add(primitive);
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

    }

    @Override
    public void draw() {
        for(IPrimitive curve: curves) {
            curve.draw();
        }
    }
}
