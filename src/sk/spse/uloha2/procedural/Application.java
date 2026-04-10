package sk.spse.uloha2.procedural;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage primaryStage) {
        // 1. Nastavenie GridPane (hlavný kontajner)
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        // 2. Vytvorenie prvkov
        Label lblMeno = new Label("Užívateľské meno:");
        TextField name = new TextField();
        name.setPromptText("zadaj meno");

        Label lblHeslo = new Label("Heslo:");
        TextField password = new TextField();
        password.setPromptText("zadaj heslo");

        Label lblPohlavie = new Label("Pohlavie:");

        // ToggleGroup a RadioButtony (pohlavieGroup je dôležité pre zadanie)
        ToggleGroup pohlavieGroup = new ToggleGroup();
        RadioButton male = new RadioButton("Muž");
        male.setToggleGroup(pohlavieGroup);
        male.setSelected(true); // Predvolený muž

        RadioButton female = new RadioButton("Žena");
        female.setToggleGroup(pohlavieGroup);

        HBox pohlavieBox = new HBox(10, male, female);
        pohlavieBox.setAlignment(Pos.CENTER_LEFT);

        // Separátor a Tlačidlá
        Separator separator = new Separator();

        Button register = new Button("Registrovať");
        Button close = new Button("Zavrieť");

        HBox buttonBox = new HBox(10, register, close);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);

        // 3. Uloženie prvkov do mriežky (stĺpec, riadok, [colspan, rowspan])
        grid.add(lblMeno, 0, 0);
        grid.add(name, 1, 0);

        grid.add(lblHeslo, 0, 1);
        grid.add(password, 1, 1);

        grid.add(lblPohlavie, 0, 2);
        grid.add(pohlavieBox, 1, 2);

        // Posledné 2 riadky majú spojené stĺpce (colspan = 2)
        grid.add(separator, 0, 3, 2, 1);
        grid.add(buttonBox, 0, 4, 2, 1);

        // 4. Logika (Event Handlery)

        // Ukončenie programu podľa zadania
        close.setOnAction(e -> Platform.exit());

        // Registrácia a Alert box
        register.setOnAction(e -> {
            // Získanie textu z vybraného RadioButtonu podľa zadania
            String vybranePohlavie = ((RadioButton)pohlavieGroup.getSelectedToggle()).getText();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registrácia užívateľa");
            alert.setHeaderText("Registrácia prebehla úspešne");
            alert.setContentText("Užívateľ " + name.getText() + " (" + vybranePohlavie +
                    ") s heslom " + password.getText() + " bol pridaný do systému");
            alert.showAndWait();
        });

        // 5. Finálne nastavenie okna
        Scene scene = new Scene(grid);
        primaryStage.setTitle("Registrácia");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}