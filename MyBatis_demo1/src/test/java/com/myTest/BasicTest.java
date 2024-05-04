package com.myTest;

import mappers.UserMapper;
import myClass.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import utils.sqlSessionUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class BasicTest {
    @Test
    void testInsert() throws IOException {
        //加载myBatis的核心配置文件
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();  //创建FactoryBuidler对象
        SqlSessionFactory factory = sqlSessionFactoryBuilder.build(is);   //工厂模式，得到factory对象
        SqlSession sqlSession = factory.openSession(true);  //得到sqlSession对象，该对象用于java与sql语句的数据库完成会话使用
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);  //得到mapper接口实现类的对象--相当jdbc中DAO接口的实现类对象

        int res1 = mapper.insertUser();   //对象该接口的对象调用其中的关于sql的方法
        //sqlSession.commit();   //mybatis核心配置文件中TransactionManger的类型属性为jdbc，需要手动提交事务
        System.out.println("result = " + res1);
    }

    @Test
    void testDelete() throws IOException {
        SqlSession sqlSession = sqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        int res2 = mapper.deleteUser();
        System.out.println("res2 = " + res2);
    }

    @Test
    void testUpdate() throws IOException {
        SqlSession sqlSession = sqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        int res3 = mapper.updateUser();
        System.out.println("res3 = " + res3);
    }

    @Test
    void testGetAllUser() throws IOException {
        SqlSession sqlSession = sqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List<User> list = mapper.getAllUser();
        list.forEach(user -> System.out.println(user));
    }

    /**
     * mybatis对于sql语句获取参数值的两种方式
     * 1.${} 本质为sql语句字符串拼接,若需要拼接到sql语句中的数据为sql的字符串,需要自己加单引号
     * 2.#{} 本质为占位符赋值,在赋值后会自动加单引号''变为字符串
     */

    @Test
    void testGetUserById() throws IOException {
        SqlSession sqlSession = sqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User u1 = mapper.getUserByID(3);
        System.out.println(u1);
    }

    @Test
    void getUserByIdAndName1() throws IOException {
        SqlSession sqlSession = sqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User jack = mapper.getUserByIdAndName1(3, "Jack");
        System.out.println(jack);
    }

    @Test
    void testInsertByUser() throws IOException {
        SqlSession sqlSession = sqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        int res = mapper.insertByUser(new User(null, "Mark", 22));
        System.out.println("res = " + res);
    }

    @Test
    void getUserByIdAndName2() throws IOException {
        SqlSession sqlSession = sqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        Map<String, Object> map = mapper.getUserByIdAndName2(11, "Mark");
        System.out.println(map);
    }

    /**
     * MyBatis中的查询功能，对于不同查询使用哪种类型来接受数据
     * 1. 查询一个字段的数据或一个结果   对应数据类型即可
     * 2. 查询一条记录   实体类对象，list集合，map集合
     * 3. 查询多条记录   list集合(集合中存储实体类对象或map)  map集合配合@MapKey注解
     * Mybatis中对于一个返回值类型的类默认配置了别名，无需额外配置或使用全类名
     */

    @Test
    void testGetCount() throws IOException {
        SqlSession sqlSession = sqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        int count = mapper.getCount();
        System.out.println("count = " + count);
    }

    @Test
    void testGetAllUserInMap() throws IOException {
        SqlSession sqlSession = sqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        Map<String, Object> users = mapper.getAllUserInMap(1, 2, 3);
        System.out.println(users);
    }

    /**
     *  Mybatis中一些情况传参时必须使用${}的方式--若使用#{}默认为字符串
     *  1. 模糊查询 like
     *  2. 范围内条件匹配 in
     *  3. 动态设置表名
     */

    @Test
    void testGetInMapByName() throws IOException {
        SqlSession sqlSession = sqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        Map<String, Object> map = mapper.getInMapByName("k");
        System.out.println(map);
    }

    @Test
    void testGetAutoKey() throws IOException {
        SqlSession sqlSession = sqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user = new User(null, "Bob", 18);

        int res = mapper.getAutoKey(user);
        System.out.println(user);
    }



}
