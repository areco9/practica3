package prog2.model;

public class PaginaEconomica extends PaginaBitacola {

    private float beneficis;
    private float exesPotencia;
    private float costosOperatius;
    private float guanysAcumulats;

    public PaginaEconomica(int dia, float beneficis, float exesPotencia, float costosOperatius, float guanysAcumulats) {
        super(dia);
        this.beneficis = beneficis;
        this.exesPotencia = exesPotencia;
        this.costosOperatius = costosOperatius;
        this.guanysAcumulats = guanysAcumulats;
    }

    // Getters & setters
    public float getBeneficis() {
        return beneficis;
    }

    public void setBeneficis(float beneficis) {
        this.beneficis = beneficis;
    }

    public float getExesPotencia() {
        return exesPotencia;
    }

    public void setExesPotencia(float exesPotencia) {
        this.exesPotencia = exesPotencia;
    }

    public float getCostosOperatius() {
        return costosOperatius;
    }

    public void setCostosOperatius(float costosOperatius) {
        this.costosOperatius = costosOperatius;
    }

    public float getGuanysAcumulats() {
        return guanysAcumulats;
    }

    public void setGuanysAcumulats(float guanysAcumulats) {
        this.guanysAcumulats = guanysAcumulats;
    }

    // Método toString en el formato correcto
    @Override
    public String toString() {
        return "\n# Pàgina Econòmica" +
                "\n- Dia: " + super.getDia() +
                "\n- Beneficis: " + getBeneficis() + " Unitats Econòmiques" +
                "\n- Penalització Excès Producció: " + getExesPotencia() + " Unitats Econòmiques" +
                "\n- Cost Operatiu: " + getCostosOperatius() + " Unitats Econòmiques" +
                "\n- Guanys Acumulats: " + getGuanysAcumulats() + " Unitats Econòmiques";
    }
}
