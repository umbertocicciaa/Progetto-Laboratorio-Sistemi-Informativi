package ui.acquisti.prodotto;

import javafx.event.ActionEvent;
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

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ProdottoController implements Initializable {

    @FXML
    private TreeView<String> homeTreeView;

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
                    }catch (Exception exception){
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

    public void searchFornitori(ActionEvent actionEvent) {

    }

    public void addAction(ActionEvent actionEvent) {

    }

    public void updateFornitore(ActionEvent actionEvent) {

    }

    public void deleteFornitore(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UIUtil.inizializzaFinestra(homeTreeView);
    }
}
