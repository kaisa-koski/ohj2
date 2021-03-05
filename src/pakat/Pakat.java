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
     * Luodaan alustava taulukko, jossa on automaattisesti
     * "Ei pakassa" -pakka. 
     */
    public Pakat() {
        this.alkiot = new Pakka[MAX_PAKKOJA];
        lisaaEiPakassa();
    }
    
    
    /**
     * Luo Ei pakassa -nimisen pakan ja lisää sen pakkoihin.
     */
    public void lisaaEiPakassa() {
        Pakka eiPakassa = new Pakka("Ei pakassa", 5, "Kortit, jotka eivät tällä hetkellä ole missään pakassa");
        try {
            this.lisaa(eiPakassa);
        } catch (SailoException e) {
            System.err.println(e.getMessage()); //TODO: Lisätäänkö samalla tavalla "Kaikki kortit"?
        }
    }
    
    
    /**
     * Uuden pakan lisääminen
     * @param pakka Lisättävä pakka
     * @throws SailoException jos tietorakenne on täynnä
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * Pakat pakat = new Pakat();
     * Pakka izzet1 = new Pakka(), izzet2 = new Pakka();
     * pakat.getLkm() === 0+1;
     * pakat.lisaa(izzet1); pakat.getLkm() === 1+1;
     * pakat.lisaa(izzet2); pakat.getLkm() === 2+1;
     * pakat.lisaa(izzet1); pakat.getLkm() === 3+1;
     * pakat.anna(0+1) === izzet1;
     * pakat.anna(1+1) === izzet2;
     * pakat.anna(2+1) === izzet1;
     * pakat.anna(1+1) == izzet1 === false;
     * pakat.anna(1+1) == izzet2 === true;
     * pakat.anna(3+1) === izzet1; #THROWS IndexOutOfBoundsException
     * pakat.lisaa(izzet1); pakat.getLkm() === 4+1;
     * pakat.lisaa(izzet1);  #THROWS SailoException      
     * </pre> 
     */
    public void lisaa(Pakka pakka) throws SailoException {
        if (lkm >= alkiot.length) throw new SailoException("Liikaa alkioita");
        pakka.rekisteroi();
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
     * Testaamisen avuksi pakkojen tiedot merkkijonona.
     * @return Pakkojen tiedot merkkijonoina
     */
    public String testiString() {
      StringBuilder sb = new StringBuilder();
      for (Pakka pakka : alkiot) {
          if (pakka == null) continue;
           sb.append(pakka.testiString());
        }
      return sb.toString();
    }

    /**
     * Pakat-luokan testaaminen
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Pakat pakat = new Pakat();

        Pakka izzet = new Pakka();
        Pakka izzet2 = new Pakka();;
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
