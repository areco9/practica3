package prog2.model;

import prog2.vista.CentralUBException;

public class Reactor implements InComponent{

    private boolean activada;
    private float temperatura;

    public Reactor() {
        this.activada = false;
        this.temperatura = 30;
    }

    @java.lang.Override
    public void activa() throws CentralUBException {
        if (this.temperatura > 1000) {
            throw new CentralUBException("El reactor está demasiado caliente, no puede ser acivado.");
        }
        this.activada = true;
    }

    @java.lang.Override
    public void desactiva() {
        this.activada = false;
    }

    @java.lang.Override
    public void revisa(PaginaIncidencies p) {
        // Si detectamos que la temperatura es superior a 1000, desactivamos
        // el reactor y generamos una incidencia
        if (this.temperatura > 1000) {
            this.activada = false;
            p.afegeixIncidencia("El reactor es va desactivar per " +
                    "superar la temperatura màxima");
        }
    }

    // El reactor tiene un coste operativo de 30
    @java.lang.Override
    public float getCostOperatiu() {
        if(activada){ return 30;}
        else{ return 0;}
    }

    // Calculamos el output según si el reactor está activado o no
    @java.lang.Override
    public float calculaOutput(float input) {
        if (this.activada) {
            return this.temperatura + (100-input)*100;
        } else {
            return this.temperatura;
        }
    }

    // Getter & setter temperatura
    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public boolean isActivada(){return activada;}

    public void setActivada(boolean activada){
        this.activada = activada;
    }

}
