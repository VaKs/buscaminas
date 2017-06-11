package Negocio;

import Presentacion.Casilla;
import Presentacion.FabricaCasilla;
import Presentacion.FrameBuscaminas;
import Presentacion.ProxyCasilla;
import java.util.*;

public class Buscaminas {
    
    FrameBuscaminas FBuscaminas;
    
    private int anchoVentana, altoVentana, bloquesFila, bloquesColumna, numeroMinas, banderasRestantes = 0;
    private int cantidadCasillas;
    private int[] posicionFilasContiguas = {-1, -1, -1, 0, 1, 1, 1, 0};
    private int[] posicionColumnasContiguas = {-1, 0, 1, 1, 1, 0, -1, -1};
    private Casilla[] casillas;
    private boolean casillasIniciadas = false;
    private MouseHandlerCasilla mh;
    private MementoAlmacen almacen = MementoAlmacen.getAlmacen();
    private boolean hasPerdido = false;
    private boolean hasGanado = false;
    private FabricaCasilla fabricaCasilla = FabricaCasilla.getFabrica();
    private Nivel nivelActual;

    public Buscaminas(FrameBuscaminas fb) {
        
        FBuscaminas= fb;
        this.iniciarCasillas();
    }

    public void iniciarCasillas() {
        casillasIniciadas = false;
        
        casillas = new ProxyCasilla[cantidadCasillas];
        mh = new MouseHandlerCasilla(this);
        
        int indice = 0;
        for (int i = 0; i < bloquesFila; i++) {
            for (int j = 0; j < bloquesColumna; j++) {
                casillas[indice] = fabricaCasilla.crearCasilla(i, j);
                casillas[indice].addMouseListener(mh);
                FBuscaminas.añadirCasillaAlPanel(casillas[indice]);
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
            banderasRestantes=10;
        } else if (nivel==nivel.INTERMEDIO) {
            anchoVentana = 320;
            altoVentana = 416;
            bloquesFila = 16;
            bloquesColumna = 16;
            numeroMinas = 70;
            banderasRestantes=70;
            cantidadCasillas = bloquesColumna * bloquesFila;
        } else if (nivel==nivel.EXPERTO) {
            anchoVentana = 400;
            altoVentana = 520;
            bloquesFila = 20;
            bloquesColumna = 20;
            numeroMinas = 150;
            banderasRestantes=150;
            cantidadCasillas = bloquesColumna * bloquesFila; 
        }
        nivelActual = nivel;
        FBuscaminas.setPanel(anchoVentana, altoVentana, bloquesFila, bloquesColumna, numeroMinas);
        this.iniciarCasillas();
    }
    public void reiniciarNivel(){
        this.setNivel(nivelActual);
        this.iniciarCasillas();
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
            FBuscaminas.hasGanado();
            
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
            FBuscaminas.actualizarContadorBanderas(banderasRestantes);
    
    }
    public void revelarCasilla(int indiceClicado) {
            
            if (casillas[indiceClicado].tieneBandera()&& !casillas[indiceClicado].esMina()) {
                    banderasRestantes++;
                    casillas[indiceClicado].setIcon(null);
                    FBuscaminas.actualizarContadorBanderas(banderasRestantes);
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
                
                FBuscaminas.hasPerdido();
                
            }else if (casillas[indiceClicado].esVacia()) {
                ExpandirDFS(casillas[indiceClicado].getFila(), casillas[indiceClicado].getColumna());
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

    public void ExpandirDFS(int fila, int columna) {

        int filaAdyacente, columnaAdyacente;
        casillas[buscarIndiceCasilla(fila, columna)].revelar();

        for (int k = 0; k < 8; k++) {
            filaAdyacente = fila + posicionFilasContiguas[k];
            columnaAdyacente = columna + posicionColumnasContiguas[k];
            int indiceAdyacente = buscarIndiceCasilla(filaAdyacente, columnaAdyacente);
            if ((filaAdyacente >= 0) && (filaAdyacente < bloquesFila) && (columnaAdyacente >= 0) && (columnaAdyacente < bloquesColumna) && (!casillas[indiceAdyacente].isRevelado())) {
                if (casillas[indiceAdyacente].esVacia()) {
                    ExpandirDFS(filaAdyacente, columnaAdyacente);
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

    
    public void primerClick(boolean clickDerecho, int indiceClicado){                
            if (this.isCasillasIniciadas() == false) {

                this.ponerMinas(indiceClicado);
                this.obtenerValorCasillas();
                this.casillasIniciadas = true;
                FBuscaminas.iniciarPartida();
                
            }
            if(clickDerecho) this.ponerQuitarBandera(indiceClicado);
            else this.revelarCasilla(indiceClicado);
            
            this.compruebaGanador();

    }
    
    public void deshacer(){
        
        Casilla ultimaCasillaCambiada= casillas[buscarIndiceCasilla(getAlmacen().getFilaUltimoMemento(), getAlmacen().getColumnaUltimoMemento())];
                
                if (isHasPerdido()) { 
                    for (int i = 0; i < numeroMinas; i++) {
                        ultimaCasillaCambiada.restaurarMemento();
                        ultimaCasillaCambiada=casillas[buscarIndiceCasilla(getAlmacen().getFilaUltimoMemento(), getAlmacen().getColumnaUltimoMemento())];
                    }
                    hasPerdido = false;
                    for (int j = 0; j < cantidadCasillas; j++) {
                        casillas[j].addMouseListener(mh);
                    }
                    FBuscaminas.reiniciarPartida();
                } else if (isHasGanado()) {
                    hasGanado = false;
                    ultimaCasillaCambiada.restaurarMemento();
                    if(ultimaCasillaCambiada.tieneBandera()) banderasRestantes--;
                    
                    for (int k = 0; k < cantidadCasillas; k++) {
                        casillas[k].addMouseListener(mh);
                    }
                    FBuscaminas.reiniciarPartida();
                } else ultimaCasillaCambiada.restaurarMemento();
                
                FBuscaminas.actualizarContadorBanderas(banderasRestantes);
    
    }

    public int getAnchoVentana() {
        return anchoVentana;
    }

    public int getAltoVentana() {
        return altoVentana;
    }

    public int getBloquesFila() {
        return bloquesFila;
    }

    public int getBloquesColumna() {
        return bloquesColumna;
    }

    public int getNumeroMinas() {
        return numeroMinas;
    }

    public int getBanderasRestantes() {
        return banderasRestantes;
    }

    public int getCantidadCasillas() {
        return cantidadCasillas;
    }

    public Casilla[] getCasillas() {
        return casillas;
    }
    public void setCasillas(Casilla[] casillas) {
        this.casillas=casillas;
    }

    public boolean isCasillasIniciadas() {
        return casillasIniciadas;
    }

    public void setCasillasIniciadas(boolean casillasIniciadas) {
        this.casillasIniciadas=casillasIniciadas;
    }
    
    public MementoAlmacen getAlmacen() {
        return almacen;
    }

    public boolean isHasPerdido() {
        return hasPerdido;
    }

    public boolean isHasGanado() {
        return hasGanado;
    }

    public Nivel getNivelActual() {
        return nivelActual;
    }
    public void setBanderasRestantes(int banderasRestantes) {
        this.banderasRestantes = banderasRestantes;
    }

}
