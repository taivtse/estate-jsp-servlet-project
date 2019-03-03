import com.laptrinhjavaweb.orm.builder.StatementBuilder;
import com.laptrinhjavaweb.entity.*;
import org.testng.annotations.Test;

public class TestAnnotation {
    @Test
    public void test() throws Exception {
        Class<?> modelClass = UserEntity.class;

        String sql1 = StatementBuilder.of(modelClass).buildSelectQuery();
        System.out.println("SELECT ALL: \n" + sql1);


        String sql2 = StatementBuilder.of(modelClass).buildSelectByIdQuery();
        System.out.println("\n\nSELECT BY ID: \n" + sql2);

        String sql3 = StatementBuilder.of(modelClass).buildInsertStatement();
        System.out.println("\n\nINSERT: \n" + sql3);

        String sql4 = StatementBuilder.of(modelClass).buildUpdateStatement();
        System.out.println("\n\nUPDATE: \n" + sql4);

        String sql5 = StatementBuilder.of(modelClass).buildDeleteStatement();
        System.out.println("\n\nDELETE: \n" + sql5);
    }
}
