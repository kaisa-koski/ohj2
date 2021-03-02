/**
 * 
 */
package pakat;

/**
 * @author Kaisa Koski
 * @version 24.2.2021
 *
 */
public class Pakkarekisteri {

    private Linkit linkit;
    private Tyypit tyypit;
    private Pakat pakat;
    private Kortit kortit;

    /**
     * Pakkarekisterin luominen
     */
    public Pakkarekisteri() {
        this.linkit = new Linkit();
        this.tyypit = new Tyypit();
        this.pakat = new Pakat();
        this.kortit = new Kortit();
    }


    /**
     * Uuden kortin lisääminen
     * @param kortti Lisättävä kortti
     * @throws SailoException Poikkeus jos lisättävä kortti ei mahdu
     */
    public void lisaa(Kortti kortti) throws SailoException {
        this.kortit.lisaa(kortti);
    }

    
    /**
     * Uuden pakan lisääminen
     * @param pakka Lisättävä pakka
     * @throws SailoException Poikkeus jos lisättävä pakka ei mahdu
     */
    public void lisaa(Pakka pakka) throws SailoException {
        this.pakat.lisaa(pakka);
    }

    /**
     * Testataan pakkarekisteri-luokan toimintaa.
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Pakkarekisteri rekisteri = new Pakkarekisteri();

        Kortti jace = new Kortti();
        jace.rekisteroi();
        jace.jaceKortti();

        Kortti jace2 = new Kortti();
        jace.rekisteroi();
        jace.jaceKortti();

        try {
            rekisteri.lisaa(jace);
            rekisteri.lisaa(jace2);
            rekisteri.lisaa(jace2);
            rekisteri.lisaa(jace2);
            rekisteri.lisaa(jace2);
        } catch (SailoException e) {
            e.printStackTrace();
        }
        
        

    }

}
