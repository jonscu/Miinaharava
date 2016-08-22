package fi.jonna.mehtonen.pelilogiikka;

import fi.jonna.mehtonen.domain.*;
import fi.jonna.mehtonen.kayttoliittyma.MiinaPaneeli;
import java.awt.Component;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 * Logiikka luokassa tarkastellaan, mitä tehdään, kun painetaan hiiren oikeata
 * tai vasenta klikkiä sekä onko peli voitettu tai hävitty. Lisäksi Logiikka
 * luokka tarkistaa, avataanko naapuri ruudut.
 */
public class Logiikka {

    private int koko, miinoja;
    private Ruutu[][] ruudut;
    private Pelilauta lauta;
    private int siirrot = 0;
    private int lippujaKaytetty = 0;

    /**
     * Logiikka luokan konstruktorissa saadaan selville luodun Pelilaudan koko,
     * itse Pelilauta ja sen Ruudut, miinojen määrä, MiinaPaneelissa luotu
     * JButton matriisi ja itse MiinaPaneeli.
     *
     * @param koko Luodun pelilaudan koko.
     * @param lauta Luotu pelilauta.
     * @param miinoja Miinojen määrä pelilaudassa.
     */
    public Logiikka(int koko, Pelilauta lauta, int miinoja) {
        this.lauta = lauta;
        this.ruudut = lauta.getRuudut();
        this.koko = koko;
        this.miinoja = miinoja;
    }

    /**
     * Tarkistetaan, onko peli voitettu. Peli on voitettu, jos kaikki
     * miinattomat ruudut ovat avattu.
     *
     * @return true, jos peli on voitettu, muulloin false.
     */
    public Boolean voitto() {
        int ruutujaAvattu = 0;

        for (int k = 0; k < koko; k++) {
            for (int j = 0; j < koko; j++) {
                if (ruudut[k][j].isOnkoAvattu() && !ruudut[k][j].isOnkoMiina()) {
                    ruutujaAvattu++;
                }
            }
        }
        if (ruutujaAvattu == (koko * koko) - miinoja) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Tarkistetaan, onko peli hävitty. Peli hävitään, jos käyttäjä avaa ruudun,
     * jossa on miina.
     *
     * @return true, jos peli hävittiin, muulloin false.
     */
    public Boolean havio() {
        for (int k = 0; k < koko; k++) {
            for (int j = 0; j < koko; j++) {
                if (ruudut[k][j].isOnkoMiina() && ruudut[k][j].isOnkoAvattu()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Jos ensimmäisenä pelaaja avaa miinan, niin miina siirretään. Muussa
     * tapauksessa Ruutu avataan ja paljastetaan naapurissa olevien miinojen
     * lukumäärä, mikäli ruutua ei ole ennen avattu.
     *
     * @param k Ruudun rivi sijainti.
     * @param j Ruudun sarake sijainti.
     */
    public void avataanRuutuTaiSiirretaanMiina(int k, int j) {
        if (ruudut[k][j].isOnkoMiina() && siirrot == 0 && !ruudut[k][j].isOnkoLippu()) {
            lauta.siirraMiina(k, j);
            ruudut[k][j].merkkaaRuutuAvatuksi();
            avaaNaapurit(k, j);
            siirrot++;
        } else if (!ruudut[k][j].isOnkoLippu()) {
            ruudut[k][j].merkkaaRuutuAvatuksi();
            if (ruudut[k][j].getArvo() == 0) {
                avaaNaapurit(k, j);
            }
            siirrot++;
        }
    }

    /**
     * Jos ruudussa ei ennestään ole lippua, niin siihen laitetaan lippu. Jos
     * ruudussa oli ennestään lippu, niin se otetaan pois.
     *
     * @param k Ruudun rivi sijainti.
     * @param j Ruudun sarake sijainti.
     */
    public void laitetaanTaiPoistetaanLippu(int k, int j) {
        if (!ruudut[k][j].isOnkoLippu() && lippujaKaytetty != miinoja && !ruudut[k][j].isOnkoAvattu()) {
            lippujaKaytetty++;
            ruudut[k][j].setLippu();
        } else if (ruudut[k][j].isOnkoLippu()) {
            lippujaKaytetty--;
            ruudut[k][j].poistaLippu();
        }
    }

    /**
     * Mikali klikatun ruudun yhdessakaan naapuriruudussa ei ole miinaa, peli
     * paljastaa ensin kaikki siihen yhteydessa olevat ruudut, joiden naapurissa
     * ei myoskaan ole miinaa, sekä lisaksi taman paljastetun alueen reunalla
     * olevat ruudut, joiden naapurissa on miina.
     *
     * @param rivi nappulan rivi sijainti.
     * @param sarake nappulan sarake sijainti.
     */
    public void avaaNaapurit(int rivi, int sarake) {
        for (int i = -1; i <= 1; i++) {
            for (int k = -1; k <= 1; k++) {
                int uusiRivi = rivi - i;
                int uusiSarake = sarake - k;
                if (uusiRivi == rivi && uusiSarake == sarake) {
                    continue;
                } else if (uusiRivi >= 0 && uusiRivi < koko && uusiSarake >= 0 && uusiSarake < koko) {
                    if (!ruudut[uusiRivi][uusiSarake].isOnkoAvattu() && ruudut[uusiRivi][uusiSarake].getArvo() == 0 && !ruudut[uusiRivi][uusiSarake].isOnkoMiina()) {
                        ruudut[uusiRivi][uusiSarake].merkkaaRuutuAvatuksi();
                        avaaNaapurit(uusiRivi, uusiSarake);
                    } else if (ruudut[rivi][sarake].getArvo() == 0 && !ruudut[uusiRivi][uusiSarake].isOnkoAvattu() && ruudut[uusiRivi][uusiSarake].getArvo() != 0 && !ruudut[uusiRivi][uusiSarake].isOnkoMiina()) {
                        ruudut[uusiRivi][uusiSarake].merkkaaRuutuAvatuksi();
                    }
                }
            }
        }
    }

    public int getLippujaKaytetty() {
        return lippujaKaytetty;
    }
}
