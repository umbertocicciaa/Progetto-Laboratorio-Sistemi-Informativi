package ui.acquisti.ordine;

import data.Prodotto;
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

public class ViewProdotti implements Initializable {

    @FXML
    private TableView<Prodotto> prodotti;
    @FXML
    private TableColumn<Prodotto, String> codice;
    @FXML
    private TableColumn<Prodotto, String> tipo;
    @FXML
    private TableColumn<Prodotto, String> quantita;

    private final ObservableList<Prodotto> prodottoTableView = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDateProdotto();
    }

    private void refreshTableProdotto() {
        try {
            prodottoTableView.clear();
            List<Prodotto> resultSet = getGestioneAcquisti().getProdotti();
            prodottoTableView.addAll(resultSet);
            prodotti.setItems(prodottoTableView);
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }

    private void loadDateProdotto() {
        refreshTableProdotto();
        codice.setCellValueFactory(new PropertyValueFactory<>("CodProdotto"));
        tipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        quantita.setCellValueFactory(new PropertyValueFactory<>("quantitaNecessaria"));
        prodotti.setItems(prodottoTableView);
    }

}
