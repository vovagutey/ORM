package vova.hut.imp;

import org.junit.Test;
import vova.hut.core.DAOException;
import vova.hut.entity.User;
import vova.hut.old.UserDAOImpl;

import java.sql.SQLException;



public class UserDAOImplTest {

    @Test
    public void create(){
        User user = new User();
        user.setLogin("fff");
        user.setPassword("asdfg");
        user.setEmail("wafffd");
        user.setPhone("33333333");

        UserDAOImpl userDAO = new UserDAOImpl();
        try {
            user = userDAO.create(user);
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(user);

    }

    @Test
    public void readById() {
        UserDAOImpl userDAO = new UserDAOImpl();
        User user = null;
        try {
            user = userDAO.readById(3);
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(user);
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void readAll() throws SQLException, DAOException {
        UserDAOImpl userDAO = new UserDAOImpl();
        System.out.println(userDAO.readAll());
    }

    }