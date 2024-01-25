package ui.acquisti.prodotto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import ui.UIUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class ProdottoController implements Initializable {

    @FXML
    private TreeView<String> homeTreeView;

    public void selectItem(MouseEvent contextMenuEvent) {

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
