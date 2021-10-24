package br.com.camila.example.primitive;

import br.com.camila.game.IGame;
import br.com.camila.game.World;
import br.com.camila.math.function.Sine;
import br.com.camila.primitive.Curve;

public class BezierGame implements IGame {
    Curve curve;

    @Override
    public void init() {
        curve = new Curve(new Sine(100, World.yFromRatio(0.5f)));
    }

    @Override
    public void terminate() {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw() {
        curve.draw();
    }
}
