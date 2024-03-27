package com.cesi.cda.courspoo.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.cesi.cda.courspoo.DAO.models.RencontreDTO;

@Repository
public class Rencontre {

    private final DataSource dataSource;

    public Rencontre(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<RencontreDTO> getAllRencontres() throws SQLException {
        List<RencontreDTO> rencontres = new ArrayList<>();
        String query = "SELECT * FROM Rencontre";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int nuGagnant = resultSet.getInt("nuGagnant");
                int nuPerdant = resultSet.getInt("nuPerdant");
                String lieuTournoi = resultSet.getString("lieuTournoi");
                String annee = resultSet.getString("annee");
                rencontres.add(new RencontreDTO(id, nuGagnant, nuPerdant, lieuTournoi, annee));
            }
        }
        return rencontres;
    }

    public void deleteRencontre(Long id) throws SQLException {
        String query = "DELETE FROM Rencontre WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }
}
