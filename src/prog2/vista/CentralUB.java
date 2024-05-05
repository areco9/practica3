/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.vista;

import prog2.adaptador.Adaptador;
import prog2.model.VariableNormal;

import java.util.Scanner;

/**
 *
 * @author dortiz
 */
public class CentralUB {
    public final static float DEMANDA_MAX = 1600;
    public final static float DEMANDA_MIN = 200;
    public final static float VAR_NORM_MEAN = 1000;
    public final static float VAR_NORM_STD = 600;
    public final static long VAR_NORM_SEED = 150;
    
    /** Generador aleatori de la demanda de potència **/
    private VariableNormal variableNormal;
    
    /** Demanda de potència del dia actual **/
    private float demandaPotencia;
    private Adaptador adaptador;

    // ENUMS PARA LAS OPCIONES DEL MENU
    static private enum OpcionsMenuPrincipal {OPCIONS_BARRES, OPCIONS_REACTOR, OPCIONS_REFRIGERACIO, MOSTRAR_ESTAT_CENTRAL, MOSRAR_BITACOLA, MOSTRAR_INCIDENCIES, FINALITZAR_DIA, GUARDAR_DADES, CARREGA_DADES, SORTIR};
    static private enum OpcionsMenuBarres {OBTENIR_INSERCIO, ESTABLIR_INSERCIO, SORTIR};
    static private enum OpcionsMenuReactor {ACTIVAR_REACTOR, DESACTIVAR_REACTOR, MOSTRAR_ESTAT, SORTIR};
    static private enum OpcionsMenuBombes {ACTIVAR_BOMBA, DESACTIVAR_BOMBA, MOSTRAR_ESTAT, SORTIR};

    // Listas de Strings para las descripciones de los menús
    String[] descripcionesMenuPrincipal = {
            "Gestión de las Barras de Control: Accede a opciones para obtener o establecer la inserción de las barras.",
            "Gestión del Reactor: Accede a opciones para activar o desactivar el reactor, y mostrar su estado.",
            "Gestión del Sistema de Refrigeración: Permite controlar las bombas refrigerantes y mostrar su estado.",
            "Mostrar Estado Central: Muestra el estado actual de la central nuclear.",
            "Mostrar Bitácora: Muestra todo el contenido de la bitácora hasta el día actual.",
            "Mostrar Incidencias: Muestra todas las páginas de incidencias registradas en la bitácora hasta el día actual.",
            "Finalizar el Día: Ejecuta todas las acciones necesarias al finalizar el día, como guardar cambios y actualizar estados.",
            "Guardar Datos: Guarda los datos actuales de la aplicación en el sistema de almacenamiento.",
            "Cargar Datos: Carga los datos previamente guardados de la aplicación.",
            "Salir: Salimos de la aplicación."
    };

    String[] descripcionesMenuBarres = {
            "Obtener Inserción de Barras: Muestra la inserción actual de las barras de control.",
            "Establecer Inserción de Barras: Permite al usuario establecer un nuevo nivel de inserción para las barras de control.",
            "Salir: Regresa al menú principal."
    };

    String[] descripcionesMenuReactor = {
            "Activar Reactor: Permite activar el reactor para iniciar operaciones.",
            "Desactivar Reactor: Permite desactivar el reactor para detener operaciones.",
            "Mostrar Estado del Reactor: Muestra información actual sobre el estado operativo y temperatura del reactor.",
            "Salir: Regresa al menú principal."
    };

    String[] descripcionesMenuBombes = {
            "Activar Bomba: Permite al usuario activar una bomba refrigerante específica mediante su identificador numérico.",
            "Desactivar Bomba: Permite al usuario desactivar una bomba refrigerante específica mediante su identificador numérico.",
            "Mostrar Estado de las Bombas: Muestra el estado actual de todas las bombas del sistema de refrigeración.",
            "Salir: Regresa al menú principal."
    };


    /* Constructor*/
    public CentralUB() {
        variableNormal = new VariableNormal(VAR_NORM_MEAN, VAR_NORM_STD, VAR_NORM_SEED);
        demandaPotencia = generaDemandaPotencia();

        // generamos un objeto del tipo adaptador.
        try {
            adaptador = new Adaptador();
        } catch (CentralUBException e) {
            throw new RuntimeException(e);
        }

    }
    
    public void gestioCentralUB() {
        // Mostrar missatge inicial
        System.out.println("Benvingut a la planta PWR de la UB");
        System.out.println("La demanda de potència elèctrica avui es de " + demandaPotencia + " unitats");

        // Generamos los diferentes menús con sus opciones
        Menu<OpcionsMenuPrincipal> menuPrincipal = new Menu<>("\nMenú principal", OpcionsMenuPrincipal.values());
        menuPrincipal.setDescripcions(descripcionesMenuPrincipal);
        Menu<OpcionsMenuBarres> menuBarres = new Menu<>("\nMenú barres", OpcionsMenuBarres.values());
        menuBarres.setDescripcions(descripcionesMenuBarres);
        Menu<OpcionsMenuReactor> menuReactor = new Menu<>("\nMenú reactor", OpcionsMenuReactor.values());
        menuReactor.setDescripcions(descripcionesMenuReactor);
        Menu<OpcionsMenuBombes> menuBombes = new Menu<>("\nMenú bombes", OpcionsMenuBombes.values());
        menuBombes.setDescripcions(descripcionesMenuBombes);

        OpcionsMenuPrincipal opcionPrincipal = null;
        do {
            menuPrincipal.mostrarMenu();

            // Creamos un objeto de tipo scanner para registrar qué ha introducido el usuario
            Scanner sc = new Scanner(System.in);
            opcionPrincipal = menuPrincipal.getOpcio(sc);

            // En función de la opción seleccionada llamamos a un método del objeto Adaptador
            switch(opcionPrincipal) {

                case OPCIONS_BARRES:
                    // Si hemos elegido gestionar las barras accedemos al menu de estas
                    OpcionsMenuBarres opcioBarres = null;
                    do {
                        menuBarres.mostrarMenu();

                        sc = new Scanner(System.in);
                        opcioBarres = menuBarres.getOpcio(sc);

                        switch(opcioBarres) {
                            case OBTENIR_INSERCIO:
                                System.out.println("Obteniendo inserción");
                                System.out.println(this.adaptador.getInsercioBarres_());
                                break;
                            case ESTABLIR_INSERCIO:
                                System.out.println("Que inserción quieres establecer?");
                                sc = new Scanner(System.in);
                                float numero = sc.nextFloat();
                                System.out.println("Estableciendo inserción");
                                try {
                                    this.adaptador.setInsercioBarres_(numero);
                                } catch (CentralUBException e) {
                                    throw new RuntimeException(e);
                                }
                                break;
                            case SORTIR:
                                break;
                        }

                    } while(opcioBarres != OpcionsMenuBarres.SORTIR);
                    break;

                case OPCIONS_REACTOR:
                    // Si hemos elegido gestionar el reactor accedemos a su menu
                    OpcionsMenuReactor opcioReactor = null;
                    do {
                        menuReactor.mostrarMenu();

                        sc = new Scanner(System.in);
                        opcioReactor = menuReactor.getOpcio(sc);

                        switch(opcioReactor) {
                            case ACTIVAR_REACTOR:
                                try {
                                    this.adaptador.activarReactor();
                                } catch (CentralUBException e) {
                                    throw new RuntimeException(e);
                                }
                                System.out.println("Reactor activado");
                                break;

                            case DESACTIVAR_REACTOR:
                                this.adaptador.desactivarReactor();
                                System.out.println("Reactor desactivado");
                                break;

                            case MOSTRAR_ESTAT:
                                System.out.println(this.adaptador.mostrarEstatReactor());
                                break;
                            case SORTIR:
                                break;
                        }

                    } while(opcioReactor != OpcionsMenuReactor.SORTIR);
                    break;

                case OPCIONS_REFRIGERACIO:
                    // Si hemos elegido gestionar el reactor accedemos a su menu
                    OpcionsMenuBombes opcioBombes = null;
                    do {
                        menuBombes.mostrarMenu();

                        sc = new Scanner(System.in);
                        opcioBombes = menuBombes.getOpcio(sc);
                        switch(opcioBombes) {
                            case ACTIVAR_BOMBA:
                                System.out.println("Que bomba quieres activar?");
                                sc = new Scanner(System.in);
                                int numero = sc.nextInt();
                                System.out.println("Activando bomba" + numero);
                                try {
                                    this.adaptador.activarBomba(numero);
                                } catch (CentralUBException e) {
                                    throw new RuntimeException(e);
                                }
                                break;
                            case DESACTIVAR_BOMBA:
                                System.out.println("Que bomba quieres desactivar?");
                                sc = new Scanner(System.in);
                                numero = sc.nextInt();
                                System.out.println("Desactivando bomba " + numero);
                                this.adaptador.desactivarBomba(numero);
                                break;
                            case MOSTRAR_ESTAT:
                                System.out.println(this.adaptador.mostrarEstatBombes());
                                break;
                            case SORTIR:
                                break;
                        }

                    } while(opcioBombes != OpcionsMenuBombes.SORTIR);
                    break;

                case MOSTRAR_ESTAT_CENTRAL:
                    System.out.println(this.adaptador.estatCentral(demandaPotencia));
                    break;

                case MOSRAR_BITACOLA:
                    System.out.println(this.adaptador.mostrarBitacola());
                    break;

                case MOSTRAR_INCIDENCIES:
                    System.out.println(this.adaptador.incidenciesCentral());
                    break;

                case FINALITZAR_DIA:
                    this.adaptador.finalitzaDia(demandaPotencia);
                    break;

                case GUARDAR_DADES:
                    System.out.println("Introduce la dirección en la que quieres guardar los datos:");
                    sc = new Scanner(System.in);
                    String camiDesti = sc.nextLine();
                    try {
                        this.adaptador.guardaDades(camiDesti);
                    } catch (CentralUBException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case CARREGA_DADES:
                    System.out.println("Introduce la dirección de dónde quieres cargar los datos:");
                    sc = new Scanner(System.in);
                    String camiOrigen = sc.nextLine();
                    try {
                        this.adaptador.carregaDades(camiOrigen);
                    } catch (CentralUBException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case SORTIR:
                    System.out.println("Hasta Pronto!");
                    break;
            }

        } while(opcionPrincipal != OpcionsMenuPrincipal.SORTIR);


    }
    
    private float generaDemandaPotencia(){
        float valor = Math.round(variableNormal.seguentValor());
        if (valor > DEMANDA_MAX)
            return DEMANDA_MAX;
        else
            if (valor < DEMANDA_MIN)
                return DEMANDA_MIN;
            else
                return valor;
    }
    
    private void finalitzaDia() {
        // Finalitzar dia i imprimir informacio de la central
        String info = new String();
        info = adaptador.finalitzaDia(demandaPotencia);
        System.out.println(info);
        System.out.println("Dia finalitzat\n");
        
        // Generar i mostrar nova demanda de potencia
        demandaPotencia = generaDemandaPotencia();
        System.out.println("La demanda de potència elèctrica avui es de " + demandaPotencia + " unitats");
    }
}
