package fi.tamk.tiko.olioohjelmointi;

import javafx.application.*;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import java.util.EnumSet;
import java.util.Set;
import javafx.scene.Scene;

@SuppressWarnings("all")
public abstract class GameEngine extends Application implements Loop {
    private float prevTime = System.nanoTime();
    private static Set<KeyCode> pressedKeys = EnumSet.noneOf(KeyCode.class);
    private Scene scene;

    @Override
    public void init() {
        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                float deltaTime = (currentNanoTime - prevTime) / 1000000000f;
                prevTime = currentNanoTime;
                render(deltaTime);
            }
        }.start();
    }

    public static boolean isKeyPressed(KeyCode code) {
        return pressedKeys.contains(code);
    }

    public Scene initializeScene(Scene scene) {
        scene.setOnKeyPressed(e -> {
            pressedKeys.add(e.getCode());
        });

        scene.setOnKeyReleased(e -> {
            pressedKeys.remove(e.getCode());
        });

        this.scene = scene;
        return scene;
    }
}