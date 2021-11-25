package br.com.app.labyrinth.model;

import br.com.app.game.World;
import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.List;

public class LabyrinthPoint {
    private float x;
    private float y;

    private final List<LabyrinthCurve> curves = new ArrayList<>();

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
        return new Vector2f(World.xFromRatio(x), World.yFromRatio(y));
    }

    public List<LabyrinthCurve> getCurves() {
        return curves;
    }
}
