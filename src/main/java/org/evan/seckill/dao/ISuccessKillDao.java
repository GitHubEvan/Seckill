package org.evan.seckill.dao;

import org.evan.seckill.entity.SuccessKilled;

/**
 * Created by Evan on 9/13/2016.
 */
public interface ISuccessKillDao {

    /**
     *
     * @param seckillId
     * @param userPhone
     * @return 插入的行数
     */
    int insertSucessKilled(long seckillId,long userPhone);

    SuccessKilled queryByIdWithSeckill(long seckillId);
}
