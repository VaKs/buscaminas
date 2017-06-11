package Presentacion;

public class CasillaNumero extends Casilla {

    public CasillaNumero(int fila, int columna, int valor) {
        super(fila,columna);
        this.setValor(valor);
    }

    @Override
    public void revelar() {
        this.setRevelado(true);
        this.setBandera(false);
    }

}
