package pakat;

import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Pakan aktivoinnin hoitava luokka.
 * 
 * @author Kaisa Koski
 * @version 16.2.2021
 *
 */
public class AktivoiController implements ModalControllerInterface<String> {

    @FXML
    private Label labelOhje;

    @FXML
    private void handleValmis() {
       valmis();
    }


    @FXML
    private void handlePeruuta() {
        ModalController.closeStage(labelOhje);
    }


    @Override
    public String getResult() {
        return null;
    }


    @Override
    public void handleShown() {
        //

    }


    @Override
    public void setDefault(String arg0) {
        //
    }
    
 //==========================================================================================   
    
    /**
     * Toiminnot, jotka tehdään kun on painettu Valmis-napista.
     * Valmiissa aliohjelmassa siirtää valittujen korttien sijaintia aktivoitavaan pakkaan. 
     */
    private void valmis() {
        ModalController.closeStage(labelOhje);
        Dialogs.showMessageDialog("Pakkojen aktivoiminen ei toimi vielä");
    }
    

}