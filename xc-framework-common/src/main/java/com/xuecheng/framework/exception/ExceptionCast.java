package com.xuecheng.framework.exception;

import com.xuecheng.framework.model.response.ResultCode;

/**
 * @ClassName ExceptionCast
 * @Description  异常抛出类
 * @Aothor zh
 * @Date 2020/9/13 22:00
 * @Version 1.0
 */
public class ExceptionCast {

    //使用次静态方法抛出自定义异常
    public static void cast(ResultCode resultCode){
        throw new CustomException(resultCode);
    }
}
