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
    private boolean ia;
    private boolean forward;
    private boolean stopped;
    private GlColor color;

    public Vehicle(boolean isIA, boolean forward, LabyrinthCurve startCurve) {
        triangle = new Triangle(
                new Vector2f(0.5f * width, 0.0f * height),
                new Vector2f(-0.5f * width, 0.5f * height),
                new Vector2f(-0.5f * width, -0.5f * height)
        );
        this.ia = isIA;
        this.forward = forward;
        color = new GlColor(isIA ? Color.red : Color.green);
        currentCurve = startCurve;
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

    public boolean isIa() {
        return ia;
    }

    public void setIa(boolean ia) {
        this.ia = ia;
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
        return (forward && currentT >= 0.5) || (!forward && currentT <= 0.5);
    }

    private boolean shouldChangeCurve() {
        return (forward && currentT >= 1.0) || (!forward && currentT <= 0.0);
    }

    public boolean isStopped() {
        return stopped;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    private void changeNextCurveRandomly() {
        if(forward && currentT >= 0.5) {
            nextCurves = currentCurve.getEndPoint().getCurves();
        } else {
            nextCurves = currentCurve.getStartPoint().getCurves();
        }

        do {
            nextCurveIndex = new Random().nextInt(nextCurves.size());
            nextCurve = nextCurves.get(nextCurveIndex);
        } while(nextCurve == currentCurve);

        configNextCurve(nextCurve);
    }

    private void changeNextCurveLinearly() {
        while(true) {
            nextCurveIndex = (nextCurveIndex + 1) % nextCurves.size();
            LabyrinthCurve nextCurve = nextCurves.get(nextCurveIndex);
            if(nextCurve == this.nextCurve) continue;
            if(nextCurve == currentCurve) continue;

            this.nextCurve = nextCurve;
            configNextCurve(nextCurve);
            break;
        }
    }

    private void configNextCurve(LabyrinthCurve nextCurve) {
        for(LabyrinthCurve curve: nextCurves) {
            curve.getBezier().setColor(Global.defaultColor);
        }

        nextCurve.getBezier().setColor(new GlColor(Color.green));
    }

    public void update(float deltaTime) {
        if(!stopped) {
            currentT += forward ? deltaTime : -deltaTime;
        }

        if(nextCurve == null && shouldChooseCurve()) {
            changeNextCurveRandomly();
        }

        if(shouldChangeCurve()) {
            currentCurve = nextCurve;
            nextCurve = null;
            Objects.requireNonNull(currentCurve).getBezier().setColor(Global.defaultColor);

            if(forward && currentT >= 1.0f) {
                currentT = 0.0f;
            } else if(!forward && currentT <= 0.0f) {
                currentT = 1.0f;
            }
        }

        if(!ia) {
            if (nextCurve != null && nextCurves.size() > 2) {
                if (KeyListener.getInstance().isKeyFirstPressed(GLFW_KEY_X)) {
                    changeNextCurveLinearly();
                }
            }

            if (KeyListener.getInstance().isKeyFirstPressed(GLFW_KEY_Z)) {
                forward = !forward;
            }

            if (KeyListener.getInstance().isKeyFirstPressed(GLFW_KEY_SPACE)) {
                stopped = !stopped;
            }
        }
    }

    @Override
    public void draw() {
        if(color != null) color.glSet();

        Vector2f position = currentCurve.getBezier().lerp(currentT);
        Vector2f nextPosition = currentCurve.getBezier().lerp(currentT + 0.0001f);
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
