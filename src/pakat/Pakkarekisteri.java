package pakat;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kaisa Koski
 * @version 16.3.2021
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
     * Palauttaa tyypin merkkijonon id:n perusteella.
     * @param tid Tyypin id
     * @return Tyypin merkkijono
     */
    public String etsiTyyppi(int tid) {
        return tyypit.etsi(tid);
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
     * Palauttaa korttilistan, joka sisältää pyydetyn pakan
     * sisältämät kortit.
     * @param pid Pakan indeksi
     * @return Korttilista pakan korteista
     */
    public List<Kortti> pakanKortit(int pid){
        List<Kortti> pakanKortit = new ArrayList<Kortti>();
        List<Integer> kidLista = linkit.pakanKortit(pid);
        for (Integer i : kidLista) {
            for(Kortti k : kortit) {
                if(i.equals(k.getID())) {
                    pakanKortit.add(k);
                    break;
                }
            }
        }
        return pakanKortit;
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
        Pakkarekisteri rekisteri = new Pakkarekisteri();
        Pakka p1 = new Pakka().izzetPakka();
        rekisteri.lisaa(p1);
        System.out.println(rekisteri.linkit);
        rekisteri.tallenna();
        rekisteri.linkit.lataa();
        System.out.println(rekisteri.linkit);
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
