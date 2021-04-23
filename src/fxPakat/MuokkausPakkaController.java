package fxPakat;

import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.ohj2.Mjonot;
import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.ModalController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pakat.Pakka;
import pakat.Tyyppi;

/**
 * Luokka vastaa pakan muokkaamisesta ja poistamisesta.
 * 
 * @author Kaisa Koski
 * @version 17.2.2021
 *
 */
public class MuokkausPakkaController implements ModalControllerInterface<Pakka> {

    @FXML private TextField editNimi;
    @FXML private TextField editMp;
    @FXML private ComboBoxChooser<Tyyppi> chooserTyyppi;

    @FXML
    private void handlePeruuta() {
        ModalController.closeStage(editNimi);  
    }


    @FXML
    private void handlePoista() {
        poista();
        ModalController.closeStage(editNimi);
    }


    @FXML
    void handleTallenna() {
        tallenna();
        ModalController.closeStage(editNimi);
    }


    @Override
    public Pakka getResult() {
        return muokattava;
    }


    @Override
    public void handleShown() {
        //

    }


    @Override
    public void setDefault(Pakka oletus) {
        muokattava = oletus;
        naytaPakka(muokattava);
    }

    // ===================================================================================================

    private Pakka muokattava;
    
    /**
     * Avataan pakan muokkaamiseen liittyvä dialogi
     * @param modalityStage Stage
     * @param pakka Muokattava
     * @return Muokattu pakka
     */
    public static Pakka muokkaaPakkaa(Stage modalityStage, Pakka pakka) {
        var resurssi = PakkarekisteriGUIController.class.getResource("MuokkausPakka.fxml");
        Pakka muokattu = ModalController.showModal(resurssi, "Muokkaa pakkaa", modalityStage, pakka);
        return muokattu;
    }
    
    /*
     * Asettaa muokattavan pakan tiedot esille
     */
    private void naytaPakka(Pakka pakka) {
        if (pakka == null) return;
        editNimi.setText(pakka.getNimi());
        chooserTyyppi.setSelectedIndex(pakka.getTyyppi() - 1);
        editMp.setText(pakka.getMuistiinpanot());
    }

    /**
     * Poistaa pakan. Kaikki pakassa sijaitsevat kortit siirtyvät Ei pakassa-sijaintiin.
     * Ei toimi vielä.
     */
    private void poista() {
        muokattava = null;
    }
    
    /**
     * Tallentaa pakan tietoihin tehdyt muutokset. Ei toimi vielä.
     */
    private void tallenna() {  //TODO: Tarkistus, onko muutoksia oikeasti tullut
        int tyyppi = Mjonot.erotaInt(chooserTyyppi.getSelectedText(), 5);
        muokattava.muokkaa(editNimi.getText(), tyyppi, editMp.getText());
    }

}