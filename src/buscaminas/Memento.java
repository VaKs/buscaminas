/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

public class Memento {
    private int indiceCasilla;
    private boolean bandera;
    private boolean revelado;
 
    public Memento()
    {
        
    }
    public Memento(int fila,int columna, boolean bandera, boolean revelado)
    {
        this.indiceCasilla=(fila*10)+columna;
        this.bandera=bandera;
        this.revelado=revelado;
        
    }

    public int getIndiceCasilla() {
        return indiceCasilla;
    }

    public void setIndiceCasilla(int indiceCasilla) {
        this.indiceCasilla = indiceCasilla;
    }

    public boolean getBandera() {
        return bandera;
    }

    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }

    public boolean getRevelado() {
        return revelado;
    }

    public void setRevelado(boolean revelado) {
        this.revelado = revelado;
    }

}
