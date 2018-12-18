package fi.tamk.tiko.olioohjelmointi;

import fi.tamk.tiko.olioohjelmointi.*;
import javafx.scene.input.KeyCode;

/**
 * Defines the Player GameObject
 * 
 * @author  Joni Alanko <joni.alanko@cs.tamk.fi>
 * @since   2018.1812
 * @version 1.0
 */
@SuppressWarnings("all")
public class Player extends GameObject {
    /**
     * Defines the speed to be used.
     */
    private float speed;

    /**
     * Overrides default constructor for Player.
     * 
     * @param path String name of the image to be loaded.
     */
    public Player(String path) {
        super(path,0,0);
        setSpeed(200);
    }

    /**
     * Moves the player using keyboard inputs.
     * 
     * @param deltaTime indicates how many seconds passed from last render() call.
     */
    public void move(float deltaTime) {
        
        if (GameEngine.isKeyPressed(KeyCode.UP) && getYPos() - getSpeed() * deltaTime > 0) {
            setYPos(getYPos() - getSpeed() * deltaTime);
        }

        if (GameEngine.isKeyPressed(KeyCode.RIGHT) && getXPos() + getRectangle().getWidth() + getSpeed() * deltaTime < getScene().getWidth()) {
            setXPos(getXPos() + getSpeed() * deltaTime);
        }

        if (GameEngine.isKeyPressed(KeyCode.DOWN) && getYPos() + getRectangle().getHeight() + getSpeed() * deltaTime < getScene().getHeight()) {
            setYPos(getYPos() + getSpeed() * deltaTime);
        }

        if (GameEngine.isKeyPressed(KeyCode.LEFT) && getXPos() - getSpeed() * deltaTime > 0) {
            setXPos(getXPos() - getSpeed() * deltaTime);
        }

        update();
    }

    /**
     * Sets the speed.
     * 
     * @param speed float to be set.
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    /**
     * Gets the speed.
     * 
     * @return speed.
     */
    public float getSpeed() {
        return this.speed;
    }
}