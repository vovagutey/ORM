package vova.hut.old;

import org.junit.Test;
import vova.hut.core.DAOException;
import vova.hut.entity.User;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class UserDAOImplTest {

    @Test
    public void create() {
    }

    @Test
    public void readById() throws SQLException, DAOException {
        UserDAOImpl dao = new UserDAOImpl();
        User testUser = dao.readById(7);
        System.out.println(testUser);
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void readAll() {
    }
}