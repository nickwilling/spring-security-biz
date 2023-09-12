package com.example.demo.controller;

import com.example.demo.exception.BizException;
import com.example.demo.exception.ExceptionEnum;
import com.example.demo.exception.ResultResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public ResultResponse bizExceptionHandler(HttpServletRequest req, BizException e){
        logger.error("发生业务异常！原因是：{}",e.getErrorMsg());
        return ResultResponse.error(e.getErrorCode(),e.getErrorMsg());
    }


    /**
     * 处理空指针的异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public ResultResponse exceptionHandler(HttpServletRequest req, NullPointerException e){
        logger.error("发生空指针异常！原因是: {}",e.getMessage());
        return ResultResponse.error(ExceptionEnum.BODY_NOT_MATCH);
    }

    @ExceptionHandler(value = DuplicateKeyException.class)
    @ResponseBody
    public ResultResponse exceptionHandler(HttpServletRequest req, DuplicateKeyException e){
        logger.error("发生唯一性约束异常！原因是: {}",e.getMessage());
        if(e.getMessage().contains("Duplicate entry")){
//            String[] split = e.getMessage().split("Duplicate entry");
            // 错误信息 [Duplicate entry 'chongfu' for key 'idx_username']
            // 在数组中拿到具体的重复信息 在数组索引位置的第三位 既split[2]
            String msg = "插入数据已存在";
            return ResultResponse.error(msg);
        }
        return ResultResponse.error(ExceptionEnum.INTERNAL_SERVER_ERROR);
    }

    /**
     * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultResponse exceptionHandler(HttpServletRequest req, Exception e){
        logger.error("未知异常！原因是: {}", e.getMessage());
        return ResultResponse.error(ExceptionEnum.INTERNAL_SERVER_ERROR);
    }
}
