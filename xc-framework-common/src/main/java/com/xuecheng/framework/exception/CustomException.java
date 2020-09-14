package com.xuecheng.framework.exception;

import com.xuecheng.framework.model.response.ResultCode;

/**
 * @ClassName CustomException
 * @Description 自定义异常类
 * @Aothor zh
 * @Date 2020/9/13 21:57
 * @Version 1.0
 */
public class CustomException extends RuntimeException {

    private final ResultCode resultCode;

    public CustomException(ResultCode resultCode){
        super("错误代码:" + resultCode.code() + "错误信息：" + resultCode.message());
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode(){
        return this.resultCode;
    }
}
