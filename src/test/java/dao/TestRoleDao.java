package dao;

import com.laptrinhjavaweb.dao.RoleDao;
import com.laptrinhjavaweb.dao.impl.RoleDaoImpl;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class TestRoleDao {
    RoleDao roleDao;

    @BeforeTest
    public void init() {
        roleDao = new RoleDaoImpl();
    }

    @Test
    public void findAll() {
        roleDao.findAll();
    }

    @Test
    public void findOneById() {
        roleDao.findOneById("USER");
    }

    @Test void deleteById() throws SQLException {
        roleDao.deleteById("AA");
    }
}
