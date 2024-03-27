package com.cesi.cda.courspoo.business;

import com.cesi.cda.courspoo.DAO.Person;
import com.cesi.cda.courspoo.DAO.Rencontre;
import com.cesi.cda.courspoo.DAO.models.PersonDTO;
import com.cesi.cda.courspoo.DAO.models.RencontreDTO;
import com.cesi.cda.courspoo.controller.match.models.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Component
public class MatchBusiness {

    private Person person;
    private Rencontre rencontre;

    @Autowired
    public MatchBusiness(Person person, Rencontre rencontre) {
        this.person = person;
        this.rencontre = rencontre;
    }

    public List<PersonDTO> getAllPersons() throws SQLException {
        return person.getAllPersons();
    }

    public List<RencontreDTO> getAllRencontres() throws SQLException {
        return rencontre.getAllRencontres();
    }

    public Match getMatch() throws SQLException {
        List<PersonDTO> persons = getAllPersons();
        List<RencontreDTO> rencontres = getAllRencontres();

        if (rencontres.isEmpty()) {
            throw new IllegalStateException("Aucune rencontre disponible pour créer un match.");
        }

        RencontreDTO rencontre = rencontres.get(0);
        int idGagnant = rencontre.getNuGagnant();
        int idPerdant = rencontre.getNuPerdant();

        List<PersonDTO> personsInFirstRencontre = new ArrayList<>();
        for (PersonDTO person : persons) {
            if (person.getId() == idGagnant || person.getId() == idPerdant) {
                personsInFirstRencontre.add(person);
            }
        }

        String nomPrenomGagnant = findPersonNameById(personsInFirstRencontre, idGagnant);
        String nomPrenomPerdant = findPersonNameById(personsInFirstRencontre, idPerdant);

        String dateLieuRencontre = rencontre.getAnneeTournoi();
        String sommeAges = String.valueOf(calculateTotalAge(personsInFirstRencontre));

        return new Match(nomPrenomGagnant, nomPrenomPerdant, dateLieuRencontre, sommeAges);
    }


    public Match getMatchById(Long id) throws SQLException {
        List<RencontreDTO> rencontres = getAllRencontres();

        for (RencontreDTO rencontre : rencontres) {
            if (rencontre.getId() == id) {
                int idGagnant = rencontre.getNuGagnant();
                int idPerdant = rencontre.getNuPerdant();

                List<PersonDTO> personsInThisRencontre = new ArrayList<>();
                for (PersonDTO person : getAllPersons()) {
                    if (person.getId() == idGagnant || person.getId() == idPerdant) {
                        personsInThisRencontre.add(person);
                    }
                }

                String nomPrenomGagnant = findPersonNameById(personsInThisRencontre, idGagnant);
                String nomPrenomPerdant = findPersonNameById(personsInThisRencontre, idPerdant);

                String dateLieuRencontre = rencontre.getAnneeTournoi();
                String sommeAges = String.valueOf(calculateTotalAge(personsInThisRencontre));

                return new Match(nomPrenomGagnant, nomPrenomPerdant, dateLieuRencontre, sommeAges);
            }
        }

        throw new IllegalArgumentException("Aucun match trouvé : " + id);
    }

    public void deleteMatch(Long id) throws SQLException {
        rencontre.deleteRencontre(id);
    }

    private int calculateTotalAge(List<PersonDTO> persons) {
        int totalAge = 0;
        LocalDate currentDate = LocalDate.now();

        for (PersonDTO person : persons) {
            int birthYear = Integer.parseInt(person.getAnneeNaissance());
            int age = Period.between(LocalDate.of(birthYear, 1, 1), currentDate).getYears();
            totalAge += age;
        }

        return totalAge;
    }
    private String findPersonNameById(List<PersonDTO> persons, int id) {
        for (PersonDTO person : persons) {
            if (person.getId() == id) {
                return person.getNom() + " " + person.getPrenom();
            }
        }
        return "Nom introuvable";
    }


}
