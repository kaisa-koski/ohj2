package fxPakat;

import java.net.URL;
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
import pakat.Linkki;
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
      /*  Dialogs.showMessageDialog(
                "Halusit katsoa kortin " + chooserKortit.getSelectedText()
                        + " tietoja, mutta ei toimi vielä");*/
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
        pakkarekisteri.lataaTiedot();
        haePakat(0);  
    }
    
    /**
     * Alustaa tarvittavat asiat, esimerkiksi lisää kuuntelijan
     * pakkalistalle.
     */
    private void alusta(){
        chooserPakat.clear();
        chooserKortit.clear();
        chooserPakat.addSelectionListener(e -> naytaPakka(chooserPakat));
        chooserKortit.addSelectionListener(e -> naytaKortti());
        chooserKortinPakatTrue.addSelectionListener(e -> naytaPakka(chooserKortinPakatTrue));
        chooserKortinPakatFalse.addSelectionListener(e -> naytaPakka(chooserKortinPakatFalse));
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
        pakkarekisteri.lisaa(uusi);
        if (chooserPakat.getSelectedText().equals("Korttivarasto")){
            Pakka v = chooserPakat.getSelectedObject();
            naytaPakanKortit(v);
        } 
        

       /** var resurssi = PakkarekisteriGUIController.class
                .getResource("UusiKortti.fxml");
        ModalController.showModal(resurssi, "Uusi kortti", null, "");*/
    }
    

    /**
     * Avaa uuden pakan luomiseen ikkunan.
     */
    private void uusiPakka() {
        Pakka uusi = new Pakka().izzetPakka(); //TODO: korvaa myöhemmin izzet-pakka dialogilla
        pakkarekisteri.lisaa(uusi);
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
   * @param valitsin Minkä valitsimen valintaa näytetään
   */
    private void naytaPakka(ListChooser<Pakka> valitsin) {
        Pakka p = valitsin.getSelectedObject();
        labelPakanNimi.setText(p.getNimi());
        labelPakanTyyppi.setText(pakkarekisteri.etsiTyyppi(p.getTyyppi()));
        labelPakanMp.setText(p.getMuistiinpanot());
        naytaPakanKortit(p);
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
            String teksti = String.format(k.getNimi()+" (%d kpl)", l.getKk());
            chooserKortit.add(teksti, k);
        }
      /* List<Kortti> kortit = new ArrayList<Kortti>(pakkarekisteri.pakanKortit(pakka.getID()));
        for (Kortti k : kortit) {
            chooserKortit.add(k.getNimi(), k); //TODO: Näyttämään, kuinka monta kpl pakassa yhteensä
        }*/
    }
    
    /**
     * Näyttää sen kortin tiedot, jonka nimeä on klikattu.
     */
    private void naytaKortti() {
        Kortti k = chooserKortit.getSelectedObject();
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
            if (l.getKp() == 0) chooserKortinPakatFalse.add(p.getNimi(), p);
            else{
                String teksti = String.format("%s (%d kpl)",p.getNimi(), l.getKp());
                chooserKortinPakatTrue.add(teksti, p);
            }
        }
        
     /*   List<Pakka> pakatTrue = new ArrayList<Pakka>(pakkarekisteri.kortinPakat(k.getID(), true));
        List<Pakka> pakatFalse = new ArrayList<Pakka>(pakkarekisteri.kortinPakat(k.getID(), false));
            for (Pakka p : pakatTrue) {
                chooserKortinPakatTrue.add(p.getNimi(), p);
            }
            for (Pakka p : pakatFalse) {
                chooserKortinPakatFalse.add(p.getNimi(), p);
            }*/
        }
    }

