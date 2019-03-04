import com.laptrinhjavaweb.orm.query.sqlquery.SqlQuery;
import com.laptrinhjavaweb.orm.session.Session;
import com.laptrinhjavaweb.orm.session.SessionFactory;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

public class TestCreateSqlQuery {

    @Test
    public void test() {
        Session session = SessionFactory.openSession();
        SqlQuery statement;
        try {
            statement = session.createSQLQuery("Select * from role where id = ?");
            statement.setParameter(1, "AA");

            List<Object[]> objectList = statement.list();

            objectList.forEach(objects -> {
                for (Object object : objects) {
                    System.out.print(object + "\t");
                }
                System.out.println();
            });

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
