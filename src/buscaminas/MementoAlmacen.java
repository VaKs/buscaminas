/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import java.util.Stack;

public class MementoAlmacen {

    private final Stack estadosAnteriores = new Stack();

    public void addMemento(int indice, boolean bandera, boolean revelado) {
        Memento memento= new Memento(indice, bandera, revelado);
        estadosAnteriores.add(memento);
    }

    public Memento getUltimoMemento() {
        return (Memento) estadosAnteriores.pop();
    }
}
