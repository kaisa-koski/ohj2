package fxPakat;

import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.fxgui.ModalController;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 * Pakkojen ja korttien listaustoiminnosta vastaava luokka. 
 * 
 * @author Kaisa Koski
 * @version 16.2.2021
 *
 */
public class ListaaController implements ModalControllerInterface<String> {

    @FXML
    private TextArea pakkalista;

    @FXML
    private void handleSulje() {
        ModalController.closeStage(pakkalista);
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
}