package prog2.model;

import prog2.vista.CentralUBException;

public class Turbina implements InComponent{

    private boolean activat;

    public Turbina() {
        this.activat = true;
    }

    // En principio no hay restricciones para activar o desactivar la turbina
    @java.lang.Override
    public void activa() throws CentralUBException {
        this.activat = true;
    }

    @java.lang.Override
    public void desactiva() {
        this.activat = false;
    }

    // Si encontramos que la turbina está desactivada, generamos una incidencia.
    @java.lang.Override
    public void revisa(PaginaIncidencies p) {
        if (this.activat) {
            return;
        } else {
            p.afegeixIncidencia("La turbina está desactivada");
        }
    }

    // Tiene un coste operativo de 20
    @java.lang.Override
    public float getCostOperatiu() {

        if (activat){ return 20;}
        else{ return 0;}
    }

    // Calculamos output según como se indica en el enunciado
    @java.lang.Override
    public float calculaOutput(float input) {
        if (this.activat && input >= 100) {
            return input*2;
        }
        return 0;
    }

    //GETTERS & SETTERS
    public boolean isActivat(){return activat;}

    public void setActivat(boolean activat){
        this.activat = activat;
    }
}
