package sk.spse.uloha5.declarative;

import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

record Jedlo(Integer id, String nazov, Integer kalorie, Double cena) {}

public class Controller implements Initializable {

    @FXML private TableView<Jedlo> tabulka;
    @FXML private TableColumn<Jedlo, Integer> id;
    @FXML private TableColumn<Jedlo, String> nazovJedal;
    @FXML private TableColumn<Jedlo, Integer> kalorie;
    @FXML private TableColumn<Jedlo, Double> cena;

    private final ObservableList<Jedlo> jedloList = FXCollections.observableArrayList(
            new Jedlo(0, "Chlieb", 200, 2.0),
            new Jedlo(1, "Mlieko", 300, 0.65),
            new Jedlo(2, "Kebab", 500, 12.5),
            new Jedlo(3, "Coca Cola", 30, 1.39),
            new Jedlo(4, "Jablko", 50, 0.99)
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().id()).asObject());
        nazovJedal.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().nazov()));
        kalorie.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().kalorie()).asObject());
        cena.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().cena()).asObject());

        tabulka.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        tabulka.setItems(jedloList);
    }

    @FXML
    public void pridajPotravinu() {
        // 1. Vytvoríme vlastný dialóg
        Dialog<Jedlo> dialog = new Dialog<>();
        dialog.setTitle("Pridať novú potravinu");
        dialog.setHeaderText("Zadajte údaje pre nové jedlo:");

        // Nastavenie tlačidiel (OK a Zrušiť)
        ButtonType loginButtonType = new ButtonType("Pridať", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        // 2. Vytvoríme políčka pre zadávanie údajov
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField nazovInput = new TextField();
        nazovInput.setPromptText("Názov");
        TextField kalorieInput = new TextField();
        kalorieInput.setPromptText("Kalórie (celé číslo)");
        TextField cenaInput = new TextField();
        cenaInput.setPromptText("Cena (napr. 2.50)");

        grid.add(new Label("Názov:"), 0, 0);
        grid.add(nazovInput, 1, 0);
        grid.add(new Label("Kalórie:"), 0, 1);
        grid.add(kalorieInput, 1, 1);
        grid.add(new Label("Cena:"), 0, 2);
        grid.add(cenaInput, 1, 2);

        dialog.getDialogPane().setContent(grid);

        // 3. Konverzia výsledku po kliknutí na "Pridat"
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                try {
                    // Prečítame hodnoty a vytvoríme nový record Jedlo
                    return new Jedlo(
                            jedloList.size(),
                            nazovInput.getText(),
                            Integer.parseInt(kalorieInput.getText()),
                            Double.parseDouble(cenaInput.getText())
                    );
                } catch (NumberFormatException e) {
                    // Ak používateľ zadá blbosti (písmená namiesto čísel), vráti null
                    return null;
                }
            }
            return null;
        });

        // 4. Zobrazenie dialógu a pridanie výsledku do zoznamu
        Optional<Jedlo> result = dialog.showAndWait();
        result.ifPresent(jedloList::add);
    }

    @FXML
    public void vymaz() {
        Jedlo selected = tabulka.getSelectionModel().getSelectedItem();
        if (selected != null) {
            jedloList.remove(selected);
        }
    }

    @FXML
    public void zatvor() {
        Platform.exit();
    }
}