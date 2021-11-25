package br.com.app.primitive;

import br.com.app.game.Global;
import br.com.app.util.GlColor;
import org.joml.Vector2f;

import static org.lwjgl.opengl.GL11.*;

public class Curve implements IDrawable {
    private int resolution = 100;
    private GlColor color;

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
    public GlColor getColor() {
        return color;
    }

    @Override
    public void setColor(GlColor color) {
        this.color = color;
    }

    @Override
    public void draw() {
        if(color != null) color.glSet();

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

        if(color != null) Global.defaultColor.glSet();
    }
}
