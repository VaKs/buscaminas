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
        boolean isMetaDown=me.isMetaDown();
        int indiceClicado = 0;
        for (int i = 0; i < buscaminas.cantidadCasillas; i++) {
            if (me.getSource() == buscaminas.casillas[i]) {
                indiceClicado = i;
                break;
            }
        }
            if (buscaminas.casillasIniciadas == false) {

                buscaminas.setmine(indiceClicado);
                buscaminas.calculation();
                buscaminas.casillasIniciadas = true;
            }

            buscaminas.revelarBloque(indiceClicado,isMetaDown);
            buscaminas.winner();

            if (buscaminas.starttime == false) {
//            buscaminas.reloj.Start();
                buscaminas.starttime = true;
            }
        }
    }
