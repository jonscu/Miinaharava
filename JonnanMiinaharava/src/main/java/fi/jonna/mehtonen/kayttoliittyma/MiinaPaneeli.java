package fi.jonna.mehtonen.kayttoliittyma;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import fi.jonna.mehtonen.domain.*;
import fi.jonna.mehtonen.pelilogiikka.Logiikka;

/**
 * MiinaPaneeli luokassa rakennetaan pelin grafiikkaruudukko.
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
    private JTextArea lippuja;
    private ImageIcon lippu, miina;
    private JLabel teksti;

    public MiinaPaneeli(int koko, Pelilauta lauta) {
        super("Miinaharava");
        setResizable(false);
        ClassLoader cl = this.getClass().getClassLoader();
        this.lippu = new ImageIcon(cl.getResource("images/lippu2.png"));
        this.miina = new ImageIcon(cl.getResource("images/miina.png"));
        this.miinoja = (koko * koko) / 5;
        this.lauta = lauta;
        this.koko = koko;
        ruudut = lauta.getRuudut();
        teksti = new JLabel("Lippuja 0/" + miinoja, JLabel.RIGHT);
        asetaMenu();
        luoNappulat();
        logiikka = new Logiikka(koko, lauta, miinoja);

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
     * Asetetaan pelilaudan yläpuolelle JMenu valikko, joka sisältää uusi
     * peli ja sulje peli. Lisäksi luodaan tekstikenttä, jossa on käytettyjen
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
                frame.add(uusiPeli.getAlkuvalikko());
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
                merkataanRuutuAvatuksi(k, j);
            }
        }
    }

    /**
     * Avataan ruutu hiiren vasemmalla näppäimellä. Miinaa esittää kuva
     * pommista, ja lukuarvo on naapurissa olevien miinojen määrä.
     *
     * @param nappula JButton, jota on klikattu.
     * @param rivi nappulan rivi sijainti.
     * @param sarake nappulan sarake sijainti.
     */
    public void merkataanRuutuAvatuksi(int rivi, int sarake) {
        if (ruudut[rivi][sarake].isOnkoMiina()) {
            nappulat[rivi][sarake].setIcon(miina);
        } else {
            String luku = Integer.toString(ruudut[rivi][sarake].getArvo());
            nappulat[rivi][sarake].setIcon(null);
            nappulat[rivi][sarake].setText(luku);
            nappulat[rivi][sarake].setEnabled(false);
        }

    }

    /**
     * Hiiren oikealla näppäimellä asetetaan lippu.
     *
     * @param nappula JButton, jota on klikattu.
     * @param rivi nappulan rivi sijainti.
     * @param sarake nappulan sarake sijainti.
     */
    public void asetaLippu(int rivi, int sarake) {
        int lippujaKaytetty = logiikka.getLippujaKaytetty();
        teksti.setText("Lippuja " + lippujaKaytetty + "/" + miinoja);
        nappulat[rivi][sarake].setIcon(lippu);
    }

    /**
     * Poistetaan lippu ruudusta hiiren oikealla näppäimellä.
     *
     * @param nappula JButton, jota on klikattu.
     * @param rivi nappulan rivi sijainti.
     * @param sarake nappulan sarake sijainti.
     */
    public void poistaLippu(int rivi, int sarake) {
        int lippujaKaytetty = logiikka.getLippujaKaytetty();
        teksti.setText("Lippuja " + lippujaKaytetty + "/" + miinoja);
        nappulat[rivi][sarake].setIcon(null);
        nappulat[rivi][sarake].setEnabled(true);
    }

    /**
     * Metodi kutsuu voitaPeli, jos peli voitettiin. Jos peli hävittiin, niin se
     * kutsuu haviaPeli metodia.
     */
    public void tarkistaPelinLoppuminen() {
        if (logiikka.voitto()) {
            voitaPeli();
        } else if (logiikka.havio()) {
            haviaPeli();
        }
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
    public void nappiaPainettu(MouseEvent tapahtuma) {
        Component nappula = tapahtuma.getComponent();
        int[] koordinaatit = etsiNappulanSijainti(nappula);
        if (nappula.isEnabled() && tapahtuma.getButton() == 1) {
            logiikka.avataanRuutuTaiSiirretaanMiina(koordinaatit[0], koordinaatit[1]);
        } else if (tapahtuma.getButton() == 3) {
            logiikka.laitetaanTaiPoistetaanLippu(koordinaatit[0], koordinaatit[1]);
        }
        paivitaJButtonit();
    }

    /**
     * Etsitään painetun nappulan sijainnin koordinaatit.
     *
     * @param nappula Painettu nappula.
     * @return palauttaa nappulan sijainnin koordinaatit.
     */
    public int[] etsiNappulanSijainti(Component nappula) {
        int[] koordinaatit = new int[2];
        for (int k = 0; k < koko; k++) {
            for (int j = 0; j < koko; j++) {
                if (nappula == nappulat[k][j]) {
                    koordinaatit[0] = k;
                    koordinaatit[1] = j;
                    break;
                }
            }
        }
        return koordinaatit;
    }

    /**
     * Piirretään JButton ruudukko uudelleen.
     */
    public void paivitaJButtonit() {
        for (int k = 0; k < koko; k++) {
            for (int j = 0; j < koko; j++) {
                if (ruudut[k][j].isOnkoAvattu()) {
                    merkataanRuutuAvatuksi(k, j);
                } else if (ruudut[k][j].isOnkoLippu()) {
                    asetaLippu(k, j);
                } else {
                    poistaLippu(k, j);
                }
            }
        }
        tarkistaPelinLoppuminen();
    }

    public JButton[][] getNappulat() {
        return nappulat;
    }

    public Pelilauta getLauta() {
        return lauta;

    }

    public class Mouse implements MouseListener {

        public Mouse() {

        }

        @Override
        public void mouseClicked(MouseEvent tapahtuma) {
            nappiaPainettu(tapahtuma);
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

}
