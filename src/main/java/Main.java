import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jerry.Jerry;
import jerry.exceptions.JerryException;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private Jerry jerry;

    @Override
    public void start(Stage stage) {
        try {
            jerry = new Jerry("data/jerry.txt");
        } catch (JerryException e) {
            System.out.println(e.getMessage());
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setDuke(jerry);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
