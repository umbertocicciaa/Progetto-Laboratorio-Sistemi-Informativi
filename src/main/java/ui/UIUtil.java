package ui;

import data.Automezzo;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

/**
 * @author umbertodomenicociccia
 */
public final class UIUtil {

    public static void erroreCaricamentoDatabase(){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Errore!");
        errorAlert.setContentText("Errore nel caricamento del database");
        errorAlert.showAndWait();
    }

    public static Stage loadUiInsert(FXMLLoader loader, String title, int x, int y) throws IOException {
        Stage dialogStage = new Stage();
        Parent root = loader.load();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setTitle(title);
        Scene scene = new Scene(root, x, y);
        dialogStage.setScene(scene);
        dialogStage.setResizable(false);
        return dialogStage;
    }

    public static Stage loadUiUpdate(FXMLLoader loader, String title) throws IOException {
        Parent root = loader.load();

        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setTitle(title);

        Scene scene = new Scene(root);
        dialogStage.setScene(scene);
        dialogStage.setResizable(false);

        return dialogStage;
    }

    public static void inizializzaFinestra(@NotNull TreeView<String> homeTreeView) {
        TreeItem<String> rootItem = new TreeItem<>("Home");
        TreeItem<String> acquisti = new TreeItem<>("Gestione Acquisti");

        //Acquisti
        TreeItem<String> fornitore = new TreeItem<>("Fornitore");
        TreeItem<String> automezziOrdinare = new TreeItem<>("Automezzo Da Ordinare");
        TreeItem<String> prodottoOrdinare = new TreeItem<>("Prodotto Da Ordinare");
        TreeItem<String> ordine = new TreeItem<>("Ordine");

        ObservableList<TreeItem<String>> children = acquisti.getChildren();
        children.add(fornitore);
        children.add(automezziOrdinare);
        children.add(prodottoOrdinare);
        children.add(ordine);

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
        errorAlert.setContentText("Hai fornito un " + text + " errato o gia esistente");
        errorAlert.showAndWait();
    }

    public static void messaggioErroreInserimentoNumero() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Errore!");
        errorAlert.setContentText("Hai fornito un numero errato");
        errorAlert.showAndWait();
    }

    public static void messaggioErroreCaricamentoFinestra() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Errore!");
        errorAlert.setContentText("Errore caricamento finestra");
        errorAlert.showAndWait();
    }

    public static void messaggioErroreAggiornamento(String text) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Errore!");
        errorAlert.setContentText("Erorre nell'aggiornamento del:" + text);
        errorAlert.showAndWait();
    }

    public static void messaggioErroreCancellazione(String text) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Errore!");
        errorAlert.setContentText("Hai eliminato un " + text + " utilizzato da un altro elemento");
        errorAlert.showAndWait();
    }

    public static void loadAutomezzoTable(TableColumn<Automezzo, String> targa, TableColumn<Automezzo, String> marca, TableColumn<Automezzo, String> assicurazione, TableColumn<Automezzo, String> prezzo, TableView<Automezzo> tableView, ObservableList<Automezzo> automezzoTableView) {
        targa.setCellValueFactory(new PropertyValueFactory<>("targa"));
        marca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        assicurazione.setCellValueFactory(new PropertyValueFactory<>("assicurazione"));
        prezzo.setCellValueFactory(new PropertyValueFactory<>("prezzo"));
        tableView.setItems(automezzoTableView);
    }

    public static void loadSelectedItem(TreeItem<String> item, Event event) {
        if (item != null) {
            switch (item.getValue()) {
                case "Fornitore" -> {
                    try {
                        Parent root = FXMLLoader.load(Objects.requireNonNull(UIUtil.class.getResource("/ui/acquisti/fornitori.fxml")));
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } catch (Exception exception) {
                        messaggioErroreCaricamentoFinestra();
                    }
                }
                case "Prodotto Da Ordinare" -> {
                    try {
                        Parent root = FXMLLoader.load(Objects.requireNonNull(UIUtil.class.getResource("/ui/prodotto/prodotto.fxml")));
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } catch (Exception exception) {
                        messaggioErroreCaricamentoFinestra();
                    }
                }
                case "Automezzo Da Ordinare" -> {
                    try {
                        Parent root = FXMLLoader.load(Objects.requireNonNull(UIUtil.class.getResource("/ui/automezzo/automezzo.fxml")));
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } catch (Exception exception) {
                        messaggioErroreCaricamentoFinestra();
                    }
                }
                case "Ordine" -> {
                    try {
                        Parent root = FXMLLoader.load(Objects.requireNonNull(UIUtil.class.getResource("/ui/ordine/ordine.fxml")));
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } catch (Exception exception) {
                        messaggioErroreCaricamentoFinestra();
                    }
                }
                default -> {
                }
            }
        }
    }

}
