/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package buscaminas;

import java.awt.Color;

/**
 *
 * @author VaKs
 */
public class CasillaMina extends Casilla{
    public CasillaMina(int fila, int columna, int valor){
        super(fila,columna,valor);
    }

    @Override
    public void revelar() {
        this.setRevelado(true);
        this.setBandera(false);
        this.setBackground(Color.GRAY);
        this.setIcon(this.getIcono(9));
        
    }
}
