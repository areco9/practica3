package prog2.model;

public class PaginaEstat extends PaginaBitacola{

    private float demandaPotencia;
    private float grauInsercio;
    private float outputReactor;
    private float outputSistema;
    private float outputGenerador;
    private float outputTurbina;

    private float percentatjeDemanda;

    public PaginaEstat(int dia, float demandaPotencia, float grauInsercio, float outputReactor, float outputSistema, float outputGenerador, float outputTurbina, float percentatjeDemanda) {
        super(dia);
        this.demandaPotencia = demandaPotencia;
        this.grauInsercio = grauInsercio;
        this.outputReactor = outputReactor;
        this.outputSistema = outputSistema;
        this.outputGenerador = outputGenerador;
        this.outputTurbina = outputTurbina;
        this.percentatjeDemanda = percentatjeDemanda;
    }

    public float getDemandaPotencia() {
        return demandaPotencia;
    }

    public void setDemandaPotencia(float demandaPotencia) {
        this.demandaPotencia = demandaPotencia;
    }

    public float getGrauInsercio() {
        return grauInsercio;
    }

    // Getters & Setters
    public void setGrauInsercio(float grauInsercio) {
        this.grauInsercio = grauInsercio;
    }

    public float getOutputReactor() {
        return outputReactor;
    }

    public void setOutputReactor(float outputReactor) {
        this.outputReactor = outputReactor;
    }

    public float getOutputGenerador() {
        return outputGenerador;
    }

    public void setOutputGenerador(float outputGenerador) {
        this.outputGenerador = outputGenerador;
    }

    public float getOutputSistema() {
        return outputSistema;
    }

    public void setOutputSistema(float outputSistema) {
        this.outputSistema = outputSistema;
    }

    public float getOutputTurbina() {
        return outputTurbina;
    }

    public void setOutputTurbina(float outputTurbina) {
        this.outputTurbina = outputTurbina;
    }

    public float getPercentatjeDemanda() {
        return percentatjeDemanda;
    }

    public void setPercentatjeDemanda(float percentatjeDemanda) {
        this.percentatjeDemanda = percentatjeDemanda;
    }

    // Método toString en el formato correcto
    @Override
    public String toString() {
        return "\n# PaginaEstat" +
                "\n- Dia: " + super.getDia() +
                "\n- Demanda de potencia: " + getDemandaPotencia() +
                "\n- Inserció Barres: " + getGrauInsercio() + " %" +
                "\n- Output Reactor: " + getOutputReactor() + " Graus" +
                "\n- Output Sistema de Refrigeració: " + getOutputSistema() + " Graus" +
                "\n- Output Generador: " + getOutputGenerador() + " Graus" +
                "\n- Output Turbina: " + getOutputTurbina() + " Unitats de Potència" +
                "\n- Demanda de Potència Satisfeta: " + getPercentatjeDemanda() + " %";
    }
}
