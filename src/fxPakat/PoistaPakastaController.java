package fxPakat;

import java.util.List;
import fi.jyu.mit.fxgui.CheckBoxChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pakat.Kortti;



/**
 * Korttien poistamisen pakasta hoitava luokka.
 * 
 * @author Kaisa Koski
 * @version 22.4.2021
 *
 */
public class PoistaPakastaController
        implements ModalControllerInterface<List<Kortti>> {

    @FXML
    private CheckBoxChooser<Kortti> chooserKortit;

    @FXML
    private Label textPakka;

    @FXML
    private void handlePoista() {
        poistaKortit();
        ModalController.closeStage(textPakka);
    }


    @FXML
    private void handlePeruuta() {
        ModalController.closeStage(textPakka);
    }

    // ====================================================================================================================================

    private List<Kortti> kortit;
    private List<Kortti> poistettavat = null;
    //private List<CheckBoxChooser<Kortti>> boksit = new ArrayList<CheckBoxChooser<Kortti>>();

    /**
     * Avaa korttien pakkaan poistamiseen liittyvän dialogin.
     * @param modalityStage Stage
     * @param pKortit Lista mahdollisista poistettavista korteista
     * @return Poistettavat kortit
     */
    public static List<Kortti> poistaKortteja(Stage modalityStage,List<Kortti> pKortit) {
        var resurssi = PakkarekisteriGUIController.class.getResource("PoistaPakasta.fxml");
        List<Kortti> poistettavatKortit = ModalController.showModal(resurssi, "Poista kortteja pakasta", modalityStage, pKortit);
        return poistettavatKortit;
    }


    /**
     * Asettaa lisättävät kortit näytille
     */
    private void naytaKortit() {
       //chooserKortit = new CheckBoxChooser<Kortti>();
       chooserKortit.clear();
       for (int i = 0; i < kortit.size(); i++) {
          // CheckBoxChooser<Kortti> boksi = new CheckBoxChooser<Kortti>();
            Kortti k = kortit.get(i);
            chooserKortit.add(k.getNimi(), k);
           // boksit.add(boksi);
       }
    }


    @Override
    public void setDefault(List<Kortti> oletus) {
        kortit = oletus;
    }


    @Override
    public void handleShown() {
        naytaKortit();
    }


    @Override
    public List<Kortti> getResult() {
     /*   poistettavat = new ArrayList<Kortti>();
        for (int i = 0; i < boksit.size(); i++) {
            Kortti k = boksit.get(i).getSelectedObject();
            if (k != null) poistettavat.add(k);
        }
        return poistettavat;*/
        poistettavat = chooserKortit.getSelectedObjects();
        return poistettavat;
    }


    private void poistaKortit() {
       poistettavat = chooserKortit.getSelectedObjects();
    }

}