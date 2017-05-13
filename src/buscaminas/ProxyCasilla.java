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
public class ProxyCasilla implements Sujeto{
    
    int fila;
    int columna;

    @Override
    public Casilla setValor(int valor) {
        
        return FabricaCasilla.getCasilla(fila, columna, valor);
        
    }
    
}
