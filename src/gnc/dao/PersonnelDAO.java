package gnc.dao;

import gnc.modele.Personnel;
import gnc.utils.BooleanIntConverter;
import gnc.utils.ConnectionGNC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nathanael on 22/05/17.
 */
public class PersonnelDAO implements AbstractDAO<Personnel> {

    private static Connection connection = ConnectionGNC.getConnection();
    private FonctionDAO fonctionDAO = new FonctionDAO();

    public List<Personnel> selectAll() {
        List<Personnel> allPersonnel = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM PERSONNEL";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Personnel personnel = new Personnel(
                        resultSet.getString("Id"),
                        resultSet.getString("Matricule"),
                        fonctionDAO.selectBy(resultSet.getString("Fonction"))
                );
                allPersonnel.add(personnel);
            }
            return allPersonnel;
        } catch (SQLException e) {
            System.out.println("Error while getting personnel");
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                resultSet = null;
            }

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                statement = null;
            }
        }
        return null;
    }

    public Personnel selectBy(String identifier) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM PERSONNEL WHERE Matricule = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, identifier);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Personnel personnel = new Personnel(
                        resultSet.getString("Id"),
                        resultSet.getString("Matricule"),
                        fonctionDAO.selectBy(resultSet.getString("Fonction"))
                );
                return personnel;
            }
        } catch (SQLException e) {
            System.out.println("Error while getting personnel by matricule");
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                resultSet = null;
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                preparedStatement = null;
            }
        }
        return null;
    }

    @Override
    public boolean create(Personnel object) {
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO PERSONNEL(Matricule, Id, Fonction) " +
                       "VALUES (?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, object.getId());
            preparedStatement.setString(2, object.getFonction().getCode());
            preparedStatement.setString(3, object.getMatricule());
            return preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void update(Personnel object) {
        PreparedStatement preparedStatement = null;
        String query = "UPDATE PERSONNEL " +
                "SET Id = ?, Fonction = ?" +
                "WHERE Matricule = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, object.getId());
            preparedStatement.setString(2, object.getFonction().getCode());
            preparedStatement.setString(3, object.getMatricule());
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            System.out.println("Error while updating personnel");
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                preparedStatement = null;
            }
        }
    }

    public void delete(String identifier) {
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM PERSONNEL " +
                "WHERE Matricule = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, identifier);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while deleting personnel");
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                preparedStatement = null;
            }
        }
    }

}
