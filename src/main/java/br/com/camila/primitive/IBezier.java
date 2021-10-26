package br.com.camila.primitive;

import org.joml.Vector2f;

public interface IBezier extends IDrawable {
    Vector2f lerp(float t);
}
