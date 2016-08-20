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
    private JButton[][] nappulat;
    private Pelilauta lauta;
    private int siirrot = 0;
    private int lippujaKaytetty = 0;
    private MiinaPaneeli paneeli;

    /**
     * Logiikka luokan konstruktorissa saadaan selville luodun Pelilaudan koko,
     * itse Pelilauta ja sen Ruudut, miinojen määrä, MiinaPaneelissa luotu
     * JButton matriisi ja itse MiinaPaneeli.
     *
     * @param koko Luodun pelilaudan koko.
     * @param lauta Luotu pelilauta.
     * @param miinoja Miinojen määrä pelilaudassa.
     * @param nappulat MiinaPaneelissa luodut JButton matriisi.
     * @param paneeli Itse MiinaPaneeli.
     */
    public Logiikka(int koko, Pelilauta lauta, int miinoja, JButton[][] nappulat, MiinaPaneeli paneeli) {
        this.lauta = lauta;
        this.ruudut = lauta.getRuudut();
        this.koko = koko;
        this.miinoja = miinoja;
        this.nappulat = nappulat;
        this.paneeli = paneeli;
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
     * Tarkistetaan painettiinko oikeeta vai vasenta hiiren nappulaa. Jos
     * klikattiin vasenta nappulaa, merkataan ruutu avatuksi ja tarkistetaan,
     * voitettiinko tai havittiinko peli. Jos klikattiin oikeata nappulaa, niin
     * laitetaan ruutuun lippu, mikali siina ei ennestaan ollut lippua. Mikäli
     * oli, niin lippu otetaan pois, jolloin nappi on avaamaton.
     *
     * @param tapahtuma Hiiren klikkauksesta aiheutuva MouseEvent.
     */
    public void avataanRuutu(MouseEvent tapahtuma) {
        Component nappula = tapahtuma.getComponent();
        if (nappula.isEnabled() && tapahtuma.getButton() == 1) {
            for (int k = 0; k < koko; k++) {
                for (int j = 0; j < koko; j++) {
                    if (nappula == nappulat[k][j]) {
                        avataanRuutuTaiSiirretaanMiina(k, j);
                        break;
                    }
                }
            }
        } else if (tapahtuma.getButton() == 3) {
            for (int k = 0; k < koko; k++) {
                for (int j = 0; j < koko; j++) {
                    if (nappula == nappulat[k][j]) {
                        laitetaanTaiPoistetaanLippu(k, j);
                        break;
                    }
                }
            }

        }
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
            paneeli.merkataanRuutuAvatuksi(nappulat[k][j], k, j);
            avaaNaapurit(nappulat[k][j], k, j, nappulat);
            siirrot++;
        } else if (!ruudut[k][j].isOnkoLippu()) {
            paneeli.merkataanRuutuAvatuksi(nappulat[k][j], k, j);
            if (ruudut[k][j].getArvo() == 0) {
                avaaNaapurit(nappulat[k][j], k, j, nappulat);
            }
            siirrot++;
            tarkistaPelinLoppuminen();
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
            paneeli.asetaLippu(nappulat[k][j], k, j);
        } else if (ruudut[k][j].isOnkoLippu()) {
            lippujaKaytetty--;
            paneeli.poistaLippu(nappulat[k][j], k, j);
        }
    }

    /**
     * Metodi kutsuu voitaPeli, jos peli voitettiin. Jos peli hävittiin, niin se
     * kutsuu haviaPeli metodia.
     */
    public void tarkistaPelinLoppuminen() {
        if (voitto()) {
            paneeli.voitaPeli();
        } else if (havio()) {
            paneeli.haviaPeli();
        }
    }

    /**
     * Mikali klikatun ruudun yhdessakaan naapuriruudussa ei ole miinaa, peli
     * paljastaa ensin kaikki siihen yhteydessa olevat ruudut, joiden naapurissa
     * ei myoskaan ole miinaa, sekä lisaksi taman paljastetun alueen reunalla
     * olevat ruudut, joiden naapurissa on miina.
     *
     * @param nappula JButton, jota on klikattu.
     * @param rivi nappulan rivi sijainti.
     * @param sarake nappulan sarake sijainti.
     * @param nappulat laudan kaikki nappulat matriisissa.
     */
    public void avaaNaapurit(JButton nappula, int rivi, int sarake, JButton[][] nappulat) {
        for (int i = -1; i <= 1; i++) {
            for (int k = -1; k <= 1; k++) {
                int uusiRivi = rivi - i;
                int uusiSarake = sarake - k;
                if (uusiRivi == rivi && uusiSarake == sarake) {
                    continue;
                } else if (uusiRivi >= 0 && uusiRivi < koko && uusiSarake >= 0 && uusiSarake < koko) {
                    if (!ruudut[uusiRivi][uusiSarake].isOnkoAvattu() && ruudut[uusiRivi][uusiSarake].getArvo() == 0 && !ruudut[uusiRivi][uusiSarake].isOnkoMiina()) {
                        paneeli.merkataanRuutuAvatuksi(nappulat[uusiRivi][uusiSarake], uusiRivi, uusiSarake);
                        avaaNaapurit(nappulat[uusiRivi][uusiSarake], uusiRivi, uusiSarake, nappulat);
                    } else if (ruudut[rivi][sarake].getArvo() == 0 && !ruudut[uusiRivi][uusiSarake].isOnkoAvattu() && ruudut[uusiRivi][uusiSarake].getArvo() != 0 && !ruudut[uusiRivi][uusiSarake].isOnkoMiina()) {
                        paneeli.merkataanRuutuAvatuksi(nappulat[uusiRivi][uusiSarake], uusiRivi, uusiSarake);
                    }
                }
            }
        }
    }

    public int getLippujaKaytetty() {
        return lippujaKaytetty;
    }
}
