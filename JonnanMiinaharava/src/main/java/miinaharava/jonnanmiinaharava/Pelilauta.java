/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.jonnanmiinaharava;

import miinaharava.jonnanmiinaharava.*;

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
    }

    public void tulostaPelilauta() {
        for (int k = 0; k < koko; k++) {
            for (int j = 0; j < koko; j++) {
                if (ruudut[j][k].isOnkoMiina()) {
                    System.out.print("*");
                } else {
                    System.out.print("0");
                }
            }
            System.out.println();

        }
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

    public int getRuudutKoko() {
        return ruudut.length;
    }

    public Ruutu[][] getRuudut() {
        return ruudut;
    }

}
