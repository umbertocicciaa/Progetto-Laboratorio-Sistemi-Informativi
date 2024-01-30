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
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @author umbertodomenicociccia
 */

public class Controller implements Initializable {

    @FXML
    private TreeView<String> homeTreeView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UIUtil.inizializzaFinestra(homeTreeView);
    }


    public void selectItem(MouseEvent event) throws IOException {
        TreeItem<String> item = homeTreeView.getSelectionModel().getSelectedItem();
        if (item != null) {
            switch (item.getValue()) {
                case "Fornitore" -> {
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ui/acquisti/fornitori.fxml")));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
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
                case "Ordine" -> {
                    // Handle Ordine case
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ui/ordine/ordine.fxml")));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                default -> {
                }
            }
        }
    }
}
