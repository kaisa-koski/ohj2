package fxPakat;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import pakat.Kortti;
import pakat.Linkki;
import pakat.Pakka;
import pakat.Pakkarekisteri;

/**
 * Käyttöliittymän pääikkunan toiminnasta vastaava luokka.
 *  //TODO: Aktivointi ei toimi vielä
 * @author Kaisa Koski
 * @version 22.4.2021
 *
 */
public class PakkarekisteriGUIController implements Initializable {

    @FXML
    private Button buttonAktivoi;
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
    private Label labelKortinNimi;
    @FXML
    private Label labelKortinCmc;
    @FXML
    private Label labelKortinKpl;
    @FXML
    private ListChooser<Pakka> chooserKortinPakatTrue;
    @FXML
    private ListChooser<Pakka> chooserKortinPakatFalse;

    @FXML
    private void handleAktivoi() {
        aktivoi();
    }


    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
    }


    @FXML
    private void handleLisaaKortti() {
        lisaaKorttiPakkaan();
    }


    @FXML
    private void handlePoistaKortti() {
        poistaKorttiPakasta();
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
        /*
         * Dialogs.showMessageDialog(
         * "Halusit katsoa klikkaamasi pakan tietoja, mutta ei toimi vielä");
         */
    }


    @FXML
    private void handleKortti() {
        /*
         * Dialogs.showMessageDialog( "Halusit katsoa kortin " +
         * chooserKortit.getSelectedText() + " tietoja, mutta ei toimi vielä");
         */
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
    private Kortti naytettavaKortti;
    private Pakka naytettavaPakka;

    private final static int EI_PAKASSA = 1;

    /**
     * Asetetaan pakkarekisteri käyttöön
     * @param pakkarekisteri Pakkarekisteri jota käytetään
     */
    public void setRekisteri(Pakkarekisteri pakkarekisteri) {
        this.pakkarekisteri = pakkarekisteri;
        pakkarekisteri.lataaTiedot();
        haePakat(0);
    }


    /**
     * Alustaa tarvittavat asiat, esimerkiksi lisää kuuntelijan
     * pakkalistalle.
     */
    private void alusta() {
        chooserPakat.clear();
        chooserKortit.clear();
        chooserPakat.addSelectionListener(e -> naytaPakka(chooserPakat));
        chooserKortit.addSelectionListener(e -> maaritaNaytettavaKortti());
        chooserKortinPakatTrue
                .addSelectionListener(e -> naytaPakka(chooserKortinPakatTrue));
        chooserKortinPakatFalse
                .addSelectionListener(e -> naytaPakka(chooserKortinPakatFalse));
    }


    /**
     * Tallentamisen tarkistus ennen sulkemista
     * @return true Jos saa sulkea sovelluksen
     */
    public boolean voikoSulkea() {
        tallenna();
        return true;
    }


    /**
     * Tallentaa tiedot tiedostoihin.
     */
    private void tallenna() {
        pakkarekisteri.tallenna();
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
     * Hakee korttia käyttäjän kirjoittaman perusteella.
     */
    private void haeKorttia() {
       String haku = textHaku.getText();
       Kortti haettu = pakkarekisteri.anna(haku);
       if (haettu != null) 
       {
           naytettavaKortti = haettu;
           naytaKortti(haettu);
       }
       
    }


    /**
     * Korttien lisääminen pakkaan
     */
    private void lisaaKorttiPakkaan() {
        if (naytettavaPakka.getID() == EI_PAKASSA) return;
        List<Kortti> pKortit = pakkarekisteri.pakanKortit(naytettavaPakka.getID(), false);
        List<Linkki> lisattavat = LisaaPakkaanController.lisaaKortteja(null, pKortit);
        for (Linkki l : lisattavat) {
            l.lisaaPid(naytettavaPakka.getID());
            pakkarekisteri.lisaa(l);
        }
        naytaPakanKortit(naytettavaPakka);
        naytaKortti(naytettavaKortti);
        paivitaAktiivisuus();
    }


    /**
     * Korttien poistaminen pakasta
     */
    private void poistaKorttiPakasta() { //TODO: EI TOIMI VIELÄ KUNNOLLA, KAATAA OHJELMAN!
        if (naytettavaPakka.getID() == EI_PAKASSA)
            return;
        List<Kortti> pKortit = pakkarekisteri.pakanKortit(naytettavaPakka.getID(), true); 
        List<Kortti> poistettavat = PoistaPakastaController.poistaKortteja(null, pKortit);
        if (poistettavat == null) return;
        for (Kortti k : poistettavat) {
           pakkarekisteri.poista(naytettavaPakka, k);
        }
        naytaPakanKortit(naytettavaPakka);
        paivitaAktiivisuus();
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
        if (naytettavaKortti == null)
            return;
        Kortti muokattava = naytettavaKortti.clone();
        Kortti tulos = MuokkausKorttiController.muokkaaKorttia(null,
                muokattava);
        if (tulos == null) {
            pakkarekisteri.poista(naytettavaKortti);
        } else {
            naytettavaKortti.muokkaa(tulos); //TODO: muokkaus pakkarekisterin kautta
            pakkarekisteri.korttiaMuokattu();
        }
        naytaPakanKortit(naytettavaPakka);
        naytaKortti(naytettavaKortti);
    }


    /**
     * Avaa pakan muokkaukseen liittyvän ikkunan.
     */
    private void muokkaaPakkaa() {
        if (naytettavaPakka == null || naytettavaPakka.getID() == EI_PAKASSA) //TODO: Klooni? Kokeile
            return;
        Pakka muokattava = naytettavaPakka;
        Pakka tulos = MuokkausPakkaController.muokkaaPakkaa(null, muokattava);
        if (tulos == null) {
            pakkarekisteri.poista(naytettavaPakka);
        } else {
            naytettavaPakka.muokkaa(tulos);
            pakkarekisteri.pakkaaMuokattu();
        }
        haePakat(naytettavaPakka.getID());
    }


    /**
     * Avaa uuden kortin luomiseen liittyvän ikkunan.
     */
    private void uusiKortti() {
        Kortti uusi = null;
        uusi = UusiKorttiController.kysyKortti(null, uusi);
        if (uusi == null)
            return;
        pakkarekisteri.lisaa(uusi);
        naytaKortti(uusi);
        naytaPakanKortit(naytettavaPakka);
    }


    /**
     * Avaa uuden pakan luomiseen ikkunan.
     */
    private void uusiPakka() {
        Pakka uusi = null;
        uusi = UusiPakkaController.kysyPakka(null, uusi);
        if (uusi == null)
            return;
        pakkarekisteri.lisaa(uusi);
        haePakat(uusi.getID());
        if (chooserPakat.getSelectedText().equals("Korttivarasto")) {
            Pakka v = chooserPakat.getSelectedObject();
            naytaPakanKortit(v);
            haePakat(uusi.getID());
        }
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
            if (pakka.getID() == pid)
                indeksi = i;
            chooserPakat.add(pakka.getNimi(), pakka);
        }
        chooserPakat.setSelectedIndex(indeksi); // TODO: Tähän ehkä tarvii myös
                                                // naytettavaPakka = pakka;
    }


    /**
     * Näyttää sen pakan tiedot, jonka nimeä on klikattu.
     * @param valitsin Minkä valitsimen valintaa näytetään
     */
    private void naytaPakka(ListChooser<Pakka> valitsin) { // TODO: Vaikeudet
                                                           // eri listojen
                                                           // välillä
        Pakka p = valitsin.getSelectedObject();
        naytettavaPakka = p;
        labelPakanNimi.setText(p.getNimi());
        labelPakanTyyppi.setText(pakkarekisteri.etsiTyyppi(p.getTyyppi()));
        labelPakanMp.setText(p.getMuistiinpanot());
        naytaPakanKortit(p);
        paivitaAktiivisuus();
    }
    
    /**
     * Päivittää näkyvillä olevan pakan aktiivisuuden
     */
    private void paivitaAktiivisuus() {
        if (pakkarekisteri.onkoAktiivinen(naytettavaPakka)) {
            buttonAktivoi.setText("Aktiivinen");
            buttonAktivoi.setTextFill(Color.GREEN);
        } else {
            buttonAktivoi.setText("AKTIVOI");
            buttonAktivoi.setTextFill(Color.RED);
        }
    }


    /**
    * Näyttää klikattuun pakkaan kuuluvat kortit. 
    * @param pakka Pakka jonka kortteja listataan
    */
    private void naytaPakanKortit(Pakka pakka) {
        chooserKortit.clear();
        List<Linkki> linkit = pakkarekisteri.pakanLinkit(pakka.getID());
        for (Linkki l : linkit) {
            Kortti k = pakkarekisteri.getKortti(l.getKID());
            String teksti = String.format(k.getNimi() + " (%d kpl)", l.getKk());
            chooserKortit.add(teksti, k);
        }
    }


    /**
     * Määrittää näytettävän kortin
     * @param k Näytettävä kortti (jota on klikattu)
     */
    private void maaritaNaytettavaKortti() {
        naytettavaKortti = chooserKortit.getSelectedObject();
        naytaKortti(naytettavaKortti);
    }


    /**
     * Näyttää kortin tiedot.
     * @param k Näytettävä kortti
     */
    private void naytaKortti(Kortti k) {
        if (naytettavaKortti == null)
            return;
        labelKortinNimi.setText(k.getNimi());
        labelKortinCmc.setText("Cmc: " + k.getCmc());
        labelKortinKpl.setText("Omistuksessa: " + k.getMaara() + " kpl");
        naytaKortinPakat(k);
    }


    /**
     * Näyttää pakat, joihin kortti kuuluu
     * @param k Kortti
     */
    private void naytaKortinPakat(Kortti k) {
        chooserKortinPakatTrue.clear();
        chooserKortinPakatFalse.clear();
        List<Linkki> linkit = pakkarekisteri.kortinLinkit(k.getID());
        for (Linkki l : linkit) {
            Pakka p = pakkarekisteri.getPakka(l.getPID());
            if (l.getKp() == 0)
                chooserKortinPakatFalse.add(p.getNimi(), p);
            else {
                String teksti = String.format("%s (%d kpl)", p.getNimi(),
                        l.getKp());
                chooserKortinPakatTrue.add(teksti, p);
            }
        }

    }
}
