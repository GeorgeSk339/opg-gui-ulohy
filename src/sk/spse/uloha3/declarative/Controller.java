package sk.spse.uloha3.declarative;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.awt.Desktop;
import java.net.URI;

public class Controller {

    @FXML
    public ImageView obrazok;

    @FXML
    public Slider slider;

    @FXML
    public void handleClose(ActionEvent actionEvent) {
        Platform.exit();
    }

    @FXML
    public void rotate(MouseEvent mouseEvent) {
        obrazok.setRotate(slider.getValue());
    }

    @FXML
    public void openWeb(ActionEvent event) {
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI("http://www.spse-po.sk"));
            }
        } catch (Exception e) {
            System.err.println("Nepodarilo sa otvoriť prehliadač: " + e.getMessage());
        }
    }
}