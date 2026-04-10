package sk.spse.uloha2.declarative;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {

    @FXML
    private TextField name;

    @FXML
    private TextField password;

    @FXML private ToggleGroup pohlavieGroup;

    @FXML
    public void handleRegister() {
        String uzivatel = name.getText();
        String heslo = password.getText();

        String vybranePohlavie = ((RadioButton)pohlavieGroup.getSelectedToggle()).getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registrácia užívateľa");
        alert.setHeaderText("Registrácia prebehla úspešne");
        alert.setContentText("Užívateľ " + uzivatel + " (" + vybranePohlavie + ") s heslom " + heslo + " bol pridaný do systému");
        alert.showAndWait();
    }

    @FXML
    public void handleClose() {
        Platform.exit();
    }
}