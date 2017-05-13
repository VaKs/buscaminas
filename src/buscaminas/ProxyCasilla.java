/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

/**
 *
 * @author VaKs
 */
public class ProxyCasilla extends Icasilla {


    public ProxyCasilla(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.bandera= false;
    }

    @Override
    public Casilla setValor(int valor) {
        return FabricaCasilla.getCasilla(this.getFila(), this.getColumna(), valor);
    }

    @Override
    public boolean isRevelado() {
        return false;
    }

    @Override
    public boolean tieneBandera() {
        return bandera;
    }

    @Override
    public void revelar() {
    }

    @Override
    public int getFila() {
        return fila;
    }
    
    @Override
    public int getColumna() {
        return columna;
    }

}
