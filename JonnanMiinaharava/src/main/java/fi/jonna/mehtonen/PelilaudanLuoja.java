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
    private int laudankoko;
    private Random rnd;

    public PelilaudanLuoja(int koko) {
        ruudut = new Ruutu[koko][koko];
        this.laudankoko = koko;
        this.rnd = new Random();
        luoRuudut();
        asetaMiinat();
    }

    public void luoRuudut() {
        for (int k = 0; k < laudankoko; k++) {
            for (int j = 0; j < laudankoko; j++) {
                ruudut[k][j] = new Ruutu();
            }
        }
    }

    public void asetaMiinat() {
        int i;
        int j;
        for (int k = 0; k < (laudankoko * laudankoko) / 5; k++) {
            i = rnd.nextInt(laudankoko);
            j = rnd.nextInt(laudankoko);

            if (ruudut[i][j].isOnkoMiina()) {
                k--;
            } else {
                ruudut[i][j].asetaMiina();
            }
        }
    }

    public Ruutu[][] getRuudut() {
        return ruudut;
    }

}
