package com.cesi.cda.courspoo.controller.match.controller;

import com.cesi.cda.courspoo.business.ExternalAPIBusiness;
import com.cesi.cda.courspoo.business.MatchBusiness;
import com.cesi.cda.courspoo.controller.match.models.Match;
import com.cesi.cda.courspoo.DAO.models.PersonDTO;
import com.cesi.cda.courspoo.DAO.models.RencontreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchController {

    public MatchBusiness matchBusiness;

    private ExternalAPIBusiness externalAPIBusiness;

    @Autowired
    public MatchController(MatchBusiness matchBusiness, ExternalAPIBusiness externalAPIBusiness) {
        this.matchBusiness = matchBusiness;
        this.externalAPIBusiness = externalAPIBusiness;
    }

    @GetMapping()
    public Match getMatch() throws SQLException {
        return matchBusiness.getMatch();
    }

    @GetMapping("/{id}")
    public Match getMatch(@PathVariable Long id) throws SQLException {
        return matchBusiness.getMatchById(id);
    }

    @PostMapping()
    public String postMatch() {
        return "match ajouter";
    }

    @PutMapping("/{id}")
    public String updateMatch(@PathVariable Long id) {
        return "match modifier";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMatch(@PathVariable Long id) {
        try {
            matchBusiness.deleteMatch(id);
            return ResponseEntity.ok("Match supprimé avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la suppression du match: " + e.getMessage());
        }
    }

    @GetMapping("/persons")
    public List<PersonDTO> getPersons() throws SQLException {
        return matchBusiness.getAllPersons();
    }

    @GetMapping("/rencontres")
    public List<RencontreDTO> getRencontres() throws SQLException {
        return matchBusiness.getAllRencontres();
    }

    @GetMapping("/dataFromAPI")
    public String getDataFromExternalAPI() throws SQLException {
        return externalAPIBusiness.fetchDataFromExternalAPI();
    }

}
