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
     * @return ���������
     */
    int insertSucessKilled(long seckillId,long userPhone);

    SuccessKilled queryByIdWithSeckill(long seckillId);
}
