import com.laptrinhjavaweb.orm.util.IEntityUtil;
import com.laptrinhjavaweb.entity.*;

public class TestAnnotation {
    public static void main(String[] args) {
        Class<?> modelClass = BuildingEntity.class;

        String sql1 = IEntityUtil.of(modelClass).buildSelectStatement();
        System.out.println("SELECT ALL: \n" + sql1);


        String sql2 = IEntityUtil.of(modelClass).buildSelectByIdStatement();
        System.out.println("\n\nSELECT BY ID: \n" + sql2);

        String sql3 = IEntityUtil.of(modelClass).buildInsertStatement();
        System.out.println("\n\nINSERT: \n" + sql3);

        String sql4 = IEntityUtil.of(modelClass).buildUpdateByIdStatement();
        System.out.println("\n\nUPDATE: \n" + sql4);

        String sql5 = IEntityUtil.of(modelClass).buildDeleteByIdStatement();
        System.out.println("\n\nDELETE: \n" + sql5);
    }
}
