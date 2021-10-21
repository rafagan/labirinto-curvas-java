package br.com.camila.game;

import static org.lwjgl.opengl.GL11.glOrtho;

public class World {

    public static void setCoordinatePlane() {
        glOrtho(
                0, Global.windowDefaultWidth,
                0, Global.windowDefaultHeight,
                0, 1
        );
    }

    public static float xFromRatio(float ratio) {
        return Global.windowDefaultWidth * ratio;
    }

    public static float yFromRatio(float ratio) {
        return Global.windowDefaultHeight * ratio;
    }
}
