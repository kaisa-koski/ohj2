package pakat;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
//import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Korttien lisäämisen pakkaan hoitava luokka.
 * 
 * @author Kaisa Koski
 * @version 16.2.2021
 *
 */
public class LisaaPakkaanController implements ModalControllerInterface<String> {

    @FXML
    private Label textPakka;

    @FXML
    private void handleOk() {
        ModalController.closeStage(textPakka);
        // Dialogs.showMessageDialog("Halusit lisätä kortteja pakkaan
        // "+textPakka.getText()+", mutta korttien lisääminen ei toimi vielä");
    }


    @FXML
    private void handlePeruuta() {
        ModalController.closeStage(textPakka);
    }


    @Override
    public String getResult() {
        //
        return null;
    }


    @Override
    public void handleShown() {
        Dialogs.showMessageDialog("Korttien lisääminen ei toimi vielä"); //Jostain syystä ei toimi kunnolla jos tämän ottaa pois
    }


    @Override
    public void setDefault(String arg0) {
        //

    }

}
