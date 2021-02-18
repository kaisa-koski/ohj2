package pakat;

import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Luokka, jonka ikkuna avautuu kun käyttäjä on valinnut haluavansa
 * poistaa kortteja pakasta. Vastaa korttien pakasta poistamisen toiminnoista.
 * 
 * @author Kaisa Koski
 * @version 18.2.2021
 *
 */
public class PoistaPakastaController implements ModalControllerInterface<String> {
    
    @FXML private Label labelPakka;
    
    @FXML
    private void handlePeruuta() {
        ModalController.closeStage(labelPakka);
    }


    @FXML
    private void handlePoista() {
        ModalController.closeStage(labelPakka);

    }

    @Override
    public String getResult() {
        return null;
    }

    @Override
    public void handleShown() {
        Dialogs.showMessageDialog("Korttien poistaminen pakasta ei vielä toimi");
    }

    @Override
    public void setDefault(String arg0) {
        //
        
    }
    
}