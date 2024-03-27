package com.cesi.cda.courspoo.DAO.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RencontreDTO {
    @JsonProperty("id")
    private int id;

    @JsonProperty("nuGagnant")
    private int nuGagnant;

    @JsonProperty("nuPerdant")
    private int nuPerdant;

    @JsonProperty("lieuTournoi")
    private String lieuTournoi;

    @JsonProperty("annee")
    private String annee;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNuGagnant() {
        return nuGagnant;
    }

    public void setNuGagnant(int nuGagnant) {
        this.nuGagnant = nuGagnant;
    }

    public int getNuPerdant() {
        return nuPerdant;
    }

    public void setNuPerdant(int nuPerdant) {
        this.nuPerdant = nuPerdant;
    }

    public String getLieuTournoi() {
        return lieuTournoi;
    }

    public void setLieuTournoi(String lieuTournoi) {
        this.lieuTournoi = lieuTournoi;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public RencontreDTO(int id, int nuGagnant, int nuPerdant, String lieuTournoi, String annee) {
        this.id = id;
        this.nuGagnant = nuGagnant;
        this.nuPerdant = nuPerdant;
        this.lieuTournoi = lieuTournoi;
        this.annee = annee;
    }

    public String getAnneeTournoi() {
        return lieuTournoi + " - " + annee;
    }
}
