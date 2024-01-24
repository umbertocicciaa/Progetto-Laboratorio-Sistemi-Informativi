package ui.acquisti.fornitore;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import logic.business.GestioneAcquisti;
import org.hibernate.HibernateException;


import java.net.URL;
import java.util.ResourceBundle;

import static ui.UIUtil.messaggioParametriScorretti;
import static ui.Util.stringheVerificate;

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

    private final GestioneAcquisti gestioneAcquisti = new GestioneAcquisti() {
    };


    public void handleOkButton() {
        String nome = nomeField.getText();
        String citta = cittaField.getText();
        if (stringheVerificate(nome, citta)) {
            try {
                gestioneAcquisti.updateFornitore(piva, nome, citta);
            } catch (HibernateException ex) {
                ex.printStackTrace();
            }
        } else {
            messaggioParametriScorretti();
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
