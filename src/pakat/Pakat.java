package pakat;

/**
 * @author Kaisa Koski
 * @version 2.3.2021
 *
 */
public class Pakat {
    
    private static final int MAX_PAKKOJA = 5;
    private int lkm = 0;
    private Pakka[] alkiot;
    
    
    /**
     * Luodaan alustava taulukko
     */
    public Pakat() {
        this.alkiot = new Pakka[MAX_PAKKOJA];
    }
    
    /**
     * Uuden pakan lisääminen
     * @param pakka Lisättävä pakka
     * @throws SailoException jos tietorakenne on täynnä
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * Pakat pakat = new pakat();
     * Pakka izzet1 = new Pakka(), izzet2 = new Pakka();
     * pakat.getLkm() === 0;
     * pakat.lisaa(izzet1); pakat.getLkm() === 1;
     * pakat.lisaa(izzet2); pakat.getLkm() === 2;
     * pakat.lisaa(izzet1); pakat.getLkm() === 3;
     * pakat.anna(0) === izzet1;
     * pakat.anna(1) === izzet2;
     * pakat.anna(2) === izzet1;
     * pakat.anna(1) == izzet1 === false;
     * pakat.anna(1) == izzet2 === true;
     * pakat.anna(3) === izzet1; #THROWS IndexOutOfBoundsException
     * pakat.lisaa(izzet1); pakat.getLkm() === 4;
     * pakat.lisaa(izzet1); pakat.getLkm() === 5;
     * pakat.lisaa(izzet1); #THROWS SailoException
     * </pre> 
     */
    public void lisaa(Pakka pakka) throws SailoException {
        if (lkm >= alkiot.length) throw new SailoException("Liikaa alkioita");
        alkiot[lkm] = pakka;
        lkm++;
    }
    
    /**
     * @return palauttaa pakkojen lukumäärän
     */
    public int getLkm() {
        return lkm;
    }

    
    /**
     * Palauttaa pakkaen joukosta pyydetyn pakan.
     * @param i Pakan indeksi
     * @return Pakka pyydetystä indeksistä
     */
    public Pakka anna(int i) {
        if (i < 0 || lkm <= i) throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return alkiot[i];
    }

    /**
     * Pakat-luokan testaaminen
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Pakat pakat = new Pakat();

        Pakka izzet = new Pakka();
        Pakka izzet2 = new Pakka();
        izzet.rekisteroi();
        izzet2.rekisteroi();
        izzet.izzetPakka();
        izzet2.izzetPakka();

        try {
            pakat.lisaa(izzet);
            pakat.lisaa(izzet2);
        } catch (SailoException e) {
            System.err.println(e.getMessage());
        }
        

        System.out.println("========== Pakat - testi ========== ");
        for (int i = 0; i < pakat.getLkm(); i++) {
            Pakka pakka = pakat.anna(i);
            System.out.println("Pakka indeksillä " + i);
            pakka.tulosta(System.out);
            System.out.println();
        }
    
    }

}
