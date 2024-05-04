package prog2.model;

import java.util.ArrayList;
import java.util.List;

public class Bitacola implements InBitacola{

    private ArrayList<PaginaBitacola> llistaPagines;

    public Bitacola() {
        this.llistaPagines = new ArrayList<PaginaBitacola>();
    }

    @java.lang.Override
    public void afegeixPagina(PaginaBitacola p) {
        this.llistaPagines.add(p);
    }

    @java.lang.Override
    public List<PaginaIncidencies> getIncidencies() {
        return null;
    }

    @Override
    public String toString() {
        // Creamos una variable "string" dónde iremos guardando los toString
        // de las diferentes páginas
        StringBuilder string = new StringBuilder();
        for (PaginaBitacola pag : this.llistaPagines) {
            string.append(pag.toString()).append("\n");
        }
        return string.toString();
    }
}
