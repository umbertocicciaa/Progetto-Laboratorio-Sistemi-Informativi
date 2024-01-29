package ui.acquisti.ordine;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

import static logic.BusinessFacade.getGestioneAcquisti;
import static ui.UIUtil.messaggioErroreInserimento;
import static ui.UIUtil.messaggioParametriScorretti;
import static ui.Util.stringheVerificate;
import static ui.Util.stringheVerificatePossibileEmpty;

public class AddOrdineController implements Initializable {

    @FXML
    private Button cancel;
    @FXML
    private TextField numeroField;
    @FXML
    private TextField statoField;
    @FXML
    private TextField quantitaField;
    @FXML
    private TextField prodottoField;
    @FXML
    private TextField automezzoField;
    @FXML
    private DatePicker dataField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void viewProdotti(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ui/ordine/visualizzaProdotti.fxml")));
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);

        Scene scene = new Scene(root, 500, 500);
        dialogStage.setScene(scene);

        dialogStage.showAndWait();
    }

    public void viewAutomezzi(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ui/ordine/visualizzaAutomezzi.fxml")));
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);

        Scene scene = new Scene(root, 500, 500);
        dialogStage.setScene(scene);

        dialogStage.showAndWait();
    }

    public void okButton(ActionEvent actionEvent) {
        String numero = numeroField.getText();
        String stato = statoField.getText();
        String quantita = quantitaField.getText();
        String prodotto = prodottoField.getText();
        String automezzo = automezzoField.getText();
        LocalDate data = dataField.getValue();

        if (!stringheVerificate(stato) || !stringheVerificatePossibileEmpty(prodotto, automezzo) || !isStatoValido(stato) || !validoNumero(numero) || !validoNumero(quantita)) {
            messaggioParametriScorretti();
            return;
        }

        try {
            int nume = Integer.parseInt(numero);
            int quantit = Integer.parseInt(quantita);
            Date date = convertLocalDateToDate(data);
            int prod = Integer.parseInt(prodotto);
            getGestioneAcquisti().addOrdine(nume, stato, date, quantit, prod, automezzo);
        } catch (Exception ex) {
            ex.printStackTrace();
            messaggioErroreInserimento("Prodotto");
        }
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();

    }

    private Date convertLocalDateToDate(LocalDate data) {
        Instant instant = data.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    private boolean isStatoValido(String stato) {
        return "lavorazione".equalsIgnoreCase(stato) || "chiuso".equalsIgnoreCase(stato) || "aperto".equalsIgnoreCase(stato);
    }

    private boolean validoNumero(String numero) {
        try {
            int num = Integer.parseInt(numero);
            return true;
        } catch (NumberFormatException excp) {
            excp.printStackTrace();
            return false;
        }
    }

    public void cancelButton(ActionEvent actionEvent) {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }
}
