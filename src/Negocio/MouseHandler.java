
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
            
        buscaminas.primerClick(clickDerecho, indiceClicado);

        }
    
    }
