package prog2.model;

import prog2.vista.CentralUBException;

import java.util.ArrayList;

public class SistemaRefrigeracio implements InComponent {
    private boolean activat;
    private ArrayList<BombaRefrigerant> llistaBombes;

    public SistemaRefrigeracio() {
        this.activat = false;
        this.llistaBombes = new ArrayList<BombaRefrigerant>();
    }

    // Llamamos al método activa de todas las bombas
    @Override
    public void activa() throws CentralUBException {
        for (BombaRefrigerant bomba : this.llistaBombes) {
            bomba.activa();
        }
    }

    // Llamamos al método desactiva de todas las bombas
    @Override
    public void desactiva() {
        for (BombaRefrigerant bomba : this.llistaBombes) {
            bomba.desactiva();
        }
    }

    @Override
    public void revisa(PaginaIncidencies p) {
        for (BombaRefrigerant bomba : this.llistaBombes) {
            bomba.revisa(p);
        }
    }

    @Override
    public float getCostOperatiu() {
        int n = 0;
        // Vemos cuantas bombas están en servicio
        for (BombaRefrigerant bomba : this.llistaBombes) {
            if (bomba.getActivat()) {
                n += 1;
            }
        }
        return 125*n;
    }

    // Calculamos el output como el mínimo entre el input y 250 * nº de bombas
    @Override
    public float calculaOutput(float input) {
        int n = 0;
        // Vemos cuantas bombas están en servicio
        for (BombaRefrigerant bomba : this.llistaBombes) {
            if (bomba.getActivat()) {
                n += 1;
            }
        }
        if (250*n < input) {
            return 250*n;
        } else {
            return input;
        }
    }

    // Añadimos método para añadir bombas a la lista
    public void afegirBomba(BombaRefrigerant bomba) {
        llistaBombes.add(bomba);
    }


    // Getters & Setters
    public boolean isActivat() {
        return activat;
    }

    public void setActivat(boolean activat) {
        this.activat = activat;
    }

    public ArrayList<BombaRefrigerant> getLlistaBombes() {
        return llistaBombes;
    }

    public void setLlistaBombes(ArrayList<BombaRefrigerant> llistaBombes) {
        this.llistaBombes = llistaBombes;
    }
}
