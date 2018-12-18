package fi.tamk.tiko.olioohjelmointi;

import fi.tamk.tiko.olioohjelmointi.*;
import javafx.scene.input.KeyCode;

/**
 * Defines the Enemy GameObject
 * 
 * @author  Joni Alanko <joni.alanko@cs.tamk.fi>
 * @since   2018.1812
 * @version 1.0
 */
@SuppressWarnings("all")
public class Enemy extends GameObject {
    /**
     * Horizontal checker for autonomic enemy movement.
     */
    private boolean goingRight = false;
    /**
     * Vetrical checker for autonomic enemy movement.
     */
    private boolean goingDown = true;
    /**
     * Defines the speed to be used.
     */
    private float speed;

    /**
     * Overrides default constructor for Enemy.
     * 
     * @param path String name of the image to be loaded.
     * @param size double forces image to have its sides be this length.
     */
    public Enemy(String path, double size) {
        super(path, size,550,400);
        setSpeed(100);
    }

    /**
     * Moves the enemy and gives its boundaries.
     * 
     * In addition of its automatic movement, the enemy is moved according to background movement and keyboard input.
     * 
     * @param deltaTime indicates how many seconds passed from last render() call.
     * @param background gives information about game boundaries.
     */
    public void move(float deltaTime, Background background) {

        if (GameEngine.isKeyPressed(KeyCode.UP) && background.getYPos() + background.getSpeed() * deltaTime < 0) {
            setYPos(getYPos() + 200 * deltaTime);
        }

        if (GameEngine.isKeyPressed(KeyCode.RIGHT) && 
                background.getXPos() + background.getRectangle().getWidth() - background.getSpeed() * deltaTime > getScene().getWidth()) {
            setXPos(getXPos() - 200 * deltaTime);
        }

        if (GameEngine.isKeyPressed(KeyCode.DOWN) &&
                background.getYPos() + background.getRectangle().getHeight() - background.getSpeed() * deltaTime > getScene().getHeight()) {
            setYPos(getYPos() - 200 * deltaTime);
        }

        if (GameEngine.isKeyPressed(KeyCode.LEFT) && background.getXPos() + background.getSpeed() * deltaTime < 0) {
            setXPos(getXPos() + 200 * deltaTime);
        }

        update();

        if (goingRight) {
            if ((getXPos() + speed * deltaTime + getRectangle().getWidth()) < background.getXPos() + background.getImage().getWidth()) {
                setXPos(getXPos() + speed * deltaTime);
            } else {
                setXPos(getXPos() + speed * deltaTime);
                goingRight = false;
            }
        } else if (!goingRight) {
            if ((getXPos() - speed * deltaTime) > background.getXPos()) {
                setXPos(getXPos() - speed * deltaTime);
            } else {
                goingRight = true;
                setXPos(getXPos() - speed * deltaTime);
            }
        }

        if (goingDown) {
            if ((getYPos() - speed * deltaTime) > background.getYPos()) {
                setYPos(getYPos() - speed * deltaTime);
            } else {
                goingDown = false;
                setYPos(getYPos() - speed * deltaTime);
            }
        } else if (!goingDown) {
            if (((getYPos() + getRectangle().getHeight()) + speed * deltaTime) < background.getYPos() + background.getImage().getHeight()) {
                setYPos(getYPos() + speed * deltaTime);
            } else {
                goingDown = true;
                setYPos(getYPos() + speed * deltaTime);
            }
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