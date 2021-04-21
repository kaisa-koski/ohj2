package pakat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Pitää yllä tietoa kaikista korteista.
 * @author Kaisa Koski
 * @version 6.4.2021
 *
 */
public class Kortit implements Iterable<Kortti> {

    private final Collection<Kortti> alkiot = new ArrayList<Kortti>(); // Kysymys ohjaajalle: Miten final vaikuttaa? Taisi olla ettei pysty enää laittaa viittaamaan muualle, mutta sisältöä voi muuttaa?
    private boolean muutettu = false;
    private String tiedosto = "kortit.dat";

    /**
     * Kortit-alustaminen
     */
    public Kortit() {
        // 
    }
    
    /**
     * Luodaan alustava taulukko
     * @param kansio Tallennuskansion nimi
     */
    public Kortit(String kansio) {
        this.tiedosto = kansio+"\\kortit.dat";
    }


    /**
     * Ladataan tiedot tiedostosta.
     */
    public void lataa() {
        try (Scanner fi = new Scanner(new FileInputStream(new File(tiedosto)))) {
            while ( fi.hasNext() ) {
                try {
                    String s = fi.nextLine();
                    Kortti kortti = new Kortti();
                    kortti.parse(s);
                    lisaa(kortti);
                } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                    continue;
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Tiedosto ei aukea! " + ex.getMessage());
            return;
        }
    }


    /**
     * Tallennetaan tiedot tiedostoon, jos on tullut muutoksia.
     */
    public void tallenna() {
        if (!muutettu) return;
        try (PrintStream fo = new PrintStream(new FileOutputStream(tiedosto, false))) {
                fo.print(toString());
        } catch (FileNotFoundException ex) {
            System.err.println("Tiedosto ei aukea: " + ex.getMessage());
        }
    }


    @Override
    public Iterator<Kortti> iterator() {
      return alkiot.iterator();
    }
    
    /**
     * Uuden kortin lisääminen
     * @param kortti Lisättävä kortti
     * @example
     * <pre name="test">
     * Kortit kortit = new Kortit();
     * Kortti jace1 = new Kortti(), jace2 = new Kortti();
     * kortit.getLkm() === 0;
     * kortit.lisaa(jace1); kortit.getLkm() === 1;
     * kortit.lisaa(jace2); kortit.getLkm() === 2;
     * kortit.anna(jace1.getID()) === jace1;
     * kortit.anna(jace2.getID()) === jace2;
     * </pre> 
     */
    public void lisaa(Kortti kortti) {
        kortti.rekisteroi();
        alkiot.add(kortti);
        muutettu = true;
    }


    /**
     * Poistaa kortin korttien joukosta
     * @param kortti Poistettava kortti
     */
    public void poista(Kortti kortti) {
        alkiot.remove(kortti);
        muutettu = true;
    }


    /**
     * @return korttien lukumäärän
     */
    public int getLkm() {
        return alkiot.size();
    }


  
    /**
     * Palauttaa korttien joukosta pyydetyn kortin.
     * @param kid Kortin ID
     * @return kortti pyydetystä indeksistä
     * @example
     * <pre name="test">
     *  Kortit kortit = new Kortit();
     *  Kortti kortti1 = new Kortti("Eka kortti", 1, 1);
     *  Kortti kortti2 = new Kortti("Muut kortit", 2, 2);
     *  kortit.lisaa(kortti1); kortit.lisaa(kortti2);
     *  kortit.anna(kortti1.getID()) === kortti1;
     *  kortit.anna(kortti2.getID()) === kortti2;
     * </pre>
     */
    public Kortti anna(int kid) {
        for(Kortti k : alkiot) {
            if (k.getID() == kid) return k;
        }
      throw new IndexOutOfBoundsException("Korttia ei löydy");
    }
    
    
    /**
     * Palauttaa kortin, jolla on parametrina annettu
     * id.
     * @param kid Kortin id
     * @return Kortti annetulla id:llä
     */
    public Kortti getKortti(int kid) {
        for(Kortti k : alkiot) {
            if (k.getID() == kid) return k;
        }
        return null;
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
     *  kortit.annaNimi(kortti1.getID()) === "Eka kortti";
     *  kortit.annaNimi(kortti2.getID()) === "Muut kortit";
     *  </pre>
     */
    public String annaNimi(int kid) {
        for (Kortti k : alkiot) {
            if (k == null) continue; // TODO: Nyt ei pitäisi olla mahdollista, voiko poistaa?
            if (k.getID() == kid) return k.getNimi();
        }
        return "Ei löydy";
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
        for (Kortti k : alkiot) {
            sb.append("\n" + k.toString());
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
            if (kortti == null)
                continue;
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

        /*
         * Kortit kortit = new Kortit(); int j = 0; while(j <= 10) {
         * kortit.lisaa(new Kortti().jaceKortti()); j++; } while(j <= 20) {
         * kortit.lisaa(new Kortti().mageKortti()); j++; }
         * System.out.println(kortit); System.out.println();
         * 
         * System.out.println("========== Kortit - testi ========== "); for (int
         * i = 0; i < kortit.getLkm(); i++) { Kortti kortti = kortit.anna(i);
         * System.out.println("Kortti indeksillä " + i);
         * kortti.tulosta(System.out); System.out.println(); }
         */

    }

}
