package pakat;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kaisa Koski
 * @version 4.3.2021
 * Sisältää id-tiedot pakkojen sisältämistä korteista ja antaat tämän tiedon pyydettäessä.
 * Pitää yllä tietoa korttien sijainneista ja pyynnöstä välittää sitä muille.
 * Huolehtii kortin lisäämisestä ja poistamisesta pakkaan (tallentaa sen id-numerotiedoilla).
 * Huolehtii linkit.dat tiedoston lukemisesta ja uudelleenkirjoittamisesta.
 */
public class Linkit {

    // private String tiedosto = "";

    private final List<Linkki> alkiot = new ArrayList<Linkki>(); //TODO: Collection?
    private final static int EI_PAKASSA = 1; 

    /**
     * Linkkien alustaminen //TODO: Tiedoston nimellä?
     */
    public Linkit() {
        //
    }


    /**
     * Lisää linkkilistaan uuden linkin
     * @param linkki Lisättävä linkki
     * @example
     * <pre name="test">
     * Linkit linkit = new Linkit();
     * linkit.getLkm() === 0;
     * Linkki linkki1 = new Linkki(1,2,1,1);
     * Linkki linkki2 = new Linkki(1,3,0,2);
     * Linkki linkki3 = new Linkki(2,1,1,1);
     * Linkki linkki4 = new Linkki(2,3,2,2);
     * linkit.lisaa(linkki1); linkit.lisaa(linkki2);
     * linkit.getLkm() === 2;
     * linkit.lisaa(linkki3); linkit.lisaa(linkki4);
     * linkit.getLkm() === 4;
     * </pre>
     */
    public void lisaa(Linkki linkki) {
        int kid = linkki.getKID();
        int montakoEiPakassa = this.eiPakassaLkm(kid);
        if (0 < montakoEiPakassa) {
            int pakkaanKuuluu = linkki.getKk();
            int uusiKp;
            if (montakoEiPakassa < pakkaanKuuluu)
                uusiKp = montakoEiPakassa;
            else
                uusiKp = pakkaanKuuluu;
            linkki.setKp(uusiKp);
            vahenna(EI_PAKASSA, kid, uusiKp); // Vähennetään Ei pakassa
                                              // -sijaitsevien korttien määrää
                                              // TODO: Käyttäjän valinta
                                              // siirtyykö ei pakasta?
        }
        alkiot.add(linkki);
    }


    /**
     * Lisätään kortin kaikkien kappaleiden sijainti "Ei pakassa"
     * @param kid Kortin ID
     * @param lkm Kortin lukumäärä
     */
    public void lisaaEiPakassa(int kid, int lkm) {
        Linkki linkki = new Linkki(EI_PAKASSA, kid, lkm, lkm);
        alkiot.add(linkki);
    }


    /**
     * Etsii pakan ja kortin id:llä oikean linkin ja poistaa sen.
     * Jos kortin sijainti oli sillä hetkellä kyseisessä pakassa,
     * se siirtyy "Ei pakassa" -pakkaan ja sijaintiin.
     * @param pid Sen pakan id, josta kortti poistetaan
     * @param kid Poistettavan kortin id
     * @example
     * <pre name="test">
     * Linkit linkit = new Linkit();
     * Linkki linkki1 = new Linkki(1,2,1,1);
     * Linkki linkki2 = new Linkki(1,3,0,2);
     * Linkki linkki3 = new Linkki(2,1,1,1);
     * Linkki linkki4 = new Linkki(2,3,2,2);
     * linkit.lisaa(linkki1); linkit.lisaa(linkki2);
     * linkit.lisaa(linkki3); linkit.lisaa(linkki4);
     * linkit.getLkm() === 4;
     * linkit.anna(1,2) === linkki1;
     * linkit.poista(1,2);
     * linkit.getLkm() === 3;
     * linkit.anna(1,2) === null;
     * </pre>
     */
    public void poista(int pid, int kid) {
        int i = 0;
        while (i < this.alkiot.size()) {
            Linkki linkki = this.alkiot.get(i);
            if (linkki.getPID() == pid && linkki.getKID() == kid) {
                int kp = linkki.getKp();
                if (0 < kp && pid != EI_PAKASSA) {
                    Linkki uusi = new Linkki(EI_PAKASSA, kid, kp, kp);
                    this.lisaa(uusi); // Pakassa sijainneet kortit siirretään
                                      // "Ei pakassa"-pakkaan
                } // TODO: Tulisiko käyttäjälle tästä jokin ilmoitus?
                this.alkiot.remove(i);
                return;
            }
            i++;
        }
    }


    /**
     * @return Palauttaa linkkien lukumäärän
     */
    public int getLkm() {
        return alkiot.size();
    }


    /**
     * Palauttaa linkkien joukosta oikean pakan id:n ja
     * kortin id:n perusteella
     * @param pid Pakan id
     * @param kid Kortin id
     * @return Linkki
     * @example
     * <pre name="test">
     * Linkit linkit = new Linkit();
     * Linkki linkki1 = new Linkki(1,2,1,1);
     * Linkki linkki2 = new Linkki(1,3,0,2);
     * Linkki linkki3 = new Linkki(2,1,1,1);
     * Linkki linkki4 = new Linkki(2,3,2,2);
     * linkit.lisaa(linkki1); linkit.lisaa(linkki2);
     * linkit.lisaa(linkki3); linkit.lisaa(linkki4);
     * linkit.anna(1,2) === linkki1;
     * linkit.anna(1,3) === linkki2;
     * linkit.poista(2, 3);
     * linkit.anna(2, 3) === null;
     * </pre>
     */
    public Linkki anna(int pid, int kid) {
        for (Linkki linkki : alkiot) {
            if (linkki.getPID() == pid && linkki.getKID() == kid) {
                return linkki;
            }
        }
        return null;
    }


    /**
     * Palauttaa luvun, kuinka monta kappaletta kyseisen id:n korttia
     * sijaitsee "Ei pakassa"
     * @param kid Kortin id
     * @return Kuinka monta sijaitsee sillä hetkellä "Ei pakassa"
     * 
     * @example
     * <pre name="test">
     * Linkki l1 = new Linkki(1,1,2,2); //Pakka nro 1, kortti 1
     * Linkki l2 = new Linkki(1,2,1,2); //Pakka nro 1, kortti 2
     * Linkki l3 = new Linkki(2,3,1,2); //Pakka nro 2, kortti 3
     * Linkki l4 = new Linkki(2,4,1,2); //Pakka nro 2, kortti 4
     * Linkit linkit = new Linkit();
     * linkit.lisaa(l1); linkit.lisaa(l2);
     * linkit.lisaa(l3); linkit.lisaa(l4);
     * linkit.eiPakassaLkm(1) === 2;
     * linkit.eiPakassaLkm(2) === 1;
     * linkit.eiPakassaLkm(3) === 0;
     * linkit.eiPakassaLkm(4) === 0;
     * </pre>
     */
    public int eiPakassaLkm(int kid) {
        for (Linkki linkki : alkiot) {
            if (linkki.getPID() == EI_PAKASSA && linkki.getKID() == kid) {
                return linkki.getKp();
            }
        }
        return 0;
    }


    /**
     * Vähentää pakassa sijaitsevien korttien määrää.
     * Jos sijainti menee nollaan ja sijainti on "Ei pakassa",
     * poistetaan koko linkki.
     * @param pid Pakan id
     * @param kid Kortin id
     * @param montako Kuinka monta korttia poistetaan sijainnista
     * 
     * <pre name="test">
     * Linkki l1 = new Linkki(1,1,2,2); //Pakka nro 1, kortti 1
     * Linkki l2 = new Linkki(1,2,1,2); //Pakka nro 1, kortti 2
     * Linkki l3 = new Linkki(2,3,1,2); //Pakka nro 2, kortti 3
     * Linkit linkit = new Linkit();
     * linkit.lisaa(l1); linkit.lisaa(l2);
     * linkit.lisaa(l3);
     * l1.getKp() === 2;
     * linkit.vahenna(1,1,1);
     * l1.getKp() === 1;
     * linkit.vahenna(1,1,1);
     * linkit.anna(1,1) === null; //Poistettu kaikki EI_PAKASSA:idstä joten poistettu koko linkki alkioiden joukosta.
     * linkit.vahenna(2, 3, 1);
     * l3.getKp() === 0;
     * </pre>
     */
    public void vahenna(int pid, int kid, int montako) {
        Linkki linkki = anna(pid, kid);
        int kpNyt = linkki.getKp();
        if (kpNyt < montako)
            return; // TODO: Mitä tehdään, ilmoitetaan jos yritetään poistaa
                    // enemmän kuin on?
        int uusiKp = linkki.getKp() - montako;
        if (uusiKp == 0 && pid == EI_PAKASSA)
            poista(pid, kid); // TODO: Haittaako jos Ei pakassa-kortin kp
                              // pienenee mutta kk ei?
        else
            linkki.setKp(uusiKp);
    }


    /**
     * Palauttaa parametrina annetun pakan korttien id:t listana.
     * @param pid Pakka jonka korttien id:t halutaan
     * @return Lista pakkaan kuuluvien korttien id-numeroista
     * 
     * @example
     * #import java.util.List;
     * <pre name="test">
     * Linkki l1 = new Linkki(2,1,2,2); //Pakka nro 2, kortti 1
     * Linkki l2 = new Linkki(2,2,2,2); //Pakka nro 2, kortti 2
     * Linkki l3 = new Linkki(3,1,0,2); //Pakka nro 3, kortti 1
     * Linkki l4 = new Linkki(3,3,1,2); //Pakka nro 3, kortti 3
     * Linkit linkit = new Linkit();
     * linkit.lisaa(l1); linkit.lisaa(l2);
     * linkit.lisaa(l3); linkit.lisaa(l4);
     * List<Integer> kortit1 = linkit.pakanKortit(2);
     * kortit1.toString() === "[1, 2]";
     * List<Integer> kortit2 = linkit.pakanKortit(3);
     * kortit2.toString() === "[1, 3]";   
     * </pre>
     */
    public List<Integer> pakanKortit(int pid) {
        List<Integer> kidlista = new ArrayList<Integer>();
        for (Linkki linkki : alkiot) {
            if (linkki.getPID() == pid)
                kidlista.add(linkki.getKID());
        }
        return kidlista;
    }


    /**
     * Palauttaa parametrina annetun pakan korttien id:t listana.
     * @param kid Kortti jonka pakkojen id:t halutaan
     * @param sijainti Halutaanko ne pakat joissa ainakin yksi kpl kyseistä korttia
     * sijaitsee vai ne missä ei sijaitse, eli jos true, palauttaa pakat joissa
     * sijaitsee.
     * @return Lista pakkaan kuuluvien korttien id-numeroista
     * 
     * @example
     * #import java.util.List;
     * <pre name="test">
     * Linkki l1 = new Linkki(2,1,2,2); //Pakka nro 2, kortti 1
     * Linkki l2 = new Linkki(2,2,2,2); //Pakka nro 2, kortti 2
     * Linkki l3 = new Linkki(3,1,0,2); //Pakka nro 3, kortti 1
     * Linkki l4 = new Linkki(3,2,1,2); //Pakka nro 3, kortti 2
     * Linkit linkit = new Linkit();
     * linkit.lisaa(l1); linkit.lisaa(l2);
     * linkit.lisaa(l3); linkit.lisaa(l4);
     * List<Integer> pakat1 = linkit.kortinPakat(2, true);
     * pakat1.toString() === "[2, 3]";
     * List<Integer> pakat2 = linkit.kortinPakat(1, true);
     * pakat2.toString() === "[2]"; 
     * List<Integer> pakat3 = linkit.kortinPakat(1, false);
     * pakat3.toString() === "[3]";    
     * </pre>
     */
    public List<Integer> kortinPakat(int kid, boolean sijainti) {
        List<Integer> pidlista = new ArrayList<Integer>();
        for (Linkki linkki : alkiot) {
            int linkinKid = linkki.getKID();
            if (linkinKid == kid) {
                if (sijainti == true && 0 < linkki.getKp()) pidlista.add(linkki.getPID());
                if (sijainti == false && linkki.getKp() == 0) pidlista.add(linkki.getPID());
            }
        }
        return pidlista;
    }


    /**
     * Palauttaa totuusarvon siitä, onko pakka aktiivinen, eli sijaitsevatko
     * kaikki pakkaan kuuluvat kortit sillä hetkellä pakassa.
     * @param pid Pakan indeksi
     * @return Onko pakka aktiivinen
     * 
     * @example
     * <pre name="test">
     * Linkki l1 = new Linkki(2,1,2,2); //Pakka nro 2, kortti 1
     * Linkki l2 = new Linkki(2,2,2,2); //Pakka nro 2, kortti 2
     * Linkki l3 = new Linkki(3,1,0,2); //Pakka nro 3, kortti 1
     * Linkki l4 = new Linkki(3,2,1,2); //Pakka nro 3, kortti 2
     * Linkit linkit = new Linkit();
     * linkit.lisaa(l1); linkit.lisaa(l2);
     * linkit.lisaa(l3); linkit.lisaa(l4);
     * linkit.onkoAktiivinen(2) === true;
     * linkit.onkoAktiivinen(3) === false;   
     * </pre>
     */
    public boolean onkoAktiivinen(int pid) {
        for (Linkki linkki : alkiot) {
            if (linkki.getPID() == pid && linkki.getKp() != linkki.getKk())
                return false;
        }
        return true;
    }


    /**
     * Testataan Linkit-luokkaa
     * @param args ei käytössä
     */
    public static void main(String[] args) {

        Linkki l1 = new Linkki(2, 1, 2, 2); // Pakka nro 2, kortti 1
        Linkki l2 = new Linkki(2, 2, 2, 2); // Pakka nro 2, kortti 2
        Linkki l3 = new Linkki(3, 1, 0, 2); // Pakka nro 3, kortti 1
        Linkki l4 = new Linkki(3, 2, 1, 2); // Pakka nro 3, kortti 2
        Linkit linkit = new Linkit();
        linkit.lisaa(l1);
        linkit.lisaa(l2);
        linkit.lisaa(l3);
        linkit.lisaa(l4);
        List<Integer> pakat1 = linkit.kortinPakat(2, true);
        System.out.println(pakat1.toString());
        List<Integer> pakat2 = linkit.kortinPakat(1, true);
        System.out.println(pakat2.toString());
        List<Integer> pakat3 = linkit.kortinPakat(1, false);
        System.out.println(pakat3.toString());
    }
}
