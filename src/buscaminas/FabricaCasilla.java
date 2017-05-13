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
public class FabricaCasilla {

    public static Casilla getCasilla(int fila, int columna, int valor) {

        if (valor == -1) {
            return new CasillaMina(fila, columna, valor);
        } else if (valor == 0) {
            return new CasillaVacia(fila, columna, valor);
        } else {
            return new CasillaNumero(fila, columna, valor);
        }

    }
}
