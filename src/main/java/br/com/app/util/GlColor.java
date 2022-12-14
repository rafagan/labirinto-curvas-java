package br.com.app.util;

import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;

public class GlColor {
    private Vector4f color = new Vector4f();

    public GlColor() {

    }

    public GlColor(java.awt.Color color) {
        this.color.x = color.getRed() / 255.0f;
        this.color.y = color.getGreen() / 255.0f;
        this.color.z = color.getBlue() / 255.0f;
        this.color.w = color.getAlpha() / 255.0f;
    }

    public GlColor(Vector4f color) {
        this.color = color;
    }

    public GlColor(int red, int green, int blue, int alpha) {
        color.x = red / 255.0f;
        color.y = green / 255.0f;
        color.z = blue / 255.0f;
        color.w = alpha / 255.0f;
    }

    public float red() {
        return color.x;
    }

    public float green() {
        return color.y;
    }

    public float blue() {
        return color.z;
    }

    public float alpha() {
        return color.w;
    }

    public void glSet() {
        GL11.glColor4f(red(), green(), blue(), alpha());
    }
}
