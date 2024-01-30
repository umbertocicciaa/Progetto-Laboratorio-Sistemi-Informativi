package ui.acquisti.prodotto;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.hibernate.HibernateException;

import java.net.URL;
import java.util.ResourceBundle;

import static logic.BusinessFacade.getGestioneAcquisti;
import static ui.UIUtil.messaggioParametriScorretti;
import static ui.Util.stringheVerificatePossibileEmpty;

public class UpdateProdottoController implements Initializable {
    @FXML
    private Button cancelButton;
    @FXML
    private TextArea tipoField;
    @FXML
    private TextArea quantitaField;
    private int codice;

    public void handleOkButton() {
        String tipo = tipoField.getText();
        String quantita = quantitaField.getText();

        if (stringheVerificatePossibileEmpty(tipo, quantita)) {
            try {
                int numero = quantita.isEmpty() ? 0 : Integer.parseInt(quantita);
                if (numero < 0) {
                    messaggioParametriScorretti();
                    return;
                }

                if (tipo.isEmpty() && quantita.isEmpty()) {
                    messaggioParametriScorretti();
                    return;
                }
                getGestioneAcquisti().updateProdotto(codice, tipo.isEmpty() ? null : tipo, quantita.isEmpty() ? null : numero);
            } catch (NumberFormatException ex) {
                messaggioParametriScorretti();
            } catch (HibernateException ex) {
                ex.printStackTrace();
            }
        } else {
            messaggioParametriScorretti();
        }

        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void handleCancelButton() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initProdotto(int codice) {
        this.codice = codice;
    }
}
