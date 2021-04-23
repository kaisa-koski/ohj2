package fxPakat;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.ohj2.Mjonot;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pakat.Pakka;
import pakat.Tyyppi;

/**
 * Luokka, joka vastaa uuden pakan luomisesta.
 * 
 * @author Kaisa Koski
 * @version 17.2.2021
 *
 */
public class UusiPakkaController implements ModalControllerInterface<Pakka> {
    
    @FXML private TextField editNimi;
    @FXML private TextField editMp;
    @FXML private ComboBoxChooser<Tyyppi> chooserTyyppi;
    
    @FXML private void handlePeruuta() {
        uusiPakka = null;
        ModalController.closeStage(editNimi);
    }

    @FXML private void handleTallenna() {
        tallenna();
        ModalController.closeStage(editNimi);
    }

    @Override
    public Pakka getResult() {
        return uusiPakka;
    }

    @Override
    public void handleShown() {
        //
        
    }

    @Override
    public void setDefault(Pakka oletus) {
        uusiPakka = oletus;   
    }
    
//=================================================================== 
    
    private Pakka uusiPakka;
    
    /**
     * Avataan pakan lisäämiseen liittyvä dialogi
     * @param modalityStage Stage
     * @param pakka Lisättävä pakka
     * @return Uusi pakka
     */
    public static Pakka kysyPakka(Stage modalityStage, Pakka pakka) {
        var resurssi = PakkarekisteriGUIController.class.getResource("UusiPakka.fxml");
        Pakka uusi = ModalController.showModal(resurssi, "UusiPakka", modalityStage, pakka);
        return uusi;
    }

    
    /**
     * Tallentaa uuden pakan tiedot. Ei toimi vielä.
     */
    private void tallenna() {
        int tyyppi = Mjonot.erotaInt(chooserTyyppi.getSelectedText(), 5);
        uusiPakka = new Pakka(editNimi.getText(), tyyppi, editMp.getText());
    }
}
