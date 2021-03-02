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
    private int kk;     //kappaleita kuuluu linkkian
    
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
    public Linkki(int pid, int kid, int kp, int kk) {
        this.pid = pid;
        this.kid = kid;
        this.kp = kp;
        this.kk = kk;
    }
    
    /**
     * Asetetaan linkille seuraava vapaana oleva ID-numero.
     * @return Linkille annettu ID-numero
     * @example
     * <pre name="test">
     * Linkki linkki1 = new Linkki();
     * linkki1.rekisteroi();
     * Linkki linkki2 = new Linkki();
     * linkki2.rekisteroi();
     * int n1 = linkki.getID();
     * int n2 = linkki2.getID();
     * n2 === n1+1;
     * </pre>
     */
    public int rekisteroi() {
        this.pid = seuraavaID;
        seuraavaID++;
        return this.pid;
    }
    
    /**
     * Testataan Linkki-luokkaa
     * @param args ei käytössä
     */
    public static void main(String[] args) {
    // TODO Auto-generated method stub
    
    }

}
