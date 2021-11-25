package br.com.app.primitive;

import br.com.app.util.GlColor;

public interface IDrawable {
    GlColor getColor();
    void setColor(GlColor color);
    void draw();
}
