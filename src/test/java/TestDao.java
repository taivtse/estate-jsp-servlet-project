import java.sql.JDBCType;

public class TestDao {
    public static void main(String[] args) throws Exception {
//        IRoleDao roleDao = new RoleDao();
//        List<RoleEntity> list = roleDao.findAll();
//
//        IUserDao userDao = new UserDao();
//        List<UserEntity> list = userDao.findAll();
//        UserEntity entity = userDao.findById(1);

//        UserEntity entity = new UserEntity();
//        entity.setUsername("duyquang");
//        entity.setPassword("12345");
//        entity.setFullName("Trần Duy Quang");
//        entity.setRoleId("MANAGER");
//        userDao.save(entity);
//        System.out.println(entity.getId());
        System.out.println(JDBCType.valueOf("string"));
    }
}
