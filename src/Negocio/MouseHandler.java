/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Presentacion.Casilla;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {

    Buscaminas buscaminas;

    public MouseHandler(Buscaminas buscamina) {
        buscaminas = buscamina;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        boolean clickDerecho=me.isMetaDown();
        
        Casilla casillaClicada=(Casilla) me.getSource();
        int columna = casillaClicada.getColumna();
        int fila = casillaClicada.getFila();
        int indiceClicado = buscaminas.buscarIndiceCasilla(fila, columna);
            
        buscaminas.heClickado(clickDerecho, indiceClicado);

        }
    
    }
