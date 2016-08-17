package fi.jonna.mehtonen;

import fi.jonna.mehtonen.domain.Ruutu;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * Main -luokassa aloitetaan peli luomalla käyttöliittymä, ja luomalla
 * aloitusikkunan frame.
 */
public class Main {

    /**
     * Luodaan käyttöliittymä ja aloitusikkunan frame.
     * @param args on joku main metodin juttu.
     */
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
