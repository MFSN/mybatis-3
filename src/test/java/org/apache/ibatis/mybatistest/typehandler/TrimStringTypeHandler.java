package org.apache.ibatis.mybatistest.typehandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 【总结】1，因为mybatis注册了String对应的多个typeHandler，String可能对应VARCHAR,CHAR,CLOB等handler
 *       2，此时自己自定义一个TrimStringTypeHandler，仅仅是String对应的VARCHAR的handler，因此只会对varchar类型的字段起作用
 *       3，如果xml的resultMap或insert等statement的相应String类型字段没指定jdbcType为VARCHAR,此时不会起作用，此时起作用的还是内置
 *         Mybatis内置的StringTypeHandler，因为只要从数据库查出来的是字符串，那么jdbcType默认为CHAR类型，此时还是会取出Mybatis内置的
 *         StringTypeHandler来处理，而不是覆盖的TrimStringTypeHandler（因为对应VARCHAR）
 *      4，当然，某个字段指定对应的typeHandler也会生效
 *      TODO QUESTION:为何${value}的执行sql默认是varchar？注意，resultMap对${value}无效，因为起作用的是resultType。
 *      TODO 为何同事能指定LongTypeHandler有效呢？为何自己在公司用两个同样的trimStringTypeHandler，默认的都是同一个，如何指定默认的呢
 */
@MappedTypes(String.class)
@MappedJdbcTypes({JdbcType.VARCHAR})
public class TrimStringTypeHandler /*extends BaseTypeHandler<String>*/ implements TypeHandler<String> {

    public TrimStringTypeHandler(){
        System.out.println("===================TrimStringTypeHandler==================");
    }

    @Override
    public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.trim());
    }

    @Override
    public String getResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getString(columnName).trim();
    }

    @Override
    public String getResult(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getString(columnIndex).trim();
    }

    @Override
    public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }

    /*@Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.trim());
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getString(columnName).trim();
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getString(columnIndex).trim();
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return cs.getString(columnIndex).trim();
    }*/
}
