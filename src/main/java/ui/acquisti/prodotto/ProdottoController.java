package ui.acquisti.prodotto;

import data.Prodotto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ui.UIUtil;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static logic.BusinessFacade.getGestioneAcquisti;
import static ui.UIUtil.*;
import static ui.Util.stringheVerificate;

public class ProdottoController implements Initializable {

    @FXML
    private ChoiceBox<String> choiceItem;
    @FXML
    private TableView<Prodotto> tableView;
    @FXML
    private TableColumn<Prodotto, String> codice;
    @FXML
    private TableColumn<Prodotto, String> tipo;
    @FXML
    private TableColumn<Prodotto, String> quantita;
    @FXML
    private TextField ricercaProdotto;
    @FXML
    private TreeView<String> homeTreeView;

    private Prodotto selectedProdotto;
    private String criterio;
    private final ObservableList<Prodotto> prodottoTableView = FXCollections.observableArrayList();

    public void selectItem(MouseEvent event) {
        TreeItem<String> item = homeTreeView.getSelectionModel().getSelectedItem();
        UIUtil.loadSelectedItem(item, event);
    }

    public void searchProdotto() {
        String text = ricercaProdotto.getText();
        if (!stringheVerificate(text)) {
            messaggioParametriScorretti();
            return;
        }
        if (criterio != null) {
            switch (criterio) {
                case "Codice" -> {
                    try {
                        int codice = Integer.parseInt(text);
                        System.out.println(codice);
                        prodottoTableView.clear();
                        prodottoTableView.addAll(getGestioneAcquisti().prodottiDiUnCodice(codice));
                        tableView.refresh();
                    } catch (NumberFormatException exception) {
                        messaggioParametriScorretti();
                    }
                }
                case "Tipo" -> {
                    prodottoTableView.clear();
                    prodottoTableView.addAll(getGestioneAcquisti().prodottiDiUnTipo(text));
                    tableView.refresh();
                }
                case "Quantita Necessaria" -> {
                    try {
                        int quantita = Integer.parseInt(text);
                        prodottoTableView.clear();
                        prodottoTableView.addAll(getGestioneAcquisti().prodottiDiUnaNecessita(quantita));
                        tableView.refresh();
                    } catch (NumberFormatException exception) {
                        messaggioParametriScorretti();
                    }
                }
                case "Tutti" -> {
                    prodottoTableView.clear();
                    prodottoTableView.addAll(getGestioneAcquisti().getProdotti());
                    tableView.refresh();
                }
            }
        }
    }

    public void addAction() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ui/prodotto/insertProdotto.fxml")));

            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setTitle("Insert Prodotto");
            dialogStage.setResizable(false);

            Scene scene = new Scene(root);
            dialogStage.setScene(scene);

            dialogStage.showAndWait();
            refreshTable();
        } catch (IOException e) {
            messaggioErroreCaricamentoFinestra();
        }
    }

    private void refreshTable() {
        prodottoTableView.clear();
        List<Prodotto> resultSet = getGestioneAcquisti().getProdotti();
        prodottoTableView.addAll(resultSet);
        tableView.setItems(prodottoTableView);
    }

    public void updateProdotto() {
        try {
            if (selectedProdotto != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/prodotto/updateProdotto.fxml"));
                Stage stage = loadUiUpdate(loader, "Update Prodotto");

                UpdateProdottoController controller = loader.getController();
                controller.initProdotto(selectedProdotto.getCodProdotto());

                stage.showAndWait();

                selectedProdotto = null;
                refreshTable();
            }
        } catch (IOException e) {
            messaggioErroreCaricamentoFinestra();
        }
    }


    public void deleteProdotto() {
        if (selectedProdotto != null) {
            getGestioneAcquisti().removeProdotto(selectedProdotto.getCodProdotto());
            selectedProdotto = null;
            refreshTable();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UIUtil.inizializzaFinestra(homeTreeView);
        choiceItem.getItems().addAll("Codice", "Tipo", "Quantita Necessaria", "Tutti");
        choiceItem.setOnAction(this::getSelectedCriterio);
        loadDate();
        tableView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                Prodotto prodotto = tableView.getSelectionModel().getSelectedItem();
                if (prodotto != null) {
                    selectedProdotto = prodotto;
                }
            }
        });
    }

    private void loadDate() {
        refreshTable();
        codice.setCellValueFactory(new PropertyValueFactory<>("CodProdotto"));
        tipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        quantita.setCellValueFactory(new PropertyValueFactory<>("quantitaNecessaria"));
        tableView.setItems(prodottoTableView);
    }

    private void getSelectedCriterio(ActionEvent actionEvent) {
        criterio = choiceItem.getValue();
        if (criterio.equals("Tutti")) {
            prodottoTableView.clear();
            prodottoTableView.addAll(getGestioneAcquisti().getProdotti());
            tableView.refresh();
        }
    }
}
