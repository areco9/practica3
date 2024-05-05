package prog2.model;

import prog2.vista.CentralUBException;

public class BombaRefrigerant implements InBombaRefrigerant{
    private int id;
    private boolean activada;
    private boolean foraDeServei;
    private VariableUniforme variableUniforme;

    public BombaRefrigerant(int id, VariableUniforme variableUniforme) {
        this.id = id;
        this.activada = true;
        this.foraDeServei = false;
        this.variableUniforme = variableUniforme;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void activa() throws CentralUBException {
        if (this.foraDeServei) {
            throw new CentralUBException("La bomba está fuera de servicio, no puede ser acivada.");
        }
        this.activada = true;
    }

    @Override
    public void desactiva() {
        this.activada = false;
    }

    @Override
    public boolean getActivat() {
        return this.activada;
    }

    @Override
    public void revisa(PaginaIncidencies p) {
        // Recuperamos las bombas que estaban fuera de servicio el día anterior
        this.foraDeServei = false;
        // Generamos un entero
        int n = this.variableUniforme.seguentValor();
        // Si este número es mayor que 100 devolvemos true
        if (n > 80) {
            this.foraDeServei = true;
            this.activada = false;
            p.afegeixIncidencia("La bomba de refrigeració " + this.getId()
                + " està fora de servei");
        }
    }

    @Override
    public boolean getForaDeServei() {
        return this.foraDeServei;
    }

    public String toString() {
        return "Id=" + getId() + ", Activitat=" + getActivat() + ", ForaDeServei=" + getForaDeServei();
    }
}
