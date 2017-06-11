package Negocio;

import Presentacion.Casilla;
import Presentacion.FrameBuscaminas;
import Presentacion.ProxyCasilla;
import static org.junit.Assert.*;
import org.junit.Test;

public class BuscaminasTest {

    FrameBuscaminas frameBuscaminas = new FrameBuscaminas();
    Buscaminas buscaminasTest = new Buscaminas(frameBuscaminas);

    public BuscaminasTest() {
    }

    @Test
    public void testIniciarCasillas() {
        System.out.println("iniciarCasillas");
        buscaminasTest.setNivel(Nivel.PRINCIPIANTE);
        int filas = buscaminasTest.getCasillasPorFila();
        int columnas = buscaminasTest.getCasillasPorColumna();
        buscaminasTest.iniciarCasillas();

        Casilla[] casillas = buscaminasTest.getCasillas();

        if (filas * columnas != casillas.length) {
            fail("No hay el numero de casillas que deberia");
        }

        for (int i = 0; i < casillas.length; i++) {
            if (!(casillas[i] instanceof ProxyCasilla)) {
                fail("No es del tipo proxyCasilla");
            }
        }

    }

    @Test
    public void testSetNivel() {
        System.out.println("setNivel");
        System.out.println("Principiante");
        buscaminasTest.setNivel(Nivel.PRINCIPIANTE);
        if (buscaminasTest.getAltoVentana() != 300) {
            fail("La ventana no es lo alto que debería");
        }
        if (buscaminasTest.getAnchoVentana() != 200) {
            fail("La ventana no es lo ancho que debería");
        }
        if (buscaminasTest.getCasillasPorColumna() != 10) {
            fail("No hay el numero de columnas que deberia");
        }
        if (buscaminasTest.getCasillasPorFila() != 10) {
            fail("No hay el numero de filas que deberia");
        }
        if (buscaminasTest.getCantidadCasillas() != 100) {
            fail("No hay el numero de casillas que deberia");
        }
        if (buscaminasTest.getNumeroMinas() != 10) {
            fail("No hay el numero de minas que deberia");
        }
        if (buscaminasTest.getBanderasRestantes() != 10) {
            fail("No hay el numero de banderas que deberia");
        }

        System.out.println("Intermedio");
        buscaminasTest.setNivel(Nivel.INTERMEDIO);
        if (buscaminasTest.getAltoVentana() != 416) {
            fail("La ventana no es lo alto que debería");
        }
        if (buscaminasTest.getAnchoVentana() != 320) {
            fail("La ventana no es lo ancho que debería");
        }
        if (buscaminasTest.getCasillasPorColumna() != 16) {
            fail("No hay el numero de columnas que deberia");
        }
        if (buscaminasTest.getCasillasPorFila() != 16) {
            fail("No hay el numero de filas que deberia");
        }
        if (buscaminasTest.getCantidadCasillas() != 256) {
            fail("No hay el numero de casillas que deberia");
        }
        if (buscaminasTest.getNumeroMinas() != 70) {
            fail("No hay el numero de minas que deberia");
        }
        if (buscaminasTest.getBanderasRestantes() != 70) {
            fail("No hay el numero de banderas que deberia");
        }

        System.out.println("Experto");
        buscaminasTest.setNivel(Nivel.EXPERTO);
        if (buscaminasTest.getAltoVentana() != 520) {
            fail("La ventana no es lo alto que debería");
        }
        if (buscaminasTest.getAnchoVentana() != 400) {
            fail("La ventana no es lo ancho que debería");
        }
        if (buscaminasTest.getCasillasPorColumna() != 20) {
            fail("No hay el numero de columnas que deberia");
        }
        if (buscaminasTest.getCasillasPorFila() != 20) {
            fail("No hay el numero de filas que deberia");
        }
        if (buscaminasTest.getCantidadCasillas() != 400) {
            fail("No hay el numero de casillas que deberia");
        }
        if (buscaminasTest.getNumeroMinas() != 150) {
            fail("No hay el numero de minas que deberia");
        }
        if (buscaminasTest.getBanderasRestantes() != 150) {
            fail("No hay el numero de banderas que deberia");
        }
    }

    @Test
    public void testReiniciarNivel() {
        System.out.println("reiniciarNivel");
        buscaminasTest.setNivel(Nivel.PRINCIPIANTE);

        System.out.println("Reset para nivel principiante");
        Casilla casillasPrin[] = buscaminasTest.getCasillas();
        casillasPrin[5].isRevelado();
        buscaminasTest.setCasillas(casillasPrin);
        int fila = buscaminasTest.getCasillasPorFila();
        int columna = buscaminasTest.getCasillasPorColumna();
        buscaminasTest.reiniciarNivel();
        Casilla casillasDespuesReinicioPrin[] = buscaminasTest.getCasillas();
        if (buscaminasTest.getNivelActual() != Nivel.PRINCIPIANTE) {
            fail("El nivel ha cambiado");
        }
        if (fila != buscaminasTest.getCasillasPorFila()) {
            fail("El numero de filas ha cambiado");
        }
        if (columna != buscaminasTest.getCasillasPorColumna()) {
            fail("El numero de columnas ha cambiado");
        }
        if (casillasDespuesReinicioPrin[5].isRevelado() == true) {
            fail("No se han tapado las casillas");
        }

        System.out.println("Reset para nivel intermedio");
        buscaminasTest.setNivel(Nivel.INTERMEDIO);
        Casilla casillasInt[] = buscaminasTest.getCasillas();
        casillasInt[5].isRevelado();
        buscaminasTest.setCasillas(casillasInt);
        fila = buscaminasTest.getCasillasPorFila();
        columna = buscaminasTest.getCasillasPorColumna();
        buscaminasTest.reiniciarNivel();
        Casilla casillasDespuesReinicioInt[] = buscaminasTest.getCasillas();
        if (buscaminasTest.getNivelActual() != Nivel.INTERMEDIO) {
            fail("El nivel ha cambiado");
        }
        if (fila != buscaminasTest.getCasillasPorFila()) {
            fail("El numero de filas ha cambiado");
        }
        if (columna != buscaminasTest.getCasillasPorColumna()) {
            fail("El numero de columnas ha cambiado");
        }
        if (casillasDespuesReinicioInt[5].isRevelado() == true) {
            fail("No se han tapado las casillas");
        }

        System.out.println("Reset para nivel experto");
        buscaminasTest.setNivel(Nivel.EXPERTO);
        Casilla casillasExp[] = buscaminasTest.getCasillas();
        casillasExp[5].isRevelado();
        buscaminasTest.setCasillas(casillasExp);
        fila = buscaminasTest.getCasillasPorFila();
        columna = buscaminasTest.getCasillasPorColumna();
        buscaminasTest.reiniciarNivel();
        Casilla casillasDespuesReinicioExp[] = buscaminasTest.getCasillas();
        if (buscaminasTest.getNivelActual() != Nivel.EXPERTO) {
            fail("El nivel ha cambiado");
        }
        if (fila != buscaminasTest.getCasillasPorFila()) {
            fail("El numero de filas ha cambiado");
        }
        if (columna != buscaminasTest.getCasillasPorColumna()) {
            fail("El numero de columnas ha cambiado");
        }
        if (casillasDespuesReinicioExp[5].isRevelado() == true) {
            fail("No se han tapado las casillas");
        }
    }

    @Test
    public void testCompruebaGanador() {
        System.out.println("compruebaGanador");
        if (buscaminasTest.isHasGanado()) {
            fail("hasGanado iniciado a true");
        }
        buscaminasTest.ponerMinas(1);
        buscaminasTest.obtenerValorCasillas();
        buscaminasTest.setCasillasIniciadas(true);
        frameBuscaminas.iniciarPartida();
        Casilla[] casillas = buscaminasTest.getCasillas();

        for (int i = 0; i < casillas.length; i++) {
            if (!casillas[i].esMina()) {
                casillas[i].setRevelado(true);
            }

        }

        buscaminasTest.setCasillas(casillas);
        buscaminasTest.compruebaGanador();

        if (!buscaminasTest.isHasGanado()) {
            fail("No se ha ganado con el resultado correcto");
        }

        buscaminasTest.iniciarCasillas();

        buscaminasTest.ponerMinas(1);
        buscaminasTest.obtenerValorCasillas();
        buscaminasTest.setCasillasIniciadas(true);
        frameBuscaminas.iniciarPartida();
        casillas = buscaminasTest.getCasillas();

        for (int i = 0; i < casillas.length - 2; i++) {
            if (!casillas[i].esMina()) {
                casillas[i].setRevelado(true);
            }

        }

        buscaminasTest.setCasillas(casillas);
        buscaminasTest.compruebaGanador();

        assertTrue("Has ganado sin revelar todas las casillas", buscaminasTest.isHasGanado());
    }

    @Test
    public void testPonerQuitarBandera() {
        System.out.println("ponerQuitarBandera");
        int indiceClicado = 5;
        buscaminasTest.setNivel(Nivel.PRINCIPIANTE);
        Casilla casillas[] = buscaminasTest.getCasillas();
        System.out.println("Test de bandera puesta");
        casillas[5].setBandera(true);
        buscaminasTest.setBanderasRestantes(9);
        int banderasActuales = buscaminasTest.getBanderasRestantes();
        buscaminasTest.ponerQuitarBandera(indiceClicado);
        if (casillas[5].tieneBandera() == true) {
            fail("No ha quitado la bandera");
        }
        System.out.println(buscaminasTest.getBanderasRestantes());
        if (buscaminasTest.getBanderasRestantes() != banderasActuales + 1) {
            fail("No ha aumentado las banderas");
        }

        System.out.println("Test de no hay bandera");
        casillas[6].setBandera(false);
        buscaminasTest.setBanderasRestantes(9);
        indiceClicado = 6;
        banderasActuales = buscaminasTest.getBanderasRestantes();
        buscaminasTest.ponerQuitarBandera(indiceClicado);
        if (casillas[6].tieneBandera() == false) {
            fail("no ha puesto la bandera");
        }
        if (buscaminasTest.getBanderasRestantes() != banderasActuales - 1) {
            fail("No ha restado las banderas");
        }

        System.out.println("Test de banderas restantes 0");
        casillas[7].setBandera(false);
        indiceClicado = 7;
        buscaminasTest.setBanderasRestantes(0);
        buscaminasTest.ponerQuitarBandera(indiceClicado);
        if (casillas[7].tieneBandera() == true) {
            fail("ha puesto la bandera con banderas = 0");
        }
        if (buscaminasTest.getBanderasRestantes() != 0) {
            fail("ha sumado una bandera, o la ha restado");
        }
    }

    @Test
    public void testRevelarCasilla() {

        System.out.println("revelarCasilla");
        int indiceClicado = 0;
        buscaminasTest.setNivel(Nivel.PRINCIPIANTE);
        frameBuscaminas.iniciarPartida();
        Casilla[] casillas = buscaminasTest.getCasillas();
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

        buscaminasTest.setCasillas(casillas);
        buscaminasTest.revelarCasilla(indiceClicado);
        casillas = buscaminasTest.getCasillas();
        if (casillas[0].isRevelado() == false) {
            fail("No se ha revelado");
        }
        if (casillas[1].isRevelado() == false) {
            fail("No se ha revelado");
        }
        if (casillas[10].isRevelado() == false) {
            fail("No se ha revelado");
        }
        if (casillas[11].isRevelado() == false) {
            fail("No se ha revelado");
        }
        if (casillas[2].isRevelado() == false) {
            fail("No Se ha revelado");
        }
        if (casillas[12].isRevelado() == false) {
            fail("No Se ha revelado");
        }
        if (casillas[22].isRevelado() == false) {
            fail("No Se ha revelado");
        }
        if (casillas[20].isRevelado() == false) {
            fail("No Se ha revelado");
        }
        if (casillas[21].isRevelado() == false) {
            fail("No Se ha revelado");
        }
        if (casillas[13].isRevelado() == true) {
            fail("Se ha revelado");
        }
        if (casillas[3].isRevelado() == true) {
            fail("Se ha revelado");
        }
        if (casillas[23].isRevelado() == true) {
            fail("Se ha revelado");
        }

        System.out.println("test de bandera sin mina");
        casillas[13].setValor(2);
        buscaminasTest.ponerQuitarBandera(13);
        buscaminasTest.setCasillas(casillas);
        buscaminasTest.setBanderasRestantes(9);
        int banderasActuales = buscaminasTest.getBanderasRestantes();
        buscaminasTest.revelarCasilla(13);
        casillas = buscaminasTest.getCasillas();
        if (buscaminasTest.getBanderasRestantes() != banderasActuales + 1) {
            fail("no ha sumado las banderas");
        }
        if (casillas[13].isRevelado() == false) {
            fail("no se ha revelado la casilla");
        }

        System.out.println("Test de revelar casilla mina");
        casillas[14].setValor(-1);
        casillas[15].setValor(-1);
        buscaminasTest.setCasillas(casillas);
        buscaminasTest.revelarCasilla(14);
        casillas = buscaminasTest.getCasillas();
        if (buscaminasTest.isHasPerdido() == false) {
            fail("no has perdido");
        }
        if (casillas[14].isRevelado() == false) {
            fail("no se han revelado todas las minas");
        }
        if (casillas[15].isRevelado() == false) {
            fail("no se han revelado todas las minas");
        }
    }

    @Test
    public void testObtenerValorCasillas() {
        System.out.println("obtenerValorCasillas");
        buscaminasTest.setNivel(Nivel.PRINCIPIANTE);
        Casilla[] casillas = buscaminasTest.getCasillas();
        casillas[0].setValor(-1);
        buscaminasTest.setCasillas(casillas);
        buscaminasTest.obtenerValorCasillas();
        casillas = buscaminasTest.getCasillas();
        if (casillas[1].getValor() != 1) {
            fail("No ha puesto bien el valor");
        }
        if (casillas[10].getValor() != 1) {
            fail("No ha puesto bien el valor");
        }
        if (casillas[11].getValor() != 1) {
            fail("No ha puesto bien el valor");
        }

    }

    @Test
    public void testBuscarIndiceCasilla() {
        System.out.println("buscarIndiceCasilla");
        buscaminasTest.setNivel(Nivel.PRINCIPIANTE);

        Casilla[] casillas = buscaminasTest.getCasillas();

        int fila = 1;
        int columna = 5;
        int result = buscaminasTest.buscarIndiceCasilla(fila, columna);
        assertEquals("No ha encontrado la casilla correcta", casillas[15], casillas[result]);
        assertFalse("No ha encontrado la casila", result == -1);
        fila = 5;
        columna = 5;
        result = buscaminasTest.buscarIndiceCasilla(fila, columna);
        assertEquals("No ha encontrado la casilla correcta", casillas[55], casillas[result]);
        assertFalse("No ha encontrado la casila", result == -1);

    }

    @Test
    public void testPonerMinas() {

        Casilla[] casillas = buscaminasTest.getCasillas();

        for (int i = 0; i < buscaminasTest.getNumeroMinas(); i++) {
            assertTrue("Hay minas antes del metodo", casillas[i].esMina());
        }
        buscaminasTest.setNivel(Nivel.PRINCIPIANTE);
        buscaminasTest.ponerMinas(1);

        casillas = buscaminasTest.getCasillas();

        int mina = 0;
        for (int i = 0; i < buscaminasTest.getCantidadCasillas(); i++) {
            if (casillas[i].esMina()) {
                mina++;
            }
        }
        assertTrue("No hay el numero de minas que deberia", mina == buscaminasTest.getNumeroMinas());

    }

    @Test
    public void testDeshacer() {
        System.out.println("deshacer");
        buscaminasTest.setNivel(Nivel.PRINCIPIANTE);
        buscaminasTest.ponerMinas(1);
        buscaminasTest.obtenerValorCasillas();

        Casilla[] casillas = buscaminasTest.getCasillas();

        casillas[2].setValor(2);
        casillas[31].setValor(3);

        buscaminasTest.setCasillas(casillas);

        buscaminasTest.revelarCasilla(2);
        buscaminasTest.ponerQuitarBandera(31);

        buscaminasTest.deshacer();
        assertTrue("Error 1: no ha deshecho la bandera", !casillas[31].tieneBandera());
        buscaminasTest.deshacer();
        assertTrue("Error 2: no ha deshecho el revelado", !casillas[2].isRevelado());
    }

    @Test
    public void testExpandirDFS() {
        System.out.println("test expandirDFS");
        int indiceClicado = 0;
        buscaminasTest.setNivel(Nivel.PRINCIPIANTE);
        frameBuscaminas.iniciarPartida();
        Casilla[] casillas = buscaminasTest.getCasillas();
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

        buscaminasTest.setCasillas(casillas);
        buscaminasTest.revelarCasilla(indiceClicado);
        casillas = buscaminasTest.getCasillas();
        if (casillas[0].isRevelado() == false) {
            fail("No se ha revelado");
        }
        if (casillas[1].isRevelado() == false) {
            fail("No se ha revelado");
        }
        if (casillas[10].isRevelado() == false) {
            fail("No se ha revelado");
        }
        if (casillas[11].isRevelado() == false) {
            fail("No se ha revelado");
        }
        if (casillas[2].isRevelado() == false) {
            fail("No Se ha revelado");
        }
        if (casillas[12].isRevelado() == false) {
            fail("No Se ha revelado");
        }
        if (casillas[22].isRevelado() == false) {
            fail("No Se ha revelado");
        }
        if (casillas[20].isRevelado() == false) {
            fail("No Se ha revelado");
        }
        if (casillas[21].isRevelado() == false) {
            fail("No Se ha revelado");
        }
        if (casillas[13].isRevelado() == true) {
            fail("Se ha revelado");
        }
        if (casillas[3].isRevelado() == true) {
            fail("Se ha revelado");
        }
        if (casillas[23].isRevelado() == true) {
            fail("Se ha revelado");
        }

    }
}
