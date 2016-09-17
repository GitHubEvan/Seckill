package org.evan.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.evan.seckill.entity.Seckill;

import java.util.Date;
import java.util.List;

/**
 * Created by Evan on 9/13/2016.
 */
public interface ISeckillDao {

    /**
     * @param seckillId
     * @param killTime
     * @return
     */
    int reduceNumber(@Param(value = "seckillId") long seckillId, @Param(value = "killTime") Date killTime);

    /**
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);

    /**
     * @param offset
     * @param limit
     * @return
     */
    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);
}
