/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.jonnanmiinaharava;

import miinaharava.jonnanmiinaharava.*;
import static java.lang.System.out;

/**
 *
 * @author jonscu
 */
public class Main {

    public static void main(String[] args) {
        Pelilauta lauta = new Pelilauta(5);
        lauta.tulostaPelilauta();
        Ruutu[][] ruudut = lauta.getRuudut();
        for (int k = 0; k < 5; k++) {
            for (int j = 0; j < 5; j++) {
                System.out.println(ruudut[k][j]);
            }
        }
    }
}
