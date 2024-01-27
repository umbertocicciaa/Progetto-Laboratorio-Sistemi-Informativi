package ui.acquisti.prodotto;

import data.Prodotto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.hibernate.HibernateException;
import ui.UIUtil;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static logic.BusinessFacade.getGestioneAcquisti;
import static ui.UIUtil.messaggioParametriScorretti;
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
        if (item != null) {
            switch (item.getValue()) {
                case "Fornitore" -> {
                    try {
                        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ui/acquisti/fornitori.fxml")));
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                case "Automezzo Da Ordinare" -> {
                    // Handle Automezzo Da Ordinare case
                    System.out.println("Handling Automezzo Da Ordinare case");
                }
                case "Ordine" -> {
                    // Handle Ordine case
                    System.out.println("Handling Ordine case");
                }
                case "Preventivo" -> {
                    // Handle Preventivo case
                    System.out.println("Handling Preventivo case");
                }
                default -> {
                }
            }
        }
    }

    public void searchProdotto(ActionEvent actionEvent) {
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
                        exception.printStackTrace();
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
                        exception.printStackTrace();
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

    public void addAction(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ui/prodotto/insertProdotto.fxml")));

        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setTitle("Insert Prodotto");

        Scene scene = new Scene(root);
        dialogStage.setScene(scene);

        dialogStage.showAndWait();
        refreshTable();
    }

    private void refreshTable() {
        try {
            prodottoTableView.clear();
            List<Prodotto> resultSet = getGestioneAcquisti().getProdotti();
            prodottoTableView.addAll(resultSet);
            tableView.setItems(prodottoTableView);
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }

    public void updateProdotto(ActionEvent actionEvent) throws IOException {
        if (selectedProdotto != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/prodotto/updateProdotto.fxml"));
            Parent root = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setTitle("Update Prodotto");

            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            UpdateProdottoController controller = loader.getController();
            controller.initProdotto(selectedProdotto.getCodProdotto());

            dialogStage.showAndWait();
            refreshTable();
            selectedProdotto = null;
            refreshTable();
        }
    }

    public void deleteProdotto(ActionEvent actionEvent) {
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
