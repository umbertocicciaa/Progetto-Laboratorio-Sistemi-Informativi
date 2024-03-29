package ui.acquisti.automezzo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

import static logic.BusinessFacade.getGestioneAcquisti;
import static ui.UIUtil.messaggioParametriScorretti;
import static ui.Util.stringheVerificate;

public class AddAutomezzoController implements Initializable {

    @FXML
    private TextArea prezzoField;
    @FXML
    private TextArea assicurazioneField;
    @FXML
    private TextArea targaField;
    @FXML
    private TextArea marcaField;

    @FXML
    private Button cancelButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void handleOkButton() {
        String targa = targaField.getText();
        String marca = marcaField.getText();
        String prezzo = prezzoField.getText();
        String assicurazione = assicurazioneField.getText();

        if (!stringheVerificate(targa, marca, assicurazione) || !validaQuantita(prezzo)) {
            messaggioParametriScorretti();
            return;
        }
        getGestioneAcquisti().addAutomezzo(targa, marca, assicurazione, BigDecimal.valueOf(Double.parseDouble(prezzo)));
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private boolean validaQuantita(String prezzo) {
        try {
            double numero = Double.parseDouble(prezzo);
            return numero >= 0;
        } catch (NumberFormatException excp) {
            return false;
        }
    }

    public void handleCancelButton() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
