package com.cesi.cda.courspoo.controller.match.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Match {

    @JsonProperty("nomPrenomGagnant")
    private String nomPrenomGagnant;

    @JsonProperty("nomPrenomPerdant")
    private String nomPrenomPerdant;

    @JsonProperty("dateLieuRencontre")
    private String dateLieuRencontre;

    @JsonProperty("sommeAges")
    private String sommeAges;

    public Match(String nomPrenomGagnant, String nomPrenomPerdant, String dateLieuRencontre, String sommeAges) {
        this.nomPrenomGagnant = nomPrenomGagnant;
        this.nomPrenomPerdant = nomPrenomPerdant;
        this.dateLieuRencontre = dateLieuRencontre;
        this.sommeAges = sommeAges;
    }
}
