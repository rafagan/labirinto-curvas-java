package br.com.app.example.blink;

import br.com.app.game.IGame;
import br.com.app.window.KeyListener;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;
import static org.lwjgl.opengl.GL11.glClearColor;

public class BlinkGame implements IGame {
    @Override
    public void init() {

    }

    @Override
    public void terminate() {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw() {
        if (KeyListener.getInstance().isKeyPressed(GLFW_KEY_SPACE)) {
            glClearColor(1, 0, 0, 0);
        } else {
            glClearColor(0, 0, 0, 0);
        }
    }
}
