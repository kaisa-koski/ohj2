package pakat;

/**
 * @author Kaisa Koski
 * @version 2.3.2021
 *
 */
public class Tyyppi {
    
    private int tid;
    private String nimi = "";
    

    /**
     * Tyypin alustus
     * @param tid Tyypin id-numero
     * @param nimi Tyypin nimi
     */
    public Tyyppi(int tid, String nimi) {
        this.tid = tid;
        this.nimi = nimi;
    }
    
    /**
     * @return palauttaa tyypin ID:n
     */
    public int getID() {
        return this.tid;
    }
    
    /**
     * @return palauttaa typpin nimen
     */
    public String getNimi() {
        return this.nimi;
    }
    
    
    
    /**
     * Testataan tyyppi-luokkaa
     * @param args ei käytössä
     */
    public static void main(String[] args) {
    // TODO Auto-generated method stub
    
    }

}
