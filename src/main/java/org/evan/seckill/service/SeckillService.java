package org.evan.seckill.service;

import org.evan.seckill.dto.Exposer;
import org.evan.seckill.dto.SeckillExcution;
import org.evan.seckill.entity.Seckill;
import org.evan.seckill.exception.RepeatSeckillException;
import org.evan.seckill.exception.SeckillCloseException;
import org.evan.seckill.exception.SeckillException;

import java.util.List;

/**
 * Created by Evan on 9/17/2016.
 */
public interface SeckillService {

    /**
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * @param id
     * @return
     */
    Seckill getSeckillById(long id);

    /**
     * 输出秒杀接口地址
     *
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀
     *
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExcution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, RepeatSeckillException, SeckillCloseException;
}
