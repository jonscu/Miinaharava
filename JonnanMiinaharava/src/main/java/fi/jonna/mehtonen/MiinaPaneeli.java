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
    JPanel paneeli = new JPanel();
    private JButton[][] ruudukko;
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
        ruudukko = new JButton[koko][koko];

        for (int k = 0; k < koko; k++) {
            for (int i = 0; i < koko; i++) {
                JButton nappula = new JButton();
                nappula.addMouseListener(new Mouse());
                ruudukko[k][i] = nappula;
                paneeli.add(ruudukko[k][i]);
            }
        }
        add(paneeli);
    }

    // Avataan ruutu. Tällä hetkellä '*' on miina, ja lukuarvo on
    // naapurissa olevien miinojen määrä
    public void avataanRuutu(JButton nappula, int rivi, int sarake) {
        if (ruudut[rivi][sarake].isOnkoMiina()) {
            nappula.setText("*");
        } else {
            String luku = Integer.toString(ruudut[rivi][sarake].getArvo());
            nappula.setText(luku);
        }
    }

    // Hiiren oikealla näppäimellä asetetaan lippu
    public void asetaLippu(JButton nappula, int rivi, int sarake) {
        nappula.setText("lippu");
    }

    public class Mouse implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent tapahtuma) {
            if (tapahtuma.getButton() == 1) {
                for (int k = 0; k < koko; k++) {
                    for (int j = 0; j < koko; j++) {
                        if (tapahtuma.getComponent() == ruudukko[k][j]) {
                            avataanRuutu(ruudukko[k][j], k, j);
                            break;
                        }
                    }
                }
            } else if (tapahtuma.getButton() == 3) {
                for (int k = 0; k < koko; k++) {
                    for (int j = 0; j < koko; j++) {
                        if (tapahtuma.getComponent() == ruudukko[k][j]) {
                            asetaLippu(ruudukko[k][j], k, j);
                            break;
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
