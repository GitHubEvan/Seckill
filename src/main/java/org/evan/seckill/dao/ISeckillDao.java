package org.evan.seckill.dao;

import org.evan.seckill.entity.Seckill;

import java.util.Date;
import java.util.List;

/**
 * Created by Evan on 9/13/2016.
 */
public interface ISeckillDao {

    /**
     *
     * @param seckillId
     * @param killTime
     * @return
     */
    int reduceNumber(long seckillId, Date killTime);

    Seckill queryById(long seckillId);

    List<Seckill> queryAll(int offset, int limit);
}
