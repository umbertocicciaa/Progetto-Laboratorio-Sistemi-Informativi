package ui.acquisti.fornitore;

import data.Fornitore;
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
import logic.business.GestioneAcquisti;
import org.hibernate.HibernateException;
import ui.UIUtil;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static ui.UIUtil.messaggioParametriScorretti;
import static ui.Util.stringheVerificate;

/**
 * @author umbertodomenicociccia
 */
public class FornitoriController implements Initializable {
    @FXML
    private ChoiceBox<String> choiceItem;
    @FXML
    private TreeView<String> homeTreeView;
    @FXML
    private TableView<Fornitore> tableView;
    @FXML
    private TableColumn<Fornitore, String> partitaIva;
    @FXML
    private TableColumn<Fornitore, String> nome;
    @FXML
    private TableColumn<Fornitore, String> citta;

    @FXML
    TextField ricercaFornitore;

    private Fornitore selectedFornitore;
    private String criterio;
    private final ObservableList<Fornitore> fornitoriTableView = FXCollections.observableArrayList();
    private final GestioneAcquisti gestioneAcquisti = new GestioneAcquisti() {
    };


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UIUtil.inizializzaFinestra(homeTreeView);
        choiceItem.getItems().addAll("Partita IVA", "Nome", "Citta", "Tutti");
        choiceItem.setOnAction(this::getSelectedCriterio);
        loadDate();
        tableView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                Fornitore fornitore = tableView.getSelectionModel().getSelectedItem();
                if (fornitore != null) {
                    selectedFornitore = fornitore;
                }
            }
        });
    }

    private void loadDate() {
        refreshTable();
        partitaIva.setCellValueFactory(new PropertyValueFactory<>("piva"));
        nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        citta.setCellValueFactory(new PropertyValueFactory<>("citta"));
        tableView.setItems(fornitoriTableView);
    }

    private void refreshTable() {
        try {
            fornitoriTableView.clear();
            List<Fornitore> resultSet = gestioneAcquisti.getFornitori();
            fornitoriTableView.addAll(resultSet);
            tableView.setItems(fornitoriTableView);
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }


    public void addAction() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ui/acquisti/insertFornitore.fxml")));

        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setTitle("Insert Fornitore");

        Scene scene = new Scene(root);
        dialogStage.setScene(scene);

        dialogStage.showAndWait();
        refreshTable();
    }


    public void updateFornitore() throws IOException {
        if (selectedFornitore != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/acquisti/updateFornitore.fxml"));
            Parent root = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setTitle("Update Fornitore");

            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            UpdateFornitoreController controller = loader.getController();
            controller.initPiva(selectedFornitore.getPiva());

            dialogStage.showAndWait();
            refreshTable();
            selectedFornitore = null;
            refreshTable();
        }
    }

    public void deleteFornitore(ActionEvent actionEvent) {
        if (selectedFornitore != null) {
            gestioneAcquisti.removeFornitore(selectedFornitore.getPiva());
            selectedFornitore = null;
            refreshTable();
        }
    }


    public void getSelectedCriterio(ActionEvent event) {
        criterio = choiceItem.getValue();
        if(criterio.equals("Tutti")){
            fornitoriTableView.clear();
            fornitoriTableView.addAll(gestioneAcquisti.getFornitori());
            tableView.refresh();
        }
    }

    public void selectItem(MouseEvent contextMenuEvent) {
        TreeItem<String> item =homeTreeView.getSelectionModel().getSelectedItem();
        if (item != null) {
            switch (item.getValue()) {
                case "Automezzo Da Ordinare" -> {
                    // Handle Automezzo Da Ordinare case
                    System.out.println("Handling Automezzo Da Ordinare case");
                }
                case "Prodotto Da Ordinare" -> {
                    try {
                        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ui/prodotto/prodotto.fxml")));
                        Stage stage = (Stage) ((Node) contextMenuEvent.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }catch (Exception exception){
                        exception.printStackTrace();
                    }
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


    public void searchFornitori() {
        String text = ricercaFornitore.getText();
        if(!stringheVerificate(text)){
            messaggioParametriScorretti();
            return;
        }
        System.out.println(text);
        if (criterio != null) {
            switch (criterio) {
                case "Partita IVA" -> {
                    fornitoriTableView.clear();
                    fornitoriTableView.addAll(gestioneAcquisti.fornitoriDiUnaPartitaIva(text));
                    tableView.refresh();
                }
                case "Nome" -> {
                    fornitoriTableView.clear();
                    fornitoriTableView.addAll(gestioneAcquisti.fornitoriDiUnNome(text));
                    tableView.refresh();
                }
                case "Citta" -> {
                    fornitoriTableView.clear();
                    fornitoriTableView.addAll(gestioneAcquisti.fornitoriDiUnaCitta(text));
                    tableView.refresh();
                }
                case "Tutti" -> {
                    fornitoriTableView.clear();
                    fornitoriTableView.addAll(gestioneAcquisti.getFornitori());
                    tableView.refresh();
                }
            }
        }
    }
}
