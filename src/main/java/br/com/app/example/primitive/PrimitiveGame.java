package br.com.app.example.primitive;

import br.com.app.game.IGame;
import br.com.app.game.World;
import br.com.app.primitive.Circle;
import br.com.app.primitive.Line;
import br.com.app.primitive.Triangle;
import br.com.app.util.GlColor;
import org.joml.Vector2f;


public class PrimitiveGame implements IGame {
    private Line line1, line2;
    private Circle circle;
    private Triangle triangle;

    @Override
    public void init() {
        line1 = new Line(
                new Vector2f(World.xFromRatio(0.4f), World.yFromRatio(0.4f)),
                new Vector2f(World.xFromRatio(0.6f), World.yFromRatio(0.6f))
        );
        line1.setStippled(true);

        line2 = new Line(
                new Vector2f(World.xFromRatio(0.6f), World.yFromRatio(0.4f)),
                new Vector2f(World.xFromRatio(0.4f), World.yFromRatio(0.6f))
        );

        circle = new Circle(
                new Vector2f(World.xFromRatio(0.5f), World.yFromRatio(0.5f)),
                100
        );
        circle.setColor(new GlColor(java.awt.Color.RED));

        triangle = new Triangle(
                new Vector2f(World.xFromRatio(0.5f), World.yFromRatio(0.8f)),
                new Vector2f(World.xFromRatio(0.3f), World.yFromRatio(0.3f)),
                new Vector2f(World.xFromRatio(0.7f), World.yFromRatio(0.3f))
        );
    }

    @Override
    public void terminate() {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw() {
        circle.draw();
        line1.draw();
        line2.draw();
        triangle.draw();
    }
}
