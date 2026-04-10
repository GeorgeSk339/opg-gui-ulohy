package sk.spse.uloha4.declarative;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import java.util.Random;

public class Controller {

    @FXML
    private VBox vbox1, vbox2, vbox3, vbox4;

    @FXML
    private ImageView img1, img2, img3, img4;

    private final Random random = new Random();

    @FXML
    private void randomize() {
        vbox1.setStyle("-fx-background-color: " + getRandomColor() + "; -fx-opacity: " + getRandomOpaque());
        vbox2.setStyle("-fx-background-color: " + getRandomColor() + "; -fx-opacity: " + getRandomOpaque());
        vbox3.setStyle("-fx-background-color: " + getRandomColor() + "; -fx-opacity: " + getRandomOpaque());
        vbox4.setStyle("-fx-background-color: " + getRandomColor() + "; -fx-opacity: " + getRandomOpaque());

        img1.setStyle("-fx-scale-x: " + getRandomScale() + "; -fx-scale-y: " + getRandomScale() + "; -fx-rotate: " + getRandomRotate());
        img2.setStyle("-fx-scale-x: " + getRandomScale() + "; -fx-scale-y: " + getRandomScale() + "; -fx-rotate: " + getRandomRotate());
        img3.setStyle("-fx-scale-x: " + getRandomScale() + "; -fx-scale-y: " + getRandomScale() + "; -fx-rotate: " + getRandomRotate());
        img4.setStyle("-fx-scale-x: " + getRandomScale() + "; -fx-scale-y: " + getRandomScale() + "; -fx-rotate: " + getRandomRotate());
    }

    @FXML
    private void close() {
        Platform.exit();
    }

    private String getRandomColor() {
        return String.format("#%02x%02x%02x", random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    private double getRandomOpaque() {
        return 0.3 + (0.7 * random.nextDouble());
    }

    private int getRandomRotate() {
        return random.nextInt(360);
    }

    private double getRandomScale() {
        return 0.4 + (0.8 * random.nextDouble());
    }
}