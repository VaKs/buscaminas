package Presentacion;

public class CasillaVacia extends Casilla {

    public CasillaVacia(int fila, int columna, int valor) {
        super(fila,columna);
        this.setValor(valor);
    }

    @Override
    public void revelar() {
        this.setRevelado(true);
        this.setBandera(false);
    }

}
