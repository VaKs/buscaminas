package buscaminas;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Buscaminas {
    
    FrameBuscaminas FBuscaminas;

    int anchoVentana, altoVentana, bloquesFila, bloquesColumna, numeroMinas, banderasRestantes = 0;
    int cantidadCasillas;
    int[] posicionFilasContiguas = {-1, -1, -1, 0, 1, 1, 1, 0};
    int[] posicionColumnasContiguas = {-1, 0, 1, 1, 1, 0, -1, -1};
    Casilla[] casillas;
    boolean casillasIniciadas = false, starttime = false;
    MouseHandler mh;
    MementoAlmacen almacen = MementoAlmacen.getAlmacen();
    boolean hasPerdido = false;
    boolean hasGanado = false;
    FabricaCasilla fabricaCasilla = FabricaCasilla.getFabrica();

    public Buscaminas(FrameBuscaminas fb) {
        
        FBuscaminas= fb;
        this.reset();
    }

    public void reset() {
        casillasIniciadas = false;
        starttime = false;
        
        casillas = new ProxyCasilla[cantidadCasillas];
        mh = new MouseHandler(this);
        
        int indice = 0;
        for (int i = 0; i < bloquesFila; i++) {
            for (int j = 0; j < bloquesColumna; j++) {
                casillas[indice] = fabricaCasilla.crearCasilla(i, j);
                casillas[indice].addMouseListener(mh);
                FBuscaminas.panelb.add(casillas[indice]);
                indice++;

            }
        }
    }

    public void setNivel(Nivel nivel) {
        if (nivel==nivel.PRINCIPIANTE) {
            anchoVentana = 200;
            altoVentana = 300;
            bloquesFila = 10;
            bloquesColumna = 10;
            cantidadCasillas = bloquesColumna * bloquesFila;
            numeroMinas = 10;
        } else if (nivel==nivel.INTERMEDIO) {
            anchoVentana = 320;
            altoVentana = 416;
            bloquesFila = 16;
            bloquesColumna = 16;
            numeroMinas = 70;
            cantidadCasillas = bloquesColumna * bloquesFila;
        } else if (nivel==nivel.EXPERTO) {
            anchoVentana = 400;
            altoVentana = 520;
            bloquesFila = 20;
            bloquesColumna = 20;
            numeroMinas = 150;
            cantidadCasillas = bloquesColumna * bloquesFila;
        }
        
        FBuscaminas.setPanel(anchoVentana, altoVentana, bloquesFila, bloquesColumna, numeroMinas);

    }

    public void componentAdded(ContainerEvent ce) {
    }

    public void componentRemoved(ContainerEvent ce) {
    }

    public void actionPerformed(ActionEvent ae) {
    }

    public void compruebaGanador() {
        boolean todasReveladas = true;
        for (int k = 0; k < cantidadCasillas; k++) {

            if (!casillas[k].esMina()) {
                todasReveladas = todasReveladas && casillas[k].isRevelado();
            }
        }
        if (todasReveladas) {
            for (int k = 0; k < cantidadCasillas; k++) {
                casillas[k].removeMouseListener(mh);
            }

            hasGanado = true;
            FBuscaminas.fmReloj.pararReloj();
            JOptionPane.showMessageDialog(null, "Has ganado!");
        }
    }

    public void ponerQuitarBandera(int indiceClicado){
        
        if (casillas[indiceClicado].tieneBandera()) {
                    casillas[indiceClicado].guardarMemento();
                    banderasRestantes++;
                    casillas[indiceClicado].setIcon(null);
                    casillas[indiceClicado].setBandera(false);
                    
                } else if(!casillas[indiceClicado].tieneBandera() && banderasRestantes != 0){
                    casillas[indiceClicado].guardarMemento();
                    banderasRestantes--;
                    casillas[indiceClicado].setBandera(true);
                }
            FBuscaminas.tf_mine.setText("" + banderasRestantes);
    
    }
    public void revelarCasilla(int indiceClicado) {
            
            if (casillas[indiceClicado].tieneBandera()&& !casillas[indiceClicado].esMina()) {
                    banderasRestantes++;
                    casillas[indiceClicado].setIcon(null);
                    FBuscaminas.tf_mine.setText("" + banderasRestantes);
            }
            
            if (casillas[indiceClicado].esMina()) {
                hasPerdido = true;
                casillas[indiceClicado].guardarMemento();
                
                for (int k = 0; k < cantidadCasillas; k++) {
                    if (casillas[k].esMina()) {
                        casillas[k].guardarMemento();
                        casillas[k].revelar();
                    }
                    casillas[k].removeMouseListener(mh);
                }
                FBuscaminas.fmReloj.pararReloj();
                FBuscaminas.b_reset.setIcon(FBuscaminas.ic[1]);
                JOptionPane.showMessageDialog(null, "Has perdido!");
                
            }else if (casillas[indiceClicado].esVacia()) {
                dfs(casillas[indiceClicado].getFila(), casillas[indiceClicado].getColumna());
            } else {
                casillas[indiceClicado].guardarMemento();
                casillas[indiceClicado].revelar();
            }
    }

    public void obtenerValorCasillas() {

        for (int i = 0; i < cantidadCasillas; i++) {
            int valor = 0;
            int valorFila, valorColumna;

            if (!casillas[i].esMina()) {
                for (int k = 0; k < 8; k++) {
                    valorFila = casillas[i].getFila() + posicionFilasContiguas[k];
                    valorColumna = casillas[i].getColumna() + posicionColumnasContiguas[k];

                    if (valorFila >= 0 && valorColumna >= 0 && valorFila < bloquesFila && valorColumna < bloquesColumna) {
                        if (casillas[buscarIndiceCasilla(valorFila, valorColumna)].esMina()) {
                            valor++;
                        }

                    }
                }
                casillas[i].setValor(valor);
            }

        }
    }

    public int buscarIndiceCasilla(int fila, int columna) {
        return (fila*bloquesColumna)+columna;
    }

    public void dfs(int fila, int columna) {

        int filaAdyacente, columnaAdyacente;
        casillas[buscarIndiceCasilla(fila, columna)].revelar();

        for (int k = 0; k < 8; k++) {
            filaAdyacente = fila + posicionFilasContiguas[k];
            columnaAdyacente = columna + posicionColumnasContiguas[k];
            int indiceAdyacente = buscarIndiceCasilla(filaAdyacente, columnaAdyacente);
            if ((filaAdyacente >= 0) && (filaAdyacente < bloquesFila) && (columnaAdyacente >= 0) && (columnaAdyacente < bloquesColumna) && (!casillas[indiceAdyacente].isRevelado())) {
                if (casillas[indiceAdyacente].esVacia()) {
                    dfs(filaAdyacente, columnaAdyacente);
                } else if (!casillas[indiceAdyacente].esMina()) {
                    casillas[indiceAdyacente].revelar();
                }
            }

        }
    }

    public void ponerMinas(int indiceClicado) {
        int indiceAleatorio;
        Random rand = new Random();

        for (int i = 0; i < numeroMinas; i++) {
            indiceAleatorio = rand.nextInt(cantidadCasillas);

            if (casillas[indiceAleatorio].esMina() || indiceClicado == indiceAleatorio) {
                i--;

            } else {
                casillas[indiceAleatorio].setValor(-1);
            }
        }
    }

    
    public void heClickado(boolean clickDerecho, int indiceClicado){                
            if (this.casillasIniciadas == false) {

                this.ponerMinas(indiceClicado);
                this.obtenerValorCasillas();
                this.casillasIniciadas = true;
                FBuscaminas.fmReloj.iniciarReloj();
                
            }
            if(clickDerecho) this.ponerQuitarBandera(indiceClicado);
            else this.revelarCasilla(indiceClicado);
            
            
            
            this.compruebaGanador();

    }
    
    public void deshacer(){
        
        Casilla ultimaCasillaCambiada= casillas[buscarIndiceCasilla(almacen.getFilaUltimoMemento(), almacen.getColumnaUltimoMemento())];
                
                if (hasPerdido) { 
                    for (int i = 0; i < numeroMinas; i++) {
                        ultimaCasillaCambiada.restaurarMemento();
                        ultimaCasillaCambiada=casillas[buscarIndiceCasilla(almacen.getFilaUltimoMemento(), almacen.getColumnaUltimoMemento())];
                    }
                    hasPerdido = false;
                    for (int j = 0; j < cantidadCasillas; j++) {
                        casillas[j].addMouseListener(mh);
                    }
                    FBuscaminas.fmReloj.reiniciar();
                } else if (hasGanado) {
                    hasGanado = false;
                    ultimaCasillaCambiada.restaurarMemento();
                    if(ultimaCasillaCambiada.tieneBandera()) banderasRestantes--;
                    
                    for (int k = 0; k < cantidadCasillas; k++) {
                        casillas[k].addMouseListener(mh);
                    }
                    FBuscaminas.fmReloj.reiniciar();
                } else ultimaCasillaCambiada.restaurarMemento();
                FBuscaminas.tf_mine.setText("" + banderasRestantes);
                FBuscaminas.b_reset.setIcon(FBuscaminas.ic[0]);
    
    }

}
