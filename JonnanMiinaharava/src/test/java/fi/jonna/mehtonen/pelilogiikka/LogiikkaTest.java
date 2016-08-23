/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jonna.mehtonen.pelilogiikka;

import fi.jonna.mehtonen.domain.Pelilauta;
import fi.jonna.mehtonen.domain.RuudukonAlustus;
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
        RuudukonAlustus alustus = new RuudukonAlustus(10);
        Pelilauta lauta = new Pelilauta(10, alustus.getRuudut());
        Ruutu[][] ruudut = lauta.getRuudut();
        Logiikka logiikka = new Logiikka(10, lauta, 20);
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
        RuudukonAlustus alustus = new RuudukonAlustus(10);
        Pelilauta lauta = new Pelilauta(10, alustus.getRuudut());
        Ruutu[][] ruudut = lauta.getRuudut();
        Logiikka logiikka = new Logiikka(10, lauta, 20);
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
        RuudukonAlustus alustus = new RuudukonAlustus(10);
        Pelilauta lauta = new Pelilauta(10, alustus.getRuudut());
        Ruutu[][] ruudut = lauta.getRuudut();
        Logiikka logiikka = new Logiikka(10, lauta, 20);
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
        RuudukonAlustus alustus = new RuudukonAlustus(10);
        Pelilauta lauta = new Pelilauta(10, alustus.getRuudut());
        Ruutu[][] ruudut = lauta.getRuudut();
        Logiikka logiikka = new Logiikka(10, lauta, 20);
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

    @Test
    public void avaaNaapuritToimii() {
        Ruutu[][] ruudut = new Ruutu[3][3];
        for (int k = 0; k < 3; k++) {
            for (int j = 0; j < 3; j++) {
                ruudut[k][j] = new Ruutu();
            }
        }
        ruudut[0][2].setMiina();
        Pelilauta lauta = new Pelilauta(3, ruudut);
        ruudut = lauta.getRuudut();
        Logiikka logiikka = new Logiikka(3, lauta, 1);
        logiikka.avataanRuutuTaiSiirretaanMiina(0, 0);

        boolean vastaus = true;
        for (int k = 0; k < 3; k++) {
            for (int j = 0; j < 3; j++) {
                if (!ruudut[k][j].isOnkoMiina() && !ruudut[k][j].isOnkoAvattu()) {
                    vastaus = false;
                    break;
                } else if (!ruudut[k][j].isOnkoMiina()) {
                    vastaus = true;
                }
            }

        }
        assertEquals(true, vastaus);
    }

    @Test
    public void laitetaanLippuToimii() {
        Ruutu[][] ruudut = new Ruutu[3][3];
        for (int k = 0; k < 3; k++) {
            for (int j = 0; j < 3; j++) {
                ruudut[k][j] = new Ruutu();
            }
        }
        ruudut[0][2].setMiina();
        Pelilauta lauta = new Pelilauta(3, ruudut);
        ruudut = lauta.getRuudut();
        Logiikka logiikka = new Logiikka(3, lauta, 1);
        logiikka.laitetaanTaiPoistetaanLippu(1, 1);
        boolean vastaus = ruudut[1][1].isOnkoLippu();
        assertEquals(true, vastaus);
    }

    @Test
    public void poistetaanLippuToimii() {
        Ruutu[][] ruudut = new Ruutu[3][3];
        for (int k = 0; k < 3; k++) {
            for (int j = 0; j < 3; j++) {
                ruudut[k][j] = new Ruutu();
            }
        }
        ruudut[0][2].setMiina();
        Pelilauta lauta = new Pelilauta(3, ruudut);
        ruudut = lauta.getRuudut();
        Logiikka logiikka = new Logiikka(3, lauta, 1);
        logiikka.laitetaanTaiPoistetaanLippu(1, 1);
        logiikka.laitetaanTaiPoistetaanLippu(1, 1);
        boolean vastaus = ruudut[1][1].isOnkoLippu();
        assertEquals(false, vastaus);
    }

    @Test
    public void avaaRuutuToimii() {
        Ruutu[][] ruudut = new Ruutu[3][3];
        for (int k = 0; k < 3; k++) {
            for (int j = 0; j < 3; j++) {
                ruudut[k][j] = new Ruutu();
            }
        }
        ruudut[0][2].setMiina();
        Pelilauta lauta = new Pelilauta(3, ruudut);
        ruudut = lauta.getRuudut();
        Logiikka logiikka = new Logiikka(3, lauta, 1);
        logiikka.avataanRuutuTaiSiirretaanMiina(1, 1);
        boolean vastaus = ruudut[1][1].isOnkoAvattu();
        assertEquals(true, vastaus);
    }

    @Test
    public void siirraMiinaToimii() {
        Ruutu[][] ruudut = new Ruutu[3][3];
        for (int k = 0; k < 3; k++) {
            for (int j = 0; j < 3; j++) {
                ruudut[k][j] = new Ruutu();
            }
        }
        ruudut[0][2].setMiina();
        Pelilauta lauta = new Pelilauta(3, ruudut);
        ruudut = lauta.getRuudut();
        Logiikka logiikka = new Logiikka(3, lauta, 1);
        logiikka.avataanRuutuTaiSiirretaanMiina(0, 2);
        boolean vastaus = ruudut[0][2].isOnkoMiina();
        assertEquals(false, vastaus);
    }
    
    @Test
    public void laitetaanLippuLisaaLippujenMaaraa() {
        Ruutu[][] ruudut = new Ruutu[3][3];
        for (int k = 0; k < 3; k++) {
            for (int j = 0; j < 3; j++) {
                ruudut[k][j] = new Ruutu();
            }
        }
        ruudut[0][2].setMiina();
        Pelilauta lauta = new Pelilauta(3, ruudut);
        ruudut = lauta.getRuudut();
        Logiikka logiikka = new Logiikka(3, lauta, 1);
        logiikka.laitetaanTaiPoistetaanLippu(1, 1);;
        assertEquals(1, logiikka.getLippujaKaytetty());
    }

    @Test
    public void poistetaanLippuVahentaaLippujenMaaraa() {
        Ruutu[][] ruudut = new Ruutu[3][3];
        for (int k = 0; k < 3; k++) {
            for (int j = 0; j < 3; j++) {
                ruudut[k][j] = new Ruutu();
            }
        }
        ruudut[0][2].setMiina();
        Pelilauta lauta = new Pelilauta(3, ruudut);
        ruudut = lauta.getRuudut();
        Logiikka logiikka = new Logiikka(3, lauta, 1);
        logiikka.laitetaanTaiPoistetaanLippu(1, 1);
        logiikka.laitetaanTaiPoistetaanLippu(1, 1);
        assertEquals(0, logiikka.getLippujaKaytetty());
    }
}
