/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author VaKs
 */
public abstract class Casilla extends JButton {

    private int fila;
    private int columna;
    private ImageIcon[] icono;
    private int valor;
    private boolean bandera;

    public Casilla(int fila, int columna, int valor) {
        icono = new ImageIcon[10];
        this.valor = valor;
        this.fila = fila;
        this.columna = columna;
        this.bandera = false;
        String name;

        for (int i = 0; i <= 8; i++) {
            name = "./src/img/" + i + ".gif";
            icono[i] = new ImageIcon(name);
        }
        icono[9] = new ImageIcon("./src/img/mine.gif");
        icono[10] = new ImageIcon("./src/img/flag.gif");
        icono[11] = new ImageIcon("./src/img/new game.gif");
        icono[12] = new ImageIcon("./src/img/crape.gif");

    }

    /**
     * @return the fila
     */
    public int getFila() {
        return fila;
    }

    /**
     * @param fila the fila to set
     */
    public void setFila(int fila) {
        this.fila = fila;
    }

    /**
     * @return the columna
     */
    public int getColumna() {
        return columna;
    }

    /**
     * @param columna the columna to set
     */
    public void setColumna(int columna) {
        this.columna = columna;
    }

    /**
     * @param ic
     * @return the icono
     */
    public ImageIcon getIcono(int ic) {
        return icono[ic];
    }

    /**
     * @return the valor
     */
    public int getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(int valor) {
        this.valor = valor;
    }

    /**
     * @return the bandera
     */
    public boolean isBandera() {
        return bandera;
    }

    /**
     * @param bandera the bandera to set
     */
    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }

    public abstract void revelar();

}
