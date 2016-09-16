package org.evan.seckill.dao;

import junit.framework.TestCase;
import org.evan.seckill.entity.Seckill;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Evan on 9/17/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class ISeckillDaoTest extends TestCase {

    @Resource
    private ISeckillDao seckillDao;

    @Test
    public void testQueryById() throws Exception {

        long id = 1000L;
        Seckill seckill = seckillDao.queryById(id);
        Assert.assertTrue(id == seckill.getSeckillId());
    }

    public void testReduceNumber() throws Exception {

    }

    public void testQueryAll() throws Exception {

    }
}