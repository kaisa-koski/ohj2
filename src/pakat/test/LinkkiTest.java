package pakat.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import pakat.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.04.21 14:58:37 // Generated by ComTest
 *
 */
@SuppressWarnings("all")
public class LinkkiTest {



  // Generated by ComTest BEGIN
  /** testRekisteroi88 */
  @Test
  public void testRekisteroi88() {    // Linkki: 88
    Linkki linkki1 = new Linkki(1,2,4,4); 
    int n1 = linkki1.rekisteroi(); 
    Linkki linkki2 = new Linkki(2,3,0,1); 
    int n2 =linkki2.rekisteroi(); 
    assertEquals("From: Linkki line: 93", n1+1, n2); 
    int n3 = linkki1.rekisteroi(); 
    assertEquals("From: Linkki line: 95", n3, n1); 
  } // Generated by ComTest END
}