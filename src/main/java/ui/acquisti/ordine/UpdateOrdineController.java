package ui.acquisti.ordine;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import static logic.BusinessFacade.getGestioneAcquisti;
import static ui.UIUtil.messaggioParametriScorretti;
import static ui.Util.stringheVerificatePossibileEmpty;

public class UpdateOrdineController implements Initializable {
    @FXML
    private Button cancel;
    @FXML
    private TextField statoField;
    @FXML
    private TextField quantitaField;
    @FXML
    private DatePicker dataField;
    private int ordine;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initOrdine(Integer numero) {
        this.ordine = numero;
    }

    public void okButton() {
        String stato = statoField.getText();
        String quantita = quantitaField.getText();

        if (stringheVerificatePossibileEmpty(stato)) {
            try {
                Date date = null;
                if (dataField.getValue() != null)
                    date = convertLocalDateToDate(dataField.getValue());
                if (!isStatoValido(stato)) {
                    messaggioParametriScorretti();
                    return;
                }
                int numero = quantita.isEmpty() ? 0 : Integer.parseInt(quantita);
                if (numero < 0) {
                    messaggioParametriScorretti();
                    return;
                }
                if (stato.isEmpty() && quantita.isEmpty() && date==null) {
                    messaggioParametriScorretti();
                    return;
                }
                getGestioneAcquisti().updateOrdine(ordine, stato.isEmpty() || !isStatoValido(stato) ? null : stato, date, quantita.isEmpty() ? -1 : numero);
            } catch (NumberFormatException ex) {
                messaggioParametriScorretti();
            }
        } else {
            messaggioParametriScorretti();
        }
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    private boolean isStatoValido(String stato) {
        return "lavorazione".equalsIgnoreCase(stato) || "chiuso".equalsIgnoreCase(stato) || "aperto".equalsIgnoreCase(stato);
    }


    private Date convertLocalDateToDate(LocalDate data) {
        Instant instant = data.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public void cancelButton() {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }
}
