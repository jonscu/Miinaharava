package fi.jonna.mehtonen.domain;

import java.util.Random;
import fi.jonna.mehtonen.domain.*;

/**
 * RuudukonAlustus luokassa luodaan Ruutu matriisi, ja arvotaan miinojen paikat.
 *
 */
public class RuudukonAlustus {

    private Ruutu[][] ruudut;
    private int koko;
    private Random rnd;

    /**
     * Luodaan pelilaudan kokoinen Ruutu matriisi ja arvotaan sinne ruutujen
     * paikat.
     *
     * @param koko Pelilaudan koko.
     */
    public RuudukonAlustus(int koko) {
        this.koko = koko;
        this.rnd = new Random();
        this.ruudut = new Ruutu[koko][koko];
        alustaPelilauta();
    }

    /**
     * Luodaan ruudut, sijoitetaan miinat random generaattorin avulla ja
     * asetetaan miinattomille ruuduille arvot.
     */
    public void alustaPelilauta() {
        luoRuudut();
        asetaMiinat();
    }

    /**
     * Luodaan matriisi, joka sisältää ruutuja.
     *
     */
    public void luoRuudut() {
        for (int k = 0; k < koko; k++) {
            for (int j = 0; j < koko; j++) {
                ruudut[k][j] = new Ruutu();
            }
        }
    }

    /**
     * Arvotaan miinoille paikat pelissä.
     *
     */
    public void asetaMiinat() {
        int i;
        int j;
        for (int k = 0; k < (koko * koko) / 5; k++) {
            i = rnd.nextInt(koko);
            j = rnd.nextInt(koko);

            if (ruudut[i][j].isOnkoMiina()) {
                k--;
            } else {
                ruudut[i][j].setMiina();
            }
        }
    }

    public Ruutu[][] getRuudut() {
        return ruudut;
    }
}
