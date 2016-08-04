/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import miinaharava.jonnanmiinaharava.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jonscu
 */
public class MiinaharavaTest {

    public MiinaharavaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void miinojaOikeaMaara() {
        Pelilauta lauta = new Pelilauta(10);
        int vastaus = lauta.miinojaYhteensa();
        assertEquals(20, vastaus);
    }

    @Test
    public void eiMiinojaKunRuutujaVahemmanKuinViisi() {
        Pelilauta lauta = new Pelilauta(2);
        int vastaus = lauta.miinojaYhteensa();
        assertEquals(0, vastaus);
    }

    @Test
    public void laudanKokoOikea() {
        Pelilauta lauta = new Pelilauta(20);
        int vastaus = lauta.getRuudutKoko();
        assertEquals(20, vastaus);
    }

    @Test
    public void ruudunSijaintiOikea() {
        Pelilauta lauta = new Pelilauta(20);
        Ruutu[][] ruudut = lauta.getRuudut();
        Sijainti sijainti = ruudut[1][2].getSijainti();
        String vastaus = sijainti.toString();
        assertEquals("1, 2", vastaus);
    }

    @Test
    public void sijaintiXToimii() {
        Sijainti sijainti = new Sijainti(2, 1);
        int vastaus = sijainti.getX();
        assertEquals(2, vastaus);
    }

    @Test
    public void sijaintiYToimii() {
        Sijainti sijainti = new Sijainti(2, 1);
        int vastaus = sijainti.getY();
        assertEquals(1, vastaus);
    }

    @Test
    public void asetaSijaintiXToimii() {
        Sijainti sijainti = new Sijainti(2, 1);
        sijainti.setX(10);
        int vastaus = sijainti.getX();
        assertEquals(10, vastaus);
    }

    @Test
    public void asetaSijaintiYToimii() {
        Sijainti sijainti = new Sijainti(2, 1);
        sijainti.setY(10);
        int vastaus = sijainti.getY();
        assertEquals(10, vastaus);
    }

    @Test
    public void ruudunAsetaMiinaToimii() {
        Ruutu ruutu = new Ruutu(1, 2);
        ruutu.asetaMiina();
        boolean vastaus = ruutu.isOnkoMiina();
        assertEquals(true, vastaus);
    }

    @Test
    public void alussaRuudullaEiMiinaa() {
        Ruutu ruutu = new Ruutu(1, 2);
        boolean vastaus = ruutu.isOnkoMiina();
        assertEquals(false, vastaus);
    }
}
