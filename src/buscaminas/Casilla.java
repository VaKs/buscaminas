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

    int fila;
    int columna;
    private final ImageIcon[] iconos;
    private Integer valor;
    boolean bandera;
    private boolean revelado;

    public Casilla(int fila, int columna) {
        this.fila=fila;
        this.columna=columna;
        
        this.iconos = new ImageIcon[10];
        this.revelado = false;
        this.bandera = false;
        String name;

        for (int i = 1; i <= 8; i++) {
            name = "./src/img/" + i + ".gif";
            iconos[i] = new ImageIcon(name);
        }
        iconos[9] = new ImageIcon("./src/img/mine.gif");

    }

    public ImageIcon getIcono(int ic) {
        return iconos[ic];
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public boolean tieneBandera() {
        return bandera;
    }

    public void setBandera(boolean bandera) {
        this.bandera = bandera;
        if (bandera) {
            this.setIcon(this.iconos[10]);
        } else {
            this.setIcon(null);
        }
            
    }

    public boolean isRevelado() {
        return revelado;
    }

    public abstract void revelar();

    public void setRevelado(boolean revelado) {
        this.revelado = revelado;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public boolean esMina() {
        if(valor==null) return false;
        return valor == -1;
    }
    public boolean esVacia() {
        if(valor==null) return false;
        return valor == 0;
    }

}
