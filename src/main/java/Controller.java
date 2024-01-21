import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ui.UIUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author umbertodomenicociccia
 */

public class Controller implements Initializable {

    @FXML
    private TreeView homeTreeView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UIUtil.inizializzaFinestra(homeTreeView);
    }


    public void selectItem(MouseEvent event) throws IOException {
        TreeItem<String> item = (TreeItem<String>) homeTreeView.getSelectionModel().getSelectedItem();
        if (item != null) {
            switch (item.getValue()) {
                case "Corsa" -> {
                    // Handle Corsa case
                    System.out.println("Handling Corsa case");
                }
                case "Fermata" -> {
                    // Handle Fermata case
                    System.out.println("Handling Fermata case");
                }
                case "Transazione" -> {
                    // Handle Transazione case
                    System.out.println("Handling Transazione case");
                }
                case "Fattura" -> {
                    // Handle Fattura case
                    System.out.println("Handling Fattura case");
                }
                case "Conto" -> {
                    // Handle Conto case
                    System.out.println("Handling Conto case");
                }
                case "Bene" -> {
                    // Handle Bene case
                    System.out.println("Handling Bene case");
                }
                case "Fornitore" -> {
                    Parent root = FXMLLoader.load(getClass().getResource("/ui/acquisti/fornitori.fxml"));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                case "Automezzo Da Ordinare" -> {
                    // Handle Automezzo Da Ordinare case
                    System.out.println("Handling Automezzo Da Ordinare case");
                }
                case "Prodotto Da Ordinare" -> {
                    // Handle Prodotto Da Ordinare case
                    System.out.println("Handling Prodotto Da Ordinare case");
                }
                case "Ordine" -> {
                    // Handle Ordine case
                    System.out.println("Handling Ordine case");
                }
                case "Preventivo" -> {
                    // Handle Preventivo case
                    System.out.println("Handling Preventivo case");
                }
                case "Dipendenti" -> {
                    // Handle Dipendenti case
                    System.out.println("Handling Dipendenti case");
                }
                case "Turno" -> {
                    // Handle Turno case
                    System.out.println("Handling Turno case");
                }
                case "Requisito minimo candidatura" -> {
                    // Handle Requisito minimo candidatura case
                    System.out.println("Handling Requisito minimo candidatura case");
                }
                case "Posti vacanti" -> {
                    // Handle Posti vacanti case
                    System.out.println("Handling Posti vacanti case");
                }
                case "Ruolo" -> {
                    // Handle Ruolo case
                    System.out.println("Handling Ruolo case");
                }
                case "Dipartimento" -> {
                    // Handle Dipartimento case
                    System.out.println("Handling Dipartimento case");
                }
                case "Richiesta Cliente" -> {
                    // Handle Richiesta Cliente case
                    System.out.println("Handling Richiesta Cliente case");
                }
                case "Cliente" -> {
                    // Handle Cliente case
                    System.out.println("Handling Cliente case");
                }
                default -> {
                }
            }
        }
    }
}
