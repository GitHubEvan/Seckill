package org.evan.seckill.service.impl;

import org.evan.seckill.dao.ISeckillDao;
import org.evan.seckill.dao.ISuccessKillDao;
import org.evan.seckill.dto.Exposer;
import org.evan.seckill.dto.SeckillExcution;
import org.evan.seckill.entity.Seckill;
import org.evan.seckill.entity.SuccessKilled;
import org.evan.seckill.enums.SeckillStateEnum;
import org.evan.seckill.exception.RepeatSeckillException;
import org.evan.seckill.exception.SeckillCloseException;
import org.evan.seckill.exception.SeckillException;
import org.evan.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by Evan on 9/17/2016.
 */
public class SeckillServiceImpl implements SeckillService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ISeckillDao seckillDao;
    private ISuccessKillDao successKillDao;

    //ÓÃÓÚ»ìÏýMD5
    private final String slat = "23454656fsdgrd!@^%^&***uyy";

    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0, 10);
    }

    public Seckill getSeckillById(long id) {
        return seckillDao.queryById(id);
    }

    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = seckillDao.queryById(seckillId);
        if (null == seckill) {
            return new Exposer(false, seckillId);
        }

        Date start = seckill.getStartTime();
        Date end = seckill.getEndTime();
        Date now = new Date();

        if (now.getTime() < start.getTime() || now.getTime() > end.getTime()) {
            return new Exposer(false, seckillId, now.getTime(), start.getTime(), end.getTime());
        }

        String md5 = getMD5(seckillId);
        return new Exposer(true, md5, seckillId);
    }

    private String getMD5(long seckillId){
        String base = seckillId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    public SeckillExcution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, RepeatSeckillException, SeckillCloseException {
        if (null == md5 || !md5.equals(getMD5(seckillId))) {
            throw new SeckillException("seckill data rewrite");
        }

        try {
            //¼õ¿â´æ & ¼ÇÂ¼ÃëÉ±
            int updateCount = seckillDao.reduceNumber(seckillId, new Date());
            if (updateCount <= 0) {
                throw new SeckillCloseException("seckill is closed");
            } else {
                int insertCount = successKillDao.insertSuccessKilled(seckillId, userPhone);
                if (insertCount <= 0) {
                    throw new RepeatSeckillException("repeate seckill");
                } else {
                    SuccessKilled successKilled = successKillDao.queryByIdWithSeckill(seckillId);
                    return new SeckillExcution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
                }
            }
        } catch (SeckillCloseException e1) {
            throw e1;
        } catch (RepeatSeckillException e2) {
            throw e2;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new SeckillException("seckill inner error:" + ex.getMessage());
        }
    }
}
