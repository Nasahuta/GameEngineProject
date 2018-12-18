package fi.tamk.tiko.olioohjelmointi;

import javafx.application.*;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import java.util.EnumSet;
import java.util.Set;
import javafx.scene.Scene;

/**
 * Defines GameEngine.
 * 
 * Extends javafx Application and forces user to implement its start(Stage stage) method.
 * Implements Loop interface that forces user to use render(float float) method.
 * 
 * @author  Joni Alanko <joni.alanko@cs.tamk.fi>
 * @since   2018.1812
 * @version 1.0
 */
@SuppressWarnings("all")
public abstract class GameEngine extends Application implements Loop {
    /**
     * Used for deltaTime calculation to remember previous nanotime.
     */
    private float prevTime = System.nanoTime();
    /**
     * Defines set of KeyCodes.
     */
    private static Set<KeyCode> pressedKeys = EnumSet.noneOf(KeyCode.class);

    /**
     * Overrides init and starts the game thread.
     * 
     * Calls render(deltaTime) every cycle.
     * Calculates deltaTime using currentNanoTime.
     */
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

    /**
     * Checks if the key is pressed.
     * 
     * @param code KeyCode to be checked.
     * @return boolean true if key is pressed.
     */
    public static boolean isKeyPressed(KeyCode code) {
        return pressedKeys.contains(code);
    }

    /**
     * Initializes input polling for scene.
     * 
     * When creating a scene and user wants input polling for it, 
     * the scene must be passed through this method once.
     * 
     * @param scene Scene to be initialized.
     * @return returns initialized scene.
     */
    public Scene initializeScene(Scene scene) {
        scene.setOnKeyPressed(e -> {
            pressedKeys.add(e.getCode());
        });

        scene.setOnKeyReleased(e -> {
            pressedKeys.remove(e.getCode());
        });

        return scene;
    }
}