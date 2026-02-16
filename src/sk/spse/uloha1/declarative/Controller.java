package sk.spse.uloha1.declarative;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * Controller pre FXML súbor – obsahuje logiku aplikácie
 */
public class Controller {

    public TextField fField;
    public TextField cField;
    private int counter = 0;

    @FXML
    private TextField counterField;

    @FXML
    private void incrementCounter() {
        counter++;
        counterField.setText(String.valueOf(counter));
    }
    @FXML
    public void convertCtoF() {
        try {
            double c = Double.parseDouble(cField.getText());
            double f = (c * 9 / 5) + 32;
            fField.setText(String.valueOf(f));
        } catch (NumberFormatException e) {
            fField.setText("Neplatné číslo");
        }
    }

    @FXML
    public void convertFtoC() {
        try {
            double f = Double.parseDouble(fField.getText());
            double c = (f - 32) * 5 / 9;
            cField.setText(String.valueOf(c));
        } catch (NumberFormatException e) {
            cField.setText("Neplatné číslo");
        }
    }
}

