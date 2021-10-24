package br.com.camila.example.curve;

import br.com.camila.game.IGame;
import br.com.camila.game.World;
import br.com.camila.math.MathUtil;
import br.com.camila.math.function.Sine;
import br.com.camila.primitive.Circle;
import br.com.camila.primitive.Curve;
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
