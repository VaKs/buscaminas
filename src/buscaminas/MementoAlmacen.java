/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import java.util.Stack;

public class MementoAlmacen {

    private final Stack<Memento> estadosAnteriores;
    private static MementoAlmacen mementoAlmacen;
    
    private MementoAlmacen(){
        estadosAnteriores = new Stack();
        Memento memento=new Memento();
        estadosAnteriores.add(memento);
    }
    public static MementoAlmacen getAlmacen(){
        
    if(mementoAlmacen==null) mementoAlmacen=new MementoAlmacen();
    
    return mementoAlmacen;
        
    }

    public void addMemento(int fila, int columna, boolean bandera, boolean revelado) {
        Memento memento= new Memento(fila, columna, bandera, revelado);
        estadosAnteriores.add(memento);
    }

    public Memento getUltimoMemento() {
        return (Memento) estadosAnteriores.pop();
    }
    public int getFilaUltimoMemento(){
        return estadosAnteriores.peek().getFila();
    }
    public int getColumnaUltimoMemento(){
        return estadosAnteriores.peek().getColumna();
    }
}
