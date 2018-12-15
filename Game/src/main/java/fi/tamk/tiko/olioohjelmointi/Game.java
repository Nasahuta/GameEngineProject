package fi.tamk.tiko.olioohjelmointi;

import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.*;

import fi.tamk.tiko.olioohjelmointi.*;

@SuppressWarnings("all")
public class Game extends GameEngine {
    private Player player;
    private Enemy enemy;
    private Scene scene;

    @Override
    public void start(Stage stage) {
        player = new Player("test.jpg");
        enemy = new Enemy("ball.png", 25d);

        Group root = new Group();
        root.getChildren().addAll(player.getPane(), enemy.getPane());
        scene = initializeScene(new Scene(root));
        enemy.setScene(scene);
        player.setScene(scene);

        stage.setHeight(480);
        stage.setWidth(640);
        stage.setScene(scene);
        stage.setOnCloseRequest(e -> Platform.exit());
        stage.show();
    }

    @Override
    public void render(float deltaTime) {
        player.move(deltaTime);
        enemy.move(deltaTime);
        if (player.collision(enemy.getRectangle())) {
            System.out.println("COLLISION");
        }
    }

    public static void main(String [] args) {
        System.out.println("Author: Joni Alanko");

        launch(args);
    }
}
