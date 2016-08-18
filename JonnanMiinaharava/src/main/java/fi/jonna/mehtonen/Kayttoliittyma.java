
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
    private Scanner scan;
    private final JPanel alkuvalikko = new JPanel(new BorderLayout(3, 3));

    public Kayttoliittyma() {
        this.scan = new Scanner(System.in);

        /**
         * Luodaan alotusikkuna ja siihen nappulat eri vaikeusasteille.
         *
         */
        alkuvalikko.setBorder(new EmptyBorder(10, 10, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        alkuvalikko.add(tools, BorderLayout.PAGE_START);

        /**
         * Testi pelin nappula.
         *
         */
        Action tosiHelppo = new AbstractAction("Tosi helppo") {

            @Override
            public void actionPerformed(ActionEvent e) {
                Window w = SwingUtilities.getWindowAncestor(alkuvalikko);
                w.setVisible(false);
                pelaa(3);
            }

        };

        tools.add(tosiHelppo);

        /**
         * Helpon pelin nappula.
         *
         */
        Action helppo = new AbstractAction("Helppo") {

            @Override
            public void actionPerformed(ActionEvent e) {
                Window w = SwingUtilities.getWindowAncestor(alkuvalikko);
                w.setVisible(false);
                pelaa(10);
            }

        };

        tools.add(helppo);

        /**
         * Keskivaikean pelin nappula.
         *
         */
        Action keskivaikea = new AbstractAction("Keskivaikea") {

            @Override
            public void actionPerformed(ActionEvent e) {
                Window w = SwingUtilities.getWindowAncestor(alkuvalikko);
                w.setVisible(false);
                pelaa(15);
            }

        };

        tools.add(keskivaikea);

        /**
         * Vaikean pelin nappula.
         *
         */
        Action vaikea = new AbstractAction("Vaikea") {

            @Override
            public void actionPerformed(ActionEvent e) {
                Window w = SwingUtilities.getWindowAncestor(alkuvalikko);
                w.setVisible(false);
                pelaa(18);
            }

        };

        tools.add(vaikea);
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

    public JPanel getAlkuvalikko() {
        return alkuvalikko;
    }

}
