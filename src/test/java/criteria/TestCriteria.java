package criteria;

import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.orm.criteria.Criteria;
import com.laptrinhjavaweb.orm.criteria.CriteriaImpl;
import com.laptrinhjavaweb.orm.criteria.criterion.Restriction;
import org.testng.annotations.Test;

public class TestCriteria {
    @Test
    public void testBuildWhere() {
        Criteria criteria = new CriteriaImpl(null, UserEntity.class);
        criteria.addRestriction(Restriction.and().eq("username", "thanhtai"));
        criteria.addRestriction(Restriction.and().eq("fullName", "1"));
        criteria.addRestriction(Restriction.or().eq("fullName", "tha2nhtai"));
        criteria.addRestriction(Restriction.or().eq("fullName", "3"));
    }
}
