package org.evan.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.evan.seckill.entity.SuccessKilled;

/**
 * Created by Evan on 9/13/2016.
 */
public interface ISuccessKillDao {

    /**
     * @param seckillId
     * @param userPhone
     * @return 插入的行数
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

    /**
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(long seckillId);
}
