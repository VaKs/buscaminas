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
public abstract class Casilla extends Icasilla{

    private int fila;
    private int columna;
    private ImageIcon[] iconos;
    private int valor;
    private boolean bandera;
    private boolean revelado;

    public Casilla(int fila, int columna, int valor) {
        iconos = new ImageIcon[10];
        this.revelado= false;
        this.valor = valor;
        this.bandera = false;
        String name;

        for (int i = 1; i <= 8; i++) {
            name = "./src/img/" + i + ".gif";
            iconos[i] = new ImageIcon(name);
        }
        iconos[9] = new ImageIcon("./src/img/mine.gif");
        iconos[10] = new ImageIcon("./src/img/flag.gif");

    }

    public ImageIcon getIcono(int ic) {
        return iconos[ic];
    }

    public int getValor() {
        return valor;
    }

    @Override
    public Casilla setValor(int valor) {
        this.valor = valor;
        return this;
    }

    public boolean tieneBandera() {
        return bandera;
    }

    public void setBandera(boolean bandera) {
        this.bandera = bandera;
        if (bandera) this.setIcon(this.iconos[10]);
        else this.setIcon(null);
    }

    public abstract void revelar();

    /**
     * @return the revelado
     */
    public boolean isRevelado() {
        return revelado;
    }

    /**
     * @param revelado the revelado to set
     */
    public void setRevelado(boolean revelado) {
        this.revelado = revelado;
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

}
