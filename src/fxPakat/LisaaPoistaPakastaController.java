package fxPakat;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * TÄLLÄ HETKELLÄ EI KÄYTÖSSÄ
 * Luokka, jonka ikkuna avautuu kun käyttäjä on 
 * painanut pakan Lisää/Poista-napista. Valitaan, halutaanko
 * kortteja poistaa pakasta vai lisätä pakkaan.
 * 
 * @author Kaisa Koski
 * @version 23.4.2021
 *
 */
public class LisaaPoistaPakastaController
        implements ModalControllerInterface<String> {

    @FXML
    private Label textPakanNimi;

    @FXML
    private void handelPoista() {
        poista();
    }


    @FXML
    private void handleLisaa() {
        lisaa();
    }


    @FXML
    private void handlePeruuta() {
        sulje();
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
    public void setDefault(String oletus) {
        //
    }

    // ==============================================================================================


    /**
     * Sulkee nykyisen ikkunan ja avaa korttien pakkaan lisäämiseen liittyvän ikkunan.
     */
    private void lisaa() {
        sulje();
        var resurssi = PakkarekisteriGUIController.class
                .getResource("LisaaPakkaan.fxml");
        ModalController.showModal(resurssi, "Lisää", null, "");
    }


    /**
     * Sulkee nykyisen ikkunan ja avaa korttien pakasta poistamiseen liittyvän ikkunan.
     */
    private void poista() {
        sulje();
        var resurssi = PakkarekisteriGUIController.class
                .getResource("PoistaPakasta.fxml");
        ModalController.showModal(resurssi, "Poista", null, "");
    }


    /**
     * Sulkee nykyisen ikkunan.
     */
    private void sulje() {
        ModalController.closeStage(textPakanNimi);
    }

}
