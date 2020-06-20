package vova.hut.core;


import vova.hut.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractDAO<T extends Identifier> implements GenericDAO<T> {


    public abstract String getCreateQuery();

    public abstract String getReadQuery();

    public abstract String getUpdateQuery();

    public abstract String getDeleteQuery();

    public abstract String getReadAllQuery();

    public abstract void setItem(CallableStatement statement, T item);

    public abstract T getItem(Integer id) throws SQLException;


    public abstract void updateUser(CallableStatement statement, T item) throws SQLException;

    public abstract List<T> getAll(PreparedStatement preparedStatement) throws SQLException;

    @Override
    public T create(T item) throws DAOException, SQLException {
        Integer id = 0;
        String create_query = getCreateQuery();

        try {

            Connection connection = DataBaseUtills.createConnection();
            CallableStatement callableStatement = connection.prepareCall(create_query);
            setItem(callableStatement, item);
            callableStatement.execute();

            PreparedStatement preparedStatement = connection.prepareStatement("select last_insert_id()");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                id = resultSet.getInt(1);
            }
        }catch (SQLException e){
            e.getMessage();
        }

        T fullItem = getItem(id);

        return fullItem;
    }

    @Override
    public T readById(Integer id) throws DAOException{
        T item = null;
        try{
            item = getItem(id);
        }catch (SQLException e){
            e.getMessage();
        }
        return item;
    }

    @Override
    public boolean update(T item) throws DAOException {
        final String UPDATE_QUERY = getUpdateQuery();
        try {
            Connection connection = DataBaseUtills.createConnection();
            CallableStatement callableStatement = connection.prepareCall(UPDATE_QUERY);

            updateUser(callableStatement, item);
            callableStatement.execute();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public boolean delete(T item) throws DAOException {
        try {
            Connection connection = DataBaseUtills.createConnection();
            CallableStatement callableStatement = connection.prepareCall(getDeleteQuery());
            callableStatement.setInt(1, item.getId());
            callableStatement.execute();

        } catch (Exception e) {

            e.getMessage();
        }
        return false;
    }

    @Override
    public List<T> readAll() throws DAOException {

        List<T> items = null;

        try (Connection connection = DataBaseUtills.createConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(getReadAllQuery());
            items = getAll(preparedStatement);
            if (items.size() <= 0) {
                throw new DAOException("Problem with getting data!!!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return items;
    }
}
