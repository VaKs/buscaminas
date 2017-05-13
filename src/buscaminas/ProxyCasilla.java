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
public class ProxyCasilla extends Icasilla {

    int fila;
    int columna;

    public ProxyCasilla(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    @Override
    public Casilla setValor(int valor) {
        return FabricaCasilla.getCasilla(fila, columna, valor);
    }

    @Override
    public boolean isRevelado() {
        return false;
    }

}
