package fi.tamk.tiko.olioohjelmointi;

import javafx.application.*;
import javafx.animation.AnimationTimer;

public abstract class GameEngine extends Application implements Loop {
    private float prevTime = System.nanoTime();

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
}