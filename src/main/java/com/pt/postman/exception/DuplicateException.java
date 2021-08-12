package com.pt.postman.exception;

/**
 * @author nate-pt
 * @date 2021/8/12 14:10
 * @Since 1.8
 * @Description
 */
public class DuplicateException  extends  Exception{
    public DuplicateException(String message){
        super(message);
    }
}
