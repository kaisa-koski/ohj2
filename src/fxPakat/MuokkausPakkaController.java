package fxPakat;

import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Luokka vastaa pakan muokkaamisesta ja poistamisesta.
 * 
 * @author Kaisa Koski
 * @version 17.2.2021
 *
 */
public class MuokkausPakkaController
        implements ModalControllerInterface<String> {

    @FXML
    private TextField textNimi;

    @FXML
    private void handlePeruuta() {
        ModalController.closeStage(textNimi);
    }


    @FXML
    private void handlePoista() {
        poista();
    }


    @FXML
    void handleTallenna() {
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

    // ===================================================================================================


    /**
     * Poistaa pakan. Kaikki pakassa sijaitsevat kortit siirtyvät Ei pakassa-sijaintiin.
     * Ei toimi vielä.
     */
    private void poista() {
        ModalController.closeStage(textNimi);
        Dialogs.showMessageDialog("Halusit poistaa pakan nimeltä "
                + textNimi.getText() + " , mutta poistaminen ei vielä toimi!");
    }
    
    /**
     * Tallentaa pakan tietoihin tehdyt muutokset. Ei toimi vielä.
     */
    private void tallenna() {
        ModalController.closeStage(textNimi);
        Dialogs.showMessageDialog("Halusit tallentaa muutokset pakkaan nimeltä "
                + textNimi.getText() + " , mutta tallennus ei vielä toimi!");
    }

}