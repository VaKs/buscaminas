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
public abstract class Casilla extends JButton  implements Icasilla {

    private int fila;
    private int columna;
    private ImageIcon[] iconos;
    private int valor;
    private boolean bandera;

    public Casilla(int fila, int columna, int valor) {
        iconos = new ImageIcon[10];
        this.valor = valor;
        this.fila = fila;
        this.columna = columna;
        this.bandera = false;
        String name;

        for (int i = 0; i <= 8; i++) {
            name = "./src/img/" + i + ".gif";
            iconos[i] = new ImageIcon(name);
        }
        iconos[9] = new ImageIcon("./src/img/mine.gif");
        iconos[10] = new ImageIcon("./src/img/flag.gif");
        iconos[11] = new ImageIcon("./src/img/new game.gif");
        iconos[12] = new ImageIcon("./src/img/crape.gif");

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

}
