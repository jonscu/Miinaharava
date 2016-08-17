
package fi.jonna.mehtonen.domain;

import javax.swing.JButton;

/**
 * Ruutu -luokka asettaa ja palauttaa tietyn ruudun
 * sisältämät tiedot.
 * 
 */
public class Ruutu {

    private boolean onkoMiina = false;
    private int arvo;
    private boolean onkoLippu = false;
    private boolean onkoAvattu = false;

    public boolean isOnkoMiina() {
        return onkoMiina;
    }

    /**
     * Asetetaan ruutuun miina.
     */
    public void setMiina() {
        this.onkoMiina = true;
    }

    /** 
     * Jos ruudussa oli miina, otetaan se pois.
     */
    public void poistaMiina() {
        this.onkoMiina = false;
    }

    public void setArvo(int arvo) {
        this.arvo = arvo;
    }

    public int getArvo() {
        return arvo;
    }

    /**
     * Tarkistetaan oliko ruudussa lippu.
     * @return palautetaan false, jos ei ollut lippua, ja true, jos oli.
     */
    public boolean isOnkoLippu() {
        return onkoLippu;
    }

    /**
     * Asetetaan ruutuun lippu, jos siinä ei ollut.
     */
    public void setLippu() {
        onkoLippu = true;
    }

    /**
     * Jos ruudussa oli lippu, otetaan se pois.
     */
    public void poistaLippu() {
        onkoLippu = false;
    }

    public boolean isOnkoAvattu() {
        return onkoAvattu;
    }

    /**
     * Merkataan, että ruutu on avattu.
     */
    public void merkkaaRuutuAvatuksi() {
        onkoAvattu = true;
    }

}
