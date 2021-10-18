package br.com.camila.glfw;


import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import java.util.Objects;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
    private static Window INSTANCE = null;
    public static Window getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Window();
        }
        return INSTANCE;
    }

    private static final String TITLE = "Labirinto com Curvas by Camila";
    private static final int DEFAULT_WIDTH = 1024;
    private static final int DEFAULT_HEIGHT = 768;

    private long glfwWindowAddress;

    public void run() {
        init();
        execution();
        terminateGracefully();
    }

    private void init() {
        GLFWErrorCallback.createPrint(System.err).set();
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        glfwWindowAddress = createAndConfigureWindow();
        glfwMakeContextCurrent(glfwWindowAddress);
        GL.createCapabilities();
        glfwShowWindow(glfwWindowAddress);
    }

    private void execution() {
        while (!glfwWindowShouldClose(glfwWindowAddress)) {
            display();
            glfwPollEvents();
        }
    }

    private void terminateGracefully() {
        glfwFreeCallbacks(glfwWindowAddress);
        glfwDestroyWindow(glfwWindowAddress);
        glfwTerminate();
        Objects.requireNonNull(glfwSetErrorCallback(null)).free();
    }

    private long createAndConfigureWindow() {
        // We will show view manually
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);

        // Cannot resize window
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

        long windowAddress = glfwCreateWindow(
                DEFAULT_WIDTH,
                DEFAULT_HEIGHT,
                TITLE,
                NULL,
                NULL
        );

        if (windowAddress == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        return windowAddress;
    }

    private void display() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        // TODO: Game

        glfwSwapBuffers(glfwWindowAddress);
    }
}
