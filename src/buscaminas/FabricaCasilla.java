/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

public class FabricaCasilla{ 

    public static Casilla crearCasilla(int fila, int columna, int valor) {

        if (valor == -1) {
            return new CasillaMina(fila,columna,valor);
        } else if (valor == 0) {
            return new CasillaVacia(fila,columna,valor);
        } else {
            return new CasillaNumero(fila,columna,valor);
        }
    }
    public static Casilla crearCasilla(int fila, int columna){
        return new ProxyCasilla(fila,columna);
    
    }
}
