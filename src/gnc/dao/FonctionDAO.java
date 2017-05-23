package gnc.dao;

import gnc.modele.Fonction;
import gnc.utils.BooleanIntConverter;
import gnc.utils.ConnectionGNC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nathanael on 22/05/17.
 */
public class FonctionDAO implements AbstractDAO<Fonction> {

    private static List<Fonction> allFonctions = new ArrayList<>();
    private static Connection connection = ConnectionGNC.getConnection();

    @Override
    public List<Fonction> selectAll() {
        if (allFonctions.size() != 0) {
            return allFonctions;
        } else {
            Statement statement = null;
            ResultSet resultSet = null;
            String query = "SELECT * FROM FONCTION";
            try {
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    Fonction fonction = new Fonction(
                            resultSet.getString("CodeFonct"),
                            resultSet.getString("LibelleFonct"),
                            BooleanIntConverter.int2Boolean(resultSet.getInt("AutorisationNC")),
                            BooleanIntConverter.int2Boolean(resultSet.getInt("AutorisationImputation")),
                            BooleanIntConverter.int2Boolean(resultSet.getInt("AutorisationAdmin"))
                    );
                    allFonctions.add(fonction);
                }
                return allFonctions;
            } catch (SQLException e) {
                System.out.println("Error while getting fonction");
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

    @Override
    public Fonction selectBy(String identifier) {
        if (allFonctions.size() != 0) {
            for (Fonction f : allFonctions) {
                if (f.getCode().equals(identifier)) {
                    return f;
                }
            }
            return null;
        } else {
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            String query = "SELECT * FROM FONCTION WHERE CodeFonct = ?";
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, identifier);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Fonction fonction = new Fonction(
                            resultSet.getString("CodeFonct"),
                            resultSet.getString("LibelleFonct"),
                            BooleanIntConverter.int2Boolean(resultSet.getInt("AutorisationNC")),
                            BooleanIntConverter.int2Boolean(resultSet.getInt("AutorisationImputation")),
                            BooleanIntConverter.int2Boolean(resultSet.getInt("AutorisationAdmin"))
                    );
                    return fonction;
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

    @Override
    public boolean create(Fonction object) {
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO FONCTION(CodeFonct, LibelleFonct, " +
                "AutorisationNC, AutorisationImputation, AutorisationAdmin)" +
                "VALUES (?, ?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, object.getCode());
            preparedStatement.setString(2, object.getLibelle());
            preparedStatement.setInt(3, BooleanIntConverter.boolean2Int(object.getAutorisationNC()));
            preparedStatement.setInt(4, BooleanIntConverter.boolean2Int(object.getAutorisationImputation()));
            preparedStatement.setInt(5, BooleanIntConverter.boolean2Int(object.getAutorisationAdmin()));
            return preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void update(Fonction object) {
        PreparedStatement preparedStatement = null;
        String query = "UPDATE FONCTION " +
                "SET CodeFonct = ?, LibelleFonct = ?," +
                " AutorisationNC = ?, AutorisationImputation = ?," +
                " AutorisationAdmin = ? " +
                "WHERE CodeFonct = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, object.getCode());
            preparedStatement.setString(2, object.getLibelle());
            preparedStatement.setInt(3, BooleanIntConverter.boolean2Int(object.getAutorisationNC()));
            preparedStatement.setInt(4, BooleanIntConverter.boolean2Int(object.getAutorisationImputation()));
            preparedStatement.setInt(5, BooleanIntConverter.boolean2Int(object.getAutorisationAdmin()));
            preparedStatement.setString(6, object.getCode());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while updating fonction");
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

    @Override
    public void delete(String identifier) {
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM FONCTION " +
                "WHERE CodeFonct = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, identifier);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while deleting fonction");
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
