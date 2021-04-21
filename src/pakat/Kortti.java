package pakat;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Pitää yllä yksittäisen kortin tietoja.
 * @author Kaisa Koski
 * @version 24.2.2021
 *
 */
public class Kortti {

    private int kid;
    private String nimi = "";
    private int cmc;
    private int maara;

    private static int seuraavaID = 1;

    /**
     * Tyhjan kortin luominen
     */
    public Kortti() {
        //
    }


    /**
     * Uuden kortin luominen
     * @param nimi kortin nimi
     * @param cmc kortin converted mana cost
     * @param maara kuinka monta kpl samaa korttia omistuksessa
     */
    public Kortti(String nimi, int maara, int cmc) {
        String korjattu = Tarkistus.mjTarkistus(nimi);
        this.nimi = korjattu;
        this.maara = maara;
        this.cmc = cmc;
    }
    
    /**
     * Tietojen poimiminen merkkijonosta 
     * @param mj Merkkijono
     * @throws NumberFormatException Jos epäkelpo merkkijono
     */
    public void parse(String mj) throws NumberFormatException {
        String[] palat = mj.split("\\|");
        setID(Integer.parseInt(palat[0]));
        nimi = palat[1];
        maara = Integer.parseInt(palat[2]);
        cmc = Integer.parseInt(palat[3]);
    }
    
    
    /**
     * Asettaa tunnusnumeron ja tarkistaa, että seuraavaID
     * pysyy ajan tasalla.
     * @param luku Asetettava id-numero
     */
    private void setID(int luku) {
        kid = luku;
        if (kid >= seuraavaID) seuraavaID = kid + 1;
    }


    /**
     * Palauttaa satunnaisen luvun pyydetyltä väliltä
     * @param ala alaraja
     * @param yla yläraja
     * @return satunnainen luku
     */
    public static int rand(int ala, int yla) {
        double n = (yla - ala) * Math.random() + ala;
        return (int) Math.round(n);
    }


    /**
     * Luo Jace-kortin (testaamista varten)
     * @return Testikortti
     */
    public Kortti jaceKortti() {
        this.nimi = "Jace, the Mind Sculptor" + rand(1000, 9999);
        this.cmc = 4;
        this.maara = 1;
        return this;
    }


    /**
     * Luo Snapcaster Mage -kortin (testaamista varten)
     * @return Testikortti
     */
    public Kortti mageKortti() {
        this.nimi = "Snapcaster Mage" + rand(1000, 9999);
        this.cmc = 2;
        this.maara = 2;
        return this;
    }


    /**
     * Tulostetaan kortin tiedot
     * @param out Tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println("Kortin ID: " + String.format("%03d", kid));
        out.println("Kortin nimi: " + nimi);
        out.println("Cmc: " + cmc);
        out.println("Määrä: " + maara + " kpl");
    }


    /**
     * Tulostetaan kortin tiedot
    * @param os Tietovirta johon tulostetaan
    */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }


    /**
     * Asetetaan kortille seuraava vapaana oleva ID-numero.
     * @return Kortille annettu ID-numero
     * @example
     * <pre name="test">
     * Kortti kortti1 = new Kortti();
     * kortti1.rekisteroi();
     * Kortti kortti2 = new Kortti();
     * kortti2.rekisteroi();
     * int n1 = kortti1.getID();
     * int n2 = kortti2.getID();
     * n2 === n1+1;
     * </pre>
     */
    public int rekisteroi() {
        if (this.kid != 0) return this.kid;
        this.kid = seuraavaID;
        seuraavaID++;
        return this.kid;
    }


    /**
     * @return palauttaa kortin id-numeron
     */
    public int getID() {
        return this.kid;
    }


    /**
     * @return palauttaa kortin nimen
     */
    public String getNimi() {
        return this.nimi;
    }


    /**
     * @return palauttaa kortin cmc-luvun
     */
    public int getCmc() {
        return this.cmc;
    }


    /**
     * @return palauttaa kortin määrän
     */
    public int getMaara() {
        return this.maara;
    }
    


    @Override
    public String toString() {
        return kid + "|" + nimi + "|" + maara + "|" + cmc;
    }


    /**
     * Merkkijono testaamisen avuksi
     * @return Merkkijono pakan tiedoista
     */
    public String testiString() {
        return "ID: " + String.format("%03d", kid) + "\n Kortin nimi: "
                + this.nimi + "\n Cmc: " + this.cmc + "\n Määrä: " + this.maara
                + " kpl\n";
    }


    /**
     * Testataan Kortti-luokkaa.
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Kortti jace = new Kortti();
        Kortti mage = new Kortti();

        jace.rekisteroi();
        mage.rekisteroi();
        jace.jaceKortti();
        
        mage.mageKortti();

        jace.tulosta(System.out);
        System.out.println();
        mage.tulosta(System.out);
        System.out.println(jace);
        System.out.println(mage);
    }
}
