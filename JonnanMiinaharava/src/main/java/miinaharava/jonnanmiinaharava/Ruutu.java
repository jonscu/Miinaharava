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
public class Ruutu {

    private Sijainti sijainti;
    private boolean onkoMiina = false;

    public Ruutu(int sarake, int rivi) {
        this.sijainti = new Sijainti(sarake, rivi);
    }

    public boolean isOnkoMiina() {
        return onkoMiina;
    }

    public Sijainti getSijainti() {
        return sijainti;
    }

    public void asetaMiina() {
        this.onkoMiina = true;
    }

    public void setSijainti(int sarake, int rivi) {
        this.sijainti = new Sijainti(sarake, rivi);
    }

    @Override
    public String toString() {
        return "Sijainti " + getSijainti() + ". Onko miina " + isOnkoMiina();
    }

}
