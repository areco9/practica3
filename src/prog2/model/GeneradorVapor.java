package prog2.model;

import prog2.vista.CentralUBException;

public class GeneradorVapor implements InComponent{

    private boolean activat;

    public GeneradorVapor() {
        this.activat = true;
    }

    // En principio no hay restricciones para activar o desactivar el generador
    @java.lang.Override
    public void activa() throws CentralUBException {
        this.activat = true;
    }

    @java.lang.Override
    public void desactiva() {
        this.activat = false;
    }

    // Si se llama al metodo revisa y el generador no está activado escribimos
    // una incidencia
    @java.lang.Override
    public void revisa(PaginaIncidencies p) {
        if (this.activat) {
            return;
        } else {
            p.afegeixIncidencia("El generador está desactivado");
        }
    }

    // Tiene coste operativo constante de 25
    @java.lang.Override
    public float getCostOperatiu() {
        return 25;
    }

    // Calculamos el output según lo indicado en el enunciado
    @java.lang.Override
    public float calculaOutput(float input) {
        if (this.activat) {
            return (float) (input*0.8);
        }
        else {
            return 30;
        }
    }
}
