package br.com.camila.example.line;

import br.com.camila.game.IGame;
import br.com.camila.game.World;
import br.com.camila.primitive.Circle;
import br.com.camila.primitive.Line;
import br.com.camila.util.Color;
import org.joml.Vector2f;


public class PrimitiveGame implements IGame {
    private Line line1, line2;
    private Circle circle;

    @Override
    public void init() {
        line1 = new Line(
                new Vector2f(World.xFromRatio(0.4f), World.yFromRatio(0.4f)),
                new Vector2f(World.xFromRatio(0.6f), World.yFromRatio(0.6f))
        );

        line2 = new Line(
                new Vector2f(World.xFromRatio(0.6f), World.yFromRatio(0.4f)),
                new Vector2f(World.xFromRatio(0.4f), World.yFromRatio(0.6f))
        );

        circle = new Circle(
                new Vector2f(World.xFromRatio(0.5f), World.yFromRatio(0.5f)),
                100
        );
        circle.setColor(new Color(java.awt.Color.RED));
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
    }
}
