/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jonna.mehtonen.domain;

import fi.jonna.mehtonen.domain.Ruutu;
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
public class RuutuTest {

    public RuutuTest() {
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

    @Test
    public void ruudunAsetaMiinaToimii() {
        Ruutu ruutu = new Ruutu();
        ruutu.setMiina();
        boolean vastaus = ruutu.isOnkoMiina();
        assertEquals(true, vastaus);
    }

    @Test
    public void alussaRuudullaEiMiinaa() {
        Ruutu ruutu = new Ruutu();
        boolean vastaus = ruutu.isOnkoMiina();
        assertEquals(false, vastaus);
    }
    
    @Test
    public void poistaMiinaToimii() {
        Ruutu ruutu = new Ruutu();
        ruutu.setMiina();
        ruutu.poistaMiina();
        boolean vastaus = ruutu.isOnkoMiina();
        assertEquals(false, vastaus);
    }
    
    @Test
    public void alussaRuudullaEiLippua() {
        Ruutu ruutu = new Ruutu();
        boolean vastaus = ruutu.isOnkoLippu();
        assertEquals(false, vastaus);
    }
    
    @Test
    public void alussaRuutuaEiAvattu() {
        Ruutu ruutu = new Ruutu();
        boolean vastaus = ruutu.isOnkoAvattu();
        assertEquals(false, vastaus);
    }
    
    @Test
    public void poistaLippuToimii() {
        Ruutu ruutu = new Ruutu();
        ruutu.setLippu();
        ruutu.poistaLippu();
        boolean vastaus = ruutu.isOnkoLippu();
        assertEquals(false, vastaus);
    }
    
    @Test
    public void asetaLippuToimii() {
        Ruutu ruutu = new Ruutu();
        ruutu.setLippu();
        boolean vastaus = ruutu.isOnkoLippu();
        assertEquals(true, vastaus);
    }
    
    @Test
    public void asetaArvoToimii() {
        Ruutu ruutu = new Ruutu();
        ruutu.setArvo(50);
        int vastaus = ruutu.getArvo();
        assertEquals(50, vastaus);
    }
    
    @Test
    public void merkkaaRuutuAvatuksiToimii() {
        Ruutu ruutu = new Ruutu();
        ruutu.merkkaaRuutuAvatuksi();
        boolean vastaus = ruutu.isOnkoAvattu();
        assertEquals(true, vastaus);
    }
}
