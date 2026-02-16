package sk.spse.uloha1.procedural;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    // Premenné musia byť prístupné pre metódy convert
    private TextField fField;
    private TextField cField;

    @Override
    public void start(Stage stage) {
        // --- Prvý riadok (Celsius) ---
        Label labelC = new Label("Stupne Celsia:");
        cField = new TextField("0");
        Label lblCUnit = new Label("C");

        HBox rowC = new HBox(20, labelC, cField, lblCUnit);
        rowC.setAlignment(Pos.CENTER_RIGHT);
        rowC.setPadding(new Insets(10));

        // --- Druhý riadok (Fahrenheit) ---
        Label labelF = new Label("Stupne Fahrenheita:");
        fField = new TextField("32");
        Label lblFUnit = new Label("F");

        HBox rowF = new HBox(20, labelF, fField, lblFUnit);
        rowF.setAlignment(Pos.CENTER_RIGHT);
        rowF.setPadding(new Insets(10));

        // --- Hlavný kontajner (VBox) ---
        VBox root = new VBox(0, rowC, rowF);

        // Nastavenie eventov
        cField.setOnKeyTyped(e -> convertCtoF());
        fField.setOnKeyTyped(e -> convertFtoC());

        Scene scene = new Scene(root, 420, 150);
        stage.setTitle("Procedural Application 1");
        stage.setScene(scene);
        stage.show();
    }

    // Metódy musia byť tu (mimo start)
    public void convertCtoF() {
        try {
            String input = cField.getText().replace(',', '.');
            if (input.isEmpty() || input.equals("-")) return;

            double c = Double.parseDouble(input);
            double f = (c * 1.8) + 32;
            fField.setText(String.format("%.2f", f));
        } catch (NumberFormatException e) {
            // fField.setText(""); // Voliteľné: vymazať ak je chyba
        }
    }

    public void convertFtoC() {
        try {
            String input = fField.getText().replace(',', '.');
            if (input.isEmpty() || input.equals("-")) return;

            double f = Double.parseDouble(input);
            double c = (f - 32) / 1.8;
            cField.setText(String.format("%.2f", c));
        } catch (NumberFormatException e) {
            // cField.setText("");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}