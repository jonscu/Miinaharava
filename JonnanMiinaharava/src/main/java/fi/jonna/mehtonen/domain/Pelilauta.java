package fi.jonna.mehtonen.domain;

import fi.jonna.mehtonen.domain.Ruutu;
import java.util.Random;

/**
 * Pelilauta luokka alustaa itse pelilaudan luomalla ruudut, asettamalla niihin
 * arvot ja asettamalla miinat.
 */
public class Pelilauta {

    private int koko;
    private Ruutu[][] ruudut;
    private Random rnd;

    /**
     * Konstruktorissa asetetaan laudan koko halutuksi, luodaan random ja Ruutu
     * -matriisi oliot sekä alustetaan pelilauta.
     *
     * @param laudanKoko Pelilaudan haluttu koko.
     * @param ruudut RuudukonAlustuksessa luotu ruudukko, joka sisältää miinat.
     */
    public Pelilauta(int laudanKoko, Ruutu[][] ruudut) {
        this.koko = laudanKoko;
        this.ruudut = ruudut;
        this.rnd = new Random();
        asetaRuuduilleArvot();
    }

    /**
     * Jos ensimmäinen ruutu, joka avataan sisältää miinan, niin miinalle
     * arvotaan uusi paikka ja peli jatkuu. Kayttaja ei huomaa siirtoa.
     *
     * @param rivi kertoo, millä rivillä korvattava miina sijaitsee.
     * @param sarake kertoo, millä sarakkeella korvattava miina sijaitsee.
     */
    public void siirraMiina(int rivi, int sarake) {
        boolean siirretty = false;
        while (!siirretty) {
            int uusiRivi = rnd.nextInt(koko);
            int uusiSarake = rnd.nextInt(koko);
            if (!ruudut[uusiRivi][uusiSarake].isOnkoMiina()) {
                ruudut[rivi][sarake].poistaMiina();
                ruudut[uusiRivi][uusiSarake].setMiina();
                asetaRuuduilleArvot();
                siirretty = true;
            }
        }
    }

    /**
     * Asetetaan ruuduille arvoiksi se luku, joka vastaa naapuriruuduissa
     * olevien miinojen maara.
     */
    public void asetaRuuduilleArvot() {
        for (int k = 0; k < koko; k++) {
            for (int j = 0; j < koko; j++) {
                if (!ruudut[k][j].isOnkoMiina()) {
                    ruudut[k][j].setArvo(montaMiinaaNaapurissa(k, j));
                }
            }
        }

    }

    /**
     * Lasketaan, kuinka monta miinaa on ruudun naapureissa.
     *
     * @param rivi kertoo, millä rivillä kyseinen ruutu sijaitsee.
     * @param sarake kertoo, millä sarakkeella kyseinen ruutu sijaitsee.
     * @return naapurissa olevien miinojen määrä.
     */
    public int montaMiinaaNaapurissa(int rivi, int sarake) {
        int arvo = 0;
        for (int i = -1; i <= 1; i++) {
            for (int k = -1; k <= 1; k++) {
                int uusiRivi = rivi - i;
                int uusiSarake = sarake - k;
                if (uusiRivi == rivi && uusiSarake == sarake) {
                    continue;
                } else if (uusiRivi >= 0 && uusiRivi < koko && uusiSarake >= 0 && uusiSarake < koko) {
                    if (ruudut[uusiRivi][uusiSarake].isOnkoMiina()) {
                        arvo++;
                    }
                }
            }
        }
        return arvo;
    }

    public int getKoko() {
        return koko;
    }

    public Ruutu[][] getRuudut() {
        return ruudut;
    }

}
