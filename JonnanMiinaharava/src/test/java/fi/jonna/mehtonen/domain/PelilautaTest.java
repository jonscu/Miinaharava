/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jonna.mehtonen.domain;

import fi.jonna.mehtonen.PelilaudanLuoja;
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

    //@Test
    //public void voittoKunMiinoissaLiput() {
      //  PelilaudanLuoja lauta = new PelilaudanLuoja(10);
        //Ruutu[][] ruudut = lauta.getRuudut();
        //for (int k = 0; k < 10; k++) {
          //  for (int j = 0; j < 10; j++) {
            //    if (ruudut[k][j].isOnkoMiina()) {
              //      ruudut[k][j].asetaLippu();
                //}
            //}
        //}
        //boolean vastaus = lauta.voitto();
        //assertEquals(true, vastaus);
    //}

    @Test
    public void naapurissaMiinojaOikeaMaara() {
        PelilaudanLuoja lauta = new PelilaudanLuoja(3);
        int vastaus = lauta.montaMiinaaNaapurissa(1, 1);
        assertEquals(1, vastaus);
    }
}
