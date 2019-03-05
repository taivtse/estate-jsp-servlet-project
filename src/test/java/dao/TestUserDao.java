package dao;

import com.laptrinhjavaweb.dao.UserDao;
import com.laptrinhjavaweb.dao.impl.UserDaoImpl;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.orm.query.criteria.criterion.Criterion;
import com.laptrinhjavaweb.orm.query.criteria.criterion.Logical;
import com.laptrinhjavaweb.orm.query.criteria.criterion.MatchMode;
import com.laptrinhjavaweb.orm.query.criteria.criterion.NamedParam;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void test(){
        NamedParam namedParam = new NamedParam("aaaa", "bbbb");
        namedParam = this.test1(namedParam);
    }
    public NamedParam test1(NamedParam namedParam){
        namedParam = new NamedParam("ahihi", "uhuhu");
        return namedParam;
    }
}
