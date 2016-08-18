
package fi.jonna.mehtonen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import fi.jonna.mehtonen.domain.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * MiinaPaneeli luokassa rakennetaan pelin grafiikkaruudukko. Luokka sisältää
 * myös logiikkaluokan, jossa tarkastellaan, mitä tehdään, kun painetaan hiiren
 * oikeata tai vasenta klikkiä sekä onko peli voitettu tai hävitty.
 */
public class MiinaPaneeli extends JFrame {

    private int koko, miinoja;
    private JPanel paneeli = new JPanel();
    private JMenuBar ylaPaneeli = new JMenuBar();
    private JMenu menu = new JMenu();
    private JMenu valikko;
    private JButton[][] nappulat;
    private Pelilauta lauta;
    private Ruutu[][] ruudut;
    private Logiikka logiikka;
    private int siirrot = 0;
    private int lippujaKaytetty = 0;
    private JTextArea lippuja;
    private ImageIcon lippu, miina;
    private JLabel teksti;

    public MiinaPaneeli(int koko, Pelilauta lauta) {
        super("Miinaharava");
        setResizable(false);
        this.lippu = new ImageIcon("C:\\Users\\jonscu\\Documents\\GitHub\\Miinaharava\\JonnanMiinaharava\\src\\main\\java\\fi\\jonna\\mehtonen\\lippu2.png");
        this.miina = new ImageIcon("C:\\Users\\jonscu\\Documents\\GitHub\\Miinaharava\\JonnanMiinaharava\\src\\main\\java\\fi\\jonna\\mehtonen\\miina.png");
        this.miinoja = (koko * koko) / 5;
        this.lauta = lauta;
        this.koko = koko;
        ruudut = lauta.getRuudut();
        logiikka = new Logiikka();
        teksti = new JLabel("Lippuja " + lippujaKaytetty + "/" + miinoja, JLabel.RIGHT);
        asetaMenu();
        luoNappulat();
    }

    /**
     * Luodaan pelipohja ja nappulat.
     *
     */
    public void luoNappulat() {
        paneeli.setLayout(new GridLayout(koko, koko));
        nappulat = new JButton[koko][koko];

        for (int k = 0; k < koko; k++) {
            for (int i = 0; i < koko; i++) {
                JButton nappula = new JButton();
                nappula.addMouseListener(new Mouse());
                nappulat[k][i] = nappula;
                paneeli.add(nappulat[k][i]);
            }
        }
        add(paneeli);
    }

    /**
     * Asetetaan pelilaudan yläpuolelle JMenu valikko, joka sisältää
     * uusi peli ja sulje peli. Lisäksi luodaan tekstikenttä, jossa on käytettyjen
     * lippujen määrä.
     */
    public void asetaMenu() {
        valikko = new JMenu("Valikko");

        Action uusiPeli = new AbstractAction("Uusi peli") {

            @Override
            public void actionPerformed(ActionEvent e) {
                Window w = SwingUtilities.getWindowAncestor(paneeli);
                w.setVisible(false);

                Kayttoliittyma uusiPeli = new Kayttoliittyma();
                JFrame frame = new JFrame("Miinaharava");
                frame.add(uusiPeli.getGui());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationByPlatform(true);
                frame.pack();
                frame.setMinimumSize(new Dimension(200, 100));
                frame.setVisible(true);
            }

        };
        valikko.add(uusiPeli);

        Action sulje = new AbstractAction("Sulje peli") {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }

        };
        valikko.add(sulje);
        ylaPaneeli.add(valikko);

        ylaPaneeli.add(teksti);
        ylaPaneeli.add(menu);
        setJMenuBar(ylaPaneeli);
    }

    /**
     * Jos peli voitetaan, tulee siitä ilmoitus yläpaneeliin.
     *
     */
    public void voitaPeli() {
        avaaKokoLauta();
        ylaPaneeli.add(new JLabel("Voitto!", JLabel.RIGHT));
    }

    /**
     * Jos peli hävitään, tulee siitä ilmoitus yläpaneeliin. Lisäksi peli
     * aukaisee jäljelle jääneet ruudut.
     */
    public void haviaPeli() {
        avaaKokoLauta();
        ylaPaneeli.add(new JLabel("Häviö!", JLabel.RIGHT));

    }

    /**
     * Metodi avaa pelilaudan kaikki ruudut.
     */
    public void avaaKokoLauta() {
        for (int k = 0; k < koko; k++) {
            for (int j = 0; j < koko; j++) {
                merkataanRuutuAvatuksi(nappulat[k][j], k, j);
            }
        }
    }

    /**
     * Avataan ruutu hiiren vasemmalla näppäimellä. Tällä hetkellä '*' on miina,
     * ja lukuarvo on naapurissa olevien miinojen määrä.
     *
     * @param nappula JButton, jota on klikattu.
     * @param rivi nappulan rivi sijainti.
     * @param sarake nappulan sarake sijainti.
     */
    public void merkataanRuutuAvatuksi(JButton nappula, int rivi, int sarake) {
        ruudut[rivi][sarake].merkkaaRuutuAvatuksi();
        if (ruudut[rivi][sarake].isOnkoMiina()) {
            nappula.setIcon(miina);
        } else {
            String luku = Integer.toString(ruudut[rivi][sarake].getArvo());
            nappula.setIcon(null);
            nappula.setText(luku);
            nappula.setEnabled(false);
        }

    }

    /**
     * Hiiren oikealla näppäimellä asetetaan lippu.
     *
     * @param nappula JButton, jota on klikattu.
     * @param rivi nappulan rivi sijainti.
     * @param sarake nappulan sarake sijainti.
     */
    public void asetaLippu(JButton nappula, int rivi, int sarake) {
        teksti.setText("Lippuja " + lippujaKaytetty + "/" + miinoja);
        nappula.setIcon(lippu);
        ruudut[rivi][sarake].setLippu();
    }

    /**
     * Poistetaan lippu ruudusta hiiren oikealla näppäimellä.
     *
     * @param nappula JButton, jota on klikattu.
     * @param rivi nappulan rivi sijainti.
     * @param sarake nappulan sarake sijainti.
     */
    public void poistaLippu(JButton nappula, int rivi, int sarake) {
        teksti.setText("Lippuja " + lippujaKaytetty + "/" + miinoja);
        nappula.setIcon(null);
        ruudut[rivi][sarake].poistaLippu();
        nappula.setEnabled(true);
    }

    public class Mouse implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent tapahtuma) {
            logiikka.avataanRuutu(tapahtuma);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

    }

    public class Logiikka {

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
         * Tarkistetaan, onko peli hävitty. Peli hävitään, jos käyttäjä avaa
         * ruudun, jossa on miina.
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
         * klikattiin vasenta nappulaa, merkataan ruutu avatuksi ja
         * tarkistetaan, voitettiinko tai havittiinko peli. Jos klikattiin
         * oikeata nappulaa, niin laitetaan ruutuun lippu, mikali siina ei
         * ennestaan ollut lippua. Mikäli oli, niin lippu otetaan pois, jolloin
         * nappi on avaamaton.
         *
         * @param tapahtuma Hiiren klikkauksesta aiheutuva MouseEvent.
         */
        public void avataanRuutu(MouseEvent tapahtuma) {
            Component nappula = tapahtuma.getComponent();
            if (nappula.isEnabled() && tapahtuma.getButton() == 1) {
                for (int k = 0; k < koko; k++) {
                    for (int j = 0; j < koko; j++) {
                        if (nappula == nappulat[k][j]) {
                            if (ruudut[k][j].isOnkoMiina() && siirrot == 0 && !ruudut[k][j].isOnkoLippu()) {
                                lauta.siirraMiina(k, j);
                                merkataanRuutuAvatuksi(nappulat[k][j], k, j);
                                avaaNaapurit(nappulat[k][j], k, j, nappulat);
                                siirrot++;
                                break;
                            } else if (!ruudut[k][j].isOnkoLippu()) {
                                merkataanRuutuAvatuksi(nappulat[k][j], k, j);
                                if (ruudut[k][j].getArvo() == 0) {
                                    avaaNaapurit(nappulat[k][j], k, j, nappulat);
                                }
                                siirrot++;
                                tarkistaPelinLoppuminen();
                                break;
                            }

                        }
                    }
                }
            } else if (tapahtuma.getButton() == 3) {
                for (int k = 0; k < koko; k++) {
                    for (int j = 0; j < koko; j++) {
                        if (nappula == nappulat[k][j]) {
                            if (!ruudut[k][j].isOnkoLippu() && lippujaKaytetty != miinoja && !ruudut[k][j].isOnkoAvattu()) {
                                lippujaKaytetty++;
                                asetaLippu(nappulat[k][j], k, j);
                                break;
                            } else if (ruudut[k][j].isOnkoLippu()) {
                                lippujaKaytetty--;
                                poistaLippu(nappulat[k][j], k, j);
                                break;
                            }
                        }
                    }
                }

            }
        }

        /**
         * Metodi kutsuu voitaPeli, jos peli voitettiin. Jos peli hävittiin,
         * niin se kutsuu haviaPeli metodia.
         */
        public void tarkistaPelinLoppuminen() {
            if (voitto()) {
                voitaPeli();
            } else if (havio()) {
                haviaPeli();
            }
        }

        /**
         * Mikali klikatun ruudun yhdessakaan naapuriruudussa ei ole miinaa,
         * peli paljastaa ensin kaikki siihen yhteydessa olevat ruudut, joiden
         * naapurissa ei myoskaan ole miinaa, sekä lisaksi taman paljastetun
         * alueen reunalla olevat ruudut, joiden naapurissa on miina.
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
                            merkataanRuutuAvatuksi(nappulat[uusiRivi][uusiSarake], uusiRivi, uusiSarake);
                            avaaNaapurit(nappulat[uusiRivi][uusiSarake], uusiRivi, uusiSarake, nappulat);
                        } else if (ruudut[rivi][sarake].getArvo() == 0 && !ruudut[uusiRivi][uusiSarake].isOnkoAvattu() && ruudut[uusiRivi][uusiSarake].getArvo() != 0 && !ruudut[uusiRivi][uusiSarake].isOnkoMiina()) {
                            merkataanRuutuAvatuksi(nappulat[uusiRivi][uusiSarake], uusiRivi, uusiSarake);
                        }
                    }
                }
            }
        }

    }

}
