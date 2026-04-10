package sk.spse.uloha5.procedural;

import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Optional;

// Definícia záznamu Jedlo
record Jedlo(Integer id, String nazov, Integer kalorie, Double cena) {}

public class Application extends javafx.application.Application {

    // Zoznam dát, ktorý zdieľame medzi tabuľkou a tlačidlami
    private final ObservableList<Jedlo> jedloList = FXCollections.observableArrayList(
            new Jedlo(0, "Chlieb", 200, 2.0),
            new Jedlo(1, "Mlieko", 300, 0.65),
            new Jedlo(2, "Kebab", 500, 12.5),
            new Jedlo(3, "Coca Cola", 30, 1.39),
            new Jedlo(4, "Jablko", 50, 0.99)
    );

    @Override
    public void start(Stage stage) {
        // 1. Hlavný kontajner BorderPane (podľa zadania)
        BorderPane root = new BorderPane();

        // 2. Nadpis
        Label titleLabel = new Label("Zoznam potravín");
        titleLabel.setFont(new Font("System Bold", 24));
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        BorderPane.setMargin(titleLabel, new Insets(15));
        root.setTop(titleLabel);

        // 3. Tabuľka a jej stĺpce
        TableView<Jedlo> tabulka = new TableView<>();

        TableColumn<Jedlo, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().id()).asObject());

        TableColumn<Jedlo, String> colNazov = new TableColumn<>("Názov");
        colNazov.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().nazov()));

        TableColumn<Jedlo, Integer> colKalorie = new TableColumn<>("Kalórie");
        colKalorie.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().kalorie()).asObject());

        TableColumn<Jedlo, Double> colCena = new TableColumn<>("Cena");
        colCena.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().cena()).asObject());

        tabulka.getColumns().addAll(colId, colNazov, colKalorie, colCena);
        tabulka.setItems(jedloList);

        // Automatická šírka podľa zadania
        tabulka.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        BorderPane.setMargin(tabulka, new Insets(0, 20, 0, 20));
        root.setCenter(tabulka);

        // 4. Tlačidlá
        Button btnPridaj = new Button("Pridaj potravinu");
        Button btnVymaz = new Button("Vymaž");
        Button btnZatvor = new Button("Zatvor");

        // Akcie pre tlačidlá
        btnZatvor.setOnAction(e -> Platform.exit());

        btnVymaz.setOnAction(e -> {
            Jedlo selected = tabulka.getSelectionModel().getSelectedItem();
            if (selected != null) {
                jedloList.remove(selected);
            }
        });

        btnPridaj.setOnAction(e -> otvorDialogNaPridanie());

        HBox buttonBox = new HBox(10, btnPridaj, btnVymaz, btnZatvor);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        buttonBox.setPadding(new Insets(15, 20, 15, 20));
        root.setBottom(buttonBox);

        // 5. Scéna a zobrazenie
        Scene scene = new Scene(root, 600, 450);
        stage.setTitle("Procedural Application 5");
        stage.setScene(scene);
        stage.show();
    }

    // Pomocná metóda pre vyskakovacie okno
    private void otvorDialogNaPridanie() {
        Dialog<Jedlo> dialog = new Dialog<>();
        dialog.setTitle("Nové jedlo");
        dialog.setHeaderText("Zadajte údaje pre novú potravinu:");

        ButtonType pridatBtnType = new ButtonType("Pridať", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(pridatBtnType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10); grid.setVgap(10);
        grid.setPadding(new Insets(20, 10, 10, 10));

        TextField txtNazov = new TextField();
        TextField txtKalorie = new TextField();
        TextField txtCena = new TextField();

        grid.add(new Label("Názov:"), 0, 0);
        grid.add(txtNazov, 1, 0);
        grid.add(new Label("Kalórie:"), 0, 1);
        grid.add(txtKalorie, 1, 1);
        grid.add(new Label("Cena:"), 0, 2);
        grid.add(txtCena, 1, 2);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(btn -> {
            if (btn == pridatBtnType) {
                try {
                    return new Jedlo(jedloList.size(), txtNazov.getText(),
                            Integer.parseInt(txtKalorie.getText()),
                            Double.parseDouble(txtCena.getText()));
                } catch (Exception ex) { return null; }
            }
            return null;
        });

        Optional<Jedlo> result = dialog.showAndWait();
        result.ifPresent(jedloList::add);
    }
}