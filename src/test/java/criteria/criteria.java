package criteria;

import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.orm.criteria.Criteria;
import com.laptrinhjavaweb.orm.criteria.CriteriaImpl;
import com.laptrinhjavaweb.orm.criteria.criterion.MatchMode;
import com.laptrinhjavaweb.orm.criteria.criterion.Restrictions;
import org.testng.annotations.Test;

public class criteria {
    @Test
    public void testBuildWhere() {
        Criteria criteria = new CriteriaImpl(null, UserEntity.class);
        criteria.addSelectField("username");
        criteria.addSelectField("fullName");
        criteria.addWhere(Restrictions.eq("username", "thanhtai"));
        criteria.addWhere(Restrictions.like("username", "thanhtai123", MatchMode.ANYWHERE));
        criteria.addWhere(Restrictions.gt("username", "thanhtai12345"));
        criteria.addWhere(Restrictions.ne("password", "123"));
        criteria.addWhere(Restrictions.eq("fullName", "123"));
    }
}
