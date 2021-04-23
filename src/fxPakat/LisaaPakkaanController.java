package fxPakat;

import java.util.ArrayList;
import java.util.List;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pakat.Kortti;
import pakat.Linkki;

/**
 * Korttien lisäämisen pakkaan hoitava luokka.
 * 
 * @author Kaisa Koski
 * @version 22.4.2021
 *
 */
public class LisaaPakkaanController implements ModalControllerInterface<ArrayList<Linkki>> {
   

    @FXML
    private GridPane gridKoko;

    @FXML
    private Label textPakka;

    @FXML
    private void handleOk() {
        lisaaKortit();
        ModalController.closeStage(textPakka);
    }


    @FXML
    private void handlePeruuta() {
        ModalController.closeStage(textPakka);
    }

//====================================================================================================================================
    
    private List<Kortti> kortit;
    private ArrayList<Linkki> lisattavat;
    private ArrayList<ComboBoxChooser<Integer>> kombot = new ArrayList<ComboBoxChooser<Integer>>();

    /**
     * Avaa korttien pakkaan lisäämiseen liittyvän dialogin.
     * @param modalityStage Stage
     * @param pKortit Lista mahdollisista lisättävistä korteista
     * @return Lisättävät kortit
     */
    public static ArrayList<Linkki> lisaaKortteja(Stage modalityStage, List<Kortti> pKortit){
        var resurssi = PakkarekisteriGUIController.class.getResource("LisaaPakkaan.fxml");
        ArrayList<Linkki> lisattavatLinkit = ModalController.<ArrayList<Linkki>, LisaaPakkaanController>showModal(resurssi, "Lisää kortteja pakkaan", modalityStage, new ArrayList<Linkki>(), ctrl -> ctrl.asetaKortit(pKortit));
        return lisattavatLinkit;
    }
    
    /**
     * @param pKortit Mahdollisesti lisättväien korttien lista
     */
    public void asetaKortit(List<Kortti> pKortit) {
        this.kortit = pKortit;
        naytaKortit();
    }
    
    /**
     * Asettaa lisättävät kortit näytille
     */
    public void naytaKortit() {
        gridKoko.getChildren().clear();
        gridKoko.setMinSize(200, 200);
        for (int i = 0; i < kortit.size(); i++) {
            Kortti k = kortit.get(i);
            Label label = new Label(k.getNimi());
            ComboBoxChooser<Integer> kombo = new ComboBoxChooser<Integer>();
            kombo.setId(String.valueOf(k.getID())); 
            for (int j = 0; j <= k.getMaara(); j++) {
                kombo.add(j);
            }
            kombo.setSelectedIndex(0);
            gridKoko.add(label, 0, i);
            gridKoko.add(kombo, 1, i);
            kombot.add(kombo);
        }
    }
    
    @Override
    public void setDefault(ArrayList<Linkki> oletus) {
        lisattavat = oletus;
    }
    
    @Override
    public void handleShown() {
        //
    }
    
    

    @Override
    public ArrayList<Linkki> getResult() {
        return lisattavat;
    }

    
    private void lisaaKortit() {
        for (int i = 0; i < kombot.size(); i++) {
            ComboBoxChooser<Integer> k = kombot.get(i);
            int lkm = k.getSelectedObject();
            if (0 < lkm ) {
                int kid = Integer.parseInt(k.getId());
                lisattavat.add(new Linkki(kid, lkm));
            }
        }
    }



}
