package com.chenle.gulimall.product.exception;

import com.chenle.common.exception.BizCodeEnum;
import com.chenle.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
//@ResponseBody
@RestControllerAdvice(basePackages = "com.chenle.gulimall.product.controller")
//集中处理所有异常
//@ControllerAdvice(basePackages = "com.chenle.gulimall.product.controller")
public class GulimallExceptionControllerAdvice {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleValidException(MethodArgumentNotValidException e) {
//        log.error("数据校验出现问题", e.getMessage(), e.getClass());
        BindingResult bindingResult = e.getBindingResult();
        Map<String, String> map = new HashMap<>();
        bindingResult.getFieldErrors().forEach((item) -> {
            map.put(item.getDefaultMessage(), item.getField());
        });
        return R.error(BizCodeEnum.VAILD_EXCEPTION.getCode(), BizCodeEnum.VAILD_EXCEPTION.getMessage()).put("data", map);
    }

    @ExceptionHandler(value = Throwable.class)
    public R handleException() {
        return R.error(BizCodeEnum.UNKNOW_EXCEPTION.getCode(), BizCodeEnum.UNKNOW_EXCEPTION.getMessage());
    }
}
