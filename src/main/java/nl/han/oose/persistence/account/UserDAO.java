package nl.han.oose.persistence.account;

import nl.han.oose.entity.account.Account;
import nl.han.oose.persistence.ConnectionFactory;
import nl.han.oose.persistence.Datamapper;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDAO extends Datamapper {

    @Inject
    private ConnectionFactory connectionFactory;

    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user;")
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String user = resultSet.getString("user");
                String password = resultSet.getString("password");

                accounts.add(new Account(user, password));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accounts;
    }

    public Account login(Account account) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE user = ? AND password = ?;")

        ) {
            statement.setString(1, account.getUser());
            statement.setString(2, account.getPassword());

            ResultSet resultSet = statement.executeQuery();

            resultSet.last();
            if (resultSet.getRow() <= 0) {
                return null;
            } else {
                return new Account(
                        resultSet.getString("user"),
                        resultSet.getString("password"));
            }
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //public Account persistAccount(Account login) { //id

    public void persistAccount(Account account) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO user (user,password) VALUES (?,?);");
        ) {
            statement.setString(1, account.getUser());
            statement.setString(2, account.getPassword());
            statement.execute();

            //statement.executeUpdate(); voor delete perposes.

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
