package ui.acquisti.prodotto;

import javafx.event.ActionEvent;
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

    public void handleOkButton(ActionEvent actionEvent) {
        String tipo = tipoField.getText();
        String quantita = quantitaField.getText();
        boolean inserito = false;
        if (stringheVerificate(tipo)) {
            int numero;
            try {
                numero = Integer.parseInt(quantita);
            } catch (NumberFormatException excp) {
                excp.printStackTrace();
                return;
            }
            if (numero < 0) {
                messaggioParametriScorretti();
                return;
            }
            try {
                getGestioneAcquisti().addProdotto(tipo, numero);
                inserito = true;
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (!inserito) {
                    messaggioErroreInserimento("Prodotto");
                }
            }
        } else {
            messaggioParametriScorretti();
        }
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }

    public void handleCancelButton(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
