package Presentacion;

import java.awt.Color;

public class ProxyCasilla extends Casilla {

    Casilla casilla = null;
    FabricaCasilla fabricaCasilla=FabricaCasilla.getFabrica();

    public ProxyCasilla(int fila, int columna) {
        super(fila, columna);
        this.bandera = false;

    }

    @Override
    public void setValor(int valor) {
        if (casilla != null) {
            casilla.setValor(valor);
        }
        this.casilla = fabricaCasilla.crearCasilla(this.fila, this.columna, valor);
    }

    @Override
    public boolean isRevelado() {
        if (casilla != null) {
            return casilla.isRevelado();
        }
        return false;
    }

    @Override
    public void revelar() {
        if (casilla != null) {
            this.setBackground(Color.GRAY);
            if (this.esMina()) {
                this.setIcon(casilla.getIcono(9));
            } else if (!this.esVacia()) {
                this.setIcon(casilla.getIcono(casilla.getValor()));
            }
            casilla.revelar();
        }
    }

    @Override
    public int getFila() {
        if (casilla != null) {
            return casilla.getFila();
        }
        return this.fila;
    }
       @Override
    public int getValor() {
        if (casilla != null) {
            return casilla.getValor();
        }
        return -2;
    }

    @Override
    public int getColumna() {
        if (casilla != null) {
            return casilla.getColumna();
        }
        return this.columna;
    }

    @Override
    public boolean esMina() {
        return casilla != null && casilla.getValor() == -1;
    }

    @Override
    public boolean esVacia() {
        return casilla != null && casilla.getValor() == 0;
    }

    @Override
    public void setRevelado(boolean revelado) {
        if (revelado) {
            this.revelar();
        } else {
            casilla.setRevelado(false);
            this.setIcon(null);
            this.setBackground(null);
        }
    }

}
