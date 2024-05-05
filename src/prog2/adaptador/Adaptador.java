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
    public void ActivarReactor() throws CentralUBException{
        dades.activaReactor();
    }
    // Desactivar Reactor
    public void DesactivarReactor(){
        dades.desactivaReactor();
    }


    // GESTIÓN SISTEMA REFRIGERACIÓN

    // Activar Bomba
    public void ActivarBomba(int id) throws CentralUBException{
        dades.activaBomba(id);
    }
    // Desactivar Bomba
    public void DesactivarBomba (int id){
        dades.desactivaBomba(id);
    }
    // Mostrar estado de las Bombas
    public String MostrarEstatBombes(){
        SistemaRefrigeracio sistemaRefrigeracioEstacio = dades.mostraSistemaRefrigeracio();
        String s = "";
        for(BombaRefrigerant bomba : sistemaRefrigeracioEstacio.getLlistaBombes()){
            s += bomba.toString() + '\n';
        }
        return s;
    }

    // HAY QUE CORREGIRLA ESTA
    // MOSTRAR ESTADO CENTRAL
    public String EstatCentral(float demandaPotencia){
        PaginaEstat estadoCentral = dades.mostraEstat(demandaPotencia);
        return estadoCentral.toString();
    }

    // Mostrar toda la información de la Bitacola
    public void MostrarBitacola(){
        Bitacola bitacolaCentral = dades.mostraBitacola();


    }

    //Muestra la lista de incidencias
    public String IncidenciesCentral(){
        String s = "";
        List<PaginaIncidencies> listaIncidencies = dades.mostraIncidencies();
        for(PaginaIncidencies pagina : listaIncidencies){
            s += pagina.toString() + "\n";
        }
        return s;
    }
}
