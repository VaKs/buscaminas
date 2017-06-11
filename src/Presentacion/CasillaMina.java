

package Presentacion;

import java.awt.Color;

public class CasillaMina extends Casilla{
    public CasillaMina(int fila, int columna, int valor){
        super(fila,columna);
        this.setValor(valor);
    }

    @Override
    public void revelar() {
        this.setRevelado(true);
        this.setBandera(false);    
    }
}
