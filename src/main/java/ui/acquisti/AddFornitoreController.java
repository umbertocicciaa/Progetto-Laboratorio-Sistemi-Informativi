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
public class AddFornitoreController implements Initializable {
    @FXML
    private TextArea pivaField;
    @FXML
    private TextArea nomeField;
    @FXML
    private TextArea cittaField;
    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;

    private final GestioneAcquisti gestioneAcquisti = new GestioneAcquisti() {
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void handleOkButton() {
        String piva = pivaField.getText();
        String nome = nomeField.getText();
        String citta = cittaField.getText();

        if (!piva.isEmpty() && !nome.isEmpty() && !citta.isEmpty()) {
            try {
                gestioneAcquisti.addFornitore(piva, nome, citta);
            } catch (HibernateException ex) {
                ex.printStackTrace();
            }
        }
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }

    public void handleCancelButton() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
