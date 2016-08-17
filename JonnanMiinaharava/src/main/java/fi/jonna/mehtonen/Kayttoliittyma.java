
package fi.jonna.mehtonen;

import fi.jonna.mehtonen.domain.Pelilauta;
import fi.jonna.mehtonen.domain.Ruutu;
import java.util.Scanner;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Kayttoliittyma luokassa luodaan aloitusikkuna, jossa valitaan pelin
 * vaikeusaste. Tässä luokassa luodaan myös MiinaPaneeli.
 */
public class Kayttoliittyma {

    private Pelilauta lauta;
    private Boolean voitto;
    private Boolean pelinLoppuminen;
    private Scanner scan;
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));

    public Kayttoliittyma() {
        this.voitto = false;
        this.pelinLoppuminen = false;
        this.scan = new Scanner(System.in);

        /**
         * Luodaan alotusikkuna ja siihen nappulat eri vaikeusasteille.
         *
         */
        gui.setBorder(new EmptyBorder(10, 10, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);

        /**
         * Testi pelin nappula.
         *
         */
        Action newGameAction4 = new AbstractAction("Tosi helppo") {

            @Override
            public void actionPerformed(ActionEvent e) {
                Window w = SwingUtilities.getWindowAncestor(gui);
                w.setVisible(false);
                pelaa(3);
            }

        };

        tools.add(newGameAction4);

        /**
         * Helpon pelin nappula.
         *
         */
        Action newGameAction = new AbstractAction("Helppo") {

            @Override
            public void actionPerformed(ActionEvent e) {
                Window w = SwingUtilities.getWindowAncestor(gui);
                w.setVisible(false);
                pelaa(10);
            }

        };

        tools.add(newGameAction);

        /**
         * Keskivaikean pelin nappula.
         *
         */
        Action newGameAction2 = new AbstractAction("Keskivaikea") {

            @Override
            public void actionPerformed(ActionEvent e) {
                Window w = SwingUtilities.getWindowAncestor(gui);
                w.setVisible(false);
                pelaa(15);
            }

        };

        tools.add(newGameAction2);

        /**
         * Vaikean pelin nappula.
         *
         */
        Action newGameAction3 = new AbstractAction("Vaikea") {

            @Override
            public void actionPerformed(ActionEvent e) {
                Window w = SwingUtilities.getWindowAncestor(gui);
                w.setVisible(false);
                pelaa(18);
            }

        };

        tools.add(newGameAction3);
    }

    /**
     * Luodaan pelilauta ja sen grafiikat.
     *
     * @param koko Pelattavan miinaharavan koko.
     */
    public void pelaa(int koko) {
        lauta = new Pelilauta(koko);

        MiinaPaneeli miinaharava = new MiinaPaneeli(koko, lauta);
        miinaharava.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        miinaharava.pack();
        if (koko == 10) {
            miinaharava.setMinimumSize(new Dimension(600, 600));
        } else if (koko == 15) {
            miinaharava.setMinimumSize(new Dimension(900, 900));
        } else if (koko == 18) {
            miinaharava.setMinimumSize(new Dimension(1080, 1080));
        } else if (koko == 3) {
            miinaharava.setMinimumSize(new Dimension(200, 200));
        }

        miinaharava.setVisible(true);
    }

    public JPanel getGui() {
        return gui;
    }

}
