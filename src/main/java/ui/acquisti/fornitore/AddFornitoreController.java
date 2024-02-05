package ui.acquisti.fornitore;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static logic.BusinessFacade.getGestioneAcquisti;
import static ui.UIUtil.messaggioParametriScorretti;
import static ui.Util.stringheVerificate;

/**
 * @author umbertodomenicociccia
 */
public class AddFornitoreController implements Initializable {
    @FXML
    private TextArea pivaField;
    @FXML
    private TextArea nomeField;
    @FXML
    private TextArea cittaField;
    @FXML
    private Button cancelButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void handleOkButton() {
        String piva = pivaField.getText();
        String nome = nomeField.getText();
        String citta = cittaField.getText();

        if (stringheVerificate(piva, nome, citta)) {
            getGestioneAcquisti().addFornitore(piva, nome, citta);
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        }else messaggioParametriScorretti();

    }

    public void handleCancelButton() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
