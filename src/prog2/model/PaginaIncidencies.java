package prog2.model;

import java.util.ArrayList;

public class PaginaIncidencies extends PaginaBitacola{

    private ArrayList<String> llistaIncidencies;

    public PaginaIncidencies(int dia) {
        super(dia);
        this.llistaIncidencies = new ArrayList<String>();
    }

    public void afegeixIncidencia(String descIncidencia) {
        llistaIncidencies.add(descIncidencia);
    }

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder("# Pàgina Incidèndies\n");
        message.append("- Dia: \n" + getDia() + "\n");

        for (String inc : llistaIncidencies) {
            message.append("\n- Descripció Incidència: ").append(inc);
        }
        return message.toString();
    }
}
