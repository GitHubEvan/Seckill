package org.evan.seckill.dao;

import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Evan on 9/17/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class ISuccessKillDaoTest extends TestCase {

    @Resource
    private  ISuccessKillDao successKillDao;

    public void testInsertSucessKilled() throws Exception {

    }

    public void testQueryByIdWithSeckill() throws Exception {

    }
}