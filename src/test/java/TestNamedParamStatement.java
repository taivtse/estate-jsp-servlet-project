import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.orm.statement.NamedParamStatement;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class TestNamedParamStatement {
    @Test
    public void test() throws SQLException {
        String sql = StatementBuilder.of(UserEntity.class).buildUpdateStatement();
        NamedParamStatement statement = new NamedParamStatement(null, sql);
    }
}
