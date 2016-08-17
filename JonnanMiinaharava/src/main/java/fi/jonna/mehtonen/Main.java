/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jonna.mehtonen;

import fi.jonna.mehtonen.domain.Ruutu;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author jonscu
 */
public class Main {

    public static void main(String[] args) {
        Kayttoliittyma liittyma = new Kayttoliittyma();

        JFrame frame = new JFrame("Miinaharava");
        frame.add(liittyma.getGui());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setMinimumSize(new Dimension(200, 100));
        frame.setVisible(true);
    }
}
