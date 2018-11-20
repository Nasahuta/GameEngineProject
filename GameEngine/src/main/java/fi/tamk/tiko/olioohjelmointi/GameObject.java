package fi.tamk.tiko.olioohjelmointi;

import javafx.scene.image.Image;
import java.net.URL;

public class GameObject {
    private int xPosition;
    private int yPosition;
    private Image model;

    public GameObject(String path) {
        System.out.println(getClass().getSimpleName());
        System.out.println(getClass().getResourceAsStream(path));
        model = new Image(getClass().getResourceAsStream(path));
        yPosition = 0;
        xPosition = 0;
    }

    public String getResource(String resourceFile) {
        System.out.println("PERKELE");
        ClassLoader loader = getClass().getClassLoader();
        URL resource = loader.getResource(resourceFile);
        return resource != null ? resource.getPath() : "PERKELE";
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