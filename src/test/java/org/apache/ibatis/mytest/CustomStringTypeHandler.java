package org.apache.ibatis.mytest;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomStringTypeHandler extends BaseTypeHandler<String> {
    public CustomStringTypeHandler(){}
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException {

    }

    public String getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String name = resultSet.getString(s);
        return name.replace(name.substring(1,2),"*");
    }

    public String getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String name = resultSet.getString(i);
        return name.replace(name.substring(1,2),"*");
    }

    public String getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String name = callableStatement.getString(i);
        return name.replace(name.substring(1,2),"*");
    }
}