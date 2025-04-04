package com.dsy.hangaituangou.handler;

import com.dsy.hangaituangou.annotation.NotControllerResponseAdvice;
import com.dsy.hangaituangou.domain.vo.base.RespBase;
import com.dsy.hangaituangou.exception.base.BusinessException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice(basePackages = {"com.dsy.hangaituangou.controller.*"}) // 指定需要拦截的包
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    private final ObjectMapper objectMapper;

    @Autowired
    public GlobalResponseHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supports(MethodParameter returnType,
                            @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        // 判断是否需要进行封装，如果方法上有 NotControllerResponseAdvice 注解，则不进行封装
        return !returnType.hasMethodAnnotation(NotControllerResponseAdvice.class);
    }

    // 处理认证相关异常
    @ExceptionHandler(BusinessException.class)
    public RespBase<String> handleAuthenticationException(BusinessException e) {
        log.error("业务异常：", e);
        return RespBase.fail("异常：" + e.getMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RespBase<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        String errorMessage = fieldErrors.stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        log.error("参数校验失败：{}", errorMessage);
        return RespBase.fail(400, errorMessage);
    }


    @Override
    @NonNull
    public Object beforeBodyWrite(Object body, @NonNull MethodParameter returnType, @NonNull MediaType selectedContentType,
                                  @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  @NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response) {
        // 如果已经是 RespBase 类型，则直接返回
        if (body instanceof RespBase) {
            return body;
        }
        // 如果返回的是 String 类型，需要特殊处理，否则会报错
        if (body instanceof String) {
            RespBase<String> resp = RespBase.success((String) body);
            try {
                return objectMapper.writeValueAsString(resp);
            } catch (JsonProcessingException e) {
                return RespBase.fail("返回String类型数据转换json异常");
            }
        }


        // 封装返回结果
        return RespBase.success(body);
    }
}