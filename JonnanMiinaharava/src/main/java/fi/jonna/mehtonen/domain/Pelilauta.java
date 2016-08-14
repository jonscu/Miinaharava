/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jonna.mehtonen.domain;

import fi.jonna.mehtonen.domain.Ruutu;
import java.util.Random;

/**
 *
 * @author jonscu
 */
public class Pelilauta {

    private int koko;
    private Ruutu[][] ruudut;
    private Random rnd;

    public Pelilauta(int laudanKoko) {
        this.koko = laudanKoko;
        this.rnd = new Random();
        ruudut = new Ruutu[koko][koko];
        luoPelilauta();
    }

    public void luoPelilauta() {
        luoRuudut();
        asetaMiinat();
        asetaRuuduilleArvot();
    }

    public void luoRuudut() {
        for (int k = 0; k < koko; k++) {
            for (int j = 0; j < koko; j++) {
                ruudut[k][j] = new Ruutu();
            }
        }
    }

    public void asetaMiinat() {
        int i;
        int j;
        for (int k = 0; k < (koko * koko) / 5; k++) {
            i = rnd.nextInt(koko);
            j = rnd.nextInt(koko);

            if (ruudut[i][j].isOnkoMiina()) {
                k--;
            } else {
                ruudut[i][j].asetaMiina();
            }
        }
    }

    public void asetaRuuduilleArvot() {
        for (int k = 0; k < koko; k++) {
            for (int j = 0; j < koko; j++) {
                if (!ruudut[k][j].isOnkoMiina()) {
                    ruudut[k][j].setArvo(montaMiinaaNaapurissa(k, j));
                }
            }
        }

    }

    public int montaMiinaaNaapurissa(int rivi, int sarake) {
        int arvo = 0;
        for (int i = -1; i <= 1; i++) {
            for (int k = -1; k <= 1; k++) {
                int uusiRivi = rivi - i;
                int uusiSarake = sarake - k;
                if (uusiRivi == rivi && uusiSarake == sarake) {
                    continue;
                } else if (uusiRivi >= 0 && uusiRivi < koko && uusiSarake >= 0 && uusiSarake < koko) {
                    if (ruudut[uusiRivi][uusiSarake].isOnkoMiina()) {
                        arvo++;
                    }
                }
            }
        }
        return arvo;
    }

    public int getKoko() {
        return koko;
    }

    public Ruutu[][] getRuudut() {
        return ruudut;
    }

}
