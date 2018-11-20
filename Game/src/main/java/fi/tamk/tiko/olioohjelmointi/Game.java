package fi.tamk.tiko.olioohjelmointi;

import javafx.application.Platform;
import javafx.stage.Stage;

import fi.tamk.tiko.olioohjelmointi.*;

public class Game extends GameEngine {
    private Player player;

    @Override
    public void start(Stage stage) {
        player = new Player("test.jpg");

        stage.setHeight(480);
        stage.setWidth(640);
        stage.setScene(player.addToScreen());
        stage.setOnCloseRequest(e -> Platform.exit());
        stage.show();
    }

    @Override
    public void render() {
        player.move();
    }

    public static void main(String [] args) {
        System.out.println("Author: Joni Alanko");

        launch(args);
    }
}
