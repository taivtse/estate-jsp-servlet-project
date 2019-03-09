package session;

import com.laptrinhjavaweb.entity.RoleEntity;
import com.laptrinhjavaweb.orm.session.Session;
import com.laptrinhjavaweb.orm.session.SessionFactory;
import com.laptrinhjavaweb.orm.transaction.Transaction;
import org.testng.annotations.Test;

public class TestSession {
    @Test
    public void test() {
        RoleEntity entity = new RoleEntity();
        entity.setCode("AA");
        entity.setName("HIXHIX");
        Session session = SessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
