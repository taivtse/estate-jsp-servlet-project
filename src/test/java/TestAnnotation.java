import com.laptrinhjavaweb.core.util.ModelAnnotationUtil;
import com.laptrinhjavaweb.model.Role;
import com.laptrinhjavaweb.model.User;

public class TestAnotation {
    public static void main(String[] args) {
        Role role = new Role();
        User user = new User();

        String sql = ModelAnnotationUtil.of(user).buildUpdateByIdStatement();
        System.out.println(sql);

    }
}
