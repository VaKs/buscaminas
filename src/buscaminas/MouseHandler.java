/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {

    Minesweeper buscaminas;
    public MouseHandler(Minesweeper buscamina){
        buscaminas=buscamina;
    }    
    
    @Override
    public void mouseClicked(MouseEvent me) {    
        
        if (buscaminas.check == true) {
            for (int i = 0; i < buscaminas.bloquesFila; i++) {
                for (int j = 0; j < buscaminas.bloquesColumna; j++) {
                    if (me.getSource() == buscaminas.bloques[i][j]) {
                        buscaminas.filaBotonClick = i;
                        buscaminas.columnaBotonClick = j;
                        //i = buscaminas.bloquesFila;
                        break;
                    }
                }
            }

            buscaminas.setmine();
            buscaminas.calculation();
            buscaminas.check = false;
        }

            buscaminas.showvalue(me);
            buscaminas.winner();

        if (buscaminas.starttime == false) {
            buscaminas.reloj.Start();
            buscaminas.starttime = true;
        }
    }
}
