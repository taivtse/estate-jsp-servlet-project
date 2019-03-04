import com.laptrinhjavaweb.orm.session.Session;
import com.laptrinhjavaweb.orm.session.SessionFactory;
import com.laptrinhjavaweb.orm.query.statement.NamedParamStatement;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class TestCreateSqlQuery {

    @Test
    public void test() {
        Session session = SessionFactory.openSession();
        NamedParamStatement statement = session.createSQLQuery("Select * from role where id = {id}");
        try {
            statement.setNamedParam("id", "AA");
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            session.close();
        }

    }
}
