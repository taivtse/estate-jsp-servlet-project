package criteria;

import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.orm.criteria.Criteria;
import com.laptrinhjavaweb.orm.criteria.CriteriaImpl;
import com.laptrinhjavaweb.orm.criteria.criterion.Logical;
import org.testng.annotations.Test;

public class TestCriteria {
    @Test
    public void testBuildWhere() {
        Criteria criteria = new CriteriaImpl(null, UserEntity.class);
        criteria.add(Logical.and("username").eq("thanhtai"));
        criteria.add(Logical.and("fullName").eq("1"));
        criteria.add(Logical.or("fullName").eq("3"));
        criteria.add(Logical.or("fullName").between("-1", "10"));
//        criteria.add(Restrictions.or().junction(Restrictions.none().eq("username", "1"),
//                Restrictions.or().eq("fullName", "haha")));
    }
}
