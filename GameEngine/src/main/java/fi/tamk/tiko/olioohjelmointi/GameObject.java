package fi.tamk.tiko.olioohjelmointi;

import javafx.scene.image.*;
import java.net.URL;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

/**
 * Defines GameObject.
 * 
 * Uses javafx in a way that the user can use the pane generated easier.
 * 
 * @author  Joni Alanko <joni.alanko@cs.tamk.fi>
 * @since   2018.1812
 * @version 1.0
 */
@SuppressWarnings("all")
public class GameObject {

    private float xPosition;
    private float yPosition;
    private Image image;
    private ImageView viewer;
    private Pane pane;
    private Scene scene;
    private Rectangle rectangle;

    /**
     * Overrides default constructor for the game object.
     *
     * @param path String name of the image to be loaded.
     * @param xPosition float initial x position of the object.
     * @param yPosition float initial y position of the object.
     */
    public GameObject(String path, float xPosition, float yPosition) {
        image = new Image(getClass().getResourceAsStream(path));
        viewer = new ImageView(image);
        pane = new Pane();
        pane.getChildren().addAll(getImageView());
        this.yPosition = yPosition;
        this.xPosition = xPosition;
        setRectangle(xPosition,yPosition,(float)image.getWidth(),(float)image.getHeight());
    }

    /**
     * Overloads default constructor for the game object.
     *
     * @param path String name of the image to be loaded.
     * @param size double forces image to have its sides be this length.
     * @param xPosition float initial x position of the object.
     * @param yPosition float initial y position of the object.
     */
    public GameObject(String path, double size, float xPosition, float yPosition) {
        image = new Image(getClass().getResourceAsStream(path), size, size,true,true);
        viewer = new ImageView(image);
        pane = new Pane();
        pane.getChildren().addAll(getImageView());
        this.yPosition = yPosition;
        this.xPosition = xPosition;
        setRectangle(xPosition,yPosition,(float)image.getWidth(),(float)image.getHeight());
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

    /**
     * Sets the scene.
     * 
     * @param scene Scene to be set.
     */
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    /**
     * Gets the scene.
     *
     * @return scene.
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Sets the rectangle.
     *
     * @param rectangle Rectangle to be set.
     */
    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    /**
     * Sets the rectangle
     *
     * @param x float x position for rectangle.
     * @param y float y position for rectangle.
     * @param width float width for rectangle.
     * @param height float height for rectangle.
     */
    public void setRectangle(float x, float y, float width, float height) {
        setRectangle(new Rectangle(x,y,width,height));
    }

    /**
     * Gets the rectangle.
     *
     * @return rectangle.
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * Gets the pane.
     *
     * @return pane.
     */
    public Pane getPane() {
        return pane;
    }

    /**
     * gets the imageView.
     *
     * @return imageView.
     */
    public ImageView getImageView() {
        return viewer;
    }

    /**
     * Gets the image.
     *
     * @return image.
     */
    public Image getImage() {
        return image;
    }

    /**
     * Sets the x position.
     *
     * @param xPos float x position to be set.
     */
    public void setXPos(float xPos) {
        this.xPosition = xPos;
    }

    /**
     * Gets the x position.
     *
     * @return xPosition.
     */
    public float getXPos() {
        return this.xPosition;
    }

    /**
     * Sets the y position.
     *
     * @param yPos float y position to be set.
     */
    public void setYPos(float yPos) {
        this.yPosition = yPos;
    }

    /**
     * Gets the y position.
     *
     * @return yPosition.
     */
    public float getYPos() {
        return this.yPosition;
    }
}