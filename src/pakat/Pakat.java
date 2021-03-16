package pakat;

/**
 * @author Kaisa Koski
 * @version 16.3.2021
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
        this.lisaa(eiPakassa);
    }
    
    
    /**
     * Uuden pakan lisääminen, lisätään tilaa
     * jos tietorakenne on täynnä.
     * @param pakka Lisättävä pakka
     * @example
     * <pre name="test">
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
     * pakat.lisaa(izzet1); pakat.getLkm() > 5 === true;     
     * </pre> 
     */
    public void lisaa(Pakka pakka) {
        if (lkm >= alkiot.length) lisaaTilaa();
        pakka.rekisteroi();
        alkiot[lkm] = pakka;
        lkm++;
    }
    
    /**
     * Tilan lisääminen pakoille.
     * @example
     * <pre name="test">
     * Pakat pakat = new Pakat();
     * pakat.getLkm() === 1;
     * pakat.getKoko() === 5;
     * pakat.lisaa(new Pakka()); pakat.lisaa(new Pakka());
     * pakat.lisaa(new Pakka()); pakat.lisaa(new Pakka());
     * pakat.lisaa(new Pakka()); pakat.getLkm() === 6;
     * pakat.getKoko() === 10;
     * </pre>
     */
    public void lisaaTilaa() {
        Pakka[] uusi = new Pakka[lkm + MAX_PAKKOJA];
        for (int i = 0; i < lkm; i++) {
         uusi[i] = alkiot[i];
     }
        this.alkiot = uusi;
    }
    
    /**
     * @return palauttaa pakkojen lukumäärän
     */
    public int getLkm() {
        return lkm;
    }
    
    /**
     * Palauttaa alkiotaulukon pituuden (testaamista varten)
     * @return Alkiotaulukon pituus
     */
    public int getKoko() {
        return alkiot.length;
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

   
            pakat.lisaa(izzet);
            pakat.lisaa(izzet2);
      
        

        System.out.println("========== Pakat - testi ========== ");
        for (int i = 0; i < pakat.getLkm(); i++) {
            Pakka pakka = pakat.anna(i);
            System.out.println("Pakka indeksillä " + i);
            pakka.tulosta(System.out);
            System.out.println();
        }
    
       
    }

}
