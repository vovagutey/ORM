package vova.hut.imp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import vova.hut.core.DAOException;
import vova.hut.core.GenericDAO;
import vova.hut.entity.User;
import vova.hut.old.UserDAO;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class User_DAOTest {
    private GenericDAO<User> dao;
    private User user;


    @Before
    public void setUp() throws Exception {
        dao = new User_DAO();
        user = new User();
        user.setUser_id(1);
        user.setLogin("fdddf091876543");
        user.setEmail("13@ddd28765");
        user.setPassword("1543r4tetr");
        user.setPhone("09886ddf7854");
    }


    @Test
    public void createTest() throws SQLException, DAOException {
        User createdUser = dao.create(user);
        System.out.println(createdUser);
        Assert.assertNotEquals(0, createdUser.getId());
        Assert.assertNotNull(createdUser.getCreated());
    }

    @Test
    public void readByIdTest() throws SQLException, DAOException {
        User testUser = dao.readById(13);
        System.out.println(testUser);
        //Assert.assertEquals(5, 5);
    }

    @Test
    public void updateTest() throws SQLException, DAOException {
        dao.update(user);
    }

    @Test
    public void deleteTest() throws DAOException {
        User delUser = new User();
        delUser.setUser_id(1);
        dao.delete(delUser);
    }

    @Test
    public void readAllTest() throws SQLException, DAOException {
        System.out.println(dao.readAll());
    }
}