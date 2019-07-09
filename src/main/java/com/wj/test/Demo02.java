package com.wj.test;

import com.wj.mapper.MyMapper;
import com.wj.mybatis.My;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class Demo02 {

    @Test
    public void test1() throws Exception{
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-cfg.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MyMapper mapper = sqlSession.getMapper(MyMapper.class);

        My my = new My();
        my.setId(123);
        my.setMoney(100.0f);
        my.setName("test-mybatis");
        mapper.insert(my);
    }
}
