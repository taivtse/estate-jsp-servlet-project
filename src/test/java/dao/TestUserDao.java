package dao;

import com.laptrinhjavaweb.dao.UserDao;
import com.laptrinhjavaweb.dao.impl.UserDaoImpl;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestUserDao {
    UserDao userDao;

    @BeforeTest
    public void init() {
        userDao = new UserDaoImpl();
    }

    @Test
    public void findAll() {
        userDao.findAll();
    }

    @Test
    public void findOneById() {
        userDao.findOneById(1);
    }
}
