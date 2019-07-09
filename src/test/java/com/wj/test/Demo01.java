package com.wj.test;

import com.wj.mapper.MyMapper;
import com.wj.mybatis.My;
import com.wj.mybatis.MyExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class Demo01 {


    private SqlSession sqlSession;

    @Before
    public void before() throws Exception{
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-cfg.xml"));
        sqlSession = sqlSessionFactory.openSession();;
    }

    @Test
    public void test1() throws Exception{
        MyMapper mapper = sqlSession.getMapper(MyMapper.class);
        My my = new My();
        my.setId(123);
        my.setMoney(100.0f);
        my.setName("test-mybatis");
        mapper.insert(my);
        sqlSession.commit();
    }

    @Test
    public void test2() throws Exception {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-cfg.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MyMapper mapper = sqlSession.getMapper(MyMapper.class);
        MyExample example = new MyExample();
        MyExample.Criteria criteria = example.createCriteria();
        List<My> myList = mapper.selectByExample(example);
        for (My my : myList) System.out.println(my);
    }

    @Test
    public void test3() throws Exception {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-cfg.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession(true);  //设置事务自动提交
        MyMapper mapper = sqlSession.getMapper(MyMapper.class);
        MyExample example = new MyExample();
        MyExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo("test-mybatis");
        int i = mapper.deleteByExample(example);
        System.out.println("删除行数为：" + i);
    }

    @Test
    public void test4() throws Exception {
        MyMapper mapper = sqlSession.getMapper(MyMapper.class);
        MyExample example = new MyExample();
        MyExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(123);
        List<My> myList = mapper.selectByExample(example);
        if (myList != null && myList.size() == 1) {
            My my = myList.get(0);
            my.setName("test");
            MyExample example2 = new MyExample();
            MyExample.Criteria criteria1 = example2.createCriteria();
            criteria1.andIdEqualTo(my.getId());
            int i = mapper.updateByExample(my,example2);
            sqlSession.commit();
        }
    }

    @After
    public void close() {
        if (null != sqlSession) sqlSession.close();
    }
}
