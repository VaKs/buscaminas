/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author vaks
 */
public class MementoAlmacen {

    private Stack estadosAnteriores = new Stack();

    public void addMemento(Memento memento) {
        estadosAnteriores.add(memento);
    }

    public Memento getUltimoMemento() {
        return (Memento) estadosAnteriores.pop();
    }
}
