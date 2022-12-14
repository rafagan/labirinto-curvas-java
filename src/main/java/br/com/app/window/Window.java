package br.com.app.window;


import br.com.app.game.Global;
import br.com.app.game.IGame;
import br.com.app.game.World;
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
        if (INSTANCE == null) INSTANCE = new Window();
        return INSTANCE;
    }

    private Window() {}

    private IGame game;
    private long glfwWindowAddress;

    public void run(IGame game) {
        this.game = game;

        init();
        execute();
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

        glfwSetKeyCallback(glfwWindowAddress, KeyListener.getInstance());

        World.setCoordinatePlane();
        game.init();
    }

    private void checkSleep(float deltaTime) {
        if (deltaTime >= Global.goalRate) return;

        try {
            Thread.sleep((long) ((Global.goalRate - deltaTime) * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void execute() {
        while (!glfwWindowShouldClose(glfwWindowAddress)) {
            long startLoopTime = System.currentTimeMillis();
            game.update();
            display();
            KeyListener.getInstance().update();
            glfwPollEvents();
            checkSleep((System.currentTimeMillis() - startLoopTime) / 1000.0f);
            Global.deltaTime = Global.goalRate;
        }
    }

    private void terminateGracefully() {
        game.terminate();
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
                Global.windowDefaultWidth,
                Global.windowDefaultHeight,
                Global.windowTitle,
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
        glClearColor(
                Global.clearColor.red(),
                Global.clearColor.green(),
                Global.clearColor.blue(),
                Global.clearColor.alpha()
        );
        Global.defaultColor.glSet();
        game.draw();
        glfwSwapBuffers(glfwWindowAddress);
    }
}
