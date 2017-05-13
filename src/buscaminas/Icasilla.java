/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package buscaminas;

import javax.swing.JButton;

/**
 *
 * @author VaKs
 */
public abstract class Icasilla extends JButton {
    public abstract Casilla setValor(int valor);
    public abstract boolean isRevelado();
}
