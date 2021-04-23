package fxPakat;

import fi.jyu.mit.fxgui.ModalControllerInterface;
import java.net.URL;
import java.util.ResourceBundle;
import fi.jyu.mit.fxgui.ModalController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pakat.Kortti;

/**
 * Kortin muokkauksesta vastaava luokka.
 * 
 * @author Kaisa Koski
 * @version 22.4.2021
 *
 */
public class MuokkausKorttiController implements ModalControllerInterface<Kortti>,Initializable {

    @FXML
    private TextField editNimi;
    
    @FXML
    private TextField editMaara;

    @FXML
    private TextField editCmc;

    @FXML
    private void handleTallenna() {
       tallenna();
       ModalController.closeStage(editNimi);
    }


    @FXML
    private void handlePoista() {
       poista();
       ModalController.closeStage(editNimi);
    }


    @FXML
    private void handlePeruuta() {
        ModalController.closeStage(editNimi);
    }
//==============================================================================================================
    
    private Kortti muokattava;
    

    @Override
    public Kortti getResult() {
        return muokattava;
    }


    @Override
    public void handleShown() {
        //

    }
    
    /**
     * Avataan kortin muokkaukseen liittyvä dialogi
     * @param modalityStage Stage
     * @param kortti Muokattava kortti
     * @return Muokattu kortti
     */
    public static Kortti muokkaaKorttia(Stage modalityStage, Kortti kortti) {
        var resurssi = PakkarekisteriGUIController.class.getResource("MuokkausKortti.fxml");
        Kortti muokattu = ModalController.showModal(resurssi, "Muokkaa", modalityStage, kortti);
        return muokattu;
    }
    
    /*
     * Asettaa muokattavan kortin tiedot esille
     */
    private void naytaKortti(Kortti kortti) {
        if (kortti == null) return;
        editNimi.setText(kortti.getNimi());
        editCmc.setText(String.valueOf(kortti.getCmc()));
        editMaara.setText(String.valueOf(kortti.getMaara()));
    }


    
    /**
     * Aliohjelma tallentaa korttiin tehdyt muutokset.
     */
    private void tallenna() {                           //TODO: Tarkistus, onko muutoksia oikeasti tullut
        try {
            muokattava.muokkaa((editNimi.getText()),
                    Integer.parseInt(editMaara.getText()),
                    Integer.parseInt(editCmc.getText()));
        } catch (NumberFormatException e) {
            return;     //TODO: Ilmoittaa käyttäjälle, että epäkelpoa syötettä
        }
    }
    
    
    /**
     * Aliohjelma poistaa kortin. Poistamisen edellytys on, ettei se ole missään pakassa.
     * Ei toimi vielä.
     */
    private void poista() {
        muokattava = null;
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void setDefault(Kortti oletus) {
        muokattava = oletus;
        naytaKortti(muokattava);
    }
    
}