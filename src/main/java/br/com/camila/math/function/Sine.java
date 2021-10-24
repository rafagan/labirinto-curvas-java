package br.com.camila.math.function;

import br.com.camila.game.Global;
import br.com.camila.primitive.Curve;

public class Sine implements Curve.CurveFunction {
    private float amplitude;
    private float height;

    public Sine(float amplitude, float height) {
        this.amplitude = amplitude;
        this.height = height;
    }

    public float getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(float amplitude) {
        this.amplitude = amplitude;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public float f(float x) {
        float angle = (float) (x / 360 * Math.PI);
        return (float) (amplitude * Math.sin(angle)) + height;
    }

    @Override
    public float[] interval() {
        return new float[]{0, Global.windowDefaultWidth};
    }
}
