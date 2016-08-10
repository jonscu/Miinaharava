/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jonna.mehtonen.domain;

import fi.jonna.mehtonen.PelilaudanLuoja;
import fi.jonna.mehtonen.domain.Ruutu;

/**
 *
 * @author jonscu
 */
public class Pelilauta {

    private int koko;
    private Ruutu[][] ruudut;

    public Pelilauta(int laudankoko) {
        this.koko = laudankoko;
        PelilaudanLuoja lauta = new PelilaudanLuoja(koko);
        ruudut = lauta.getRuudut();
        asetaRuuduilleArvot();
    }

    public void tulostaPelilauta() {
        for (int k = 0; k < koko; k++) {
            for (int j = 0; j < koko; j++) {
                if (ruudut[k][j].isOnkoMiina()) {
                    System.out.print("*");
                } else {
                    System.out.print(ruudut[k][j].getArvo());
                }
            }
            System.out.println();

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
                int uusirivi = rivi - i;
                int uusisarake = sarake - k;
                if (uusirivi == rivi && uusisarake == sarake) {
                    continue;
                } else if (uusirivi >= 0 && uusirivi < koko && uusisarake >= 0 && uusisarake < koko) {
                    if (ruudut[uusirivi][uusisarake].isOnkoMiina()) {
                        arvo++;
                    }
                }
            }
        }
        return arvo;
    }

    public int miinojaYhteensa() {
        int miinoja = 0;
        for (int k = 0; k < koko; k++) {
            for (int j = 0; j < koko; j++) {
                if (ruudut[k][j].isOnkoMiina()) {
                    miinoja++;
                }
            }
        }
        return miinoja;
    }

    public Boolean voitto() {
        int miinoja = miinojaYhteensa();
        int miinojaMerkattu = 0;

        for (int k = 0; k < koko; k++) {
            for (int j = 0; j < koko; j++) {
                if (ruudut[k][j].isOnkoLippu() && ruudut[k][j].isOnkoMiina()) {
                    miinojaMerkattu++;
                }
            }
        }
        if (miinojaMerkattu == miinoja) {
            return true;
        } else {
            return false;
        }
    }

    public void avaaRuutu(int rivi, int sarake) {
        ruudut[rivi][sarake].merkkaaRuutuAvatuksi();
    }

    public int getRuudutKoko() {
        return ruudut.length;
    }

    public Ruutu[][] getRuudut() {
        return ruudut;
    }

}
