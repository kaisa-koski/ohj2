package pakat;

/**
 * @author Kaisa Koski
 * @version 2.3.2021
 *
 */
public class Linkki {
    
    private int lid;    //linkin id
    private int pid;    //pakan id
    private int kid;    //kortin id
    private int kp;     //kappaleita pakassa
    private int kk;     //kappaleita kuuluu pakkaan
    
    private static int seuraavaID = 1;

    /**
     * Alustaa tyhjän linkin
     */
    public Linkki() {
        //
    }
    
    /**
     * Uuden linkin luominen
     * @param pid Pakan id
     * @param kid Kortin id
     * @param kp Kappaleita pakassa sillä hetkellä
     * @param kk Kappaleita kuuluu pakkaan
     */
    public Linkki(int pid, int kid, int kp, int kk) { //TODO: Tarkistukset, onko parametrit pienempiä kuin nolla? Onko kp isompi kuin kk?
        this.pid = pid;
        this.kid = kid;
        this.kp = kp;
        this.kk = kk;
    }
    
    /**
     * Uuden linkin luominen, kp oletuksena 0.
     * @param pid Pakan id
     * @param kid Kortin id
     * @param kk Kappaleita kuuluu pakkaan
     */
    public Linkki(int pid, int kid, int kk) { //TODO: Tarkistukset, onko parametrit pienempiä kuin nolla? Onko kp isompi kuin kk?
        this(pid, kid, 0, kk);
    }
    
    /**
     * Asetetaan linkille seuraava vapaana oleva ID-numero.
     * @return Linkille annettu ID-numero
     * @example
     * <pre name="test">
     * Linkki linkki1 = new Linkki(1,2,4,4);
     * int n1 = linkki1.rekisteroi();
     * Linkki linkki2 = new Linkki(2,3,0,1);
     * int n2 =linkki2.rekisteroi();
     * n2 === n1+1;
     * int n3 = linkki1.rekisteroi();
     * n1 === n3;
     * </pre>
     */
    public int rekisteroi() {
        if (this.lid != 0) return this.lid;
        this.lid = seuraavaID;
        seuraavaID++;
        return this.lid;
    }
    
    @Override
    public String toString() {
        return lid + "|" + pid + "|" + kid + "|" + kp + "|" + kk;
    }
   
    
    /**
     * @return Palauttaa linkin ID:n
     */
    public int getID() {
        return this.lid;
    }
    
    /**
     * @return Palauttaa kortin ID:n
     */
    public int getKID() {
        return this.kid;
    }
    
    /**
     * @return Palauttaa kortin ID:n
     */
    
    
    public int getPID() {
        return this.pid;
    }
    
    /**
     * @return Palauttaa luvun, kuinka monta kyseistä korttia 
     * pakassa sillä hetkellä sijaitsee.
     */
    public int getKp() {
        return this.kp;
    }
    
    
    /**
     * @return Palauttaa luvun, kuinka monta kyseistä korttia 
     * pakkaan kaikenkaikkiaan kuuluu
     */
    public int getKk() {
        return this.kk;
    }
    
    /**
     * Muuttaa pakassa sijaitsevien korttien määrää.
     * @param uusiKp Uusi pakassa sijaitsevien korttien määrä
     */
    public void setKp(int uusiKp) {
        if (uusiKp < 0 || this.kk < uusiKp) return;
        this.kp = uusiKp;
    }
    
    //TODO: toString

    /**
     * Testataan Linkki-luokkaa
     * @param args ei käytössä
     */
    public static void main(String[] args) {
    // TODO Auto-generated method stub
    
    }

}
