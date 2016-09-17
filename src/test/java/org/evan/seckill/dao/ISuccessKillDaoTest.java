package org.evan.seckill.dao;

import junit.framework.TestCase;
import org.evan.seckill.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Evan on 9/17/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class ISuccessKillDaoTest extends TestCase {

    @Resource
    private  ISuccessKillDao successKillDao;

    @Test
    public void testInsertSucessKilled() throws Exception {

       int b = successKillDao.insertSuccessKilled(1000L, 13012345678L);
        System.out.println(b);
    }

    @Test
    public void testQueryByIdWithSeckill() throws Exception {

        SuccessKilled successKilled = successKillDao.queryByIdWithSeckill(1000L);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }
}