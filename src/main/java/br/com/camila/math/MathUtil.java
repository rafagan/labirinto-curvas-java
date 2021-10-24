package br.com.camila.math;

public class MathUtil {
    public static float lerp(float t, float start, float end) {
        return start * (1.0f - t) + end * t;
    }
}
