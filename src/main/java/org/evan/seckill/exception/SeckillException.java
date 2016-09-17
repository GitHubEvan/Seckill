package org.evan.seckill.exception;

/**
 * Created by Evan on 9/17/2016.
 */
public class SeckillException extends RuntimeException {

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
