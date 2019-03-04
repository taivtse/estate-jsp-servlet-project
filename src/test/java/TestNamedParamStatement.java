import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.orm.builder.StatementBuilder;
import com.laptrinhjavaweb.orm.query.statement.NamedParamStatement;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class TestNamedParamStatement {
    @Test
    public void test() throws SQLException {
        String sql = StatementBuilder.buildUpdateStatement(UserEntity.class);
        NamedParamStatement statement = new NamedParamStatement(null, sql);
    }
}
