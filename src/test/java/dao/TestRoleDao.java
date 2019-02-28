package dao;

import com.laptrinhjavaweb.dao.RoleDao;
import com.laptrinhjavaweb.dao.impl.RoleDaoImpl;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

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
}
