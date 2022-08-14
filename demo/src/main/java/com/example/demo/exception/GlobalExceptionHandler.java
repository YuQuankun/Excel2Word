package com.example.demo.exception;

/**
 * @author 曳戈泰尔
 * @version 1.0
 * @description 曳戈泰尔
 * @date 2022/8/14 5:25 PM
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** 全局异常处理类 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理其他异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public String exceptionHandler(Exception e) {
        logger.error("未知异常！原因是:", e.getMessage());

        return "发生未知异常，请检查后重试。";
    }
}
