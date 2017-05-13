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
public class ProxyCasilla extends Casilla {

    
    Casilla casilla=null;

    public ProxyCasilla(int fila, int columna) {
        super(fila, columna);
        this.bandera= false;
        
    }

    @Override
    public void setValor(int valor) {
        if(casilla!=null) casilla.setValor(valor);
        this.casilla=FabricaCasilla.getCasilla(this.fila, this.columna, valor);
    }

    @Override
    public boolean isRevelado() {
        if(casilla!=null) return casilla.isRevelado();
        return false;
    }

    @Override
    public boolean tieneBandera() {
        if(casilla!=null) return casilla.tieneBandera();
        return bandera;
    }

    @Override
    public void revelar() {
        if(casilla!=null) casilla.revelar();
    }

    @Override
    public int getFila() {
        if(casilla!=null) return casilla.getFila();
        return this.fila;
    }
    
    @Override
    public int getColumna() {
        if(casilla!=null) return casilla.getColumna();
        return this.columna;
    }
    @Override
    public boolean esMina(){
        return casilla!=null && casilla.getValor()==-1;
    }

    @Override
    public boolean esVacia(){
        return casilla!=null && casilla.getValor()==0;
    }

}
