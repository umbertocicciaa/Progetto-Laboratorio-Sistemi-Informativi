package ui.acquisti.prodotto;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static logic.BusinessFacade.getGestioneAcquisti;
import static ui.UIUtil.messaggioErroreInserimento;
import static ui.UIUtil.messaggioParametriScorretti;
import static ui.Util.stringheVerificate;

public class AddProdottoController implements Initializable {

    @FXML
    private TextArea tipoField;
    @FXML
    private TextArea quantitaField;
    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;

    public void handleOkButton() {
        String tipo = tipoField.getText();
        String quantita = quantitaField.getText();

        if (!stringheVerificate(tipo) || !validaQuantita(quantita)) {
            messaggioParametriScorretti();
            return;
        }

        try {
            getGestioneAcquisti().addProdotto(tipo, Integer.parseInt(quantita));
            closeStage();
        } catch (Exception ex) {
            ex.printStackTrace();
            messaggioErroreInserimento("Prodotto");
        }
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }

    private boolean validaQuantita(String quantita) {
        try {
            int numero = Integer.parseInt(quantita);
            return numero >= 0;
        } catch (NumberFormatException excp) {
            excp.printStackTrace();
            return false;
        }
    }

    private void closeStage() {
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }
    public void handleCancelButton() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
