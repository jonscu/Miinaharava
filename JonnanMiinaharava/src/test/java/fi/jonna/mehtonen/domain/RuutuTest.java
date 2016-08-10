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
        ruutu.asetaMiina();
        boolean vastaus = ruutu.isOnkoMiina();
        assertEquals(true, vastaus);
    }

    @Test
    public void alussaRuudullaEiMiinaa() {
        Ruutu ruutu = new Ruutu();
        boolean vastaus = ruutu.isOnkoMiina();
        assertEquals(false, vastaus);
    }
}
