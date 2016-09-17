package org.evan.seckill.dao;

import junit.framework.TestCase;
import org.evan.seckill.entity.Seckill;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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

    @Test
    public void testReduceNumber() throws Exception {

        Date killTime = new Date();
        int updateCount = seckillDao.reduceNumber(1000L,killTime);
        System.out.println(updateCount);
    }

    @Test
    public void testQueryAll() throws Exception {

        List<Seckill> seckills = seckillDao.queryAll(0, 10);
        for (Seckill seckill : seckills) {
            System.out.println(seckill);
        }
    }
}