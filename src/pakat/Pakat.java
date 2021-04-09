package pakat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Kaisa Koski
 * @version 6.4.2021
 *
 */
public class Pakat {
    
    private static final int MAX_PAKKOJA = 5;
    private int lkm = 0;
    private Pakka[] alkiot;
    private String tiedosto = "";
    private boolean muutettu = false;
    
    
    /**
     * Luodaan alustava taulukko, jossa on automaattisesti
     * "Ei pakassa" -pakka. 
     */
    public Pakat() {
        this.alkiot = new Pakka[MAX_PAKKOJA];
    }
    
    /**
     * Luodaan alustava taulukko, jossa on automaattisesti
     * "Ei pakassa" -pakka. Parametrina tallennustiedoston nimi 
     * @param tiedosto Tiedoston nimi
     */
    public Pakat(String tiedosto) {
        this.alkiot = new Pakka[MAX_PAKKOJA];
        this.tiedosto = tiedosto;
    }
    
    
    /**
     * Ladataan tiedot tiedostosta.
     */
    public void lataa() {
        try (Scanner fi = new Scanner(new FileInputStream(new File(tiedosto)))) {
            while ( fi.hasNext() ) {
                try {
                    String s = fi.nextLine();
                    String[] palat = s.split("\\|");
                    String nimi = palat[1];
                    int tyyppi = Integer.parseInt(palat[2]);
                    String mp = palat[3];
                    lisaa(new Pakka(nimi, tyyppi, mp));
                } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                    continue;
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Tiedosto ei aukea! " + ex.getMessage());
            lisaaEiPakassa();
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
    
    
    /**
     * Luo Ei pakassa -nimisen pakan ja lisää sen pakkoihin. //TODO: Mieti, missä vaiheessa tehdään, onko vain tiedostossa?
     */
    public void lisaaEiPakassa() {
        if (0 < lkm) return;
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
        muutettu = true;
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
     * Poistetaan pakka ja siirretään jäljelle jääneitä pakkoja niin,
     * ettei taulukkoon jää keskelle tyhjää tilaa.
     * @param p Poistettava pakka
     * @example
     * <pre name="test">
     * Pakat pakat = new Pakat();
     * pakat.getLkm() === 1;
     * pakat.getKoko() === 5;
     * pakat.lisaa(new Pakka("Eka pakka", 1, "")); pakat.lisaa(new Pakka("Toka pakka", 1, ""));
     * pakat.lisaa(new Pakka("Kolmas pakka", 1, "")); pakat.lisaa(new Pakka("Neljäs pakka", 1, ""));
     * pakat.anna(0).getNimi() === "Ei pakassa";
     * Pakka eka = pakat.anna(1);
     * eka.getNimi() === "Eka pakka";
     * pakat.anna(2).getNimi() === "Toka pakka";
     * pakat.anna(4).getNimi() === "Neljäs pakka";
     * pakat.getLkm() === 5;
     * pakat.poista(eka);
     * pakat.anna(0).getNimi() === "Ei pakassa";
     * pakat.anna(1).getNimi() === "Toka pakka";
     * pakat.anna(2).getNimi() === "Kolmas pakka";
     * pakat.anna(4) === null; #THROWS IndexOutOfBoundsException 
     * pakat.getLkm() === 4;     
     * </pre>
     */
    public void poista(Pakka p) {
        for (int i = 1; i < alkiot.length; i++) {
            if (alkiot[i] == p) {
                for (int j = i + 1; j < lkm; j++) {
                    alkiot[j - 1] = alkiot[j];
                }
                alkiot[lkm - 1] = null;
                lkm--;
                muutettu = true;
                return;
            }
        }
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
    
    @Override
    /**
     * @example
     * <pre name="test">
     * Pakat pakat = new Pakat();
     * pakat.getLkm() === 1;
     * pakat.lisaa(new Pakka("Eka pakka", 1, "")); pakat.lisaa(new Pakka("Toka pakka", 1, "")); 
     * pakat.anna(0).getNimi() === "Ei pakassa";
     * pakat.toString() === "pID|Pakan nimi|Pakan tyyppi|Omat muistiinpanot\n"+pakat.anna(0).getID()+"|Ei pakassa|5|Kortit, jotka eivät tällä hetkellä ole missään pakassa\n"+pakat.anna(1).getID()+"|Eka pakka|1|\n"+pakat.anna(2).getID()+"|Toka pakka|1|";
     *  </pre>
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("pID|Pakan nimi|Pakan tyyppi|Omat muistiinpanot"); //TODO: Ohjaajalta: Miten Stringiä saa testattu niin että voi laittaa eri riveille? Valittaa plussasta!
        for (int i = 0; i < lkm; i++) {
            sb.append("\r\n" + alkiot[i].toString());
        }
        return sb.toString();
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
