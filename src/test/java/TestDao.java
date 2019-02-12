import com.laptrinhjavaweb.dao.IRoleDao;
import com.laptrinhjavaweb.dao.IUserDao;
import com.laptrinhjavaweb.dao.impl.RoleDao;
import com.laptrinhjavaweb.dao.impl.UserDao;
import com.laptrinhjavaweb.model.RoleModel;
import com.laptrinhjavaweb.model.UserModel;

import java.util.List;

public class TestDao {
    public static void main(String[] args) throws Exception {
        /*IRoleDao roleDao = new RoleDao();
        List<RoleModel> list = roleDao.findAll();*/

        IUserDao userDao = new UserDao();
//        List<UserModel> list = userDao.findAll();
//        UserModel model = userDao.findById(1);

        UserModel model = new UserModel();
        model.setUsername("duyquang");
        model.setPassword("12345");
        model.setFullName("Trần Duy Quang");
        model.setRoleId("MANAGER");
        userDao.save(model);
        System.out.println(model.getId());
    }
}
