package br.com.camila.example.bezier;

import br.com.camila.game.IGame;
import br.com.camila.game.World;
import br.com.camila.math.MathUtil;
import br.com.camila.primitive.Bezier4P;
import br.com.camila.primitive.Circle;
import org.joml.Vector2f;

public class TestCurvesGame implements IGame {
    Vector2f p1, p2, p3, p4;
    Vector2f p5, p6, p7;
    Circle c1, c2, c3, c4;
    Bezier4P bezier1, bezier2;

    @Override
    public void init() {
        p1 = new Vector2f(World.xFromRatio(0.5f), World.yFromRatio(0.99f));
        p2 = new Vector2f(World.xFromRatio(0.4f), World.yFromRatio(0.92f));
        p3 = new Vector2f(World.xFromRatio(0.6f), World.yFromRatio(0.92f));
        p4 = new Vector2f(World.xFromRatio(0.5f), World.yFromRatio(0.83f));

        p5 = new Vector2f(World.xFromRatio(0.5f), World.yFromRatio(0.99f));
        p6 = new Vector2f(World.xFromRatio(0.4f), World.yFromRatio(0.92f));
        p7 = new Vector2f(World.xFromRatio(0.6f), World.yFromRatio(0.67f));

        c1 = new Circle(p1, 5);
        c2 = new Circle(p2, 5);
        c3 = new Circle(p3, 5);
        c4 = new Circle(p4, 5);

        bezier1 = new Bezier4P(p1, p2, p3, p4);
        bezier2 = new Bezier4P(p4, p5, p6, p7);
    }

    @Override
    public void terminate() {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw() {
        bezier1.draw();
        bezier2.draw();
        c1.draw();
        c2.draw();
        c3.draw();
        c4.draw();
    }
}
