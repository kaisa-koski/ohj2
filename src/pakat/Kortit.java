/**
 * 
 */
package pakat;

/**
 * @author Kaisa Koski
 * @version 24.2.2021
 *
 */
public class Kortit {

    private static final int MAX_KORTTEJA = 5;
    private int lkm = 0;
    private Kortti[] alkiot;

    /**
     * Luodaan alustava taulukko
     */
    public Kortit() {
        alkiot = new Kortti[MAX_KORTTEJA];
    }


    /**
     * Uuden kortin lisääminen
     * @param kortti Lisättävä kortti
     * @throws SailoException jos tietorakenne on täynnä
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * Kortit kortit = new Kortit();
     * Kortti jace1 = new Kortti(), jace2 = new Kortti();
     * kortit.getLkm() === 0;
     * kortit.lisaa(jace1); kortit.getLkm() === 1;
     * kortit.lisaa(jace2); kortit.getLkm() === 2;
     * kortit.lisaa(jace1); kortit.getLkm() === 3;
     * kortit.anna(0) === jace1;
     * kortit.anna(1) === jace2;
     * kortit.anna(2) === jace1;
     * kortit.anna(1) == jace1 === false;
     * kortit.anna(1) == jace2 === true;
     * kortit.anna(3) === jace1; #THROWS IndexOutOfBoundsException
     * kortit.lisaa(jace1); kortit.getLkm() === 4;
     * kortit.lisaa(jace1); kortit.getLkm() === 5;
     * kortit.lisaa(jace1); #THROWS SailoException
     * </pre> 
     */
    public void lisaa(Kortti kortti) throws SailoException {
        if (lkm >= alkiot.length) throw new SailoException("Liikaa alkioita");
        alkiot[lkm] = kortti;
        lkm++;
    }
    
    
    /**
     * @return korttien lukumäärän
     */
    public int getLkm() {
        return lkm;
    }

    
    /**
     * Palauttaa korttien joukosta pyydetyn kortin.
     * @param i kortin indeksi
     * @return kortti pyydetystä indeksistä
     */
    public Kortti anna(int i) {
        if (i < 0 || lkm <= i) throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return alkiot[i];
    }
    

    /**
     * Testataan Kortit-luokkaa.
     * @param args ei käytössä
     */
    public static void main(String[] args) {

        Kortit kortit = new Kortit();

        Kortti jace = new Kortti();
        Kortti jace2 = new Kortti();
        jace.rekisteroi();
        jace2.rekisteroi();
        jace.jaceKortti();
        jace2.jaceKortti();

        try {
            kortit.lisaa(jace);
            kortit.lisaa(jace2);
        } catch (SailoException e) {
            System.err.println(e.getMessage());
        }
        

        System.out.println("========== Kortit - testi ========== ");
        for (int i = 0; i < kortit.getLkm(); i++) {
            Kortti kortti = kortit.anna(i);
            System.out.println("Kortti indeksillä " + i);
            kortti.tulosta(System.out);
            System.out.println();
        }

    }

}
