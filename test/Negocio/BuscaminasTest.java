package Negocio;

import Presentacion.Casilla;
import Presentacion.CasillaMina;
import Presentacion.CasillaNumero;
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
        instance.setNivel(Nivel.PRINCIPIANTE);
        int filas=instance.getBloquesFila();
        int columnas = instance.getBloquesColumna();
        instance.iniciarCasillas();
        
        Casilla[] casillas = instance.getCasillas();
        
        if(filas*columnas!=casillas.length) fail("No hay el numero de casillas que deberia");

        for(int i=0; i<casillas.length;i++){
            if(!(casillas[i] instanceof ProxyCasilla)) fail("No es del tipo proxyCasilla");
        }
        
    }

    @Test
    public void testSetNivel() {
        System.out.println("setNivel");
        System.out.println("Principiante");
        instance.setNivel(Nivel.PRINCIPIANTE);
        if(instance.getAltoVentana()!= 300) fail("La ventana no es lo alto que debería");
        if(instance.getAnchoVentana()!= 200) fail("La ventana no es lo ancho que debería");
        if(instance.getBloquesColumna() != 10) fail("No hay el numero de columnas que deberia");
        if(instance.getBloquesFila() != 10) fail("No hay el numero de filas que deberia");
        if(instance.getCantidadCasillas()!= 100) fail("No hay el numero de casillas que deberia");
        if(instance.getNumeroMinas()!= 10) fail("No hay el numero de minas que deberia");
        if(instance.getBanderasRestantes()!= 10) fail("No hay el numero de banderas que deberia");
        
        System.out.println("Intermedio");
        instance.setNivel(Nivel.INTERMEDIO);
        if(instance.getAltoVentana()!= 416) fail("La ventana no es lo alto que debería");
        if(instance.getAnchoVentana()!= 320) fail("La ventana no es lo ancho que debería");
        if(instance.getBloquesColumna() != 16) fail("No hay el numero de columnas que deberia");
        if(instance.getBloquesFila() != 16) fail("No hay el numero de filas que deberia");
        if(instance.getCantidadCasillas()!= 256) fail("No hay el numero de casillas que deberia");
        if(instance.getNumeroMinas()!= 70) fail("No hay el numero de minas que deberia");
        if(instance.getBanderasRestantes()!= 70) fail("No hay el numero de banderas que deberia");
        
        System.out.println("Experto");
        instance.setNivel(Nivel.EXPERTO);
        if(instance.getAltoVentana()!= 520) fail("La ventana no es lo alto que debería");
        if(instance.getAnchoVentana()!= 400) fail("La ventana no es lo ancho que debería");
        if(instance.getBloquesColumna() != 20) fail("No hay el numero de columnas que deberia");
        if(instance.getBloquesFila() != 20) fail("No hay el numero de filas que deberia");
        if(instance.getCantidadCasillas()!= 400) fail("No hay el numero de casillas que deberia");
        if(instance.getNumeroMinas()!= 150) fail("No hay el numero de minas que deberia");
        if(instance.getBanderasRestantes()!= 150) fail("No hay el numero de banderas que deberia");
    }

    @Test
    public void testReiniciarNivel() {
        System.out.println("reiniciarNivel");
        
        System.out.println("Reset para nivel principiante");
        instance.setNivel(Nivel.PRINCIPIANTE);
        Casilla casillasPrin[] = instance.getCasillas();
        casillasPrin[5].isRevelado();
        instance.setCasillas(casillasPrin);
        int fila = instance.getBloquesFila();
        int columna = instance.getBloquesColumna();
        instance.reiniciarNivel();
        Casilla casillasDespuesReinicioPrin[] = instance.getCasillas();
        if(instance.getNivelActual()!= Nivel.PRINCIPIANTE) fail("El nivel ha cambiado");
        if(fila != instance.getBloquesFila()) fail("El numero de filas ha cambiado");
        if(columna != instance.getBloquesColumna()) fail("El numero de columnas ha cambiado");
        if(casillasDespuesReinicioPrin[5].isRevelado() == true) fail("No se han tapado las casillas");
        
        System.out.println("Reset para nivel intermedio");
        instance.setNivel(Nivel.INTERMEDIO);
        Casilla casillasInt[] = instance.getCasillas();
        casillasInt[5].isRevelado();
        instance.setCasillas(casillasInt);
        fila = instance.getBloquesFila();
        columna = instance.getBloquesColumna();
        instance.reiniciarNivel();
        Casilla casillasDespuesReinicioInt[] = instance.getCasillas();
        if(instance.getNivelActual()!= Nivel.INTERMEDIO) fail("El nivel ha cambiado");
        if(fila != instance.getBloquesFila()) fail("El numero de filas ha cambiado");
        if(columna != instance.getBloquesColumna()) fail("El numero de columnas ha cambiado");
        if(casillasDespuesReinicioInt[5].isRevelado() == true) fail("No se han tapado las casillas");
        
        System.out.println("Reset para nivel experto");
        instance.setNivel(Nivel.EXPERTO);
        Casilla casillasExp[] = instance.getCasillas();
        casillasExp[5].isRevelado();
        instance.setCasillas(casillasExp);
        fila = instance.getBloquesFila();
        columna = instance.getBloquesColumna();
        instance.reiniciarNivel();
        Casilla casillasDespuesReinicioExp[] = instance.getCasillas();
        if(instance.getNivelActual()!= Nivel.EXPERTO) fail("El nivel ha cambiado");
        if(fila != instance.getBloquesFila()) fail("El numero de filas ha cambiado");
        if(columna != instance.getBloquesColumna()) fail("El numero de columnas ha cambiado");
        if(casillasDespuesReinicioExp[5].isRevelado() == true) fail("No se han tapado las casillas");
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
