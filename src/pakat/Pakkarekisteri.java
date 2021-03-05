/**
 * 
 */
package pakat;

import java.util.List;

/**
 * @author Kaisa Koski
 * @version 5.3.2021
 *
 */
public class Pakkarekisteri {

    private Linkit linkit;
    private Tyypit tyypit;
    private Pakat pakat;
    private Kortit kortit;

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
     * Uuden kortin lisääminen
     * @param kortti Lisättävä kortti
     * @throws SailoException Poikkeus jos lisättävä kortti ei mahdu
     */
    public void lisaa(Kortti kortti) throws SailoException {
        this.kortit.lisaa(kortti);
        this.linkit.lisaaEiPakassa(kortti);
    }

    
    /**
     * Uuden pakan lisääminen
     * @param pakka Lisättävä pakka
     * @throws SailoException Poikkeus jos lisättävä pakka ei mahdu
     */
    public void lisaa(Pakka pakka) throws SailoException {
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
        Pakkarekisteri pakkarekisteri = new Pakkarekisteri();

        Kortti jace = new Kortti(); //Käyttäkä luo ensin kortteja
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
    }
    


}
