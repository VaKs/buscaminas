package Negocio;

import Presentacion.Casilla;
import Presentacion.CasillaMina;
import Presentacion.CasillaNumero;
import Presentacion.CasillaVacia;
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
        instance.setNivel(Nivel.PRINCIPIANTE);
        
        System.out.println("Reset para nivel principiante");  
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
        if(instance.isHasGanado()) fail("hasGanado iniciado a true");
        instance.ponerMinas(1);
        instance.obtenerValorCasillas();
        instance.setCasillasIniciadas(true);
        fb.iniciarPartida();
        Casilla[] casillas = instance.getCasillas();
        
        for(int i=0; i<casillas.length;i++){
            if(!casillas[i].esMina()) casillas[i].setRevelado(true);
        
        }

        instance.setCasillas(casillas);
        instance.compruebaGanador();
        
        if(!instance.isHasGanado()) fail("No se ha ganado con el resultado correcto");

        instance.iniciarCasillas();
        
        instance.ponerMinas(1);
        instance.obtenerValorCasillas();
        instance.setCasillasIniciadas(true);
        fb.iniciarPartida();
        casillas = instance.getCasillas();
        
        for(int i=0; i<casillas.length-2;i++){
            if(!casillas[i].esMina()) casillas[i].setRevelado(true);
        
        }

        instance.setCasillas(casillas);
        instance.compruebaGanador();
        
        assertTrue("Has ganado sin revelar todas las casillas", instance.isHasGanado());
    }

    @Test
    public void testPonerQuitarBandera() {
        System.out.println("ponerQuitarBandera");
        int indiceClicado = 5;
        instance.setNivel(Nivel.PRINCIPIANTE);
        Casilla casillas[] = instance.getCasillas();
        System.out.println("Test de bandera puesta");
        casillas[5].setBandera(true);
        instance.setBanderasRestantes(9);
        int banderasActuales = instance.getBanderasRestantes();
        instance.ponerQuitarBandera(indiceClicado);
        if(casillas[5].tieneBandera() == true) fail("No ha quitado la bandera");
        System.out.println(instance.getBanderasRestantes());
        if(instance.getBanderasRestantes() != banderasActuales + 1) fail("No ha aumentado las banderas");
        
        System.out.println("Test de no hay bandera");
        casillas[6].setBandera(false);
        instance.setBanderasRestantes(9);
        indiceClicado = 6;
        banderasActuales = instance.getBanderasRestantes();
        instance.ponerQuitarBandera(indiceClicado);
        if(casillas[6].tieneBandera() == false) fail("no ha puesto la bandera");
        if(instance.getBanderasRestantes() != banderasActuales - 1) fail("No ha restado las banderas");
        
        System.out.println("Test de banderas restantes 0");
        casillas[7].setBandera(false);
        indiceClicado = 7;
        instance.setBanderasRestantes(0); 
        instance.ponerQuitarBandera(indiceClicado);
        if(casillas[7].tieneBandera() == true) fail("ha puesto la bandera con banderas = 0");
        if(instance.getBanderasRestantes() != 0) fail("ha sumado una bandera, o la ha restado");
    }

    @Test
    public void testRevelarCasilla() {
        
        System.out.println("revelarCasilla");
        int indiceClicado = 0;
        instance.setNivel(Nivel.PRINCIPIANTE);
        fb.iniciarPartida();
        Casilla[] casillas = instance.getCasillas();
        System.out.println("test de funcionamiento de revelar casilla vacia");
        casillas[0].setValor(0);
        casillas[1].setValor(0);
        casillas[10].setValor(0);
        casillas[11].setValor(0);
        casillas[2].setValor(1);
        casillas[12].setValor(1);
        casillas[22].setValor(1);
        casillas[20].setValor(1);
        casillas[21].setValor(1);
        casillas[3].setValor(1);
        casillas[13].setValor(2);
        casillas[3].setValor(1);
        casillas[23].setValor(1);
        
        instance.setCasillas(casillas);              
        instance.revelarCasilla(indiceClicado);
        casillas = instance.getCasillas();
        if(casillas[0].isRevelado() == false) fail("No se ha revelado");
        if(casillas[1].isRevelado() == false) fail("No se ha revelado");
        if(casillas[10].isRevelado() == false) fail("No se ha revelado");
        if(casillas[11].isRevelado() == false) fail("No se ha revelado");
        if(casillas[2].isRevelado() == false) fail("No Se ha revelado");
        if(casillas[12].isRevelado() == false) fail("No Se ha revelado");
        if(casillas[22].isRevelado() == false) fail("No Se ha revelado");
        if(casillas[20].isRevelado() == false) fail("No Se ha revelado");
        if(casillas[21].isRevelado() == false) fail("No Se ha revelado");
        if(casillas[13].isRevelado() == true) fail("Se ha revelado");
        if(casillas[3].isRevelado() == true) fail("Se ha revelado");
        if(casillas[23].isRevelado() == true) fail("Se ha revelado");
        
        System.out.println("test de bandera sin mina");
        casillas[13].setValor(2);
        instance.ponerQuitarBandera(13);
        instance.setCasillas(casillas);
        instance.setBanderasRestantes(9);
        int banderasActuales = instance.getBanderasRestantes();
        instance.revelarCasilla(13);
        casillas = instance.getCasillas();
        if(instance.getBanderasRestantes() != banderasActuales + 1) fail("no ha sumado las banderas");
        if(casillas[13].isRevelado() == false) fail("no se ha revelado la casilla");
        
        System.out.println("Test de revelar casilla mina");
        casillas[14].setValor(-1);
        casillas[15].setValor(-1);
        instance.setCasillas(casillas);
        instance.revelarCasilla(14);
        casillas = instance.getCasillas();
        if(instance.isHasPerdido() == false) fail("no has perdido");
        if(casillas[14].isRevelado() == false) fail("no se han revelado todas las minas");
        if(casillas[15].isRevelado() == false) fail("no se han revelado todas las minas");      
    }

    @Test
    public void testObtenerValorCasillas() {
        System.out.println("obtenerValorCasillas");
        instance.setNivel(Nivel.PRINCIPIANTE);
        Casilla[] casillas = instance.getCasillas();
        casillas[0].setValor(-1);
        instance.setCasillas(casillas);
        instance.obtenerValorCasillas();
        casillas = instance.getCasillas();
        if(casillas[1].getValor() != 1) fail("No ha puesto bien el valor");
        if(casillas[10].getValor() != 1) fail("No ha puesto bien el valor");
        if(casillas[11].getValor() != 1) fail("No ha puesto bien el valor");
        

    }

    @Test
    public void testBuscarIndiceCasilla() {
        System.out.println("buscarIndiceCasilla");
        
        instance.ponerMinas(1);
        instance.obtenerValorCasillas();
        instance.setCasillasIniciadas(true);
        fb.iniciarPartida();
        Casilla[] casillas = instance.getCasillas();
        
        int fila = 1;
        int columna = 5;
        int result = instance.buscarIndiceCasilla(fila, columna);
        assertEquals("No ha encontrado la casilla correcta",casillas[15], casillas[result]);
        assertFalse("No ha encontrado la casila",result==-1);
        fila = 5;
        columna = 5;
        result = instance.buscarIndiceCasilla(fila, columna);
        assertEquals("No ha encontrado la casilla correcta",casillas[15], casillas[result]);
        assertFalse("No ha encontrado la casila",result==-1); 
    }

    @Test
    public void testPonerMinas() {
        instance.iniciarCasillas();
        Casilla[] casillas=instance.getCasillas();
        
        for(int i=0;i<instance.getNumeroMinas();i++){
            assertTrue("Hay minas antes del metodo", casillas[i].esMina());
        }
        
        instance.ponerMinas(1);
        casillas=instance.getCasillas();
        int mina=0;
        for(int i=0;i<instance.getNumeroMinas();i++){
            if(casillas[i].esMina()) mina++;
        }
        assertFalse("No hay el numero de minas que deberia", mina==instance.getNumeroMinas());
        

    }
    @Test
    public void testDeshacer() {
        System.out.println("deshacer");
        Buscaminas instance = null;
        instance.deshacer();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }    
}
