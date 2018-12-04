package fi.tamk.tiko.olioohjelmointi;

import fi.tamk.tiko.olioohjelmointi.*;

public class Player extends GameObject {
    public Player(String path) {
        super(path);
        super.setSpeed(200);
    }

    @Override
    public void move(float deltaTime) {
        getScene().setOnKeyPressed(e -> {
            e.consume();

            switch (e.getCode()) {
                case UP:    setYPos(getYPos() - getSpeed() * deltaTime); break;
                case RIGHT: setXPos(getXPos() + getSpeed() * deltaTime); break;
                case DOWN:  setYPos(getYPos() + getSpeed() * deltaTime); break;
                case LEFT:  setXPos(getXPos() - getSpeed() * deltaTime); break;
            }
        });

        update();
    }
}