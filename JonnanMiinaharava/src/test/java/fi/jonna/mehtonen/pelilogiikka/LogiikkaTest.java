/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jonna.mehtonen.pelilogiikka;

import fi.jonna.mehtonen.domain.Pelilauta;
import fi.jonna.mehtonen.domain.Ruutu;
import fi.jonna.mehtonen.kayttoliittyma.MiinaPaneeli;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
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
public class LogiikkaTest {

    public LogiikkaTest() {
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
    public void voittoToimii() {
        Pelilauta lauta = new Pelilauta(10);
        Ruutu[][] ruudut = lauta.getRuudut();
        MiinaPaneeli paneeli = new MiinaPaneeli(10, lauta);
        Logiikka logiikka = new Logiikka(10, lauta, 20, new JButton[10][10], paneeli);
        for (int k = 0; k < 10; k++) {
            for (int j = 0; j < 10; j++) {
                if (!ruudut[k][j].isOnkoMiina()) {
                    ruudut[k][j].merkkaaRuutuAvatuksi();
                }
            }
        }

        assertEquals(true, logiikka.voitto());

    }

    @Test
    public void havioToimii() {
        Pelilauta lauta = new Pelilauta(10);
        Ruutu[][] ruudut = lauta.getRuudut();
        MiinaPaneeli paneeli = new MiinaPaneeli(10, lauta);
        Logiikka logiikka = new Logiikka(10, lauta, 20, new JButton[10][10], paneeli);
        for (int k = 0; k < 10; k++) {
            for (int j = 0; j < 10; j++) {
                if (ruudut[k][j].isOnkoMiina()) {
                    ruudut[k][j].merkkaaRuutuAvatuksi();
                    break;
                }
            }
        }

        assertEquals(true, logiikka.havio());
    }

    @Test
    public void eiVoittoa() {
        Pelilauta lauta = new Pelilauta(10);
        Ruutu[][] ruudut = lauta.getRuudut();
        MiinaPaneeli paneeli = new MiinaPaneeli(10, lauta);
        Logiikka logiikka = new Logiikka(10, lauta, 20, new JButton[10][10], paneeli);
        for (int k = 0; k < 10; k++) {
            for (int j = 0; j < 10; j++) {
                if (!ruudut[k][j].isOnkoMiina()) {
                    ruudut[k][j].merkkaaRuutuAvatuksi();
                    break;
                }
            }
        }

        assertEquals(false, logiikka.voitto());

    }

    @Test
    public void eiHaviota() {
        Pelilauta lauta = new Pelilauta(10);
        Ruutu[][] ruudut = lauta.getRuudut();
        MiinaPaneeli paneeli = new MiinaPaneeli(10, lauta);
        Logiikka logiikka = new Logiikka(10, lauta, 20, new JButton[10][10], paneeli);
        for (int k = 0; k < 10; k++) {
            for (int j = 0; j < 10; j++) {
                if (!ruudut[k][j].isOnkoMiina()) {
                    ruudut[k][j].merkkaaRuutuAvatuksi();
                    break;
                }
            }
        }

        assertEquals(false, logiikka.havio());
    }

}
