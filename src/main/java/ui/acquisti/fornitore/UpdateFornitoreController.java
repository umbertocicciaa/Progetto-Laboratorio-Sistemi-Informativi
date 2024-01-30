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
import static ui.Util.stringheVerificatePossibileEmpty;

/**
 * @author umbertodomenicociccia
 */
public class UpdateFornitoreController implements Initializable {
    @FXML
    private TextArea nomeField;
    @FXML
    private TextArea cittaField;
    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;
    private String piva;

    public void handleOkButton() {
        String nome = nomeField.getText();
        String citta = cittaField.getText();
        if (stringheVerificatePossibileEmpty(nome, citta)) {
            if (nome.isEmpty() && citta.isEmpty()) {
                messaggioParametriScorretti();
                return;
            }
            getGestioneAcquisti().updateFornitore(piva, nome.isEmpty() ? null : nome, citta.isEmpty() ? null : citta);
        }
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }

    public void initPiva(String piva) {
        this.piva = piva;
    }

    public void handleCancelButton() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
