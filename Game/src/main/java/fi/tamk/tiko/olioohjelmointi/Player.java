package fi.tamk.tiko.olioohjelmointi;

import fi.tamk.tiko.olioohjelmointi.*;
import javafx.scene.input.KeyEvent;
import javafx.event.*;
import javafx.scene.input.KeyCode;
import java.util.*;

@SuppressWarnings("all")
public class Player extends GameObject {
    private float speed;

    public Player(String path) {
        super(path);
        setSpeed(200);
    }

    public void move(float deltaTime) {
        
        if (GameEngine.isKeyPressed(KeyCode.UP)) {
            setYPos(getYPos() - getSpeed() * deltaTime);
        }

        if (GameEngine.isKeyPressed(KeyCode.RIGHT)) {
            setXPos(getXPos() + getSpeed() * deltaTime);
        }

        if (GameEngine.isKeyPressed(KeyCode.DOWN)) {
            setYPos(getYPos() + getSpeed() * deltaTime);
        }

        if (GameEngine.isKeyPressed(KeyCode.LEFT)) {
            setXPos(getXPos() - getSpeed() * deltaTime);
        }

        update();
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return this.speed;
    }
}