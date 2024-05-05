package prog2.adaptador;

import prog2.model.BombaRefrigerant;
import prog2.model.Dades;
import prog2.model.PaginaIncidencies;
import prog2.model.SistemaRefrigeracio;
import prog2.vista.CentralUB;
import prog2.vista.CentralUBException;

import java.io.*;
import java.util.List;

public class Adaptador implements Serializable {
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

    // GUARDAR DADES
    public void guardaDades(String camiDesti) throws CentralUBException {
        // Creamos el archivo con la ruta especificada
        File fitxer = new File(camiDesti);

        try {
            // Abrimos un FileOutputStream para escribir en el archivo
            FileOutputStream fout = new FileOutputStream(fitxer);

            // Creamos un ObjectOutputStream
            ObjectOutputStream oos = new ObjectOutputStream(fout);

            // Cargamos el objeto en el archivo
            oos.writeObject(dades.mostraBitacola());

            // Cerramos el ObjectOutputStream
            oos.close();

            // Cerramos el FileOutputStream
            fout.close();
        } catch (FileNotFoundException e) {
            // Si el archivo no existe
            throw new CentralUBException("Archivo no encontrado: " + camiDesti);
        } catch (IOException e) {
            // Si hay un problema con la escritura
            throw new CentralUBException("Error de escritura al escribir en el archivo: " + e.getMessage());
        }
    }


    // CARREGAR DADES
    public void carregaDades(String camiOrigen)throws CentralUBException {
        // Asignamos a fitxer el archivo del camiOrigen
        File fitxer = new File(camiOrigen);

        try{
            // Abrimos un FileInputStream
            FileInputStream fin = new FileInputStream(fitxer);

            // Creamos un ObjectInputStream
            ObjectInputStream oos = new ObjectInputStream(fin);

            // Leemos el objeto desde el archivo
            Object objecte = oos.readObject();

            if(objecte instanceof CentralUB){
                CentralUB central = (CentralUB) objecte;
            }
            else{
                throw new CentralUBException("El objeto no es del tipo esperado");
            }
            // Cerramos el ObjectInputStream
            oos.close();
        } catch(FileNotFoundException e){
            throw new CentralUBException("Archivo no encontrado: " + camiOrigen);
        }catch (IOException | ClassNotFoundException e) {
            throw new CentralUBException("Error de lectura al leer el archivo: " + e.getMessage());
        }
    }
}
