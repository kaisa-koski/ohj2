package pakat;

/**
 * Luokassa on erilaisia tarkistusaliohjelmia
 * @author Kaisa Koski
 * @version 2.3.2021
 *
 */
public class Tarkistus {

    
    /**
     * Poistaa merkkijonosta pystyviivat, jotka voisivat häiritä
     * tiedoston lukemista ja kirjoittamista. Poistaa myös mahdolliset
     * useammat välilyönnit sanojen välistä ja kaikki välilyönnit
     * alusta ja lopusta.
     * @param mj Syötteenä saatu nimi
     * @return nimi josta on poistettu mahdolliset |-merkit
     * @example
     * <pre name="test">
     * String huonoNimi = "Snapcaster | Mage";
     * String parempiNimi = mjTarkistus(huonoNimi);
     * parempiNimi === "Snapcaster Mage";
     * String h2 = "Snapcaster    Mage ";
     * String p2 = mjTarkistus(h2);
     * p2 === "Snapcaster Mage";
     * </pre>
     */
    public static String mjTarkistus(String mj) {
        String korjattuMj = mj.replace("|", "").trim().replaceAll(" +", " ");
        return korjattuMj;
    }
    
    
    /**
     * Tarkistus-aliohjelmian testaaminen
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        String huonoNimi = "   Ville   | Vallaton   ";
        System.out.println("Esimerkki huonosta nimestä: " + huonoNimi);
        String parempi = mjTarkistus(huonoNimi);
        System.out.println("Nimi korjattuna: " + parempi);
    }

}
