package br.com.camila.primitive;

import br.com.camila.game.Global;
import br.com.camila.math.MathUtil;
import br.com.camila.util.GlColor;
import org.joml.Vector2f;
import org.lwjgl.opengl.GL11;

public class Bezier4P implements IBezier {
    private Vector2f p1, p2, p3, p4;
    private int resolution = 100;
    private GlColor color;

    private final Vector2f l1 = new Vector2f();
    private final Vector2f l2 = new Vector2f();
    private final Vector2f l3 = new Vector2f();
    private final Vector2f m1 = new Vector2f();
    private final Vector2f m2 = new Vector2f();
    private final Vector2f p = new Vector2f();

    public Bezier4P(Vector2f p1, Vector2f p2, Vector2f p3, Vector2f p4) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
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

    public Vector2f getP3() {
        return p3;
    }

    public void setP3(Vector2f p3) {
        this.p3 = p3;
    }

    public Vector2f getP4() {
        return p4;
    }

    public void setP4(Vector2f p4) {
        this.p4 = p4;
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
    public Vector2f lerp(float t) {
        t = MathUtil.clamp(t, 0.0f, 1.0f);
        p1.lerp(p2, t, l1);
        p2.lerp(p3, t, l2);
        p3.lerp(p4, t, l3);
        l1.lerp(l2, t, m1);
        l2.lerp(l3, t, m2);
        m1.lerp(m2, t, p);
        return new Vector2f(p);
    }

    @Override
    public void draw() {
        if(color != null) color.glSet();

        GL11.glBegin(GL11.GL_LINE_STRIP);
        for(float t = 0f; t < 1f; t += 1f / resolution) {
            lerp(t);
            GL11.glVertex2f(p.x, p.y);
        }
        GL11.glEnd();

        if(color != null) Global.defaultColor.glSet();
    }
}
