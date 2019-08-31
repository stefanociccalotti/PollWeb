package it.univaq.disim.model;

public class Sondaggio {

    private Integer id;
    private String titolo;
    private String apertura;
    private String chiusura;
    private boolean privato;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getApertura() {
        return apertura;
    }

    public void setApertura(String apertura) {
        this.apertura = apertura;
    }

    public String getChiusura() {
        return chiusura;
    }

    public void setChiusura(String chiusura) {
        this.chiusura = chiusura;
    }

    public boolean isPrivato() {
        return privato;
    }

    public void setPrivato(boolean privato) {
        this.privato = privato;
    }
}
