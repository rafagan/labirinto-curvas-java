package br.com.app.example.bezier;

import br.com.app.game.IGame;
import br.com.app.game.World;
import br.com.app.primitive.Bezier3P;
import br.com.app.primitive.Circle;
import org.joml.Vector2f;

public class Bezier3PointsGame implements IGame {
    Vector2f p1, p2, p3;
    Circle c1, c2, c3;
    Bezier3P bezier;
    Circle circle;

    float time = 0f;
    Vector2f position = new Vector2f();

    @Override
    public void init() {
        p1 = new Vector2f(World.xFromRatio(0.2f), World.yFromRatio(0.2f));
        p2 = new Vector2f(World.xFromRatio(0.5f), World.yFromRatio(0.8f));
        p3 = new Vector2f(World.xFromRatio(0.8f), World.yFromRatio(0.2f));

        c1 = new Circle(p1, 5);
        c2 = new Circle(p2, 5);
        c3 = new Circle(p3, 5);

        bezier = new Bezier3P(p1, p2, p3);
        circle = new Circle(position, 5);
        circle.setFilled(true);
    }

    @Override
    public void terminate() {

    }

    @Override
    public void update() {
        position.set(bezier.lerp(time += 0.001f));
    }

    @Override
    public void draw() {
        bezier.draw();
        c1.draw();
        c2.draw();
        c3.draw();
        circle.draw();
    }
}
