/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jonna.mehtonen.domain;

import fi.jonna.mehtonen.domain.Pelilauta;
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
public class PelilautaTest {

    public PelilautaTest() {
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
    public void miinojaOikeaMaara() {
        RuudukonAlustus alustus = new RuudukonAlustus(10);
        Pelilauta lauta = new Pelilauta(10, alustus.getRuudut());
        Ruutu[][] ruudut = lauta.getRuudut();
        int miinoja = 0;
        for (int k = 0; k < 10; k++) {
            for (int j = 0; j < 10; j++) {
                if (ruudut[k][j].isOnkoMiina()) {
                    miinoja++;
                }
            }
        }
        assertEquals(20, miinoja);
    }

    @Test
    public void eiMiinojaKunRuutujaVahemmanKuinViisi() {
        RuudukonAlustus alustus = new RuudukonAlustus(2);
        Pelilauta lauta = new Pelilauta(2, alustus.getRuudut());
        Ruutu[][] ruudut = lauta.getRuudut();
        int miinoja = 0;
        for (int k = 0; k < 2; k++) {
            for (int j = 0; j < 2; j++) {
                if (ruudut[k][j].isOnkoMiina()) {
                    miinoja++;
                }
            }
        }
        assertEquals(0, miinoja);
    }

    @Test
    public void laudanKokoOikea() {
        RuudukonAlustus alustus = new RuudukonAlustus(20);
        Pelilauta lauta = new Pelilauta(20, alustus.getRuudut());
        int vastaus = lauta.getKoko();
        assertEquals(20, vastaus);
    }

    @Test
    public void naapurissaMiinojaOikeaMaara() {
        RuudukonAlustus alustus = new RuudukonAlustus(3);
        Pelilauta lauta = new Pelilauta(3, alustus.getRuudut());
        int vastaus = lauta.montaMiinaaNaapurissa(1, 1);
        assertEquals(1, vastaus);
    }

    @Test
    public void siirraMiinaToimii() {
        RuudukonAlustus alustus = new RuudukonAlustus(5);
        Pelilauta lauta = new Pelilauta(5, alustus.getRuudut());
        Ruutu[][] ruudut = lauta.getRuudut();
        int rivi = 0;
        int sarake = 0;
        for (int k = 0; k < 5; k++) {
            for (int j = 0; j < 5; j++) {
                if (ruudut[k][j].isOnkoMiina()) {
                    rivi = k;
                    sarake = j;
                    break;
                }
            }
        }
        lauta.siirraMiina(rivi, sarake);
        boolean vastaus = ruudut[rivi][sarake].isOnkoMiina();
        assertEquals(false, vastaus);
    }
}
