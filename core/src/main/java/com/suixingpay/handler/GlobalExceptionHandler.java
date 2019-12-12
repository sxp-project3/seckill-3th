package com.suixingpay.handler;

import com.suixingpay.enumeration.CodeEnum;
import com.suixingpay.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * @author 詹文良
 * @program: seckill-3th
 * @description: 全局异常处理器
 * <p>
 * Created by Jalr4ever on 2019/12/12.
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * 日志打印器
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 默认异常处理
     *
     * @param e 祖先异常类
     * @return 其他类型的异常信息
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Response exceptionHandler(Exception e) {
        LOGGER.warn(e.getMessage());
        return Response.getInstance(CodeEnum.FAIL, e.getMessage());
    }

    /**
     * 方法参数异常处理器
     *
     * @param manve 方法参数异常类
     * @return 其他服务器异常信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Response methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException manve) {
        BindingResult bindingResult = manve.getBindingResult();
        StringBuilder sb = new StringBuilder();
        if (bindingResult.hasErrors()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {

                // 注解的 message
                sb.append(fieldError.getDefaultMessage()).append(" : ");

                // 参数字段
                sb.append(fieldError.getField()).append(" is ");

                // 参数错误值
                sb.append(fieldError.getRejectedValue()).append(" 、");

            }
        }
        sb.deleteCharAt(sb.length() - 1);
        LOGGER.error(sb.toString());
        return Response.getInstance(CodeEnum.FAIL, sb.toString());
    }

    /**
     * 参数校验异常处理器
     *
     * @param cve 参数校验异常类
     * @return 参数校验异常信息
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public Response constraintViolationExceptionHandler(ConstraintViolationException cve) {
        Set<ConstraintViolation<?>> constraintViolations = cve.getConstraintViolations();
        StringBuilder sb = new StringBuilder();
        for (ConstraintViolation violation : constraintViolations) {

            // 注解的 message
            sb.append(violation.getMessage()).append(" : ");

            // 参数字段
            sb.append(violation.getPropertyPath()).append(" is ");

            // 参数错误值
            sb.append(violation.getInvalidValue()).append("、");

        }
        sb.deleteCharAt(sb.length() - 1);
        LOGGER.error(sb.toString());
        return Response.getInstance(CodeEnum.FAIL, sb.toString());
    }
}
