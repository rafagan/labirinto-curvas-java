package br.com.camila.primitive;

import br.com.camila.game.Global;
import br.com.camila.util.Color;
import org.joml.Vector2d;
import org.joml.Vector2f;

import static org.lwjgl.opengl.GL11.*;

public class Circle implements Primitive {
    private Vector2f center;
    private double radius;
    private int resolution = 3000;
    private Color color;
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
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
