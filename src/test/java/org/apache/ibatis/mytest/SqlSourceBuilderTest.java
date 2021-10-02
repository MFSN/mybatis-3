package org.apache.ibatis.mytest;

import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.junit.Test;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 */
public class SqlSourceBuilderTest {
    /**
     * 测试#{age}不带任何其他配置，此时age是People的一个属性，此时javaType可以从People的getAge获取
     */
    @Test
    public void testOnlyPlaceHolder() {
        String sql = "SELECT * FROM People WHERE age = #{age}";
        SqlSourceBuilder sqlSourceBuilder = new SqlSourceBuilder(new Configuration());
        SqlSource sqlSource = sqlSourceBuilder.parse(sql, People.class, new HashMap<>());
        BoundSql boundSql = sqlSource.getBoundSql(new People());

        System.out.println(String.format("SQL: %s\n", boundSql.getSql()));
        System.out.println(String.format("ParameterMappings: %s", boundSql.getParameterMappings()));
    }

    /**
     * 测试#{age}不带任何其他配置，此时age是People的一个属性，此时javaType可以从People的getAge获取
     */
    @Test
    public void testPlaceHolderWithTypeHandler() {
        String sql = "SELECT * FROM People WHERE name = #{name, typeHandler=org.apache.ibatis.mytest.CustomStringTypeHandler}";
        SqlSourceBuilder sqlSourceBuilder = new SqlSourceBuilder(new Configuration());
        SqlSource sqlSource = sqlSourceBuilder.parse(sql, People.class, new HashMap<>());
        BoundSql boundSql = sqlSource.getBoundSql(new People());

        System.out.println(String.format("SQL: %s\n", boundSql.getSql()));
        System.out.println(String.format("ParameterMappings: %s", boundSql.getParameterMappings()));
    }
}

class People {
    private Integer id;
    private String name;
    private Integer age;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}



