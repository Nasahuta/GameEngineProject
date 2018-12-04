package fi.tamk.tiko.olioohjelmointi;

import javafx.scene.image.*;
import java.net.URL;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class GameObject {
    private float speed;

    private float xPosition;
    private float yPosition;
    private Image model;
    private ImageView viewer;
    private Pane pane;
    private Scene scene;
    private Rectangle rectangle;

    public GameObject(String path) {
        model = new Image(getClass().getResourceAsStream(path), 130d, 250d,true,false);
        viewer = new ImageView(model);
        pane = new Pane();
        pane.getChildren().addAll(getImageView());
        yPosition = 0;
        xPosition = 0;
        speed = 50;
        setRectangle(xPosition,yPosition,(float)model.getWidth(),(float)model.getHeight());
    }

    public GameObject(String path, double size) {
        model = new Image(getClass().getResourceAsStream(path), size, size,true,true);
        viewer = new ImageView(model);
        pane = new Pane();
        pane.getChildren().addAll(getImageView());
        yPosition = 0;
        xPosition = 0;
        speed = 50;
        setRectangle(xPosition,yPosition,(float)model.getWidth(),(float)model.getHeight());
    }

    /**
     * Checks for collision between rectangles.
     *
     * @param b is the one to be compared to.
     * @return boolean true if collision is detected.
     */
    public boolean collision(Rectangle b) {
        return rectangle.getBoundsInLocal().intersects(b.getBoundsInLocal());
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

    /**
     * Updates position.
     *
     * Updates image and rectangle position.
     */
    public void update() {
        viewer.setLayoutX(getXPos());
        viewer.setLayoutY(getYPos());
        
        if (rectangle != null) {
            rectangle.setX(getXPos());
            rectangle.setY(getYPos());
        }
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return this.speed;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Scene getScene() {
        return scene;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public void setRectangle(float x, float y, float width, float height) {
        setRectangle(new Rectangle(x,y,width,height));
    }

    public Rectangle getRectangle() {
        return rectangle;
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