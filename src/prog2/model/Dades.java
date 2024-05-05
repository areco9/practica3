/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.model;

import prog2.vista.CentralUBException;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dortiz
 */
public class Dades implements InDades {
    public final static long  VAR_UNIF_SEED = 123;
    public final static float GUANYS_INICIALS = 0;
    public final static float PREU_UNITAT_POTENCIA = 1;
    public final static float PENALITZACIO_EXCES_POTENCIA = 200;
    private Reactor reactor;
    private SistemaRefrigeracio sistemaRefrigeracio;
    private GeneradorVapor generadorVapor;
    private Turbina turbina;
    private Bitacola bitacola;
    private int dia;
    private float guanysAcumulats;
    private VariableUniforme variableUniforme;
    private float insercioBarres;

    // Afegir atributs:

    public Dades() throws CentralUBException {
        // Inicialitza Atributs
        this.variableUniforme = new VariableUniforme(VAR_UNIF_SEED);
        this.insercioBarres = 100;
        this.reactor = new Reactor();
        this.sistemaRefrigeracio = new SistemaRefrigeracio();
        this.generadorVapor = new GeneradorVapor();
        this.generadorVapor.activa();
        this.turbina = new Turbina();
        this.turbina.activa();
        this.bitacola = new Bitacola();
        this.dia = 1;
        this.guanysAcumulats = GUANYS_INICIALS;

        // Crea bombes refrigerants
        BombaRefrigerant b0 = new BombaRefrigerant(0, variableUniforme);
        BombaRefrigerant b1 = new BombaRefrigerant(1, variableUniforme);
        BombaRefrigerant b2 = new BombaRefrigerant(2, variableUniforme);
        BombaRefrigerant b3 = new BombaRefrigerant(3, variableUniforme);

        // Afegeix bombes refrigerants
        this.sistemaRefrigeracio.afegirBomba(b0);
        this.sistemaRefrigeracio.afegirBomba(b1);
        this.sistemaRefrigeracio.afegirBomba(b2);
        this.sistemaRefrigeracio.afegirBomba(b3);
    }

    /**
     * Actualitza l'economia de la central. Genera una pàgina econòmica a
     * partir de la demanda de potencia actual. Aquesta pàgina econòmica inclou
     * beneficis, penalització per excès de potència, costos operatius y
     * guanys acumulats.
     * @param demandaPotencia Demanda de potència actual.
     *
    public PaginaEconomica(int dia, float beneficis, float exesPotencia, float costosOperatius, float guanysAcumulats) {
     *
     */
    private PaginaEconomica actualitzaEconomia(float demandaPotencia){
        // Calculamos los costes operativos
        float costeOperativo = 0;
        costeOperativo += reactor.getCostOperatiu();
        costeOperativo += turbina.getCostOperatiu();
        costeOperativo += sistemaRefrigeracio.getCostOperatiu();

        // En cualquier caso, tenemos que pasar el beneficio según al potencia hecha
        // Si nos pasamos de demandapotencia, entonces penalitzacio = PENALITZACIO_EXCES_POTENCIA
        float output;
        output = calculaPotencia();
        PaginaEconomica economiaCentral = null;

        if(demandaPotencia < output){
            economiaCentral = new PaginaEconomica(dia, output , PENALITZACIO_EXCES_POTENCIA, costeOperativo, guanysAcumulats);
        }
        else{
            economiaCentral = new PaginaEconomica(dia, output , 0, costeOperativo, guanysAcumulats);
        }
        return economiaCentral;
    }

    /**
     * Actualitza l'estat de la central. El mètodo ha de establir la nova
     * temperatura del reactor i revisar els components de la central. Si
     * es troben incidències, s'han de registrar en la pàgina d'incidències
     * que es proporciona com a paràmetre d'entrada.
     * @param paginaIncidencies Pàgina d'incidències.
     */
    private void actualitzaEstatCentral(PaginaIncidencies paginaIncidencies) {
        // ACTUALIZAR TEMPERATURA
        // Primero miramos cuál es la temperatura del reactor y cuánta quita el sistema de refrigeración
        float temperaturaReactor = this.reactor.calculaOutput(this.insercioBarres);
        float temperaturaExtreta = this.sistemaRefrigeracio.calculaOutput(temperaturaReactor);
        // Calculamos la nueva temperatura
        temperaturaReactor = temperaturaReactor-temperaturaExtreta;
        // Comprovamos que no sea menor a 30 y actualizamos
        if (temperaturaReactor > 30) {
            this.reactor.setTemperatura(temperaturaReactor);
        } else {
            this.reactor.setTemperatura(30);
        }

        // REVISAR COMPONENTES
        this.reactor.revisa(paginaIncidencies);
        this.sistemaRefrigeracio.revisa(paginaIncidencies);
        this.generadorVapor.revisa(paginaIncidencies);
        this.turbina.revisa(paginaIncidencies);
    }

    @Override
    public float getInsercioBarres() {
        return this.insercioBarres;
    }

    @Override
    public void setInsercioBarres(float insercioBarres) throws CentralUBException {
        this.insercioBarres = insercioBarres;
    }

    // Llamamos al método activa del reactor
    @Override
    public void activaReactor() throws CentralUBException {
        this.reactor.activa();
    }

    // Llamamos al método desactiva del reactor
    @Override
    public void desactivaReactor() {
        this.reactor.desactiva();
    }

    @Override
    public Reactor mostraReactor() {
        return this.reactor;
    }

    @Override
    public void activaBomba(int id) throws CentralUBException {
        BombaRefrigerant bombaRefrigerant = null;
        // Buscamos la bomba que se corresponde con el identificador introducido
        for (BombaRefrigerant bomba : this.sistemaRefrigeracio.getLlistaBombes()) {
            if (bomba.getId() == id) {
                bombaRefrigerant = bomba;
                break;
            }
        }
        // Consideremos el caso de que no se haya encontrado una bomba con el identificador proporcionado,
        // entonces no hacemos nada
        if (bombaRefrigerant == null) {
            return;
        }
        // Activamos la bomba con el método activa (esto podría generar una excepción)
        bombaRefrigerant.activa();;
    }

    @Override
    public void desactivaBomba(int id) {
        BombaRefrigerant bombaRefrigerant = null;
        // Buscamos la bomba con el Id proporcionado
        for (BombaRefrigerant bomba : this.sistemaRefrigeracio.getLlistaBombes()) {
            if (bomba.getId() == id) {
                bombaRefrigerant = bomba;
                break;
            }
        }
        // En el caso de que no hayamos encontrado una bomba con ese Id no hacemos nada
        if (bombaRefrigerant == null) {
            return;
        }
        // En el caso de que la hayamos encontrado la desactivamos
        bombaRefrigerant.desactiva();;
    }

    @Override
    public SistemaRefrigeracio mostraSistemaRefrigeracio() {
        return this.sistemaRefrigeracio;
    }

    @Override
    public float calculaPotencia() {
        // Calculamos el output de las componentes de la central
        float outputReactor = reactor.calculaOutput(this.getInsercioBarres());
        float outputSistema = sistemaRefrigeracio.calculaOutput(outputReactor);
        float outputGenerador = generadorVapor.calculaOutput(outputSistema);

        // Devolvemos el output de la turbina
        return turbina.calculaOutput(outputGenerador);
    }

    @Override
    public PaginaEstat mostraEstat(float demandaPotencia) {
        // Calculamos los ouputs de las diferentes componentes de la central
        float outputReactor = reactor.calculaOutput(this.getInsercioBarres());
        float outputSistema = sistemaRefrigeracio.calculaOutput(outputReactor);
        float outputGenerador = generadorVapor.calculaOutput(outputSistema);
        float outputTurbina = turbina.calculaOutput(outputGenerador);

        // Calculamos el porcentaje de demanda que hemos logrado
        float demandaSatisfeta = outputTurbina/demandaPotencia * 100;

        // Devolvemos una página con toda la info
        return new PaginaEstat(this.dia, demandaPotencia, getInsercioBarres(), outputReactor, outputSistema, outputGenerador, outputTurbina, demandaSatisfeta);
    }

    // Retorna la bitacola de la central
    @Override
    public Bitacola mostraBitacola() {
        return bitacola;
    }

    // Retorna una llista amb totes les pàgines d'incidències de la bitàcola de la central
    @Override
    public List<PaginaIncidencies> mostraIncidencies() {
        List<PaginaIncidencies> llistaIncidencies = new ArrayList<>();
        llistaIncidencies = bitacola.getIncidencies();
        return llistaIncidencies;
    }

    public Bitacola finalitzaDia(float demandaPotencia) {
        // Actualitza economia
        PaginaEconomica paginaEconomica = actualitzaEconomia(demandaPotencia);

        // Genera pàgina d'estat
        PaginaEstat paginaEstat = mostraEstat(demandaPotencia);

        // Actualitza estat central i registra incidencies
        PaginaIncidencies paginaIncidencies = new PaginaIncidencies(dia);
        actualitzaEstatCentral(paginaIncidencies);


        // Incrementa dia
        dia += 1;

        // Guarda pàgines de bitacola
        bitacola.afegeixPagina(paginaEconomica);
        bitacola.afegeixPagina(paginaEstat);
        bitacola.afegeixPagina(paginaIncidencies);

        // Retorna pàgines
        Bitacola bitacolaDia = new Bitacola();
        bitacolaDia.afegeixPagina(paginaEconomica);
        bitacolaDia.afegeixPagina(paginaEstat);
        bitacolaDia.afegeixPagina(paginaIncidencies);
        return bitacolaDia;
    }
}
