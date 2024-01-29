package ui.acquisti.ordine;

import data.Ordine;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static logic.BusinessFacade.getGestioneAcquisti;
import static ui.UIUtil.messaggioParametriScorretti;
import static ui.Util.stringheVerificate;

public class OrdineController implements Initializable {
    @FXML
    private TreeView<String> homeTreeView;
    @FXML
    private TextField ricercaOrdine;
    @FXML
    private Button addButton;
    @FXML
    private ChoiceBox<String> choiceItem;
    @FXML
    private TableView<Ordine> tableView;
    @FXML
    private TableColumn<Ordine, String> numero;
    @FXML
    private TableColumn<Ordine, String> stato;
    @FXML
    private TableColumn<Ordine, String> data;
    @FXML
    private TableColumn<Ordine, String> quantita;
    @FXML
    private TableColumn<Ordine, String> prodotto;
    @FXML
    private TableColumn<Ordine, String> automezzo;

    private Ordine selectedOrdine;
    private String criterio;
    private final ObservableList<Ordine> ordineTableView = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UIUtil.inizializzaFinestra(homeTreeView);
        choiceItem.getItems().addAll("Numero", "Stato", "Data", "Quantita", "Tutti");
        choiceItem.setOnAction(this::getSelectedCriterio);
        loadDate();
        tableView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                Ordine ordine = tableView.getSelectionModel().getSelectedItem();
                if (ordine != null) {
                    selectedOrdine = ordine;
                }
            }
        });
    }

    private void loadDate() {
        refreshTable();
        numero.setCellValueFactory(new PropertyValueFactory<>("Numero"));
        stato.setCellValueFactory(new PropertyValueFactory<>("Stato"));
        data.setCellValueFactory(new PropertyValueFactory<>("Data"));
        quantita.setCellValueFactory(new PropertyValueFactory<>("Quantita"));
        prodotto.setCellValueFactory(new PropertyValueFactory<>("ProdottoDaOrdinare"));
        automezzo.setCellValueFactory(new PropertyValueFactory<>("AutomezzoDaOrdinare"));
        tableView.setItems(ordineTableView);
    }

    private void refreshTable() {
        try {
            ordineTableView.clear();
            List<Ordine> resultSet = getGestioneAcquisti().getOrdine();
            ordineTableView.addAll(resultSet);
            tableView.setItems(ordineTableView);
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }

    private void getSelectedCriterio(ActionEvent actionEvent) {
        criterio = choiceItem.getValue();
        if (criterio.equals("Tutti")) {
            ordineTableView.clear();
            ordineTableView.addAll(getGestioneAcquisti().getOrdine());
            tableView.refresh();
        }
    }

    public void updateOrdine(ActionEvent actionEvent) throws IOException {
        if (selectedOrdine != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/ordine/updateOrdine.fxml"));
            Parent root = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setTitle("Update Prodotto");

            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            UpdateOrdineController controller = loader.getController();
            controller.initOrdine(selectedOrdine.getNumero());

            dialogStage.showAndWait();
            refreshTable();
            selectedOrdine = null;
            refreshTable();
        }
    }

    public void deleteOrdine(ActionEvent actionEvent) {
        if (selectedOrdine != null) {
            getGestioneAcquisti().removeOrdine(selectedOrdine.getNumero());
            selectedOrdine = null;
            refreshTable();
        }
    }

    public void addAction(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ui/ordine/insertOrdine.fxml")));

            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setTitle("Insert Ordine");

            Scene scene = new Scene(root, 300, 170);
            dialogStage.setScene(scene);

            dialogStage.showAndWait();
            refreshTable();
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public void selectItem(MouseEvent event) throws IOException {
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
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ui/automezzo/automezzo.fxml")));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                case "Prodotto Da Ordinare" -> {
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ui/prodotto/prodotto.fxml")));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
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

    public void searchOrdine(ActionEvent actionEvent) {
        String text = ricercaOrdine.getText();
        if (!stringheVerificate(text)) {
            messaggioParametriScorretti();
            return;
        }
        if (criterio != null) {
            switch (criterio) {
                case "Numero" -> {
                    try {
                        int codice=Integer.parseInt(text);
                        ordineTableView.clear();
                        ordineTableView.addAll(getGestioneAcquisti().getOrdineByNumero(codice));
                        tableView.refresh();
                    }catch (NumberFormatException exception){
                        exception.printStackTrace();
                        messaggioParametriScorretti();
                    }
                }
                case "Stato" -> {
                    ordineTableView.clear();
                    ordineTableView.addAll(getGestioneAcquisti().getOrdineByStato(text));
                    tableView.refresh();
                }
                case "Data" -> {
                    try {

                        String pattern = "yyyy-MM-dd";
                        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
                        Date date = dateFormat.parse(text);
                        ordineTableView.clear();
                        ordineTableView.addAll(getGestioneAcquisti().getOrdineByData(date));
                        tableView.refresh();
                    }catch (ParseException e) {
                        e.printStackTrace();
                        messaggioParametriScorretti();
                    }
                }
                case "Quantita"->{
                    try {
                        int quantita=Integer.parseInt(text);
                        ordineTableView.clear();
                        ordineTableView.addAll(getGestioneAcquisti().getOrdineByQuantita(quantita));
                        tableView.refresh();
                    }catch (NumberFormatException exception){
                        exception.printStackTrace();
                        messaggioParametriScorretti();
                    }
                }
                case "Tutti" -> {
                    ordineTableView.clear();
                    ordineTableView.addAll(getGestioneAcquisti().getOrdine());
                    tableView.refresh();
                }
            }
        }
    }
}
