package br.com.app.math;

public class MathUtil {
    public static float lerp(float t, float start, float end) {
        return start * (1.0f - t) + end * t;
    }

    public static float clamp(float v, float min, float max) {
        return Math.min(max, Math.max(min, v));
    }
}
