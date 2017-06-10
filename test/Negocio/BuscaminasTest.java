package Negocio;

import Presentacion.Casilla;
import Presentacion.FrameBuscaminas;
import Presentacion.ProxyCasilla;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class BuscaminasTest {
    FrameBuscaminas fb=new FrameBuscaminas();
    Buscaminas instance=new Buscaminas(fb);
    
    public BuscaminasTest() {
    }
    
    @Test
    public void testIniciarCasillas() {
        System.out.println("iniciarCasillas");
        instance.iniciarCasillas();
        Casilla[] casillas = instance.getCasillas();
        int filas=instance.getBloquesFila();
        int columnas = instance.getBloquesColumna();
        if(filas*columnas!=casillas.length) fail("No hay el numero de casillas que deberia");
        
        for(int i=0; i<casillas.length;i++){
            if(!(casillas[i] instanceof ProxyCasilla)) fail("No es del tipo proxyCasilla");
            if(casillas[i].getMouseListeners().length!=1) fail("Numero de mose liseners incorrecto");
        }
        
    }

    @Test
    public void testSetNivel() {
        System.out.println("setNivel");
        Nivel nivel = null;
        Buscaminas instance = null;
        instance.setNivel(nivel);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testReiniciarNivel() {
        System.out.println("reiniciarNivel");
        Buscaminas instance = null;
        instance.reiniciarNivel();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testCompruebaGanador() {
        System.out.println("compruebaGanador");
        Buscaminas instance = null;
        instance.compruebaGanador();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testPonerQuitarBandera() {
        System.out.println("ponerQuitarBandera");
        int indiceClicado = 0;
        Buscaminas instance = null;
        instance.ponerQuitarBandera(indiceClicado);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testRevelarCasilla() {
        System.out.println("revelarCasilla");
        int indiceClicado = 0;
        Buscaminas instance = null;
        instance.revelarCasilla(indiceClicado);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testObtenerValorCasillas() {
        System.out.println("obtenerValorCasillas");
        Buscaminas instance = null;
        instance.obtenerValorCasillas();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testBuscarIndiceCasilla() {
        System.out.println("buscarIndiceCasilla");
        int fila = 0;
        int columna = 0;
        Buscaminas instance = null;
        int expResult = 0;
        int result = instance.buscarIndiceCasilla(fila, columna);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testExpandirDFS() {
        System.out.println("ExpandirDFS");
        int fila = 0;
        int columna = 0;
        Buscaminas instance = null;
        instance.ExpandirDFS(fila, columna);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testPonerMinas() {
        System.out.println("ponerMinas");
        int indiceClicado = 0;
        Buscaminas instance = null;
        instance.ponerMinas(indiceClicado);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testHeClickado() {
        System.out.println("heClickado");
        boolean clickDerecho = false;
        int indiceClicado = 0;
        Buscaminas instance = null;
        instance.heClickado(clickDerecho, indiceClicado);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testDeshacer() {
        System.out.println("deshacer");
        Buscaminas instance = null;
        instance.deshacer();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetAnchoVentana() {
        System.out.println("getAnchoVentana");
        Buscaminas instance = null;
        int expResult = 0;
        int result = instance.getAnchoVentana();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetAltoVentana() {
        System.out.println("getAltoVentana");
        Buscaminas instance = null;
        int expResult = 0;
        int result = instance.getAltoVentana();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetBloquesFila() {
        System.out.println("getBloquesFila");
        Buscaminas instance = null;
        int expResult = 0;
        int result = instance.getBloquesFila();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetBloquesColumna() {
        System.out.println("getBloquesColumna");
        Buscaminas instance = null;
        int expResult = 0;
        int result = instance.getBloquesColumna();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetNumeroMinas() {
        System.out.println("getNumeroMinas");
        Buscaminas instance = null;
        int expResult = 0;
        int result = instance.getNumeroMinas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetBanderasRestantes() {
        System.out.println("getBanderasRestantes");
        Buscaminas instance = null;
        int expResult = 0;
        int result = instance.getBanderasRestantes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetCantidadCasillas() {
        System.out.println("getCantidadCasillas");
        Buscaminas instance = null;
        int expResult = 0;
        int result = instance.getCantidadCasillas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetCasillas() {
        System.out.println("getCasillas");
        Buscaminas instance = null;
        Casilla[] expResult = null;
        Casilla[] result = instance.getCasillas();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetCasillas() {
        System.out.println("setCasillas");
        Casilla[] casillas = null;
        Buscaminas instance = null;
        instance.setCasillas(casillas);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testIsCasillasIniciadas() {
        System.out.println("isCasillasIniciadas");
        Buscaminas instance = null;
        boolean expResult = false;
        boolean result = instance.isCasillasIniciadas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetAlmacen() {
        System.out.println("getAlmacen");
        Buscaminas instance = null;
        MementoAlmacen expResult = null;
        MementoAlmacen result = instance.getAlmacen();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testIsHasPerdido() {
        System.out.println("isHasPerdido");
        Buscaminas instance = null;
        boolean expResult = false;
        boolean result = instance.isHasPerdido();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testIsHasGanado() {
        System.out.println("isHasGanado");
        Buscaminas instance = null;
        boolean expResult = false;
        boolean result = instance.isHasGanado();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
