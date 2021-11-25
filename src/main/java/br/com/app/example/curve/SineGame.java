package br.com.app.example.curve;

import br.com.app.game.IGame;
import br.com.app.game.World;
import br.com.app.math.MathUtil;
import br.com.app.math.function.Sine;
import br.com.app.primitive.Circle;
import br.com.app.primitive.Curve;
import org.joml.Vector2f;

public class SineGame implements IGame {
    Curve curve;
    Circle circle;

    float time = 0f;
    Vector2f position = new Vector2f();
    Curve.CurveFunction func = new Sine(200, World.yFromRatio(0.5f));

    @Override
    public void init() {
        circle = new Circle(position, 5f);
        curve = new Curve(func);
    }

    @Override
    public void terminate() {

    }

    @Override
    public void update() {
        position.x = MathUtil.lerp(time, func.interval()[0], func.interval()[1]);
        position.y = func.f(position.x);
        time += 0.0001;
    }

    @Override
    public void draw() {
        curve.draw();
        circle.draw();
    }
}
