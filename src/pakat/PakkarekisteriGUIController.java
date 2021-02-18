package pakat;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Käyttöliittymän pääikkunan toiminnasta vastaava luokka.
 *  
 * @author Kaisa Koski
 * @version 15.2.2021
 *
 */
public class PakkarekisteriGUIController {

    @FXML
    private ListChooser<String> listPakat;
    @FXML
    private ListChooser<String> listKortit;
    @FXML
    private TextField textHaku;

    @FXML
    private void handleAktivoi() {
        aktivoi();
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
        Dialogs.showMessageDialog(
                "Halusit katsoa klikkaamasi pakan tietoja, mutta ei toimi vielä");
    }


    @FXML
    private void handleKortti() {
        Dialogs.showMessageDialog(
                "Halusit katsoa kortin " + listKortit.getSelectedText()
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
        var resurssi = PakkarekisteriGUIController.class
                .getResource("UusiKortti.fxml");
        ModalController.showModal(resurssi, "Uusi kortti", null, "");
    }

    /**
     * Avaa uuden pakan luomiseen ikkunan.
     */
    private void uusiPakka() {
        var resurssi = PakkarekisteriGUIController.class
                .getResource("UusiPakka.fxml");
        ModalController.showModal(resurssi, "UusiPakka", null, "");
    }

}