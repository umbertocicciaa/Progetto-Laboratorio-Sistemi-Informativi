import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.HibernateFactory;

import java.util.Objects;

/**
 * @author umbertodomenicociccia
 * */

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        HibernateFactory.ISTANCE.getSessionFactory();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main.fxml")));
        stage.setTitle("Consorzio Autolinee Cosenza");
        stage.setScene(new Scene(root,700,400));
        stage.show();
    }
}
