package org.apache.ibatis.mybatistest;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserMapperTest {
    private SqlSessionFactory sqlSessionFactory;
    private SqlSession sqlSession;
    private UserMapper userMapper;

    @Before
    public void setUp() throws IOException {
        String resource = "org\\apache\\ibatis\\mybatistest\\mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);// 新建一个SqlSessionFactoryBuilder实例，用来构建SqlSessionFactory实例
        sqlSession = sqlSessionFactory.openSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
        inputStream.close();
    }


    @Test
    public void testInsert() throws Exception {
        userMapper.insert(new User(" aaa6  ", "  a6 ", UserSexEnum.MAN, new StringWrapper(" aaNickName6 ")));
        userMapper.insert(new User("  bbb6  ", "b6   ", UserSexEnum.WOMAN, new StringWrapper(" bbNickName6 ")));
        userMapper.insert(new User("  cc6  ", "c6", UserSexEnum.WOMAN, new StringWrapper(" ccNickName6 ")));
/*        sqlSession.commit();
        sqlSession.close();*/
        //Assert.assertEquals(3, userMapper.getAll().size());
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
    public void testQueryPage() throws Exception {
        Integer pageNum = 1;
        Integer pageSize = 2;
        PageHelper.startPage(pageNum, pageSize, true);
        List<User> list = userMapper.getAll();
        System.out.println(list);
    }


    @Test
    public void testUpdate() throws Exception {
        User user = userMapper.getOne(71L);
        System.out.println(user.toString());
        user.setNickName(new StringWrapper("yuanmabiji"));
        userMapper.update(user);
        Assert.assertTrue(("yuanmabiji".equals(userMapper.getOne(71L).getNickName())));
    }

    @Test
    public void testExecuteAnySelectSql() throws Exception {
        String sql = "select * from users;";
        List<Map> userMap = userMapper.executeAnySelectSql(sql);
        System.out.println(userMap);
    }
}
