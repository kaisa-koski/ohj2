package pakat;

/**
 * @author Kaisa Koski
 * @version 15.3.2021
 *
 */
public class Tyypit {
    
    private static final int MAX_TYYPIT = 5;
    private Tyyppi[] alkiot;
    private int lkm;
   
    
    /**
     * Luodaan alustava taulukko
     */
    public Tyypit() {
        this.alkiot = new Tyyppi[MAX_TYYPIT]; //TODO: luo nyt suoraan, ehkä myöhemmin tiedostosta?
        lisaa(new Tyyppi(1, "Modern"));
        lisaa(new Tyyppi(2, "Standard"));
        lisaa(new Tyyppi(3, "Pioner"));
        lisaa(new Tyyppi(4, "Commander"));
        lisaa(new Tyyppi(5, "Muu"));
    }
   
    
    /**
     * Lisätään uusi tyyppi. Jos taulukko on jo täynnä, 
     * ei tehdä mitään.
     * @param tyyppi Lisättävä tyyppi
     */
    private void lisaa(Tyyppi tyyppi) {
        if (lkm >= MAX_TYYPIT) return; //TODO: Poikkeus?
        alkiot[lkm] = tyyppi;
        lkm++;
    }
    
    
    /**
     * Etsii ja palauttaa oikean tyypin nimen tyypin 
     * ID:n perusteella.
     * @param tid Tyypin id
     * @return Tyypin nimi
     */
    public String etsi(int tid) {
        for (Tyyppi t : alkiot) {
            if (t.getID() == tid) return t.getNimi();
        }
        return null;
    }

    /**
     * Testataan Tyypit-luokkaa
     * @param args ei käytössä
     */
    public static void main(String[] args) {
    // TODO
    }

}
