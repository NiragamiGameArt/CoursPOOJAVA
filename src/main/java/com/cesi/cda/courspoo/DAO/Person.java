package com.cesi.cda.courspoo.DAO;

import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.cesi.cda.courspoo.DAO.models.PersonDTO;

@Repository
public class Person {
    private final DataSource dataSource;

    public Person(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<PersonDTO> getAllPersons() throws SQLException {
        List<PersonDTO> persons = new ArrayList<>();
        String query = "SELECT * FROM Person";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String anneeNaissance = resultSet.getString("anneeNaissance");
                String nationalite = resultSet.getString("nationalite");
                persons.add(new PersonDTO(id, nom, prenom, anneeNaissance, nationalite));
            }
        }
        return persons;
    }
}
