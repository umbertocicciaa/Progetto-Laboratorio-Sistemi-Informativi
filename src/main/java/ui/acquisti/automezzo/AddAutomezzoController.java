package ui.acquisti.automezzo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

import static logic.BusinessFacade.getGestioneAcquisti;
import static ui.UIUtil.messaggioErroreInserimento;
import static ui.UIUtil.messaggioParametriScorretti;
import static ui.Util.stringheVerificate;

public class AddAutomezzoController implements Initializable {

    @FXML
    private  TextArea prezzoField;
    @FXML
    private TextArea assicurazioneField;
    @FXML
    private TextArea targaField;
    @FXML
    private TextArea marcaField;

    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void handleOkButton(ActionEvent actionEvent) {
        String targa = targaField.getText();
        String marca = marcaField.getText();
        String prezzo = prezzoField.getText();
        String assicurazione = assicurazioneField.getText();

        if (!stringheVerificate(targa,marca,assicurazione) || !validaQuantita(prezzo)) {
            messaggioParametriScorretti();
            return;
        }
        try {
            getGestioneAcquisti().addAutomezzo(targa,marca,assicurazione,BigDecimal.valueOf(Double.parseDouble(prezzo)));
            closeStage();
        } catch (Exception ex) {
            ex.printStackTrace();
            messaggioErroreInserimento("Prodotto");
        }
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }
    private void closeStage() {
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }
    private boolean validaQuantita(String prezzo) {
        try {
            double numero = Double.parseDouble(prezzo);
            return numero >= 0;
        } catch (NumberFormatException excp) {
            excp.printStackTrace();
            return false;
        }
    }

    public void handleCancelButton(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
