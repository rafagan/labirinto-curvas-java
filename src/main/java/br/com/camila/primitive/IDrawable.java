package br.com.camila.primitive;

import br.com.camila.util.GlColor;

public interface IDrawable {
    void draw();
    GlColor getColor();
    void setColor(GlColor color);
}
