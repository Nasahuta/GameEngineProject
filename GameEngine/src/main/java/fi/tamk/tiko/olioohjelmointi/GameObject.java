package fi.tamk.tiko.olioohjelmointi;

import javafx.scene.image.*;
import java.net.URL;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class GameObject {
    private float speed;

    private float xPosition;
    private float yPosition;
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
        speed = 50;
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
        speed = 50;
    }

    public Scene addToScreen() {
        Group root = new Group();
        root.getChildren().add(getPane());
        scene = new Scene(root);
        scene.setFill(Color.BLACK);

        return scene;
    }

    public void move(float deltaTime) {
        scene.setOnKeyPressed(e -> {
            e.consume();

            switch (e.getCode()) {
                case UP:    setYPos(getYPos() - speed * deltaTime); break;
                case RIGHT: setXPos(getXPos() + speed * deltaTime); break;
                case DOWN:  setYPos(getYPos() + speed * deltaTime); break;
                case LEFT:  setXPos(getXPos() - speed * deltaTime); break;
            }
        });

        update();
    }

    public void update() {
        viewer.setLayoutX(getXPos());
        viewer.setLayoutY(getYPos());
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
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

    public void setXPos(float xPos) {
        this.xPosition = xPos;
    }

    public float getXPos() {
        return this.xPosition;
    }

    public void setYPos(float yPos) {
        this.yPosition = yPos;
    }

    public float getYPos() {
        return this.yPosition;
    }
}