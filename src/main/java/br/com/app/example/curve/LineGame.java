package br.com.app.example.curve;

import br.com.app.game.IGame;
import br.com.app.game.World;
import br.com.app.math.MathUtil;
import br.com.app.primitive.Circle;
import br.com.app.primitive.Line;
import org.joml.Vector2f;

public class LineGame implements IGame {
    Line line;
    Circle circle;
    Vector2f start = new Vector2f(World.xFromRatio(0f), World.yFromRatio(0f));
    Vector2f end = new Vector2f(World.xFromRatio(1f), World.yFromRatio(1f));

    float time = 0f;
    Vector2f position = new Vector2f();

    @Override
    public void init() {
        circle = new Circle(position, 5f);
        line = new Line(start, end);
    }

    @Override
    public void terminate() {

    }

    @Override
    public void update() {
        position.x = MathUtil.lerp(time, start.x, end.x);
        position.y = MathUtil.lerp(time, start.y, end.y);
        time += 0.0001;
    }

    @Override
    public void draw() {
        line.draw();
        circle.draw();
    }
}
