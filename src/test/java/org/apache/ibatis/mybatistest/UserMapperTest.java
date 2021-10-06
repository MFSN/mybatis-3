package org.apache.ibatis.mybatistest;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mybatistest.enums.UserSexEnum;
import org.apache.ibatis.mybatistest.mapper.UserMapper;
import org.apache.ibatis.mybatistest.model.StringWrapper;
import org.apache.ibatis.mybatistest.model.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class UserMapperTest {
    private SqlSessionFactory sqlSessionFactory;
    private UserMapper userMapper;

    @Before
    public void setUp() throws IOException {
        String resource = "org\\apache\\ibatis\\mybatistest\\mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        userMapper = sqlSessionFactory.openSession().getMapper(UserMapper.class);
        inputStream.close();
    }


    @Test
    public void testInsert() throws Exception {
        userMapper.insert(new User(" aa  ", "  a123456 ", UserSexEnum.MAN, new StringWrapper(" aaNickName ")));
        userMapper.insert(new User("  bb  ", "b123456   ", UserSexEnum.WOMAN, new StringWrapper(" bbNickName ")));
        userMapper.insert(new User("  cc  ", "c123456", UserSexEnum.WOMAN, new StringWrapper(" ccNickName ")));

        Assert.assertEquals(3, userMapper.getAll().size());
    }

    @Test
    public void testQuery() throws Exception {
        List<User> users = userMapper.getAll();
        if(users==null || users.size()==0){
            System.out.println("is null");
        }else{
            System.out.println(users.toString());
        }
    }


    @Test
    public void testUpdate() throws Exception {
        User user = userMapper.getOne(6l);
        System.out.println(user.toString());
        user.setNickName(new StringWrapper("neo"));
        userMapper.update(user);
        Assert.assertTrue(("neo".equals(userMapper.getOne(6l).getNickName())));
    }

    @Test
    public void testExecuteAnySelectSql() throws Exception {
        String sql = "select * from users;";
        List<Map> userMap = userMapper.executeAnySelectSql(sql);
        System.out.println(userMap);
    }
}
