package org.evan.seckill.service;

import junit.framework.TestCase;
import org.evan.seckill.dto.Exposer;
import org.evan.seckill.dto.SeckillExcution;
import org.evan.seckill.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Evan on 9/18/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class SeckillServiceTest extends TestCase {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void testGetSeckillList() throws Exception {

        List<Seckill> seckills = seckillService.getSeckillList();
        logger.info("list={}", seckills);
    }

    @Test
    public void testGetSeckillById() throws Exception {

        Seckill seckill = seckillService.getSeckillById(1000L);
        logger.info("seckill={}", seckill);
    }

    @Test
    public void testExportSeckillUrl() throws Exception {

        long id = 1002L;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("exposer={}", exposer);
    }

    @Test
    public void testExecuteSeckill() throws Exception {

        long id = 1000L;
        long userPhone = 13012345678L;
        String md5 = "dfsssssssss";
        SeckillExcution excution = seckillService.executeSeckill(id, userPhone, md5);
        logger.info("excution={}", excution);
    }

    @Test
    public void testSeckillLogic() {
        long id = 1002L;
        long userPhone = 13012345609L;

        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("exposer={}", exposer);
        if (exposer.isExposed()) {

            SeckillExcution excution = seckillService.executeSeckill(id, userPhone, exposer.getMd5());
            logger.info("excution={}", excution);
        }
    }
}