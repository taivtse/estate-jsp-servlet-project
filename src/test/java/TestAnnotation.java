import com.laptrinhjavaweb.orm.builder.QueryBuilder;
import com.laptrinhjavaweb.entity.*;

public class TestAnnotation {
    public static void main(String[] args) throws Exception {
        Class<?> modelClass = AssignmentEntity.class;

        String sql1 = QueryBuilder.of(modelClass).buildSelectQuery();
        System.out.println("SELECT ALL: \n" + sql1);


        String sql2 = QueryBuilder.of(modelClass).buildSelectByIdQuery();
        System.out.println("\n\nSELECT BY ID: \n" + sql2);

        String sql3 = QueryBuilder.of(modelClass).buildInsertQuery();
        System.out.println("\n\nINSERT: \n" + sql3);

        String sql4 = QueryBuilder.of(modelClass).buildUpdateQuery();
        System.out.println("\n\nUPDATE: \n" + sql4);

        String sql5 = QueryBuilder.of(modelClass).buildDeleteQuery();
        System.out.println("\n\nDELETE: \n" + sql5);
    }
}
