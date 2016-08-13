/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jonna.mehtonen;

import fi.jonna.mehtonen.domain.Ruutu;
import java.util.Random;

/**
 *
 * @author jonscu
 */
public class PelilaudanLuoja {

    private Ruutu[][] ruudut;
    private int laudanKoko;
    private Random rnd;

    public PelilaudanLuoja(int koko) {
        ruudut = new Ruutu[koko][koko];
        this.laudanKoko = koko;
        this.rnd = new Random();
        luoRuudut();
        asetaMiinat();
        asetaRuuduilleArvot();
    }

    public void luoRuudut() {
        for (int k = 0; k < laudanKoko; k++) {
            for (int j = 0; j < laudanKoko; j++) {
                ruudut[k][j] = new Ruutu();
            }
        }
    }

    public void asetaMiinat() {
        int i;
        int j;
        for (int k = 0; k < (laudanKoko * laudanKoko) / 5; k++) {
            i = rnd.nextInt(laudanKoko);
            j = rnd.nextInt(laudanKoko);

            if (ruudut[i][j].isOnkoMiina()) {
                k--;
            } else {
                ruudut[i][j].asetaMiina();
            }
        }
    }
    
        public void asetaRuuduilleArvot() {
        for (int k = 0; k < laudanKoko; k++) {
            for (int j = 0; j < laudanKoko; j++) {
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
                } else if (uusiRivi >= 0 && uusiRivi < laudanKoko && uusiSarake >= 0 && uusiSarake < laudanKoko) {
                    if (ruudut[uusiRivi][uusiSarake].isOnkoMiina()) {
                        arvo++;
                    }
                }
            }
        }
        return arvo;
    }

    public Ruutu[][] getRuudut() {
        return ruudut;
    }

}
