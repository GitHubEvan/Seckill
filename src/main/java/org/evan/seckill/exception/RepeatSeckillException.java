package org.evan.seckill.exception;

/**
 * �ظ���ɱ�쳣
 * Created by Evan on 9/17/2016.
 */
public class RepeatSeckillException extends SeckillException {


    public RepeatSeckillException(String message) {
        super(message);
    }

    public RepeatSeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
