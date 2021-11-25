package br.com.app.game;

import br.com.app.util.GlColor;

public class Global {
    public static final GlColor clearColor = new GlColor(java.awt.Color.darkGray);
    public static final GlColor defaultColor = new GlColor(java.awt.Color.white);

    public static final String windowTitle = "Curves";
    public static final int windowDefaultWidth = 1024;
    public static final int windowDefaultHeight = 768;

    public static boolean gameOver = false;
    public static final int frameRate = 60;
    public static final float goalRate = 1.0f / Global.frameRate;
    public static float deltaTime = 0;
}
