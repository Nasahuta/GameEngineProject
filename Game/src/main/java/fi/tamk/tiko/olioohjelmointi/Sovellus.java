package fi.tamk.tiko.olioohjelmointi;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.animation.AnimationTimer;
import java.util.*;

public class Sovellus extends Application {
    private final int KEYBOARD_MOVEMENT_DELTA = 5;

    @Override
    public void start(Stage stage) {
        ImageView viewer = new ImageView();
        Player testi = new Player();
        Pane pane = new Pane();

        viewer.setImage(testi.getImage());
        viewer.setFitWidth(200);
        viewer.setPreserveRatio(true);
        viewer.setSmooth(true);

        Group root = new Group();
        Scene scene = new Scene(root);
        scene.setFill(Color.BLACK);
        //HBox box = new HBox();
        //box.getChildren().add(viewer);
        pane.getChildren().addAll(viewer);
        root.getChildren().add(pane);

        stage.setHeight(480);
        stage.setWidth(640);
        //move(scene, testi);
        stage.setScene(scene);
        //stage.sizeToScene();
        stage.setOnCloseRequest(e -> Platform.exit());
        stage.show();
        //viewer.setX(testi.getXPos() + 50);
        //viewer.setLayoutX(viewer.getLayoutX() + 10);

        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                move(scene, testi);
                //viewer.setLayoutX(viewer.getLayoutX() + 10);
                viewer.setLayoutX(testi.getXPos());
                viewer.setLayoutY(testi.getYPos());
                //viewer.setX(viewer.getX() + 5);
            }
        }.start();
    }

    public void move(Scene scene, Player testi) {
        scene.setOnKeyPressed(e -> {
            e.consume();

            switch (e.getCode()) {
                case UP:    testi.setYPos(testi.getYPos() - KEYBOARD_MOVEMENT_DELTA);
                    //System.out.println("Pressed up maddafakka");
                    break;
                case RIGHT: testi.setXPos(testi.getXPos() + KEYBOARD_MOVEMENT_DELTA); break;
                case DOWN:  testi.setYPos(testi.getYPos() + KEYBOARD_MOVEMENT_DELTA); break;
                case LEFT:  testi.setXPos(testi.getXPos() - KEYBOARD_MOVEMENT_DELTA); break;
            }
        });
    }

    public static void main(String [] args) {
        System.out.println("Author: Joni Alanko");

        launch(args);
    }
}

class Player {
    private int xPosition;
    private int yPosition;
    private Image model;

    public Player() {
        model = new Image("file:resources/test.jpg");
        yPosition = 0;
        xPosition = 0;
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