/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import Negocio.Buscaminas;
import Presentacion.Casilla;
import Presentacion.CasillaMina;
import Presentacion.CasillaNumero;
import Presentacion.CasillaVacia;
import java.awt.event.ActionEvent;
import java.awt.event.ContainerEvent;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class BuscaminasTest {
    
    public BuscaminasTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of reset method, of class Buscaminas.
     */
    @Test
    public void testReset() {
        
        System.out.println("test del reset");
        
        Buscaminas testDelReset = new Buscaminas();
        CasillaMina casillamina = new CasillaMina(1, 1, -1);
        CasillaVacia casillavacia = new CasillaVacia(2,2,0);
        CasillaNumero casillanumero = new CasillaNumero(4, 4, 2);
        boolean esProxy = true;
        Casilla casillasTest[] = null;
        casillasTest[0] = casillamina;
        casillasTest[1] = casillanumero;
        casillasTest[2] = casillavacia;
        testDelReset.casillas = casillasTest;
        testDelReset.reset();
        for(int i = 0; i<testDelReset.casillas.length;i++){
            if(testDelReset.casillas[i] != null){
                esProxy = false;
            }
        }
        
        assertTrue(esProxy);
        fail("No se ha reseteado");
    }

    /**
     * Test of compruebaGanador method, of class Buscaminas.
     */
    @Test
    public void testCompruebaGanador() {
        System.out.println("compruebaGanador");
        System.out.println("Test de es cierto");
        Buscaminas testDeGanador = new Buscaminas();
        CasillaMina casillamina = new CasillaMina(1, 1, -1);
        CasillaVacia casillavacia = new CasillaVacia(2,2,0);
        CasillaNumero casillanumero = new CasillaNumero(4, 4, 2);
        Casilla casillasTest[] = null;
        casillavacia.revelar();
        casillanumero.revelar();
        casillasTest[0] = casillamina;
        casillasTest[1] = casillanumero;
        casillasTest[2] = casillavacia;
        testDeGanador.casillas = casillasTest;
        testDeGanador.compruebaGanador();
        assertTrue(testDeGanador.hasGanado);
        System.out.println("Test de es false");
        CasillaMina casillamina2 = new CasillaMina(1, 1, -1);
        CasillaVacia casillavacia2 = new CasillaVacia(2,2,0);
        CasillaNumero casillanumero2 = new CasillaNumero(4, 4, 2);
        casillavacia2.revelar();
        casillasTest[0] = casillamina2;
        casillasTest[1] = casillanumero2;
        casillasTest[2] = casillavacia2;
        testDeGanador.compruebaGanador();
        assertFalse(testDeGanador.hasGanado);
        fail("Error");
    }

    /**
     * Test of ponerQuitarBandera method, of class Buscaminas.
     */
    @Test
    public void testPonerQuitarBandera() {
        System.out.println("ponerQuitarBandera");
        int indiceClicado = 0;
        Buscaminas instance = new Buscaminas();
        instance.ponerQuitarBandera(indiceClicado);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of revelarCasilla method, of class Buscaminas.
     */
    @Test
    public void testRevelarCasilla() {
        System.out.println("revelarCasilla");
        int indiceClicado = 0;
        Buscaminas instance = new Buscaminas();
        instance.revelarCasilla(indiceClicado);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerValorCasillas method, of class Buscaminas.
     */
    @Test
    public void testObtenerValorCasillas() {
        System.out.println("obtenerValorCasillas");
        Buscaminas instance = new Buscaminas();
        instance.obtenerValorCasillas();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarIndiceCasilla method, of class Buscaminas.
     */
    @Test
    public void testBuscarIndiceCasilla() {
        System.out.println("buscarIndiceCasilla");
        int fila = 0;
        int columna = 0;
        Buscaminas instance = new Buscaminas();
        int expResult = 0;
        int result = instance.buscarIndiceCasilla(fila, columna);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dfs method, of class Buscaminas.
     */
    @Test
    public void testDfs() {
        System.out.println("dfs");
        int fila = 0;
        int columna = 0;
        Buscaminas instance = new Buscaminas();
        instance.dfs(fila, columna);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ponerMinas method, of class Buscaminas.
     */
    @Test
    public void testPonerMinas() {
        System.out.println("ponerMinas");
        int indiceClicado = 0;
        Buscaminas instance = new Buscaminas();
        instance.ponerMinas(indiceClicado);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIcono method, of class Buscaminas.
     */
    @Test
    public void testSetIcono() {
        System.out.println("setIcono");
        Buscaminas instance = new Buscaminas();
        instance.setIcono();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of heClickado method, of class Buscaminas.
     */
    @Test
    public void testHeClickado() {
        System.out.println("heClickado");
        boolean clickDerecho = false;
        int indiceClicado = 0;
        Buscaminas instance = new Buscaminas();
        instance.heClickado(clickDerecho, indiceClicado);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
