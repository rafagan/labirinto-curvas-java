package br.com.camila.labyrinth.model;

import br.com.camila.game.Global;
import br.com.camila.primitive.IDrawable;
import br.com.camila.primitive.Triangle;
import br.com.camila.util.GlColor;
import br.com.camila.window.KeyListener;
import org.joml.Vector2f;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static org.lwjgl.glfw.GLFW.*;

public class Vehicle implements IDrawable {
    private final int width = 20;
    private final int height = 20;

    private final Triangle triangle;
    private LabyrinthCurve currentCurve;
    private LabyrinthCurve nextCurve;
    private int nextCurveIndex;
    private List<LabyrinthCurve> nextCurves;
    private float currentT = 0.5f;
    private boolean invertedT = true;
    private boolean nextInvertedT = true;
    private boolean ai;
    private boolean forward;
    private boolean stopped;
    private GlColor color;
    private Vector2f position;

    public Vehicle(boolean isAI, boolean forward, LabyrinthCurve startCurve) {
        triangle = new Triangle(
                new Vector2f(0.5f * width, 0.0f * height),
                new Vector2f(-0.5f * width, 0.5f * height),
                new Vector2f(-0.5f * width, -0.5f * height)
        );
        this.ai = isAI;
        this.forward = forward;
        color = new GlColor(isAI ? Color.red : Color.green);
        currentCurve = startCurve;
        position = startCurve.getBezier().lerp(0.0f);
    }

    public LabyrinthCurve getCurrentCurve() {
        return currentCurve;
    }

    public void setCurrentCurve(LabyrinthCurve currentCurve) {
        this.currentCurve = currentCurve;
    }

    public LabyrinthCurve getNextCurve() {
        return nextCurve;
    }

    public void setNextCurve(LabyrinthCurve nextCurve) {
        this.nextCurve = nextCurve;
    }

    public boolean isAi() {
        return ai;
    }

    public void setAi(boolean ai) {
        this.ai = ai;
    }

    public boolean isForward() {
        return forward;
    }

    public void setForward(boolean forward) {
        this.forward = forward;
    }

    @Override
    public GlColor getColor() {
        return color;
    }

    @Override
    public void setColor(GlColor color) {
        this.color = color;
    }

    private boolean shouldChooseCurve() {
        return currentT >= 0.5;
    }

    private boolean shouldChangeCurve() {
        return currentT >= 1.0;
    }

    public boolean isStopped() {
        return stopped;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    private boolean shouldBeInverted() {
        Vector2f finalPosition = currentCurve.getBezier().lerp(invertedT ? 0.0f : 1.0f);
        float dist1 = (finalPosition.sub(nextCurve.getStartPoint().getVector(), new Vector2f())).lengthSquared();
        float dist2 = (finalPosition.sub(nextCurve.getEndPoint().getVector(), new Vector2f())).lengthSquared();
        return dist1 > dist2;
    }

    private void discoverNextCurves() {
        nextCurves = (invertedT ? currentCurve.getStartPoint() : currentCurve.getEndPoint()).getCurves();
    }

    private void changeNextCurveRandomly() {
        discoverNextCurves();

        LabyrinthCurve nextCurve;
        do {
            nextCurveIndex = new Random().nextInt(nextCurves.size());
            nextCurve = nextCurves.get(nextCurveIndex);
        } while(nextCurve == currentCurve);

        configNextCurve(nextCurve);
    }

    private void changeNextCurveLinearly() {
        LabyrinthCurve nextCurve;
        while(true) {
            nextCurveIndex = (nextCurveIndex + 1) % nextCurves.size();
            nextCurve = nextCurves.get(nextCurveIndex);
            if(nextCurve == this.nextCurve) continue;
            if(nextCurve == currentCurve) continue;

            break;
        }

        configNextCurve(nextCurve);
    }

    private void configNextCurve(LabyrinthCurve nextCurve) {
        this.nextCurve = nextCurve;
        nextInvertedT = shouldBeInverted();

        if(ai) return;

        for(LabyrinthCurve curve: nextCurves) {
            curve.getBezier().setColor(Global.defaultColor);
        }

        nextCurve.getBezier().setColor(new GlColor(Color.green));
    }

    public Vector2f getMin() {
        return new Vector2f(position.x + -0.5f * width, position.y + -0.5f * height);
    }

    public Vector2f getMax() {
        return new Vector2f(position.x + 0.5f * width, position.y + 0.5f * height);
    }

    public boolean hasCollision(Vehicle vehicle) {
        if(getMax().x < vehicle.getMin().x) return false;
        if(getMin().x > vehicle.getMax().x) return false;
        if(getMax().y < vehicle.getMin().y) return false;
        if(getMin().y > vehicle.getMax().y) return false;
        return true;
    }

    public void update() {
        if(!stopped) {
            currentT += Global.deltaTime / 2.0;
        }

        if(nextCurve == null && shouldChooseCurve()) {
            changeNextCurveRandomly();
        }

        if(shouldChangeCurve()) {
            currentCurve = nextCurve;
            nextCurve = null;
            invertedT = nextInvertedT;
            currentT = 0.0f;

            if(!ai) {
                Objects.requireNonNull(currentCurve).getBezier().setColor(Global.defaultColor);
            }
        }

        if(!ai) {
            if (nextCurve != null && nextCurves.size() > 2) {
                if (KeyListener.getInstance().isKeyFirstPressed(GLFW_KEY_X)) {
                    changeNextCurveLinearly();
                }
            }

            if (KeyListener.getInstance().isKeyFirstPressed(GLFW_KEY_Z)) {
                forward = !forward;
                invertedT = !invertedT;
                currentT = 1.0f - currentT;
                if(nextCurve != null) nextCurve.getBezier().setColor(Global.defaultColor);
                nextCurve = null;
            }

            if (KeyListener.getInstance().isKeyFirstPressed(GLFW_KEY_SPACE)) {
                stopped = !stopped;
            }
        }
    }

    @Override
    public void draw() {
        if(color != null) color.glSet();

        float t = invertedT ? 1 - currentT : currentT;
        position = currentCurve.getBezier().lerp(t);
        Vector2f nextPosition = currentCurve.getBezier().lerp(t + (invertedT ? -0.0001f : 0.0001f));
        Vector2f direction = (nextPosition.sub(position, new Vector2f())).normalize();
        float angle = (float) (Math.atan2(direction.y, direction.x) / (2 * Math.PI) * 360.0f);

        GL11.glPushMatrix();
        GL11.glTranslatef(position.x, position.y, 0.0f);
        GL11.glRotatef(angle, 0, 0, 1);
        triangle.draw();
        GL11.glPopMatrix();

        if(color != null) Global.defaultColor.glSet();
    }
}
