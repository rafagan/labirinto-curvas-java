package br.com.camila.primitive;

import br.com.camila.game.Global;
import br.com.camila.util.Color;
import org.joml.Vector2f;

import static org.lwjgl.opengl.GL11.*;

public class Triangle implements Primitive {
    private Vector2f p1;
    private Vector2f p2;
    private Vector2f p3;
    private Color color;
    private boolean filled;

    public Triangle() {}

    public Triangle(Vector2f p1, Vector2f p2, Vector2f p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    @Override
    public void draw() {
        if(color != null) color.glSet();

        glBegin(filled ? GL_TRIANGLES : GL_LINE_LOOP);

        glVertex2f(p1.x, p1.y);
        glVertex2f(p2.x, p2.y);
        glVertex2f(p3.x, p3.y);

        glEnd();
        if(color != null) Global.defaultColor.glSet();
    }
}
