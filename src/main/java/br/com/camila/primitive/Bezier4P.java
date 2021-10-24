package br.com.camila.primitive;

import org.joml.Vector2f;
import org.lwjgl.opengl.GL11;

public class Bezier4P implements IPrimitive {
    private Vector2f p1, p2, p3, p4;

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

    @Override
    public void draw() {
        GL11.glBegin(GL11.GL_LINE_STRIP);
        for(float t = 0f; t < 1f; t += 0.01f) {
            Vector2f l1 = p1.lerp(p2, t, new Vector2f());
            Vector2f l2 = p2.lerp(p3, t, new Vector2f());
            Vector2f l3 = p3.lerp(p4, t, new Vector2f());
            Vector2f m1 = l1.lerp(l2, t, new Vector2f());
            Vector2f m2 = l2.lerp(l3, t, new Vector2f());
            Vector2f p = m1.lerp(m2, t, new Vector2f());
            GL11.glVertex2f(p.x, p.y);
        }
        GL11.glEnd();
    }
}
