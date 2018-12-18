package fi.tamk.tiko.olioohjelmointi;

import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.layout.*;
import java.io.FileWriter;
import java.io.File;
import java.nio.file.*;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.stage.StageStyle;
import javafx.scene.text.Text;

import fi.tamk.tiko.olioohjelmointi.*;

/**
 * Defines the Game.
 * 
 * @author  Joni Alanko <joni.alanko@cs.tamk.fi>
 * @since   2018.1812
 * @version 1.0
 */
@SuppressWarnings("all")
public class Game extends GameEngine {
    private Player player;
    private Enemy enemy;
    /**
     * Main scene of the game.
     */
    private Scene scene;
    /**
     * Background as a gameObject
     */
    private Background background;

    /**
     * Used to check if game is paused.
     */
    private boolean pause = false;
    /**
     * Used to see how long game has been paused.
     */
    private float pauseTimer = 0;
    /**
     * Used to see how long game has been on while not paused. Used for high score.
     */
    private float gameTimer = 0;
    /**
     * Used for previous high score
     */
    private float previousHighScore = 0;
    /**
     * Used if highscore is achieved. If previous high score is better, this is set to false.
     */
    private boolean highScore = true;
    /**
     * Used for render handling to make sure the game over screen is opened only once.
     */
    private boolean showMenu = false;

    /**
     * Overrides start.
     * 
     * Used to create and initialize window and scene for the game.
     * 
     * @param stage Stage is the main window of the game.
     */
    @Override
    public void start(Stage stage) {
        player = new Player("test.png");
        enemy = new Enemy("ball.png", 25d);
        background = new Background("background.jpg");

        Pane root = new Pane();
        root.getChildren().addAll(background.getPane(), player.getPane(), enemy.getPane());
        scene = initializeScene(new Scene(root));
        enemy.setScene(scene);
        player.setScene(scene);
        background.setScene(scene);

        stage.setHeight(480);
        stage.setWidth(640);
        stage.setScene(scene);
        stage.setOnCloseRequest(e -> Platform.exit());
        stage.show();
    }

    /**
     * Overrides render.
     * 
     * @param deltaTime indicates how many seconds passed from last render() call.
     */
    @Override
    public void render(float deltaTime) {
        if (pause) {
            pauseTimer += 1 * deltaTime;
            deltaTime = 0;
        } else {
            gameTimer += 1 * deltaTime;
        }

        player.move(deltaTime);
        enemy.move(deltaTime, background);
        background.move(deltaTime);

        if (player.collision(enemy.getRectangle()) && !pause) {
            System.out.printf("COLLISION in time of: %.2f seconds!%n", gameTimer);
            pauseGame();
        } else if (player.collision(enemy.getRectangle()) && pauseTimer > 0.1 && !showMenu) {
            fileHandler();
            menu();
        }
    }

    /**
     * Handles file manipulation.
     * 
     * Creates HighScore.txt if it doesn't exist in location and records the highscore if necessary.
     */
    public void fileHandler() {
        File highScoreData = new File("HighScore.txt");

        try  {
            if (highScoreData.isFile()) {
                 Path path = Paths.get(highScoreData.getPath());

                try {
                    Files.readAllLines(path).stream().forEach((String line) -> {
                        if(!line.isEmpty()) {
                            if (Float.parseFloat(line) < gameTimer) {
                                previousHighScore = gameTimer - Float.parseFloat(line);
                                System.out.printf("You beat the highscore by %.2f seconds!%n", previousHighScore);
                            } else {
                                highScore = false;
                            }
                        }
                    });
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        } catch(Exception e) {
            System.out.println(e);
        }
        
        if (highScore) {
            try (FileWriter fw = new FileWriter(highScoreData)) {
                fw.write("" + gameTimer);
                fw.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Creates Game Over screen.
     * 
     * Displays game time and tells if highscore was made.
     */
    public void menu() {
        showMenu = true;

        Button btn = new Button();
        btn.setText("Exit");
        btn.setOnAction(e -> Platform.exit());
        BorderPane root = new BorderPane();
        BorderPane bottom = new BorderPane();
        BorderPane top = new BorderPane();
        Text text = new Text(10, 40, "Game Over!");
        Text text2;

        if (highScore) {
            text2 = new Text(10, 40, "HighScore! " + gameTimer + " seconds");
        } else {
            text2 = new Text(10, 40, "Your time was " + gameTimer + " seconds");
        }

        root.setCenter(new Group(text2));
        top.setCenter(new Group(text));
        bottom.setCenter(btn);
        root.setBottom(bottom);
        root.setTop(top);

        Scene scene = new Scene(root, 200, 150); 
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        
        stage.setScene(scene);
        stage.setTitle("Game Over"); 
        stage.setOnCloseRequest(e -> Platform.exit());
        stage.show();
    }

    /**
     * Pauses the game.
     */
    public void pauseGame() {
        pause = true;
    }

    /**
     * Continues the game.
     */
    public void continueGame() {
        pause = false;
    }

    /**
     * Launches the game
     * 
     * @param args command line arguments. Fed to javafx launch() method.
     */
    public static void main(String [] args) {
        System.out.println("Author: Joni Alanko");

        launch(args);
    }
}
