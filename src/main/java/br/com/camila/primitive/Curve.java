package br.com.camila.primitive;

import org.joml.Vector2f;

import static org.lwjgl.opengl.GL11.*;

public class Curve implements IPrimitive {
    private int resolution = 100;

    public interface CurveFunction {
        float f(float x);
        float[] interval();
    }

    private final CurveFunction function;

    public Curve(CurveFunction function) {
        this.function = function;
    }

    public int getResolution() {
        return resolution;
    }

    public void setResolution(int resolution) {
        this.resolution = resolution;
    }

    @Override
    public void draw() {
        glBegin(GL_LINE_STRIP);
        {
            float[] it = function.interval();
            float delta = (it[1] - it[0]) / resolution;
            for(float x = it[0]; x < it[1]; x += delta) {
                Vector2f v = new Vector2f(x, function.f(x));
                glVertex2f(v.x, v.y);
            }
        }
        glEnd();
    }
}
