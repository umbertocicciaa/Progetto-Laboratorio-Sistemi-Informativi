import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.HibernateFactory;
import org.hibernate.SessionFactory;

import java.util.Objects;

/**
 * @author umbertodomenicociccia
 */

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        SessionFactory factory = HibernateFactory.ISTANCE.getSessionFactory();

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main.fxml")));
        stage.setTitle("Consorzio Autolinee Cosenza");
        stage.setScene(new Scene(root, 700, 400));
        stage.show();
        stage.setOnCloseRequest(event -> {
            Platform.exit();
        });
    }
}
