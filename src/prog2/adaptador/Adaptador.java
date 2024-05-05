package prog2.adaptador;
import prog2.model.*;
import prog2.vista.CentralUBException;

import java.util.List;

public class Adaptador {
    public Dades dades;

    public Adaptador() throws CentralUBException {
        this.dades = new Dades();
    }
    /*
     En la clase Adaptador debemos poner los métodos que nos hagan directamente las funcionalidades que nos piden
    */


    // GESTIÓN BARRAS DE CONTROL

    //Obtenir inserció barres
    public float getInsercioBarres_(){
        return dades.getInsercioBarres();
    }
    // Establecer Inserció Barres
    public void setInsercioBarres_(float insercioBarres) throws CentralUBException {
        dades.setInsercioBarres(insercioBarres);
    }


    // GESTIÓN REACTOR

    // Activar Reactor
    public void activarReactor() throws CentralUBException{
        dades.activaReactor();
    }
    // Desactivar Reactor
    public void desactivarReactor(){
        dades.desactivaReactor();
    }
    // Mostrar estado del reactor
    public String mostrarEstatReactor() {
        return "ESTAT REACTOR -> Activat: " + dades.mostraReactor().isActivada() +
                ". Temperatura: " + dades.mostraReactor().getTemperatura() + " graus";
    }


    // GESTIÓN SISTEMA REFRIGERACIÓN

    // Activar Bomba
    public void activarBomba(int id) throws CentralUBException{
        dades.activaBomba(id);
    }
    // Desactivar Bomba
    public void desactivarBomba (int id){
        dades.desactivaBomba(id);
    }
    // Mostrar estado de las Bombas
    public String mostrarEstatBombes(){
        SistemaRefrigeracio sistemaRefrigeracioEstacio = dades.mostraSistemaRefrigeracio();
        StringBuilder s = new StringBuilder();
        for(BombaRefrigerant bomba : sistemaRefrigeracioEstacio.getLlistaBombes()){
            s.append("BOMBA " + bomba.getId() + " -> Activada: " + bomba.getActivat() +
                    ". Fora de servei: " + bomba.getForaDeServei() + "\n");
        }
        return s.toString();
    }

    // HAY QUE CORREGIRLA ESTA
    // MOSTRAR ESTADO CENTRAL
    public String estatCentral(float demandaPotencia){
        return dades.mostraEstat(demandaPotencia).toString();
    }

    // MOSTRAR TODA LA INFORMACIÓN DE LA BITACOLA
    public String mostrarBitacola(){
        return dades.mostraBitacola().toString();
    }

    // MOSTRAR LISTA DE INCIDENCIAS
    public String incidenciesCentral(){
        StringBuilder s = new StringBuilder();
        // Tomamos la lista de incidencias
        List<PaginaIncidencies> incidencies = dades.mostraBitacola().getIncidencies();

        // La vamos recorriendo y usamos el método toString para generar la lista de todas las incidencias
        for (PaginaIncidencies incidencia : incidencies) {
            s.append(incidencia.toString() + "\n");
        }
        return s.toString();
    }

    // FINALIZAR DIA
    public String finalitzaDia(float demandaPotencia) {
        return this.dades.finalitzaDia(demandaPotencia).toString();
    }
}
