/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jonna.mehtonen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import fi.jonna.mehtonen.domain.*;

/**
 *
 * @author jonscu
 */
public class MiinaPaneeli extends JFrame {

    private int koko;
    private int miinoja;
    private JPanel paneeli = new JPanel();
    private JButton[][] nappulat;
    private Pelilauta lauta;
    private Ruutu[][] ruudut;

    public MiinaPaneeli(int koko, Pelilauta lauta) {
        super("Miinaharava");
        setSize(1000, 1000);
        setResizable(true);
        this.koko = koko;
        this.miinoja = (koko * koko) / 5;
        this.lauta = lauta;
        ruudut = lauta.getRuudut();

        //Luodaan pelipohja ja nappulat
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

    // Avataan ruutu hiiren vasemmalla näppäimellä. 
    // Tällä hetkellä '*' on miina, ja lukuarvo on
    // naapurissa olevien miinojen määrä.
    public void avataanRuutu(JButton nappula, int rivi, int sarake) {
        ruudut[rivi][sarake].merkkaaRuutuAvatuksi();
        if (ruudut[rivi][sarake].isOnkoMiina()) {
            nappula.setText("*");
        } else {
            String luku = Integer.toString(ruudut[rivi][sarake].getArvo());
            nappula.setText(luku);
        }
        nappula.setEnabled(false);
    }

    // Hiiren oikealla näppäimellä asetetaan lippu
    public void asetaLippu(JButton nappula, int rivi, int sarake) {
        nappula.setText("lippu");
        ruudut[rivi][sarake].asetaLippu();
        nappula.setEnabled(false);
    }

    private void poistaLippu(JButton nappula, int rivi, int sarake) {
        nappula.setText("");
        ruudut[rivi][sarake].poistaLippu();
        nappula.setEnabled(true);
    }

    // Tarkistetaan, onko peli voitettu.
    public Boolean voitto() {
        int miinojaMerkattu = 0;

        for (int k = 0; k < koko; k++) {
            for (int j = 0; j < koko; j++) {
                if (ruudut[k][j].isOnkoLippu() && ruudut[k][j].isOnkoMiina()) {
                    miinojaMerkattu++;
                }
            }
        }
        if (miinojaMerkattu == miinoja) {
            return true;
        } else {
            return false;
        }
    }

    public class Mouse implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent tapahtuma) {
            Component nappula = tapahtuma.getComponent();
            if (nappula.isEnabled() && tapahtuma.getButton() == 1) {
                for (int k = 0; k < koko; k++) {
                    for (int j = 0; j < koko; j++) {
                        if (nappula == nappulat[k][j]) {
                            avataanRuutu(nappulat[k][j], k, j);
                            break;
                        }
                    }
                }
            } else if (tapahtuma.getButton() == 3) {
                for (int k = 0; k < koko; k++) {
                    for (int j = 0; j < koko; j++) {
                        if (nappula == nappulat[k][j]) {
                            if (nappula.isEnabled()) {
                                asetaLippu(nappulat[k][j], k, j);
                                break;
                            } else if (!nappula.isEnabled() && ruudut[k][j].isOnkoLippu()) {
                                poistaLippu(nappulat[k][j], k, j);
                                break;
                            }
                        }
                    }
                }

            }

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
