import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.orm.builder.StatementBuilder;
import org.testng.annotations.Test;

public class TestAnnotation {
    @Test
    public void test() throws Exception {
        Class<?> modelClass = UserEntity.class;

        String sql1 = StatementBuilder.buildSelectQuery(modelClass);
        System.out.println("SELECT ALL: \n" + sql1);


        String sql2 = StatementBuilder.buildSelectByIdQuery(modelClass);
        System.out.println("\n\nSELECT BY ID: \n" + sql2);

        String sql3 = StatementBuilder.buildInsertStatement(modelClass);
        System.out.println("\n\nINSERT: \n" + sql3);

        String sql4 = StatementBuilder.buildUpdateStatement(modelClass);
        System.out.println("\n\nUPDATE: \n" + sql4);

        String sql5 = StatementBuilder.buildDeleteStatement(modelClass);
        System.out.println("\n\nDELETE: \n" + sql5);
    }
}
