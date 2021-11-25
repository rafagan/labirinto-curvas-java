package br.com.app.primitive;

import br.com.app.game.Global;
import br.com.app.util.GlColor;
import org.joml.Vector2f;

import static org.lwjgl.opengl.GL11.*;

public class Line implements IDrawable {
    private Vector2f p1;
    private Vector2f p2;
    private GlColor color;
    private boolean stippled;

    public Line() {

    }

    public Line(Vector2f p1, Vector2f p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Vector2f getP1() {
        return p1;
    }

    public void setP1(Vector2f p1) {
        this.p1 = p1;
    }

    public Vector2f getP2() {
        return p2;
    }

    public void setP2(Vector2f p2) {
        this.p2 = p2;
    }

    @Override
    public GlColor getColor() {
        return color;
    }

    @Override
    public void setColor(GlColor color) {
        this.color = color;
    }

    public boolean isStippled() {
        return stippled;
    }

    public void setStippled(boolean stippled) {
        this.stippled = stippled;
    }

    @Override
    public void draw() {
        if(color != null) color.glSet();

        if(stippled) {
            glPushAttrib(GL_ENABLE_BIT);
            glLineStipple(10, (short) 0xAAAA);
            glEnable(GL_LINE_STIPPLE);
        }

        glBegin(GL_LINES);
        {
            glVertex2f(p1.x, p1.y);
            glVertex2f(p2.x, p2.y);
        }
        glEnd();

        if(stippled) glPopAttrib();
        if(color != null) Global.defaultColor.glSet();
    }
}
