package pakat;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * (CRC-kortin tiedot tähän)
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
    public Kortti(String nimi, int cmc, int maara) {
        this.nimi = nimi;
        this.cmc = cmc;
        this.maara = maara;
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
     */
    public void jaceKortti() {
        this.nimi = "Jace, the Mind Sculptor" + rand(1000, 9999);
        this.cmc = 4;
        this.maara = 1;
    }


    /**
     * Tulostetaan kortin tiedot
     * @param out Tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println("Kortin ID: " + kid);
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
     * Testataan Kortti-luokkaa.
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Kortti jace = new Kortti();
        Kortti mage = new Kortti();

        jace.rekisteroi();
        mage.rekisteroi();
        jace.jaceKortti();;
        mage.jaceKortti();

        jace.tulosta(System.out);
        System.out.println();
        mage.tulosta(System.out);

    }
}