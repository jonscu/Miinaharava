/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jonna.mehtonen;

import fi.jonna.mehtonen.domain.Pelilauta;
import fi.jonna.mehtonen.domain.Ruutu;
import java.util.Scanner;

/**
 *
 * @author jonscu
 */
public class Kayttoliittyma {

    private Pelilauta lauta;
    private Boolean voitto;
    private Boolean pelinLoppuminen;
    private Scanner scan;

    public Kayttoliittyma() {
        this.voitto = false;
        this.pelinLoppuminen = false;
        this.scan = new Scanner(System.in);
        pelaa();
    }

    public void pelaa() {
        boolean totta = false;
        int laudanKoko = 0;
        System.out.println("Anna laudan koko YxY. Oltava vähintään Y=3:");
        while (!totta) {
            laudanKoko = scan.nextInt();
            if (laudanKoko < 3) {
                System.out.println("Laudan oltava isompi! Anna uusi koko: ");
                totta = false;
            } else {
                totta = true;
            }
        }
        lauta = new Pelilauta(laudanKoko);

        lauta.tulostaPelilauta();
        System.out.println(lauta.miinojaYhteensa());
    }

    public void pelaaVuoro() {
        System.out.println("Anna avattavan ruudun rivi sijainti:");
        int sijaintiRivi = scan.nextInt();
        System.out.println("Anna avattavan ruudun sarake sijainti:");
        int sijaintiSarake = scan.nextInt();

        avaaRuutu(sijaintiRivi, sijaintiSarake);
    }

    public void avaaRuutu(int rivi, int sarake) {
        Ruutu[][] ruudut = lauta.getRuudut();
    }

}
