package ui.acquisti.ordine;

import data.Automezzo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.HibernateException;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static logic.BusinessFacade.getGestioneAcquisti;

public class ViewAutomezzi implements Initializable {

    @FXML
    private TableView<Automezzo> automezzi;
    @FXML
    private TableColumn<Automezzo, String> targa;
    @FXML
    private TableColumn<Automezzo, String> marca;
    @FXML
    private TableColumn<Automezzo, String> assicurazione;
    @FXML
    private TableColumn<Automezzo, String> prezzo;

    private final ObservableList<Automezzo> automezzoTableView = FXCollections.observableArrayList();

    private void loadDateAutomezzo() {
        refreshTableAutomezzo();
        targa.setCellValueFactory(new PropertyValueFactory<>("targa"));
        marca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        assicurazione.setCellValueFactory(new PropertyValueFactory<>("assicurazione"));
        prezzo.setCellValueFactory(new PropertyValueFactory<>("prezzo"));
        automezzi.setItems(automezzoTableView);
    }

    private void refreshTableAutomezzo() {
        try {
            automezzoTableView.clear();
            List<Automezzo> resultSet = getGestioneAcquisti().getAutomezzi();
            automezzoTableView.addAll(resultSet);
            automezzi.setItems(automezzoTableView);
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDateAutomezzo();
    }
}
