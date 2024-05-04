package prog2.adaptador;
import prog2.model.*;
import prog2.vista.CentralUBException;

public class Adaptador {
    public Dades dades;

    public Adaptador() throws CentralUBException {
        this.dades = new Dades();
    }
    /*
     En la clase Adaptador debemos poner los métodos que nos hagan directamente las funcionalidades que nos piden
    */

    //Obtenir inserció barres
    public float getInsercioBarres_(){
        return dades.getInsercioBarres();
    }

    // Establecer Inserció Barres
    public void setInsercioBarres_(float insercioBarres) throws CentralUBException{
        dades.setInsercioBarres(insercioBarres);
    }

    // Activar Reactor
    public void ActivarReactor() throws CentralUBException{
        dades.activaReactor();
    }

    public void DesactivarReactor(){
        dades.desactivaReactor();
    }

}
