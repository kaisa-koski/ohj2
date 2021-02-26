package fxPakat;

import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Luokka, joka vastaa uuden kortin luomisesta.
 * 
 * @author Kaisa Koski
 * @version 18.2.2021
 *
 */
public class UusiKorttiController implements ModalControllerInterface<String> {

    @FXML
    private TextField textNimi;

    @FXML
    private void handleTallenna() {
        tallenna();
    }


    @FXML
    private void handlePeruuta() {
        ModalController.closeStage(textNimi);
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
 
//=================================================================================
   
    /**
     * Tallentaa uuden kortin tiedot. Ei toimi vielä.
     */
    private void tallenna() {
        ModalController.closeStage(textNimi);
        Dialogs.showMessageDialog(
                "Halusit luoda kortin "+ textNimi.getText() +", mutta lisääminen ei toimi vielä");
    }

}