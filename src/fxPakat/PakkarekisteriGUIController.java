package fxPakat;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pakat.Kortti;
import pakat.Pakka;
import pakat.Pakkarekisteri;

/**
 * Käyttöliittymän pääikkunan toiminnasta vastaava luokka.
 *  
 * @author Kaisa Koski
 * @version 16.3.2021
 *
 */
public class PakkarekisteriGUIController implements Initializable {

    @FXML
    private ListChooser<Pakka> chooserPakat;
    @FXML
    private ListChooser<Kortti> chooserKortit;
    @FXML
    private Label labelPakanNimi;
    @FXML
    private Label labelPakanTyyppi;
    @FXML
    private Label labelPakanMp;
    @FXML
    private TextField textHaku;
    @FXML
    private ListChooser<Pakka> kortinPakatTrue;
    @FXML
    private ListChooser<Pakka> kortinPakatFalse;
    @FXML
    private void handleAktivoi() {
        aktivoi();
    }


    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
    }
    
    
    @FXML
    private void handleLisaaPoistaKortti() {
        lisaaKortti();
    }


    @FXML
    private void handleListaa() {
        listaa();
    }


    @FXML
    private void handleMuokkausKortti() {
        muokkaaKorttia();
    }


    @FXML
    private void handleMuokkausPakka() {
        muokkaaPakkaa();
    }


    @FXML
    private void handleNaytaPakat() {
        Dialogs.showMessageDialog(
                "Tämä listaa vain tietyn tyyppiset pakat, muttei vielä toimi");
    }


    @FXML
    private void handlePakka() {
        /*Dialogs.showMessageDialog(
                "Halusit katsoa klikkaamasi pakan tietoja, mutta ei toimi vielä");*/
    }


    @FXML
    private void handleKortti() {
        Dialogs.showMessageDialog(
                "Halusit katsoa kortin " + chooserKortit.getSelectedText()
                        + " tietoja, mutta ei toimi vielä");
    }


    @FXML
    private void handleOhjeita() {
        Dialogs.showMessageDialog(
                "Tähän tulee käyttöohjeita, mutta niitä ei vielä ole");
    }


    @FXML
    private void handleTietoja() {
        Dialogs.showMessageDialog(
                "Tähän tulee tietoja, kuten esim. että tekijä on Kaisa Koski ja että tämä on Ohjelmointi 2 -kurssin harkkatyö");
    }


    @FXML
    private void handleUusiKortti() {
        uusiKortti();
    }


    @FXML
    private void handleUusiPakka() {
        uusiPakka();
    }


    @FXML
    private void handleHaku() {
        haeKorttia();
    }

    // =========================================================================================================================

   private Pakkarekisteri pakkarekisteri;
    
    /**
     * Asetetaan pakkarekisteri käyttöön
     * @param pakkarekisteri Pakkarekisteri jota käytetään
     */
    public void setRekisteri(Pakkarekisteri pakkarekisteri) {
        this.pakkarekisteri = pakkarekisteri;
    }
    
    /**
     * Alustaa tarvittavat asiat, esimerkiksi lisää kuuntelijan
     * pakkalistalle.
     */
    private void alusta(){
        chooserPakat.clear();
        chooserPakat.addSelectionListener(e -> naytaPakka());
    }
    
    /**
     * Avaa pakan aktivointiin liittyvän ikkunan.
     */
    private void aktivoi() {
        var resurssi = PakkarekisteriGUIController.class
                .getResource("Aktivoi.fxml");
        ModalController.showModal(resurssi, "Aktivoi", null, "");
    }


    /**
     * Hakee korttia käyttäjän kirjoittaman perusteella. Ei toimi vielä.
     */
    private void haeKorttia() {
        Dialogs.showMessageDialog("Hait korttia " + textHaku.getText()
                + ", mutta haku ei vielä toimi");
    }

    /**
     * Avaa korttien pakkaan lisäämiseen ja pakasta poistamiseen liittyvän ikkunan.
     */
    private void lisaaKortti() {
        var resurssi = PakkarekisteriGUIController.class
                .getResource("LisaaPoistaPakasta.fxml");
        ModalController.showModal(resurssi, "Lisää/poista", null, "");
    }

    /**
     * Avaa pakkojen ja korttien listaukseen liittyvän ikkunan.
     */
    private void listaa() {
        var resurssi = PakkarekisteriGUIController.class
                .getResource("Listaa.fxml");
        ModalController.showModal(resurssi, "Listaa", null, "");
    }


    /**
     * Avaa kortin muokkaukseen liittyvän ikkunan.
     */
    private void muokkaaKorttia() {
        var resurssi = PakkarekisteriGUIController.class
                .getResource("MuokkausKortti.fxml");
        ModalController.showModal(resurssi, "Muokkaa", null, "");
    }


    /**
     * Avaa pakan muokkaukseen liittyvän ikkunan.
     */
    private void muokkaaPakkaa() {
        var resurssi = PakkarekisteriGUIController.class
                .getResource("MuokkausPakka.fxml");
        ModalController.showModal(resurssi, "Muokkaa", null, "");
    }


    /**
     * Avaa uuden kortin luomiseen liittyvän ikkunan.
     */
    private void uusiKortti() {
        Kortti uusi = new Kortti().jaceKortti(); // TODO: Korvaa myöhemmin
        pakkarekisteri.lisaa(uusi); //rekisteröi samalla

       /** var resurssi = PakkarekisteriGUIController.class
                .getResource("UusiKortti.fxml");
        ModalController.showModal(resurssi, "Uusi kortti", null, "");*/
    }
    

    /**
     * Avaa uuden pakan luomiseen ikkunan.
     */
    private void uusiPakka() {
        Pakka uusi = new Pakka().izzetPakka(); //TODO: korvaa myöhemmin izzet-pakka dialogilla
        pakkarekisteri.lisaa(uusi); //Lisäämisessä rekisteröi samalla.
        haePakat(uusi.getID());
        
      /**  var resurssi = PakkarekisteriGUIController.class
                .getResource("UusiPakka.fxml");
        ModalController.showModal(resurssi, "UusiPakka", null, "");*/
    }
    
    /**
     * Hakee pakkojen tiedot listaan
     * @param pid Pakan id
     */
    private void haePakat(int pid) {
        chooserPakat.clear();
        int indeksi = 0;
        for (int i = 0; i < pakkarekisteri.getPakatLkm(); i++) {
            Pakka pakka = pakkarekisteri.anna(i);
            if (pakka.getID() == pid) indeksi = i;
            chooserPakat.add(pakka.getNimi(), pakka);
        }
        chooserPakat.setSelectedIndex(indeksi);
    }
    
    /**
     * Näyttää sen pakan tiedot, jonka nimeä on klikattu.
     */
    private void naytaPakka() {
        Pakka p = chooserPakat.getSelectedObject();
        labelPakanNimi.setText(p.getNimi());
        labelPakanTyyppi.setText(pakkarekisteri.etsiTyyppi(p.getTyyppi()));
        labelPakanMp.setText(p.getMuistiinpanot());
        naytaPakanKortit(p);
    }
    
    
    private void naytaPakanKortit(Pakka pakka) {
        chooserKortit.clear();
        List<Kortti> kortit = new ArrayList<Kortti>(pakkarekisteri.pakanKortit(pakka.getID()));
        for (Kortti k : kortit) {
            chooserKortit.add(k.getNimi(), k);
        }
    }

}