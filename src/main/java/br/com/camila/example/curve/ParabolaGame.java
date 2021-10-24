package br.com.camila.example.curve;

import br.com.camila.game.Global;
import br.com.camila.game.IGame;
import br.com.camila.math.MathUtil;
import br.com.camila.primitive.Circle;
import br.com.camila.primitive.Curve;
import org.joml.Vector2f;
import org.lwjgl.opengl.GL11;

public class ParabolaGame implements IGame {
    Curve curve;
    @Override
    public void init() {
        curve = new Curve(new Curve.CurveFunction() {
            final float a = -0.01f;
            final float b = 0;
            final float c = 0;

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
