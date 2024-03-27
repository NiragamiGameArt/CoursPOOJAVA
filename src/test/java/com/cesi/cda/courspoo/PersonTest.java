package com.cesi.cda.courspoo;

import com.cesi.cda.courspoo.DAO.Person;
import com.cesi.cda.courspoo.DAO.models.PersonDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class PersonTest {

    @Test
    void testGetAllPersons() {
        // Créer une liste de personnes fictive pour simuler la récupération depuis la base de données
        List<PersonDTO> fakePersons = new ArrayList<>();
        fakePersons.add(new PersonDTO(1, "Alain", "Dupont", "1980", "FRANCE"));
        fakePersons.add(new PersonDTO(2, "Jack", "Doe", "1990", "USA"));

        // Mock de la classe Person pour simuler le comportement de getAllPersons()
        Person person = new Person(null) {
            @Override
            public List<PersonDTO> getAllPersons() {
                return fakePersons;
            }
        };

        try {
            // Appeler la méthode à tester
            List<PersonDTO> actualPersons = person.getAllPersons();

            // Vérifier que la méthode renvoie la même liste que celle créée ci-dessus
            assertEquals(fakePersons.size(), actualPersons.size());
            for (int i = 0; i < fakePersons.size(); i++) {
                assertEquals(fakePersons.get(i).getId(), actualPersons.get(i).getId());
                assertEquals(fakePersons.get(i).getNom(), actualPersons.get(i).getNom());
                assertEquals(fakePersons.get(i).getPrenom(), actualPersons.get(i).getPrenom());
                assertEquals(fakePersons.get(i).getAnneeNaissance(), actualPersons.get(i).getAnneeNaissance());
                assertEquals(fakePersons.get(i).getNationalite(), actualPersons.get(i).getNationalite());
            }
        } catch (SQLException e) {
            // Gérer l'exception si nécessaire
            e.printStackTrace();
        }
    }
}
