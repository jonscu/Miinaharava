/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jonna.mehtonen.domain;

import javax.swing.JButton;

/**
 *
 * @author jonscu
 */
public class Ruutu {

    private boolean onkoMiina = false;
    private int arvo;
    private boolean onkoLippu = false;
    private boolean onkoAvattu = false;

    public boolean isOnkoMiina() {
        return onkoMiina;
    }

    public void asetaMiina() {
        this.onkoMiina = true;
    }

    public void setArvo(int arvo) {
        this.arvo = arvo;
    }

    public int getArvo() {
        return arvo;
    }

    public boolean isOnkoLippu() {
        return onkoLippu;
    }

    public void asetaLippu() {
        onkoLippu = true;
    }
    
    public void poistaLippu() {
        onkoLippu = false;
    }

    public boolean isOnkoAvattu() {
        return onkoAvattu;
    }

    public void merkkaaRuutuAvatuksi() {
        onkoAvattu = true;
    }

}
