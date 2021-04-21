package pakat.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import pakat.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.04.21 15:04:22 // Generated by ComTest
 *
 */
@SuppressWarnings("all")
public class PakatTest {



  // Generated by ComTest BEGIN
  /** testLisaa94 */
  @Test
  public void testLisaa94() {    // Pakat: 94
    Pakat pakat = new Pakat(); 
    Pakka izzet1 = new Pakka(), izzet2 = new Pakka(); 
    assertEquals("From: Pakat line: 97", 0+1, pakat.getLkm()); 
    pakat.lisaa(izzet1); assertEquals("From: Pakat line: 98", 1+1, pakat.getLkm()); 
    pakat.lisaa(izzet2); assertEquals("From: Pakat line: 99", 2+1, pakat.getLkm()); 
    pakat.lisaa(izzet1); assertEquals("From: Pakat line: 100", 3+1, pakat.getLkm()); 
    assertEquals("From: Pakat line: 101", izzet1, pakat.anna(0+1)); 
    assertEquals("From: Pakat line: 102", izzet2, pakat.anna(1+1)); 
    assertEquals("From: Pakat line: 103", izzet1, pakat.anna(2+1)); 
    assertEquals("From: Pakat line: 104", false, pakat.anna(1+1) == izzet1); 
    assertEquals("From: Pakat line: 105", true, pakat.anna(1+1) == izzet2); 
    try {
    assertEquals("From: Pakat line: 106", izzet1, pakat.anna(3+1)); 
    fail("Pakat: 106 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    pakat.lisaa(izzet1); assertEquals("From: Pakat line: 107", 4+1, pakat.getLkm()); 
    pakat.lisaa(izzet1); assertEquals("From: Pakat line: 108", true, pakat.getLkm() > 5); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testLisaaTilaa122 */
  @Test
  public void testLisaaTilaa122() {    // Pakat: 122
    Pakat pakat = new Pakat(); 
    assertEquals("From: Pakat line: 124", 1, pakat.getLkm()); 
    assertEquals("From: Pakat line: 125", 5, pakat.getKoko()); 
    pakat.lisaa(new Pakka()); pakat.lisaa(new Pakka()); 
    pakat.lisaa(new Pakka()); pakat.lisaa(new Pakka()); 
    pakat.lisaa(new Pakka()); assertEquals("From: Pakat line: 128", 6, pakat.getLkm()); 
    assertEquals("From: Pakat line: 129", 10, pakat.getKoko()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testPoista146 */
  @Test
  public void testPoista146() {    // Pakat: 146
    Pakat pakat = new Pakat(); 
    assertEquals("From: Pakat line: 148", 1, pakat.getLkm()); 
    assertEquals("From: Pakat line: 149", 5, pakat.getKoko()); 
    pakat.lisaa(new Pakka("Eka pakka", 1, "")); pakat.lisaa(new Pakka("Toka pakka", 1, "")); 
    pakat.lisaa(new Pakka("Kolmas pakka", 1, "")); pakat.lisaa(new Pakka("Neljäs pakka", 1, "")); 
    assertEquals("From: Pakat line: 152", "Korttivarasto", pakat.anna(0).getNimi()); 
    Pakka eka = pakat.anna(1); 
    assertEquals("From: Pakat line: 154", "Eka pakka", eka.getNimi()); 
    assertEquals("From: Pakat line: 155", "Toka pakka", pakat.anna(2).getNimi()); 
    assertEquals("From: Pakat line: 156", "Neljäs pakka", pakat.anna(4).getNimi()); 
    assertEquals("From: Pakat line: 157", 5, pakat.getLkm()); 
    pakat.poista(eka); 
    assertEquals("From: Pakat line: 159", "Korttivarasto", pakat.anna(0).getNimi()); 
    assertEquals("From: Pakat line: 160", "Toka pakka", pakat.anna(1).getNimi()); 
    assertEquals("From: Pakat line: 161", "Kolmas pakka", pakat.anna(2).getNimi()); 
    try {
    assertEquals("From: Pakat line: 162", null, pakat.anna(4)); 
    fail("Pakat: 162 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    assertEquals("From: Pakat line: 163", 4, pakat.getLkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testToString209 */
  @Test
  public void testToString209() {    // Pakat: 209
    Pakat pakat = new Pakat(); 
    assertEquals("From: Pakat line: 211", 1, pakat.getLkm()); 
    pakat.lisaa(new Pakka("Eka pakka", 1, "")); pakat.lisaa(new Pakka("Toka pakka", 1, "")); 
    assertEquals("From: Pakat line: 213", "Korttivarasto", pakat.anna(0).getNimi()); 
    assertEquals("From: Pakat line: 214", "pID|Pakan nimi|Pakan tyyppi|Omat muistiinpanot\n"+pakat.anna(0).getID()+"|Korttivarasto|5|Tällä hetkellä ei pakassa\n"+pakat.anna(1).getID()+"|Eka pakka|1|\n"+pakat.anna(2).getID()+"|Toka pakka|1|", pakat.toString()); 
  } // Generated by ComTest END
}