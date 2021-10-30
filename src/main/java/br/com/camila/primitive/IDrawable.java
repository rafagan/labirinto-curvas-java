package br.com.camila.primitive;

import br.com.camila.util.GlColor;

public interface IDrawable {
    GlColor getColor();
    void setColor(GlColor color);
    void draw();
}
