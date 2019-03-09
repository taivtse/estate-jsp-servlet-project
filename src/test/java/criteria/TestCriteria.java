package criteria;

import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.orm.query.criteria.Criteria;
import com.laptrinhjavaweb.orm.query.criteria.CriteriaImpl;
import com.laptrinhjavaweb.orm.query.criteria.criterion.Logical;
import com.laptrinhjavaweb.orm.query.criteria.criterion.MatchMode;
import com.laptrinhjavaweb.orm.query.criteria.criterion.expression.GroupExpression;
import com.laptrinhjavaweb.orm.query.criteria.criterion.projection.Projections;
import org.testng.annotations.Test;

public class TestCriteria {
    //    @Test
    public void testBuildWhere() {
        Criteria criteria = new CriteriaImpl(null, UserEntity.class);
//        criteria.add(Logical.and("username").eq("thanhtai"));
//        criteria.add(Logical.and("fullName").eq("1"));
//        criteria.add(Logical.or("fullName").eq("3"));
//        criteria.add(Logical.or("fullName").between("-1", "10"));

        GroupExpression groupExpression = Logical.andGroup();
        groupExpression.add(Logical.and("username").eq("1"))
                .add(Logical.and("fullName").like("ahihi", MatchMode.START))
                .add(Logical.and("fullName").between("-1", "10"));

        criteria.addCriterion(groupExpression);
    }

    @Test
    public void testProjection() {
        Criteria criteria = new CriteriaImpl(null, UserEntity.class);
//        criteria.setProjection(Projections.avg("id"));
        criteria.addSelection(Projections.countDistinct("id"));
//        criteria.setProjection(Projections.alias(Projections.rowCount(), "ahihi"));
    }
}
