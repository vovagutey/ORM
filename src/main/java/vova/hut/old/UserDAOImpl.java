package vova.hut.old;

import vova.hut.core.DAOException;
import vova.hut.core.DataBaseUtills;
import vova.hut.entity.User;
import vova.hut.old.UserDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private Connection connection;

    @Override
    public User create(User user) throws DAOException, SQLException {
        String create_query = "{call full_user_create(?,?,?,?)}";
        connection = DataBaseUtills.createConnection();
        CallableStatement callableStatement = connection.prepareCall(create_query);

        callableStatement.setString(1, user.getLogin());
        callableStatement.setString(2, user.getPassword());
        callableStatement.setString(3, user.getEmail());
        callableStatement.setString(4, user.getPhone());

        if (callableStatement.execute()) {
            PreparedStatement preparedStatement = connection.prepareStatement("{call get_created_user(?)}");
            preparedStatement.setString(1, user.getLogin());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setUser_id(resultSet.getInt(1));
                user.setLogin(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setPhone(resultSet.getString(5));
            }
        }

        return user;
    }

    @Override
    public User readById(long id) throws DAOException, SQLException {
        User user = new User();
        final String READ_QUERY = "{call get_byId(?,?,?,?,?,?)}";
        connection = DataBaseUtills.createConnection();
        CallableStatement callableStatement = connection.prepareCall(READ_QUERY);

        callableStatement.setLong(1, id);
        callableStatement.registerOutParameter(2, Types.VARCHAR);
        callableStatement.registerOutParameter(3, Types.VARCHAR);
        callableStatement.registerOutParameter(4, Types.VARCHAR);
        callableStatement.registerOutParameter(5, Types.VARCHAR);
        callableStatement.registerOutParameter(6, Types.TIMESTAMP);

        callableStatement.executeUpdate();

        user.setUser_id((int) id);
        user.setLogin(callableStatement.getString("user_login"));
        user.setPassword(callableStatement.getString("user_pass"));
        user.setEmail(callableStatement.getString("user_email"));
        user.setPhone(callableStatement.getString("user_phone"));
        user.setCreated(callableStatement.getTimestamp("user_created").toLocalDateTime());
        return  user;
    }

    @Override
    public boolean update(User user) throws DAOException, SQLException {
        final String UPDATE_QUERY = "{call user_update(?,?,?,?,?)}";
        try {

            connection = DataBaseUtills.createConnection();
            CallableStatement callableStatement = connection.prepareCall(UPDATE_QUERY);
            callableStatement.setLong(1, user.getUser_id());
            callableStatement.setString(2, user.getLogin());
            callableStatement.setString(3, user.getPassword());
            callableStatement.setString(4, user.getEmail());
            callableStatement.setString(5, user.getPhone());

            return true;
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }

    @Override
    public boolean delete(User user) throws DAOException {
        final String DELETE_QUERY = "call user_delete(?)";
        try {
            connection = DataBaseUtills.createConnection();
            CallableStatement callableStatement = connection.prepareCall(DELETE_QUERY);
            callableStatement.setLong(1, user.getUser_id());

        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }

    @Override
    public List<User> readAll() throws DAOException, SQLException {
     final String READ_ALL_QUERY = "{CALL getAll}";

     Collection<User> users = new ArrayList<>();

     connection = DataBaseUtills.createConnection();
     PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_QUERY);

    ResultSet resultSet = preparedStatement.executeQuery();

    while (resultSet.next()){
        User user = new User();
        int i = 0;
        user.setUser_id(resultSet.getInt(++i));
        user.setLogin(resultSet.getString(++i));
        user.setPassword(resultSet.getString(++i));
        user.setEmail(resultSet.getString(++i));
        user.setPhone(resultSet.getString(++i));
        user.setCreated(resultSet.getTimestamp(++i).toLocalDateTime());
        users.add(user);

    }


        return (List<User>) users;
    }
}
