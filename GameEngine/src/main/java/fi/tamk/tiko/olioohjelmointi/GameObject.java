package fi.tamk.tiko.olioohjelmointi;

import javafx.scene.image.*;
import java.net.URL;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class GameObject {
    private int speed;

    private int xPosition;
    private int yPosition;
    private Image model;
    private ImageView viewer;
    private Pane pane;
    private Scene scene;

    public GameObject(String path) {
        model = new Image(getClass().getResourceAsStream(path));
        viewer = new ImageView(model);
        viewer.setFitWidth(200);
        viewer.setPreserveRatio(true);
        viewer.setSmooth(true);
        pane = new Pane();
        pane.getChildren().addAll(getImageView());
        yPosition = 0;
        xPosition = 0;
        speed = 5;
    }

    public GameObject(String path, int size) {
        model = new Image(getClass().getResourceAsStream(path));
        viewer = new ImageView(model);
        viewer.setFitWidth(size);
        viewer.setPreserveRatio(true);
        viewer.setSmooth(true);
        pane = new Pane();
        pane.getChildren().addAll(getImageView());
        yPosition = 0;
        xPosition = 0;
        speed = 5;
    }

    public Scene addToScreen() {
        Group root = new Group();
        root.getChildren().add(getPane());
        scene = new Scene(root);
        scene.setFill(Color.BLACK);

        return scene;
    }

    public void move() {
        scene.setOnKeyPressed(e -> {
            e.consume();

            switch (e.getCode()) {
                case UP:    setYPos(getYPos() - speed); break;
                case RIGHT: setXPos(getXPos() + speed); break;
                case DOWN:  setYPos(getYPos() + speed); break;
                case LEFT:  setXPos(getXPos() - speed); break;
            }
        });

        viewer.setLayoutX(getXPos());
        viewer.setLayoutY(getYPos());
    }

    public void update() {
        viewer.setLayoutX(getXPos());
        viewer.setLayoutY(getYPos());
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return this.speed;
    }

    public Pane getPane() {
        return pane;
    }

    public ImageView getImageView() {
        return viewer;
    }

    public Image getImage() {
        return model;
    }

    public void setXPos(int xPos) {
        this.xPosition = xPos;
    }

    public int getXPos() {
        return this.xPosition;
    }

    public void setYPos(int yPos) {
        this.yPosition = yPos;
    }

    public int getYPos() {
        return this.yPosition;
    }
}