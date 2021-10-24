package br.com.camila.example.curve;

import br.com.camila.game.Global;
import br.com.camila.game.IGame;
import br.com.camila.game.World;
import br.com.camila.math.function.Sine;
import br.com.camila.primitive.Curve;
import org.lwjgl.opengl.GL11;

public class CurveGame implements IGame {
    Curve curve;

    @Override
    public void init() {
        // curve = new Curve(new Sine(100, World.yFromRatio(0.5f)));
        curve = new Curve(new Curve.CurveFunction() {
            float a = -0.01f;
            float b = 0;
            float c = 0;

            @Override
            public float f(float x) {
                return (float) (a * Math.pow(x, 2) + b * x + c);
            }

            @Override
            public float[] interval() {
                return new float[]{
                        -Global.windowDefaultWidth / 2.0f,
                        Global.windowDefaultWidth / 2.0f
                };
            }
        });
    }

    @Override
    public void terminate() {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw() {
        GL11.glPushMatrix();
        GL11.glTranslatef(Global.windowDefaultWidth / 2f, Global.windowDefaultHeight, 0f);
        curve.draw();
        GL11.glPopMatrix();
    }
}
