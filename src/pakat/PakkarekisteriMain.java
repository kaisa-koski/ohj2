package pakat;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author kaisa
 * @version 3.2.2021
 *
 */
public class PakkarekisteriMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("PakkarekisteriGUIView.fxml"));
            final Pane root = ldr.load();
            //final PakkarekisteriGUIController pakkarekisteriCtrl = (PakkarekisteriGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("pakkarekisteri.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Pakkarekisteri");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        launch(args);
    }
}