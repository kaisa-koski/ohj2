/**
 * 
 */
package pakat;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Pitää yllä yksittäisen pakan tiedot.
 * @author Kaisa Koski
 * @version 16.3.2021
 *
 */
public class Pakka {

    private int pid;
    private String nimi = "";
    private int tyyppi;
    private String muistiinpanot = "";

    private static int seuraavaID = 1;

    /**
     * Tyhjän pakan luominen.
     */
    public Pakka() {
        //
    }


    /**
     * Uuden pakan luominen
     * @param nimi Pakan nimi
     * @param tyyppi Pakan tyypin ID-numero
     * @param muistiinpanot Pakan muistiinpanot
     */
    public Pakka(String nimi, int tyyppi, String muistiinpanot) {
        String korjattuNimi = Tarkistus.mjTarkistus(nimi);
        String korjattuMp =Tarkistus.mjTarkistus(muistiinpanot);
        this.nimi = korjattuNimi;
        this.tyyppi = tyyppi;
        this.muistiinpanot = korjattuMp;
    }


    /**
     * Asetetaan pakalle seuraava vapaana oleva ID-numero.
     * @return Pakalle annettu ID-numero
     * @example
     * <pre name="test">
     * Pakka pakka1 = new Pakka();
     * pakka1.rekisteroi();
     * Pakka pakka2 = new Pakka();
     * pakka2.rekisteroi();
     * int n1 = pakka1.getID();
     * int n2 = pakka2.getID();
     * n2 === n1+1;
     * </pre>
     */
    public int rekisteroi() {
        this.pid = seuraavaID;
        seuraavaID++;
        return this.pid;
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
     * Luo Izzet Control-pakan (testaamista varten)
     * @return testipakka
     */
    public Pakka izzetPakka() {
        this.nimi = "Izzet Control" + rand(1000, 9999);
        this.tyyppi = 1;
        this.muistiinpanot = "Sininen kotelo" + rand(1000, 9999);
        return this;
    }


    /**
     * @return palauttaa pakan id-numeron
     */
    public int getID() {
        return this.pid;
    }


    /**
     * @return palauttaa pakan nimen
     */
    public String getNimi() {
        return this.nimi;
    }


    /**
     * @return palauttaa pakan id-numeron
     */
    public int getTyyppi() {
        return this.tyyppi;
    }


    /**
     * @return palauttaa pakan nimen    
     */
    public String getMuistiinpanot() {  //TODO: Mieti, tarviiko näitä kaikkia gettereitä
        return this.muistiinpanot;
    }
    
    @Override
    /**
     * Merkkijono tiedostoon tallentamista varten
     * 
     * @example
     * <pre name="test">
     * Pakka pakka1 = new Pakka("Ekapakka", 2, "Eka");
     * Pakka pakka2 = new Pakka("Tokapakka", 3, "Toka");
     * pakka1.rekisteroi();
     * pakka2.rekisteroi();
     * pakka1.toString() === "1|Ekapakka|2|Eka";
     * pakka2.toString() === "2|Tokapakka|3|Toka";
     * </pre>
     */
    public String toString() {
        return pid + "|" + nimi + "|" + tyyppi + "|" + muistiinpanot;
    }
    
    /**
     * Merkkijono testaamisen avuksi 
     * @return Merkkijono pakan tiedoista
     */
    public String testiString() {
        return "ID: " + this.pid +"\n Pakan nimi: "+ this.nimi +"\n Pakan tyyppi: "+ this.tyyppi+"\n Muistiinpanot: "+muistiinpanot+"\n";
    }


    /**
     * Tulostetaan kortin tiedot
     * @param out Tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println("Pakan ID: " + this.getID()); //TODO: tämä testailua varten, käyttäjä ei tarvitse ID:tä
        out.println("Pakan nimi: " + this.getNimi());
        out.println("Pakan tyyppi: " + this.getTyyppi());
        out.println("Muistiinpanot: " + this.getMuistiinpanot());
    }


    /**
     * Tulostetaan kortin tiedot
    * @param os Tietovirta johon tulostetaan
    */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }


    /**
     * Testataan Pakka-luokkaa
     * @param args ei käytössä
     */

    public static void main(String[] args) {
        Pakka izzet1 = new Pakka();
        izzet1.rekisteroi();
        Pakka izzet2 = new Pakka();
        izzet2.rekisteroi();
        
        izzet1.tulosta(System.out);
        izzet2.tulosta(System.out);
        
        izzet1.izzetPakka();
        izzet2.izzetPakka();
        
        izzet1.tulosta(System.out);
        izzet2.tulosta(System.out);
        
    }

}
