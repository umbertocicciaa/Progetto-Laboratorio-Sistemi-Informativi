package ui.acquisti.automezzo;

import data.Automezzo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ui.UIUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static logic.BusinessFacade.getGestioneAcquisti;
import static ui.UIUtil.*;
import static ui.Util.stringheVerificate;

public class AutomezzoController implements Initializable {

    @FXML
    private TreeView<String> homeTreeView;
    @FXML
    private TextField ricercaAutomezzo;
    @FXML
    private ChoiceBox<String> choiceItem;
    @FXML
    private TableView<Automezzo> tableView;
    @FXML
    private TableColumn<Automezzo, String> targa;
    @FXML
    private TableColumn<Automezzo, String> marca;
    @FXML
    private TableColumn<Automezzo, String> assicurazione;
    @FXML
    private TableColumn<Automezzo, String> prezzo;

    private Automezzo selectedAutomezzo;

    private String criterio;

    private final ObservableList<Automezzo> automezzoTableView = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UIUtil.inizializzaFinestra(homeTreeView);
        choiceItem.getItems().addAll("Targa", "Assicurazione", "Prezzo", "Marca", "Tutti");
        choiceItem.setOnAction(this::getSelectedCriterio);
        loadDate();
        tableView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                Automezzo automezzo = tableView.getSelectionModel().getSelectedItem();
                if (automezzo != null) {
                    selectedAutomezzo = automezzo;
                }
            }
        });
    }

    private void loadDate() {
        refreshTable();
        loadAutomezzoTable(targa, marca, assicurazione, prezzo, tableView, automezzoTableView);
    }


    private void refreshTable() {

        automezzoTableView.clear();
        List<Automezzo> resultSet = getGestioneAcquisti().getAutomezzi();
        automezzoTableView.addAll(resultSet);
        tableView.setItems(automezzoTableView);

    }

    private void getSelectedCriterio(ActionEvent actionEvent) {
        criterio = choiceItem.getValue();
        if (criterio.equals("Tutti")) {
            automezzoTableView.clear();
            automezzoTableView.addAll(getGestioneAcquisti().getAutomezzi());
            tableView.refresh();
        }
    }

    public void updateAutomezzo() throws IOException {
        if (selectedAutomezzo != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/automezzo/updateAutomezzo.fxml"));
            Parent root = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setTitle("Update Automezzo");

            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            UpdateAutomezzoController controller = loader.getController();
            controller.initAutomezzo(selectedAutomezzo.getTarga());

            dialogStage.showAndWait();
            selectedAutomezzo = null;
            refreshTable();
        }
    }

    public void deleteAutomezzo() {
        if (selectedAutomezzo != null) {
            getGestioneAcquisti().removeAutomezzo(selectedAutomezzo.getTarga());
            selectedAutomezzo = null;
            refreshTable();
        }
    }

    public void addAction() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ui/automezzo/insertAutomezzo.fxml")));

        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setTitle("Insert Prodotto");

        Scene scene = new Scene(root, 300, 150);
        dialogStage.setScene(scene);

        dialogStage.showAndWait();
        refreshTable();
    }

    public void selectItem(MouseEvent event) {
        TreeItem<String> item = homeTreeView.getSelectionModel().getSelectedItem();
        UIUtil.loadSelectedItem(item, event);
    }

    public void searchAutomezzo() {
        String text = ricercaAutomezzo.getText();
        if (!stringheVerificate(text)) {
            messaggioParametriScorretti();
            return;
        }
        if (criterio != null) {
            switch (criterio) {
                case "Targa" -> {
                    automezzoTableView.clear();
                    automezzoTableView.addAll(getGestioneAcquisti().automezziDiUnaTarga(text));
                    tableView.refresh();
                }
                case "Assicurazione" -> {
                    automezzoTableView.clear();
                    automezzoTableView.addAll(getGestioneAcquisti().automezziDiUnaAssicurazione(text));
                    tableView.refresh();
                }
                case "Prezzo" -> {
                    try {
                        double prezzo = Double.parseDouble(text);
                        automezzoTableView.clear();
                        automezzoTableView.addAll(getGestioneAcquisti().automezziDiUnPrezzo(BigDecimal.valueOf(prezzo)));
                        tableView.refresh();
                    } catch (NumberFormatException exception) {
                        messaggioErroreInserimentoNumero();
                    }
                }
                case "Marca" -> {
                    automezzoTableView.clear();
                    automezzoTableView.addAll(getGestioneAcquisti().automezziDiUnaMarca(text));
                    tableView.refresh();
                }
                case "Tutti" -> {
                    automezzoTableView.clear();
                    automezzoTableView.addAll(getGestioneAcquisti().getAutomezzi());
                    tableView.refresh();
                }
            }
        }
    }
}
