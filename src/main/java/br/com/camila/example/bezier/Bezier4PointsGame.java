package br.com.camila.example.bezier;

import br.com.camila.game.IGame;
import br.com.camila.game.World;
import br.com.camila.primitive.Bezier4P;
import br.com.camila.primitive.Circle;
import org.joml.Vector2f;

public class Bezier4PointsGame implements IGame {
    Vector2f p1, p2, p3, p4;
    Circle c1, c2, c3, c4;
    Bezier4P bezier;

    @Override
    public void init() {
        p1 = new Vector2f(World.xFromRatio(0.2f), World.yFromRatio(0.2f));
        p2 = new Vector2f(World.xFromRatio(0.1f), World.yFromRatio(0.8f));
        p3 = new Vector2f(World.xFromRatio(0.8f), World.yFromRatio(0.8f));
        p4 = new Vector2f(World.xFromRatio(0.9f), World.yFromRatio(0.2f));

        c1 = new Circle(p1, 5);
        c2 = new Circle(p2, 5);
        c3 = new Circle(p3, 5);
        c4 = new Circle(p4, 5);

        bezier = new Bezier4P(p1, p2, p3, p4);
    }

    @Override
    public void terminate() {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw() {
        bezier.draw();
        c1.draw();
        c2.draw();
        c3.draw();
        c4.draw();
    }
}
