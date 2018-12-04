package fi.tamk.tiko.olioohjelmointi;

import fi.tamk.tiko.olioohjelmointi.*;

public class Enemy extends GameObject {
    private boolean goingRight = true;
    private boolean goingDown = true;
    private float speed;

    public Enemy(String path, double size) {
        super(path, size);
        super.setSpeed(100);
        speed = super.getSpeed();
    }

    @Override
    public void move(float deltaTime) {
        if (goingRight) {
            if ((getXPos() + speed * deltaTime + getRectangle().getWidth()) < getScene().getWidth()) {
                setXPos(getXPos() + speed * deltaTime);
            } else {
                setXPos(getXPos() + speed * deltaTime);
                goingRight = false;
            }
        } else if (!goingRight) {
            if ((getXPos() - speed * deltaTime) > 0) {
                setXPos(getXPos() - speed * deltaTime);
            } else {
                goingRight = true;
                setXPos(getXPos() - speed * deltaTime);
            }
        }

        if (goingDown) {
            if ((getYPos() - speed * deltaTime) > 0) {
                setYPos(getYPos() - speed * deltaTime);
            } else {
                goingDown = false;
                setYPos(getYPos() - speed * deltaTime);
            }
        } else if (!goingDown) {
            if (((getYPos() + getRectangle().getHeight()) + speed * deltaTime) < getScene().getHeight()) {
                setYPos(getYPos() + speed * deltaTime);
            } else {
                goingDown = true;
                setYPos(getYPos() + speed * deltaTime);
            }
        }

        update();
    }
}