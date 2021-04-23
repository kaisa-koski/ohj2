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
 * Luokka, joka vastaa uuden kortin luomisesta.
 * 
 * @author Kaisa Koski
 * @version 22.4.2021
 *
 */
public class UusiKorttiController implements ModalControllerInterface<Kortti>, Initializable {

    @FXML
    private TextField editNimi;
    @FXML
    private TextField editCmc;
    @FXML
    private TextField editMaara;

    @FXML
    private void handleTallenna() {
        tallenna();
        ModalController.closeStage(editNimi);
    }


    @FXML
    private void handlePeruuta() {
        uusiKortti = null;
        ModalController.closeStage(editNimi);
    }


    @Override
    public void handleShown() {
        editNimi.requestFocus();

    }

    // =================================================================================

    private Kortti uusiKortti;

    /**
     * Avataan kortin lisäämiseen liittyvä dialogi
     * @param modalityStage Stage
     * @param kortti Lisättävä kortti
     * @return Uusi kortti
     */
    public static Kortti kysyKortti(Stage modalityStage, Kortti kortti) {
        var resurssi = PakkarekisteriGUIController.class
                .getResource("UusiKortti.fxml");
       Kortti uusi = ModalController.showModal(resurssi, "Uusi kortti", modalityStage,
                kortti);
        return uusi;
    }


    /**
     * Tallentaa uuden kortin tiedot. Ei toimi vielä.
     */
    private void tallenna() {
        try {
            uusiKortti = new Kortti(editNimi.getText(),
                    Integer.parseInt(editMaara.getText()),
                    Integer.parseInt(editCmc.getText()));
        } catch (NumberFormatException e) {
            uusiKortti = null;
        }
    }


    @Override
    public void setDefault(Kortti oletus) {
        uusiKortti = oletus;

    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub

    }


    @Override
    public Kortti getResult() {
        return uusiKortti;
    }

}