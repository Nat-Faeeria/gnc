package gnc.dao;

import gnc.modele.Personnel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nathanael on 22/05/17.
 */
public class PersonnelDAO {

    private static List<Personnel> allPersonnel = new ArrayList<>();
    private static Connection connection = ConnectionGNC.getConnection();

    public static List<Personnel> selectAll() {
        if (allPersonnel.size() != 0) {
            return allPersonnel;
        } else {
            Statement statement = null;
            ResultSet resultSet = null;
            try {
                statement = connection.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM PERSONNEL");

                while (resultSet.next()) {
                    Personnel personnel = new Personnel();
                    personnel.setId(resultSet.getString("Id"));
                    personnel.setMatricule(resultSet.getString("Matricule"));
                    //personnel.setFonction(FonctionDAO.selectById(resultSet.getString("Fonction")));
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
    }

    public Personnel selectByMatricule(String matricule) {
        if (allPersonnel.size() != 0) {
            for (Personnel p : allPersonnel) {
                if (p.getMatricule().equals(matricule)) {
                    return p;
                }
            }
            return  null;
        } else {
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try {
                String query = "SELECT * FROM PERSONNEL WHERE Matricule = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, matricule);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Personnel personnel = new Personnel();
                    personnel.setId(resultSet.getString("Id"));
                    personnel.setMatricule(resultSet.getString("Matricule"));
                    //personnel.setFonction(FonctionDAO.selectById(resultSet.getString("Fonction")));
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
    }

    public void update(Personnel personnel) {
        PreparedStatement preparedStatement = null;
        try {
            String query = "UPDATE PERSONNEL " +
                           "SET Id = ?, Fonction = ?" +
                           "WHERE Matricule = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, personnel.getId());
            preparedStatement.setString(2, personnel.getFonction().getCode());
            preparedStatement.setString(3, personnel.getMatricule());
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

    public void delete(String matricule) {
        PreparedStatement preparedStatement = null;
        try {
            String query = "DELETE FROM PERSONNEL " +
                           "WHERE Matricule = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, matricule);
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
