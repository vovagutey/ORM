package vova.hut.old;

import vova.hut.core.DAOException;
import vova.hut.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    User create(User user) throws DAOException, SQLException;
    User readById(long id) throws DAOException, SQLException;
    boolean update(User user) throws DAOException, SQLException;
    boolean delete(User user) throws DAOException;
    List<User> readAll() throws DAOException, SQLException;

}
