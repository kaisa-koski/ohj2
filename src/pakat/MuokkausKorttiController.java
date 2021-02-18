package pakat;

import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Kortin muokkauksesta vastaava luokka.
 * 
 * @author Kaisa Koski
 * @version 18.2.2021
 *
 */
public class MuokkausKorttiController implements ModalControllerInterface<String> {

    @FXML
    private TextField textNimi;

    @FXML
    private void handleTallenna() {
       tallenna();
    }


    @FXML
    private void handlePoista() {
       poista();
    }


    @FXML
    private void handlePeruuta() {
        ModalController.closeStage(textNimi);
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

 //============================================================================================
    
    /**
     * Aliohjelma tallentaa korttiin tehdyt muutokset (ei toimi vielä).
     */
    private void tallenna() {
        ModalController.closeStage(textNimi);
        Dialogs.showMessageDialog("Halusit muokata korttia nimeltä "
                + textNimi.getText() + " , mutta muokkaus ei vielä toimi!");
    }
    
    
    /**
     * Aliohjelma poistaa kortin. Poistamisen edellytys on, ettei se ole missään pakassa.
     * Ei toimi vielä.
     */
    private void poista() {
        ModalController.closeStage(textNimi);
        Dialogs.showMessageDialog("Halusit poistaa kortin nimeltä "
                + textNimi.getText() + " , mutta poistaminen ei vielä toimi!");
    }
    
}