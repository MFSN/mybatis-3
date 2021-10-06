package org.apache.ibatis.mybatistest.typehandler;


import org.apache.ibatis.mybatistest.enums.UserSexEnum;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(UserSexEnum.class)
@MappedJdbcTypes(JdbcType.INTEGER)
public class SexEnumTypeHandler implements TypeHandler<UserSexEnum> {

    public SexEnumTypeHandler() {
        System.out.println("=================SexEnumTypeHandler=====================");
    }
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, UserSexEnum userSexEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, userSexEnum.getId());
    }

    @Override
    public UserSexEnum getResult(ResultSet resultSet, String s) throws SQLException {
        int id = resultSet.getInt(s);
        return UserSexEnum.getUserSex(id);
    }

    @Override
    public UserSexEnum getResult(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt(i);
        return UserSexEnum.getUserSex(id);
    }

    @Override
    public UserSexEnum getResult(CallableStatement callableStatement, int i) throws SQLException {
        int id = callableStatement.getInt(i);
        return UserSexEnum.getUserSex(id);
    }
}
