/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.jonnanmiinaharava;

/**
 *
 * @author jonscu
 */
public class Sijainti {

    private int x;
    private int y;

    public Sijainti(int x1, int y1) {
        this.x = x1;
        this.y = y1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return x + ", " + y;
    }

}
