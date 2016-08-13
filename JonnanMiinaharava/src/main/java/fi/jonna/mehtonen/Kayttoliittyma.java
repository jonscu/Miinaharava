/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author jonscu
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

        gui.setBorder(new EmptyBorder(10, 10, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);

        // Helpon pelin nappula
        Action newGameAction = new AbstractAction("Helppo") {

            @Override
            public void actionPerformed(ActionEvent e) {
                Window w = SwingUtilities.getWindowAncestor(gui);
                w.setVisible(false);
                pelaa(10);
            }

        };

        tools.add(newGameAction);
        //tools.addSeparator();

        // Keskivaikean pelin nappula
        Action newGameAction2 = new AbstractAction("Keskivaikea") {

            @Override
            public void actionPerformed(ActionEvent e) {
                Window w = SwingUtilities.getWindowAncestor(gui);
                w.setVisible(false);
                pelaa(15);
            }

        };

        tools.add(newGameAction2);

        // Vaikean pelin nappula
        Action newGameAction3 = new AbstractAction("Vaikea") {

            @Override
            public void actionPerformed(ActionEvent e) {
                Window w = SwingUtilities.getWindowAncestor(gui);
                w.setVisible(false);
                pelaa(20);
            }

        };

        tools.add(newGameAction3);
    }

    public void pelaa(int koko) {
        /**
         * boolean totta = false; int laudanKoko = 0; System.out.println("Anna
         * laudan koko YxY. Oltava vähintään Y=3:"); while (!totta) { laudanKoko
         * = scan.nextInt(); if (laudanKoko < 3) { System.out.println("Laudan
         * oltava isompi! Anna uusi koko: "); totta = false; } else { totta =
         * true; } }*
         */
        lauta = new Pelilauta(koko);

        MiinaPaneeli miinaharava = new MiinaPaneeli(koko, lauta);
        miinaharava.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        miinaharava.pack();
        miinaharava.setMinimumSize(new Dimension(600, 600));
        miinaharava.setVisible(true);
    }

    public JPanel getGui() {
        return gui;
    }

}
