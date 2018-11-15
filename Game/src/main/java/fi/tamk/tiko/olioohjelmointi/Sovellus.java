package fi.tamk.tiko.olioohjelmointi;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class Sovellus extends Application {

    @Override
    public void start(Stage stage) {
        stage.setHeight(480);
        stage.setWidth(640);
        stage.setOnCloseRequest(e -> Platform.exit());
        stage.show();

        Player testi = new Player();
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
    }
}