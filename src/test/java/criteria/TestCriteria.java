package criteria;

import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.orm.criteria.Criteria;
import com.laptrinhjavaweb.orm.criteria.CriteriaImpl;
import com.laptrinhjavaweb.orm.criteria.criterion.Restrictions;
import org.testng.annotations.Test;

public class TestCriteria {
    @Test
    public void testBuildWhere() {
        Criteria criteria = new CriteriaImpl(null, UserEntity.class);
//        criteria.add(Restrictions.and().eq("username", "thanhtai"));
//        criteria.add(Restrictions.and().eq("fullName", "1"));
        criteria.add(Restrictions.or().eq("fullName", "3"));
//        criteria.add(Restrictions.or().between("fullName", "-1", "10"));
        criteria.add(Restrictions.or().junction(Restrictions.none().eq("username", "1"),
                Restrictions.or().eq("fullName", "haha")));
    }
}
