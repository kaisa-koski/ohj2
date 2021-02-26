package fxPakat;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Luokka, joka vastaa uuden pakan luomisesta.
 * 
 * @author Kaisa Koski
 * @version 17.2.2021
 *
 */
public class UusiPakkaController implements ModalControllerInterface<String> {
    
    @FXML private TextField textNimi;
    
    @FXML private void handlePeruuta() {
        ModalController.closeStage(textNimi);
    }

    @FXML private void handleTallenna() {
        tallenna();
    }

    @Override
    public String getResult() {
        // 
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
    
//===================================================================    
    
    /**
     * Tallentaa uuden pakan tiedot. Ei toimi vielä.
     */
    private void tallenna() {
        ModalController.closeStage(textNimi);
        Dialogs.showMessageDialog("Halusit luoda pakan nimeltä "
                + textNimi.getText() + " , mutta tallennus ei vielä toimi! :)");
    }
}
