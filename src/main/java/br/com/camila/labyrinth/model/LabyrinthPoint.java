package br.com.camila.labyrinth.model;

import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.List;

public class LabyrinthPoint {
    private float x;
    private float y;

    private List<LabyrinthCurve> curves = new ArrayList<>();

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Vector2f getVector() {
        return new Vector2f(x, y);
    }

    public List<LabyrinthCurve> getCurves() {
        return curves;
    }

    public void setCurves(List<LabyrinthCurve> curves) {
        this.curves = curves;
    }
}
