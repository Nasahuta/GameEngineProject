package fi.tamk.tiko.olioohjelmointi;

import javafx.application.*;
import javafx.animation.AnimationTimer;

public abstract class GameEngine extends Application implements Loop {
    @Override
    public void init() {
        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                render();
            }
        }.start();
    }
}