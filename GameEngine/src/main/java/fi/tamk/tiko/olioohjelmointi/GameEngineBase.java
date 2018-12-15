package fi.tamk.tiko.olioohjelmointi;

import javafx.application.*;
import javafx.animation.AnimationTimer;
import javafx.stage.Stage;

@SuppressWarnings("all")
public class GameEngineBase extends Application implements Loop {
    private float prevTime = System.nanoTime();

    @Override
    public void start(Stage stage) {

        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                float deltaTime = (currentNanoTime - prevTime) / 1000000000f;
                prevTime = currentNanoTime;
                render(deltaTime);
            }
        }.start();
    }

    @Override
    public void render(float deltaTime) {

    }
}