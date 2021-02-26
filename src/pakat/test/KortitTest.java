package pakat.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import pakat.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.02.24 13:35:31 // Generated by ComTest
 *
 */
@SuppressWarnings("all")
public class KortitTest {



  // Generated by ComTest BEGIN
  /** 
   * testLisaa30 
   * @throws SailoException when error
   */
  @Test
  public void testLisaa30() throws SailoException {    // Kortit: 30
    Kortit kortit = new Kortit(); 
    Kortti jace1 = new Kortti(), jace2 = new Kortti(); 
    assertEquals("From: Kortit line: 34", 0, kortit.getLkm()); 
    kortit.lisaa(jace1); assertEquals("From: Kortit line: 35", 1, kortit.getLkm()); 
    kortit.lisaa(jace2); assertEquals("From: Kortit line: 36", 2, kortit.getLkm()); 
    kortit.lisaa(jace1); assertEquals("From: Kortit line: 37", 3, kortit.getLkm()); 
    assertEquals("From: Kortit line: 38", jace1, kortit.anna(0)); 
    assertEquals("From: Kortit line: 39", jace2, kortit.anna(1)); 
    assertEquals("From: Kortit line: 40", jace1, kortit.anna(2)); 
    assertEquals("From: Kortit line: 41", false, kortit.anna(1) == jace1); 
    assertEquals("From: Kortit line: 42", true, kortit.anna(1) == jace2); 
    try {
    assertEquals("From: Kortit line: 43", jace1, kortit.anna(3)); 
    fail("Kortit: 43 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    kortit.lisaa(jace1); assertEquals("From: Kortit line: 44", 4, kortit.getLkm()); 
    kortit.lisaa(jace1); assertEquals("From: Kortit line: 45", 5, kortit.getLkm()); 
    try {
    kortit.lisaa(jace1); 
    fail("Kortit: 46 Did not throw SailoException");
    } catch(SailoException _e_){ _e_.getMessage(); }
  } // Generated by ComTest END
}