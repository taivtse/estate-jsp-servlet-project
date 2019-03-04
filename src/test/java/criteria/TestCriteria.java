package criteria;

import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.orm.query.criteria.Criteria;
import com.laptrinhjavaweb.orm.query.criteria.CriteriaImpl;
import com.laptrinhjavaweb.orm.query.criteria.criterion.Logical;
import com.laptrinhjavaweb.orm.query.criteria.criterion.MatchMode;
import com.laptrinhjavaweb.orm.query.criteria.criterion.impl.GroupExpression;
import org.testng.annotations.Test;

public class TestCriteria {
    @Test
    public void testBuildWhere() {
        Criteria criteria = new CriteriaImpl(null, UserEntity.class);
//        criteria.add(Logical.and("username").eq("thanhtai"));
//        criteria.add(Logical.and("fullName").eq("1"));
//        criteria.add(Logical.or("fullName").eq("3"));
//        criteria.add(Logical.or("fullName").between("-1", "10"));

        GroupExpression groupExpression = Logical.noPrefixGroup();
        groupExpression.add(Logical.noPrefix("username").eq("1"))
                .add(Logical.and("fullName").like("ahihi", MatchMode.START))
                .add(Logical.and("fullName").between("-1", "10"));

        criteria.add(groupExpression);
    }
}
