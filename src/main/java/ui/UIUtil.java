package ui;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.jetbrains.annotations.NotNull;

/**
 * @author umbertodomenicociccia
 */
public final class UIUtil {
    public static void inizializzaFinestra(@NotNull TreeView<String> homeTreeView) {
        TreeItem<String> rootItem = new TreeItem<>("Home");
        TreeItem<String> acquisti = new TreeItem<>("Gestione Acquisti");

        //Acquisti
        TreeItem<String> fornitore = new TreeItem<>("Fornitore");
        TreeItem<String> automezziOrdinare = new TreeItem<>("Automezzo Da Ordinare");
        TreeItem<String> prodottoOrdinare = new TreeItem<>("Prodotto Da Ordinare");
        TreeItem<String> ordine = new TreeItem<>("Ordine");
        TreeItem<String> preventivo = new TreeItem<>("Preventivo");

        ObservableList<TreeItem<String>> children = acquisti.getChildren();
        children.add(fornitore);
        children.add(automezziOrdinare);
        children.add(prodottoOrdinare);
        children.add(ordine);
        children.add(preventivo);

        rootItem.getChildren().add(acquisti);
        homeTreeView.setRoot(rootItem);
    }

    public static void messaggioParametriScorretti() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Errore!");
        errorAlert.setContentText("Hai fornito parametri scorretti");
        errorAlert.showAndWait();
    }

    public static void messaggioErroreInserimento(String text) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Errore!");
        errorAlert.setContentText("Hai inserito un " + text + " gia esistente");
        errorAlert.showAndWait();
    }
}
