package vova.hut.core;

import vova.hut.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<P> {
    P create(P item) throws DAOException, SQLException;
    P readById(Integer id) throws DAOException, SQLException;
    boolean update(P item) throws DAOException, SQLException;
    boolean delete(P item) throws DAOException;
    List<P> readAll() throws DAOException, SQLException;
}
