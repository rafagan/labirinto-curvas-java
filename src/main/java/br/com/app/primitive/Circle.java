package br.com.app.primitive;

import br.com.app.game.Global;
import br.com.app.util.GlColor;
import org.joml.Vector2d;
import org.joml.Vector2f;

import static org.lwjgl.opengl.GL11.*;

public class Circle implements IDrawable {
    private Vector2f center;
    private double radius;
    private int resolution = 3000;
    private GlColor color;
    private boolean filled;

    public Circle() {}

    public Circle(Vector2f center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public Vector2f getCenter() {
        return center;
    }

    public void setCenter(Vector2f center) {
        this.center = center;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public GlColor getColor() {
        return color;
    }

    @Override
    public void setColor(GlColor color) {
        this.color = color;
    }

    public int getResolution() {
        return resolution;
    }

    public void setResolution(int resolution) {
        this.resolution = resolution;
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
            double angleStepRadians =  (2 * Math.PI) / (double)resolution;
            for(int i = 0; i < resolution; i++) {
                double radians = angleStepRadians * i;
                Vector2d point = new Vector2d(
                        Math.cos(radians),
                        Math.sin(radians)
                ).mul(radius).add(center);
                glVertex2d(point.x, point.y);
            }
        }
        glEnd();

        if(color != null) Global.defaultColor.glSet();
    }
}
