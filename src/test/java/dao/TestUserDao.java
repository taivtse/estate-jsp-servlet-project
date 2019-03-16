package dao;

import com.laptrinhjavaweb.dao.UserDao;
import com.laptrinhjavaweb.dao.util.SingletonDaoUtil;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.orm.query.criteria.criterion.Criterion;
import com.laptrinhjavaweb.orm.query.criteria.criterion.Logical;
import com.laptrinhjavaweb.orm.query.criteria.criterion.MatchMode;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class TestUserDao {
    UserDao userDao;

    @BeforeTest
    public void init() {
        userDao = SingletonDaoUtil.getUserDaoInstance();
    }

    @Test
    public void findAll() {
        userDao.findAll();
    }

    @Test
    public void findOneById() {
        userDao.findOneById(1);
    }

    @Test
    public void findByProperties() {
        List<Criterion> criterionList = new ArrayList<>();
        criterionList.add(Logical.and("username").like("duy", MatchMode.START));
        criterionList.add(Logical.and("id").between(2, 3));
        List<UserEntity> list = userDao.findAllByProperties(null, criterionList);
    }

    @Test
    public void rowCount() {
        List<Criterion> criterionList = new ArrayList<>();
        criterionList.add(Logical.and("username").like("duy", MatchMode.START));
        Long count = userDao.countByProperties(criterionList);
    }

}
