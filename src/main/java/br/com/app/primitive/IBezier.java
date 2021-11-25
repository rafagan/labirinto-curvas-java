package br.com.app.primitive;

import org.joml.Vector2f;

public interface IBezier extends IDrawable {
    Vector2f lerp(float t);
    float length();
}
