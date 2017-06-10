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
    private MouseHandler mh;
    private MementoAlmacen almacen = MementoAlmacen.getAlmacen();
    private boolean hasPerdido = false;
    private boolean hasGanado = false;
    private FabricaCasilla fabricaCasilla = FabricaCasilla.getFabrica();

    public Buscaminas(FrameBuscaminas fb) {
        
        FBuscaminas= fb;
        this.iniciarCasillas();
    }

    public void iniciarCasillas() {
        casillasIniciadas = false;
        
        casillas = new ProxyCasilla[getCantidadCasillas()];
        mh = new MouseHandler(this);
        
        int indice = 0;
        for (int i = 0; i < getBloquesFila(); i++) {
            for (int j = 0; j < getBloquesColumna(); j++) {
                casillas[indice] = fabricaCasilla.crearCasilla(i, j);
                getCasillas()[indice].addMouseListener(mh);
                FBuscaminas.añadirCasillaAlPanel(getCasillas()[indice]);
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
            cantidadCasillas = getBloquesColumna() * getBloquesFila();
            numeroMinas = 10;
            banderasRestantes=10;
        } else if (nivel==nivel.INTERMEDIO) {
            anchoVentana = 320;
            altoVentana = 416;
            bloquesFila = 16;
            bloquesColumna = 16;
            numeroMinas = 70;
            banderasRestantes=70;
            cantidadCasillas = getBloquesColumna() * getBloquesFila();
        } else if (nivel==nivel.EXPERTO) {
            anchoVentana = 400;
            altoVentana = 520;
            bloquesFila = 20;
            bloquesColumna = 20;
            numeroMinas = 150;
            banderasRestantes=150;
            cantidadCasillas = getBloquesColumna() * getBloquesFila();
        }
        
        FBuscaminas.setPanel(getAnchoVentana(), getAltoVentana(), getBloquesFila(), getBloquesColumna(), getNumeroMinas());
        this.iniciarCasillas();
    }
    public void reiniciarNivel(){
        FBuscaminas.setPanel(getAnchoVentana(), getAltoVentana(), getBloquesFila(), getBloquesColumna(), getNumeroMinas());
        this.iniciarCasillas();
    }

    public void compruebaGanador() {
        boolean todasReveladas = true;
        for (int k = 0; k < getCantidadCasillas(); k++) {

            if (!casillas[k].esMina()) {
                todasReveladas = todasReveladas && getCasillas()[k].isRevelado();
            }
        }
        if (todasReveladas) {
            for (int k = 0; k < getCantidadCasillas(); k++) {
                getCasillas()[k].removeMouseListener(mh);
            }

            hasGanado = true;
            FBuscaminas.hasGanado();
            
        }
    }

    public void ponerQuitarBandera(int indiceClicado){
        
        if (getCasillas()[indiceClicado].tieneBandera()) {
                    getCasillas()[indiceClicado].guardarMemento();
                    banderasRestantes++;
                    getCasillas()[indiceClicado].setIcon(null);
                    getCasillas()[indiceClicado].setBandera(false);
                    
                } else if(!casillas[indiceClicado].tieneBandera() && getBanderasRestantes() != 0){
                    getCasillas()[indiceClicado].guardarMemento();
                    banderasRestantes--;
                    getCasillas()[indiceClicado].setBandera(true);
                }
            FBuscaminas.actualizarContadorBanderas(getBanderasRestantes());
    
    }
    public void revelarCasilla(int indiceClicado) {
            
            if (getCasillas()[indiceClicado].tieneBandera()&& !casillas[indiceClicado].esMina()) {
                    banderasRestantes++;
                    getCasillas()[indiceClicado].setIcon(null);
                    FBuscaminas.actualizarContadorBanderas(getBanderasRestantes());
            }
            
            if (getCasillas()[indiceClicado].esMina()) {
                hasPerdido = true;
                getCasillas()[indiceClicado].guardarMemento();
                
                for (int k = 0; k < getCantidadCasillas(); k++) {
                    if (getCasillas()[k].esMina()) {
                        getCasillas()[k].guardarMemento();
                        getCasillas()[k].revelar();
                    }
                    getCasillas()[k].removeMouseListener(mh);
                }
                
                FBuscaminas.hasPerdido();
                
            }else if (getCasillas()[indiceClicado].esVacia()) {
                ExpandirDFS(getCasillas()[indiceClicado].getFila(), getCasillas()[indiceClicado].getColumna());
            } else {
                getCasillas()[indiceClicado].guardarMemento();
                getCasillas()[indiceClicado].revelar();
            }
    }

    public void obtenerValorCasillas() {

        for (int i = 0; i < getCantidadCasillas(); i++) {
            int valor = 0;
            int valorFila, valorColumna;

            if (!casillas[i].esMina()) {
                for (int k = 0; k < 8; k++) {
                    valorFila = getCasillas()[i].getFila() + posicionFilasContiguas[k];
                    valorColumna = getCasillas()[i].getColumna() + posicionColumnasContiguas[k];

                    if (valorFila >= 0 && valorColumna >= 0 && valorFila < getBloquesFila() && valorColumna < getBloquesColumna()) {
                        if (getCasillas()[buscarIndiceCasilla(valorFila, valorColumna)].esMina()) {
                            valor++;
                        }

                    }
                }
                getCasillas()[i].setValor(valor);
            }

        }
    }

    public int buscarIndiceCasilla(int fila, int columna) {
        return (fila*getBloquesColumna())+columna;
    }

    public void ExpandirDFS(int fila, int columna) {

        int filaAdyacente, columnaAdyacente;
        getCasillas()[buscarIndiceCasilla(fila, columna)].revelar();

        for (int k = 0; k < 8; k++) {
            filaAdyacente = fila + posicionFilasContiguas[k];
            columnaAdyacente = columna + posicionColumnasContiguas[k];
            int indiceAdyacente = buscarIndiceCasilla(filaAdyacente, columnaAdyacente);
            if ((filaAdyacente >= 0) && (filaAdyacente < getBloquesFila()) && (columnaAdyacente >= 0) && (columnaAdyacente < getBloquesColumna()) && (!casillas[indiceAdyacente].isRevelado())) {
                if (getCasillas()[indiceAdyacente].esVacia()) {
                    ExpandirDFS(filaAdyacente, columnaAdyacente);
                } else if (!casillas[indiceAdyacente].esMina()) {
                    getCasillas()[indiceAdyacente].revelar();
                }
            }

        }
    }

    public void ponerMinas(int indiceClicado) {
        int indiceAleatorio;
        Random rand = new Random();

        for (int i = 0; i < getNumeroMinas(); i++) {
            indiceAleatorio = rand.nextInt(getCantidadCasillas());

            if (getCasillas()[indiceAleatorio].esMina() || indiceClicado == indiceAleatorio) {
                i--;

            } else {
                getCasillas()[indiceAleatorio].setValor(-1);
            }
        }
    }

    
    public void heClickado(boolean clickDerecho, int indiceClicado){                
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
        
        Casilla ultimaCasillaCambiada= getCasillas()[buscarIndiceCasilla(getAlmacen().getFilaUltimoMemento(), getAlmacen().getColumnaUltimoMemento())];
                
                if (isHasPerdido()) { 
                    for (int i = 0; i < getNumeroMinas(); i++) {
                        ultimaCasillaCambiada.restaurarMemento();
                        ultimaCasillaCambiada=getCasillas()[buscarIndiceCasilla(getAlmacen().getFilaUltimoMemento(), getAlmacen().getColumnaUltimoMemento())];
                    }
                    hasPerdido = false;
                    for (int j = 0; j < getCantidadCasillas(); j++) {
                        getCasillas()[j].addMouseListener(mh);
                    }
                    FBuscaminas.reiniciarPartida();
                } else if (isHasGanado()) {
                    hasGanado = false;
                    ultimaCasillaCambiada.restaurarMemento();
                    if(ultimaCasillaCambiada.tieneBandera()) banderasRestantes--;
                    
                    for (int k = 0; k < getCantidadCasillas(); k++) {
                        getCasillas()[k].addMouseListener(mh);
                    }
                    FBuscaminas.reiniciarPartida();
                } else ultimaCasillaCambiada.restaurarMemento();
                
                FBuscaminas.actualizarContadorBanderas(getBanderasRestantes());
    
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

    public MementoAlmacen getAlmacen() {
        return almacen;
    }

    public boolean isHasPerdido() {
        return hasPerdido;
    }

    public boolean isHasGanado() {
        return hasGanado;
    }

}
