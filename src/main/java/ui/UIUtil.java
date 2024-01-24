package ui;

import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
/**
 * @author umbertodomenicociccia
 * */
public  final  class UIUtil {
   public static void inizializzaFinestra(TreeView homeTreeView){
       TreeItem<String> rootItem = new TreeItem<>("Home");
       TreeItem<String> acquisti = new TreeItem<>("Gestione Acquisti");
       TreeItem<String> contabilita = new TreeItem<>("Gestione Contabilita");
       TreeItem<String> corse = new TreeItem<>("Gestione Corse");
       TreeItem<String> dipendenti = new TreeItem<>("Gestione Dipendenti");
       TreeItem<String> reclamo = new TreeItem<>("Gestione Reclamo");

       //Corse
       TreeItem<String> corsa = new TreeItem<>("Corsa");
       TreeItem<String> fermata = new TreeItem<>("Fermata");
       corse.getChildren().addAll(corsa, fermata);

       //Contabilita
       TreeItem<String> transazione = new TreeItem<>("Transazione");
       TreeItem<String> fattura = new TreeItem<>("Fattura");
       TreeItem<String> conto = new TreeItem<>("Conto");
       TreeItem<String> bene = new TreeItem<>("Bene");
       contabilita.getChildren().addAll(transazione, fattura, conto, bene);

       //Acquisti
       TreeItem<String> fornitore = new TreeItem<>("Fornitore");
       TreeItem<String> automezziOrdinare = new TreeItem<>("Automezzo Da Ordinare");
       TreeItem<String> prodottoOrdinare = new TreeItem<>("Prodotto Da Ordinare");
       TreeItem<String> ordine = new TreeItem<>("Ordine");
       TreeItem<String> preventivo = new TreeItem<>("Preventivo");
       acquisti.getChildren().addAll(fornitore, automezziOrdinare, prodottoOrdinare, ordine, preventivo);

       //Dipendenti
       TreeItem<String> dipendente = new TreeItem<>("Dipendenti");
       TreeItem<String> turni = new TreeItem<>("Turno");
       TreeItem<String> requisitiMinimiCandidatura = new TreeItem<>("Requisito minimo candidatura");
       TreeItem<String> postiVacanti = new TreeItem<>("Posti vacanti");
       TreeItem<String> ruolo = new TreeItem<>("Ruolo");
       TreeItem<String> dipartimento = new TreeItem<>("Dipartimento");
       dipendenti.getChildren().addAll(dipartimento, dipendente, turni, ruolo, postiVacanti, requisitiMinimiCandidatura);

       //Reclamo
       TreeItem<String> richiestaCliente = new TreeItem<>("Richiesta Cliente");
       TreeItem<String> cliente = new TreeItem<>("Cliente");
       reclamo.getChildren().addAll(cliente, richiestaCliente);

       rootItem.getChildren().addAll(acquisti, contabilita, corse, dipendenti, reclamo);
       homeTreeView.setRoot(rootItem);
   }

    public static void messaggioParametriScorretti(){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Errore!");
        errorAlert.setContentText("Hai fornito parametri scorretti");
        errorAlert.showAndWait();
    }

    public static void messaggioErroreInserimento(String text){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Errore!");
        errorAlert.setContentText("Hai inserito un "+text+" gia esistente");
        errorAlert.showAndWait();
    }
}
