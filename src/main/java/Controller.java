import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import ui.UIUtil;

import java.io.IOException;
import java.net.URL;
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
        UIUtil.loadSelectedItem(item, event);
    }
}
