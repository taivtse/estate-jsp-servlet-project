import com.laptrinhjavaweb.orm.util.IOrmEntityUtil;
import com.laptrinhjavaweb.entity.*;

public class TestAnnotation {
    public static void main(String[] args) {
        Class<?> modelClass = BuildingEntity.class;

        String sql1 = IOrmEntityUtil.of(modelClass).buildSelectStatement();
        System.out.println("SELECT ALL: \n" + sql1);


        String sql2 = IOrmEntityUtil.of(modelClass).buildSelectByIdStatement();
        System.out.println("\n\nSELECT BY ID: \n" + sql2);

        String sql3 = IOrmEntityUtil.of(modelClass).buildInsertStatement();
        System.out.println("\n\nINSERT: \n" + sql3);

        String sql4 = IOrmEntityUtil.of(modelClass).buildUpdateByIdStatement();
        System.out.println("\n\nUPDATE: \n" + sql4);

        String sql5 = IOrmEntityUtil.of(modelClass).buildDeleteByIdStatement();
        System.out.println("\n\nDELETE: \n" + sql5);
    }
}
