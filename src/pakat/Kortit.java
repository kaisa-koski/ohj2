package pakat;

/**
 * Pitää yllä tietoa kaikista korteista.
 * @author Kaisa Koski
 * @version 19.3.2021
 *
 */
public class Kortit {

    private static final int MAX_KORTTEJA = 10;
    private int lkm = 0;
    private Kortti[] alkiot;

    /**
     * Luodaan alustava taulukko
     */
    public Kortit() {
        alkiot = new Kortti[MAX_KORTTEJA];
    }


    /**
     * Uuden kortin lisääminen, lisätään tilaa
     * jos se loppuu
     * @param kortti Lisättävä kortti
     * @example
     * <pre name="test">
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
     * kortit.lisaa(jace1); kortit.getLkm() === 6;
     * kortit.lisaa(jace1); kortit.getLkm() === 7;
     * kortit.lisaa(jace1); kortit.getLkm() === 8;
     * kortit.lisaa(jace1); kortit.getLkm() === 9;
     * kortit.lisaa(jace1); kortit.getLkm() === 10;
     * kortit.lisaa(jace1); kortit.getLkm() === 11;
     * </pre> 
     */
    public void lisaa(Kortti kortti) {
        if (lkm >= alkiot.length) lisaaTilaa();
        kortti.rekisteroi();
        alkiot[lkm] = kortti;
        lkm++;
    }
    
    
    /**
     * Lisätään korttien tallennustilaa.
     * @example
     * <pre name="test">
     *  Kortit kortit = new Kortit();
     *  Kortti kortti1 = new Kortti("Eka kortti", 1, 1);
     *  Kortti kortti2 = new Kortti("Muut kortit", 2, 2);
     *  kortit.lisaa(kortti1);
     *  kortit.lisaa(kortti2); kortit.lisaa(kortti2);
     *  kortit.lisaa(kortti2); kortit.lisaa(kortti2);
     *  kortit.lisaa(kortti2); kortit.lisaa(kortti2);
     *  kortit.lisaa(kortti2); kortit.lisaa(kortti2);
     *  kortit.lisaa(kortti2); kortit.getLkm() === 10; //alkuperäinen maksimi
     *  kortit.lisaa(kortti2); kortit.getLkm() === 11; //nyt tehty uusi taulukko
     *  kortit.anna(0) === kortti1;
     *  kortit.anna(1) === kortti2; //kopioitu taulukko sisältää myös aiemmin lisätyt
     * </pre>
     */
    public void lisaaTilaa() {
       Kortti[] uusi = new Kortti[this.getLkm() + MAX_KORTTEJA];
       for (int i = 0; i < alkiot.length; i++) {
        uusi[i] = alkiot[i];
    }
       this.alkiot = uusi;
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
     * @example
     * <pre name="test">
     *  Kortit kortit = new Kortit();
     *  Kortti kortti1 = new Kortti("Eka kortti", 1, 1);
     *  Kortti kortti2 = new Kortti("Muut kortit", 2, 2);
     *  kortit.anna(0) === kortti1;
     *  kortit.anna(1) === kortti2
     * </pre>
     */
    public Kortti anna(int i) {
        if (i < 0 || lkm <= i) throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return alkiot[i];
    }
    
    
    /**
     * Palauttaa kortin nimen ID:n perusteella
     * @param kid Kortin id
     * @return Kortin nimi
     * @example
     * <pre name="test">
     *  Kortit kortit = new Kortit();
     *  Kortti kortti1 = new Kortti("Eka kortti", 1, 1);
     *  Kortti kortti2 = new Kortti("Muut kortit", 2, 2);
     *  kortit.lisaa(kortti1); kortit.lisaa(kortti2);
     *  kortit.annaNimi(1) === "Eka kortti";
     *  kortit.annaNimi(2) === "Muut kortit";
     *  </pre>
     */
    public String annaNimi(int kid) {
        for (int i = 0; i < alkiot.length; i++) {
            Kortti k = alkiot[i];
            if (k == null) continue;
            if (k.getID() == kid) return k.getNimi();
        }
        return "Ei löydy"; //TODO: selvitä, miksei testi toimi vaikka mainissa toimii
    }
    
    
    @Override
    /**
     * @example
     * <pre name="test">
     *  Kortit kortit = new Kortit();
     *  Kortti kortti1 = new Kortti("Eka kortti", 1, 1);
     *  Kortti kortti2 = new Kortti("Muut kortit", 2, 2);
     *  kortit.lisaa(kortti1); kortit.lisaa(kortti2);
     *  kortit.toString() === "kID|kortin nimi|kpl|cmc\n1|Eka kortti|1|1\n2|Muut kortit|2|2";
     *  </pre>
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("kID|kortin nimi|kpl|cmc");
        for (int i = 0; i < lkm; i++) {
            sb.append("\n"+alkiot[i]);
        }
        return sb.toString();
    }
    
    
    /**
     * Testaamisen avuksi korttien tiedot merkkijonona.
     * @return Korttien tiedot merkkijonoina
     */
    public String testiString() {
      StringBuilder sb = new StringBuilder();
      for (Kortti kortti : alkiot) {
          if (kortti == null) continue;
           sb.append(kortti.testiString());
        }
      return sb.toString();
    }
    

    /**
     * Testataan Kortit-luokkaa.
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        
        Kortit kortit = new Kortit();
        Kortti kortti1 = new Kortti("Eka kortti", 1, 1);
        Kortti kortti2 = new Kortti("Muut kortit", 2, 2);
        System.out.println(kortti1.getID());
        System.out.println(kortti2.getID());
        kortit.lisaa(kortti1);
        kortit.lisaa(kortti2);
        System.out.println(kortti1.getID());
        System.out.println(kortti2.getID());
        System.out.println(kortit.annaNimi(1));
        System.out.println(kortit.annaNimi(2));

           /* Kortit kortit = new Kortit();
           int j = 0; 
           while(j <= 10) {
               kortit.lisaa(new Kortti().jaceKortti());
               j++;
           }
           while(j <= 20) {
               kortit.lisaa(new Kortti().mageKortti());
               j++;
           }
        System.out.println(kortit);   
        System.out.println();

        System.out.println("========== Kortit - testi ========== ");
        for (int i = 0; i < kortit.getLkm(); i++) {
            Kortti kortti = kortit.anna(i);
            System.out.println("Kortti indeksillä " + i);
            kortti.tulosta(System.out);
            System.out.println();
        }*/

    }

}
