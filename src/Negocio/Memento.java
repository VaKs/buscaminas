
package Negocio;

public class Memento {
    private int fila;
    private int columna;
    private boolean bandera;
    private boolean revelado;
 
    public Memento()
    {
        
    }
    public Memento(int fila,int columna, boolean bandera, boolean revelado)
    {
        this.fila = fila;
        this.columna = columna;
        this.bandera=bandera;
        this.revelado=revelado;
        
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

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

}
