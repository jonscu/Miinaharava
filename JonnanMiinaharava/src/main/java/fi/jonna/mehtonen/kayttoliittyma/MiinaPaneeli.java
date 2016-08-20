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
        this.lippu = new ImageIcon("C:\\Users\\jonscu\\Documents\\GitHub\\Miinaharava\\JonnanMiinaharava\\src\\main\\java\\fi\\jonna\\mehtonen\\lippu2.png");
        this.miina = new ImageIcon("C:\\Users\\jonscu\\Documents\\GitHub\\Miinaharava\\JonnanMiinaharava\\src\\main\\java\\fi\\jonna\\mehtonen\\miina.png");
        this.miinoja = (koko * koko) / 5;
        this.lauta = lauta;
        this.koko = koko;
        ruudut = lauta.getRuudut();
        teksti = new JLabel("Lippuja 0/" + miinoja, JLabel.RIGHT);
        asetaMenu();
        luoNappulat();
        logiikka = new Logiikka(koko, lauta, miinoja, nappulat, this);

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
     * Asetetaan pelilaudan yläpuolelle JMenu valikko, joka sisältää uusi peli
     * ja sulje peli. Lisäksi luodaan tekstikenttä, jossa on käytettyjen
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
        int lippujaKaytetty = logiikka.getLippujaKaytetty();
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
        int lippujaKaytetty = logiikka.getLippujaKaytetty();
        teksti.setText("Lippuja " + lippujaKaytetty + "/" + miinoja);
        nappula.setIcon(null);
        ruudut[rivi][sarake].poistaLippu();
        nappula.setEnabled(true);
    }

    public JButton[][] getNappulat() {
        return nappulat;
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

}
