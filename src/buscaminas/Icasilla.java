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
    
    public int fila;
    public int columna;
    public boolean bandera;
    
    public abstract Casilla setValor(int valor);
    public abstract boolean isRevelado();
    public abstract boolean tieneBandera();
    public abstract void revelar();
    public abstract int getFila();
    public abstract int getColumna();
}
