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
        return 125*llistaBombes.size();
    }

    // Calculamos el output como el mínimo entre el input y 250 * nº de bombas
    @Override
    public float calculaOutput(float input) {
        int x = 250 * llistaBombes.size();
        if (input < x) {
            return input;
        } else {
            return x;
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
