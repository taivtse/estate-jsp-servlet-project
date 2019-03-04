package com.laptrinhjavaweb.orm.query.sqlquery;

import com.laptrinhjavaweb.orm.session.util.CloseExecutorUtil;
import com.laptrinhjavaweb.orm.query.statement.NamedParamStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlQueryImpl implements SqlQuery {
    private NamedParamStatement statement;

    public SqlQueryImpl(Connection connection, String sql) {
        try {
            this.statement = new NamedParamStatement(connection, sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResultSet list() {
        try {
            return this.statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return null;
    }

    @Override
    public int executeUpdate() {
        try {
            return this.statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return 0;
    }

    @Override
    public void setParameter(int index, Object parameter) {
        try {
            this.statement.setParamAt(index, parameter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setParameter(String namedParam, Object parameter) {
        try {
            this.statement.setNamedParam(namedParam, parameter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void close() {
        try {
            CloseExecutorUtil.closeNamedParamStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
