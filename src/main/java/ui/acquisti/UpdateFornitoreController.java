package ui.acquisti;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import logic.business.GestioneAcquisti;
import org.hibernate.HibernateException;

import java.net.URL;
import java.util.ResourceBundle;
/**
 * @author umbertodomenicociccia
 * */
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
        String nome = null;
        String citta = null;
        if (!nomeField.getText().isEmpty())
            nome = nomeField.getText();
        if (!cittaField.getText().isEmpty())
            citta = cittaField.getText();
        try {
            gestioneAcquisti.updateFornitore(piva, nome, citta);
        } catch (HibernateException ex) {
            ex.printStackTrace();
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
