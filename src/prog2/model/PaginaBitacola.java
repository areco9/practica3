package prog2.model;

public abstract class PaginaBitacola {
    private int dia;

    public PaginaBitacola(int dia) {
        this.dia = dia;
    }

    // Getter & setter
    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }
}
