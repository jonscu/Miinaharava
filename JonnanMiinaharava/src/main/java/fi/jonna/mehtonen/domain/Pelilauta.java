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

    public Pelilauta(int laudanKoko) {
        this.koko = laudanKoko;
        PelilaudanLuoja lauta = new PelilaudanLuoja(koko);
        ruudut = lauta.getRuudut();
    }

    //Turha, tarkistin vain, että koodi toimii
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

    //Tämäkin ehkä turha..
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
