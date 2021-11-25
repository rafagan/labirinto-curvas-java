package br.com.app.primitive;

import br.com.app.game.Global;
import br.com.app.util.GlColor;
import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class Polygon implements IDrawable {
    private List<Vector2f> vertices = new ArrayList<>();
    private GlColor color;
    private boolean filled;

    public Polygon() {}

    public Polygon(List<Vector2f> vertices) {
        this.vertices = vertices;
    }

    public List<Vector2f> getVertices() {
        return vertices;
    }

    public void setVertices(List<Vector2f> vertices) {
        this.vertices = vertices;
    }

    @Override
    public GlColor getColor() {
        return color;
    }

    @Override
    public void setColor(GlColor color) {
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

        glBegin(filled ? GL_POLYGON : GL_LINE_LOOP);
        {
            for(Vector2f v: vertices) {
                glVertex2f(v.x, v.y);
            }
        }
        glEnd();

        if(color != null) Global.defaultColor.glSet();
    }
}
