package nl.han.oose.persistence.account;

import nl.han.oose.entity.account.Account;
import nl.han.oose.entity.account.UserToken;
import nl.han.oose.persistence.ConnectionFactory;

import javax.inject.Inject;
import javax.naming.AuthenticationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class TokenDAO {

    @Inject
    private ConnectionFactory connectionFactory;


    public UserToken getToken(String token) throws AuthenticationException {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement query = connection.prepareStatement("SELECT * FROM token WHERE token = ?;")
        ) {
            query.setString(1, token);
            ResultSet resultSet = query.executeQuery();
            resultSet.last();
            if (resultSet.getRow() == 1) {
                return new UserToken(resultSet.getString("user"), token);
            } else {
                throw new AuthenticationException();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserToken getNewToken(Account login) {
        String user = login.getUser();
        String token = generateToken();
        String currentDatetime = getDatetime();
        UserToken loginToken = new UserToken(user, token);
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement query = connection.prepareStatement("INSERT INTO token (user, token, expiry_date) VALUES (?, ?, ?);")
        ) {
            query.setString(1, user);
            query.setString(2, token);
            query.setString(3, currentDatetime);
            query.execute();
            return loginToken;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateToken() {

        return UUID.randomUUID().toString();
    }

    private String getDatetime() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        date = c.getTime();
        return dateFormat.format(date);
    }

}
