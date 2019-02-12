import com.laptrinhjavaweb.core.util.ModelAnnotationUtil;
import com.laptrinhjavaweb.model.*;

public class TestAnnotation {
    public static void main(String[] args) {
        Role role = new Role();
        User user = new User();
        Assignment assignment = new Assignment();
        Building building = new Building();
        District district = new District();

        Object testObj = district;

        String sql1 = ModelAnnotationUtil.of(testObj).buildSelectStatement();
        System.out.println("SELECT ALL: \n" + sql1);


        String sql2 = ModelAnnotationUtil.of(testObj).buildSelectByIdStatement();
        System.out.println("\n\nSELECT BY ID: \n" + sql2);

        String sql3 = ModelAnnotationUtil.of(testObj).buildInsertStatement();
        System.out.println("\n\nINSERT: \n" + sql3);

        String sql4 = ModelAnnotationUtil.of(testObj).buildUpdateByIdStatement();
        System.out.println("\n\nUPDATE: \n" + sql4);

        String sql5 = ModelAnnotationUtil.of(testObj).buildDeleteByIdStatement();
        System.out.println("\n\nDELETE: \n" + sql5);
    }
}
