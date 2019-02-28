import com.laptrinhjavaweb.dao.UserDao;
import com.laptrinhjavaweb.dao.impl.UserDaoImpl;
import com.laptrinhjavaweb.entity.UserEntity;

import java.util.List;

public class TestDao {
    public static void main(String[] args) throws Exception {
        UserDao userDao = new UserDaoImpl();

        List<UserEntity> list = userDao.findAll();
        System.out.printf(list.get(0).getFullName());

//        UserEntity entity = new UserEntity();
//        entity.setUsername("duyquang");
//        entity.setPassword("12345");
//        entity.setFullName("Trần Duy Quang");
//        entity.setRoleId("MANAGER");
//        userDaoImpl.save(entity);
//        System.out.println(entity.getId());
    }
}
