/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

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
        int indiceClicado = 0;
        for (int i = 0; i < buscaminas.cantidadCasillas; i++) {
            if (me.getSource() == buscaminas.casillas[i]) {
                indiceClicado = i;
                break;
            }
        }
        buscaminas.heClickado(clickDerecho, indiceClicado);
            /*if (buscaminas.casillasIniciadas == false) {

                buscaminas.ponerMinas(indiceClicado);
                buscaminas.obtenerValorCasillas();
                buscaminas.casillasIniciadas = true;
                buscaminas.fmReloj.iniciarReloj();
                
            }
            if(clickDerecho) buscaminas.ponerQuitarBandera(indiceClicado);
            else buscaminas.revelarCasilla(indiceClicado);
            
            
            
            buscaminas.compruebaGanador();*/

        }
    
    }
