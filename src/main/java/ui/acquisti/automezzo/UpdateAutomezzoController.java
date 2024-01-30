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
import static ui.UIUtil.messaggioErroreInserimentoNumero;
import static ui.UIUtil.messaggioParametriScorretti;
import static ui.Util.stringheVerificatePossibileEmpty;

public class UpdateAutomezzoController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private TextArea marcaField;
    @FXML
    private TextArea assicurazioneField;
    @FXML
    private TextArea prezzoField;

    private String targa;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void handleOkButton() {
        String marca = marcaField.getText();
        String assicurazione = assicurazioneField.getText();
        String prezzoS = prezzoField.getText();

        if (!stringheVerificatePossibileEmpty(marca, assicurazione, prezzoS)) {
            messaggioParametriScorretti();
            return;
        }
        try {
            double prezzo = prezzoS.isEmpty() ? 0 : Double.parseDouble(prezzoS);
            if (prezzo < 0) {
                messaggioParametriScorretti();
                return;
            }
            if (marca.isEmpty() && assicurazione.isEmpty() && prezzoS.isEmpty()) {
                messaggioParametriScorretti();
                return;
            }
            getGestioneAcquisti().updateAutomezzo(targa, marca.isEmpty() ? null : marca, assicurazione.isEmpty() ? null : assicurazione, prezzoS.isEmpty() ? null : BigDecimal.valueOf(prezzo));
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        } catch (NumberFormatException ex) {
            messaggioErroreInserimentoNumero();
        }
    }

    public void initAutomezzo(String targa) {
        this.targa = targa;
    }

    public void handleCancelButton() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
