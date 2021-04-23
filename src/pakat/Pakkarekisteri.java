package pakat;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kaisa Koski
 * @version 23.4.2021
 *
 */
public class Pakkarekisteri {

    private Linkit linkit;
    private Tyypit tyypit;
    private Pakat pakat;
    private Kortit kortit;
  //  private final String kansio; //TODO: Kansion nimen määrittäminen?

    /**
     * Pakkarekisterin luominen
     */
    public Pakkarekisteri() {
        this.linkit = new Linkit();
        this.tyypit = new Tyypit();
        this.pakat = new Pakat();
        this.kortit = new Kortit();
    }
    
    /**
     * Ladataan tiedot tallennustiedostosta.
     */
    public void lataaTiedot() {
        linkit.lataa();
        pakat.lataa();
        kortit.lataa();
    }
    
    
    /**
     * Tallennetaan tiedot tiedostoihin.
     */
    public void tallenna() {
        linkit.tallenna();
        pakat.tallenna();
        kortit.tallenna();
    }
    
    /**
     * @return Palauttaa pakkojen lukumäärän
     */
    public int getPakatLkm() {
        return pakat.getLkm();
    }
    
    /**
     * Palauttaa pakan tietystä indeksistä.
     * @param i Indeksi
     * @return Pakka pyydetystä indeksistä
     */
    public Pakka anna(int i) {
        return pakat.anna(i);
    }
    
    /**
     * Palauttaa annetulla merkkijonolla alkavan
     * kortin, jos sellainen löytyy.
     * @param nimi Kortin nimi/nimen alkuosa
     * @return Kortti oikealal nimellä
     */
    public Kortti anna(String nimi) {
        return kortit.anna(nimi);
    }


    /**
     * Uuden kortin lisääminen
     * @param kortti Lisättävä kortti
     */
    public void lisaa(Kortti kortti) {
        this.kortit.lisaa(kortti);
        this.linkit.lisaaEiPakassa(kortti.getID(),kortti.getMaara());
    }
    
    /**
     * Uuden pakan lisääminen
     * @param pakka Lisättävä pakka
     */
    public void lisaa(Pakka pakka) {
        this.pakat.lisaa(pakka);
    }
    
    
    /**
     * Uuden linkin lisääminen
     * @param linkki Lisättävä linkki
     */
    public void lisaa(Linkki linkki) {
        linkit.lisaa(linkki);
    }
    
    
    /**
     * Kortin poistaminen
     * @param kortti Poistettava kortti
     */
    public void poista(Kortti kortti) {
        int kid = kortti.getID();
        kortit.poista(kortti);
        linkit.poistaKortinLinkit(kid);
    }
    
    /**
     * Pakan poistaminen
     * @param pakka Poistettava pakka
     */
    public void poista(Pakka pakka) {
        int pid = pakka.getID();
        pakat.poista(pakka);
        linkit.poistaPakanLinkit(pid);
    }
    
    /**
     * Poistaa pakan ja kortin linkin. Siirtää tarvittaessa pakassa
     * sijainneet kortit korttivarastoon.
     * @param pakka Pakka 
     * @param kortti Kortti
     */
    public void poista(Pakka pakka, Kortti kortti) {
        linkit.poista(pakka.getID(), kortti.getID());
    }


    /**
     * Lisää kortin pakkaan eli luo uuden linkin pakan ja kortin
     * id-tiedoilla ja lukumäärillä.
     * @param pid Pakan id
     * @param kid Kortin id
     * @param lkm Montako pakkaan kuuluu
     */
    public void korttiPakkaan(int pid, int kid, int lkm) {
        Linkki linkki = new Linkki(pid, kid, lkm); 
        this.linkit.lisaa(linkki);
    }
    
    /**
     * Asettaa korttien muokattu -muuttujan todeksi
     */
    public void korttiaMuokattu() {
        kortit.muokattu();
    }
    
    /**
     * Asettaa pakkojen muokattu -muuttujan todeksi
     */
    public void pakkaaMuokattu() {
        pakat.muokattu();
    }
    
    
    /**
     * Palauttaa tyypin merkkijonon id:n perusteella.
     * @param tid Tyypin id
     * @return Tyypin merkkijono
     */
    public String etsiTyyppi(int tid) {
        return tyypit.etsi(tid);
    }
    
    
    /**
     * Palauttaa tiedon pakan aktiivisuudesta = onko siinä
     * kaikki tarvittavat kortit sillä hetkellä
     * @param p Pakka
     * @return Onko pakka aktiivinen
     */
    public boolean onkoAktiivinen(Pakka p) {
        return linkit.onkoAktiivinen(p.getID());
    }
    
    /**
     * Palauttaa kortin, jolla on parametrina annettu
     * id.
     * @param kid Kortin id
     * @return Kortti annetulla id:llä
     */
    public Kortti getKortti(int kid) {
        return kortit.getKortti(kid);
    }
    
    /**
     * Palauttaa pakan, jolla on parametrina annettu
     * id.
     * @param pid Pakan id
     * @return Pakka annetulla id:llä
     */
    public Pakka getPakka(int pid) {
        return pakat.getPakka(pid);
    }

    /**
     * Palauttaa linkkilistan, joka sisältää parametrina annettuun
     * pakan ID:seen liittyvät linkit.
     * @param pid Pakan indeksi
     * @return Korttilista pakan korteista
     */
    public List<Linkki> pakanLinkit(int pid){
        return linkit.pakanLinkit(pid);
    }
    
    /**
     * Palauttaa linkkilistan, joka sisältää parametrina annettuun
     * pakan ID:seen liittyvät linkit.
     * @param kid Kortin indeksi
     * @return Korttilista pakan korteista
     */
    public List<Linkki> kortinLinkit(int kid){
        return linkit.kortinLinkit(kid);
    }
    
    
    /**
     * Palauttaa korttilistan, joka sisältää joko pyydetyn pakan
     * sisältämät kortit tai ne joita siinä ei ole ollenkaan.
     * @param pid Pakan indeksi
     * @param kuuluuPakkaan Palautetaanko pakkaan kuuluvat vai ei-kuuluvat kortit
     * @return Korttilista pakan korteista
     */
    public List<Kortti> pakanKortit(int pid, boolean kuuluuPakkaan) {
        List<Kortti> pKortit = new ArrayList<Kortti>();
        List<Integer> kidLista = linkit.pakanKortit(pid);
        if (kuuluuPakkaan) {
            for (Integer i : kidLista) {
                for (Kortti k : kortit) {
                    if (i.equals(k.getID())) {
                        pKortit.add(k);
                        break;
                    }
                }
            }
        } else {
            for (Kortti k : kortit) {
                boolean lisataanko = true;
                for (Integer i : kidLista) {
                    if (i.equals(k.getID())) {
                        lisataanko = false;
                        break;
                    }
                }
                if (lisataanko) pKortit.add(k); //TODO: Ei ehkä toimi vielä halutulla tavalla!
            }
        }
        return pKortit;
    }
    
    /**
     * Palauttaa pakkalistan, joka sisältää pyydetyn kortin
     * pakat kortit.
     * @param kid Kortti jonka pakkalista palautetaan
     * @param sijainti Annetaanko pakat, joissa sijaistsee vai ei
     * @return Pakkalista
     */
    public List<Pakka> kortinPakat(int kid, boolean sijainti){
        List<Pakka> kortinPakat = new ArrayList<Pakka>();
        List<Integer> pidLista = linkit.kortinPakat(kid, sijainti); //TODO: Jos aikaa, voi tehdä iteraattorin
        for (Integer i : pidLista) {
            for(int j = 0; j < pakat.getLkm(); j++) {
                Pakka p = pakat.anna(j);
                if(i.equals(p.getID())) {
                    kortinPakat.add(p);
                    break;
                }
            }
        }
        return kortinPakat;
    }
    
    
    /**
     * Tulostaa pakkojen ja korttien tiedot (testaamisen avuksi)
     */
    public void testiTulostus() {
        System.out.println("Pakat:");
        System.out.println(this.pakat.testiString());
        System.out.println("Kortit:");
        System.out.println(this.kortit.testiString());
    }

    /**
     * Testataan pakkarekisteri-luokan toimintaa.
     * @param args ei käytössä
     */
    public static void main(String[] args) {
            //
    }
    


}
    
    
/**    Pakkarekisteri pakkarekisteri = new Pakkarekisteri(); //Aiempi testipääohjelma

    Kortti jace = new Kortti(); //Käyttäjä luo ensin kortteja
    jace.jaceKortti();

    Kortti jace2 = new Kortti();
    jace2.jaceKortti();
    
    Kortti mage1 = new Kortti();
    mage1.mageKortti();
    
    Kortti mage2 = new Kortti();
    mage2.mageKortti();

    try {
        pakkarekisteri.lisaa(jace);
        pakkarekisteri.lisaa(jace2);
        pakkarekisteri.lisaa(mage1);
        pakkarekisteri.lisaa(mage2);
    } catch (SailoException e) {
        e.printStackTrace();
    }
    
    Pakka pakka1 = new Pakka("Eka pakka", 1, "Ensimmäinen esimerkki"); //Käyttäjä luo muutaman pakan
    Pakka pakka2 = new Pakka("Toka pakka", 2, "Toinen esimerkki");
    try {
        pakkarekisteri.lisaa(pakka1);
        pakkarekisteri.lisaa(pakka2);
    } catch (SailoException e) {
        e.printStackTrace();
    }
    
    pakkarekisteri.testiTulostus();
    List<Integer> kidlista = pakkarekisteri.linkit.pakanKortit(1);
    System.out.println("Tällä hetkellä ei pakassa:");
    for (int i = 0; i < kidlista.size(); i++) {
        int kid = kidlista.get(i);
        System.out.println(pakkarekisteri.kortit.annaNimi(kid));
    }
    System.out.println();
    
    pakkarekisteri.korttiPakkaan(2, 1, 1); //Käyttäjä lisää yhden eli kaikki ID 1 -kortit Eka pakkaan
    kidlista = pakkarekisteri.linkit.pakanKortit(1);
    System.out.println("Lisäyksen jälkeen ei pakassa:");
    for (int i = 0; i < kidlista.size(); i++) {
        int kid = kidlista.get(i);
        System.out.println(pakkarekisteri.kortit.annaNimi(kid));
    }
    System.out.println();
    
    List<Integer> kidlista2 = pakkarekisteri.linkit.pakanKortit(2);
    System.out.println("Tällä hetkellä ekassa pakassa:");
    for (int i = 0; i < kidlista2.size(); i++) {
        int kid = kidlista2.get(i);
        System.out.println(pakkarekisteri.kortit.annaNimi(kid));
    }
    System.out.println();
    
    pakkarekisteri.korttiPakkaan(3, 3, 1); //Käyttäjä lisää yhden eli ei kaikkia ID 3 -kortin Toka pakkaan
    kidlista = pakkarekisteri.linkit.pakanKortit(1);
    System.out.println("Lisäyksen jälkeen ei pakassa:");    //Nyt ID 3 -kortti näkyy myös Ei pakassa, sillä toisen kappaleen sijainti on siellä
    for (int i = 0; i < kidlista.size(); i++) {
        int kid = kidlista.get(i);
        System.out.println(pakkarekisteri.kortit.annaNimi(kid));
    }
    System.out.println();
    
    kidlista2 = pakkarekisteri.linkit.pakanKortit(3);
    System.out.println("Tällä hetkellä tokassa pakassa:");
    for (int i = 0; i < kidlista2.size(); i++) {
        int kid = kidlista2.get(i);
        System.out.println(pakkarekisteri.kortit.annaNimi(kid));
    }
*/
