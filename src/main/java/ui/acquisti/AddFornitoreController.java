package ui.acquisti;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import logic.business.GestioneAcquisti;
import org.hibernate.HibernateException;
import ui.Util;

import javax.swing.*;
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

        if (isVerifica(piva, nome, citta)) {
            try {
                gestioneAcquisti.addFornitore(piva, nome, citta);
            } catch (HibernateException ex) {
                ex.printStackTrace();
            }
        }else{
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Errore!");
            errorAlert.setContentText("Hai fornito parametri scorretti");
            errorAlert.showAndWait();
        }
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }

    private static boolean isVerifica(String piva, String nome, String citta) {
        boolean conditionPiva=!piva.isEmpty()&& !piva.toLowerCase().contains(Util.getFrom()) && !piva.toLowerCase().contains(Util.getWhere()) && !piva.toLowerCase().contains(Util.getSelect());
        boolean conditioNome=!nome.isEmpty() && !nome.toLowerCase().contains(Util.getFrom()) && !nome.toLowerCase().contains(Util.getWhere()) && !nome.toLowerCase().contains(Util.getSelect());
        boolean conditionCitta=!citta.isEmpty() && !citta.toLowerCase().contains(Util.getFrom()) && !citta.toLowerCase().contains(Util.getWhere()) && !citta.toLowerCase().contains(Util.getSelect());
        return  conditionPiva && conditioNome && conditionCitta;
    }

    public void handleCancelButton() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
