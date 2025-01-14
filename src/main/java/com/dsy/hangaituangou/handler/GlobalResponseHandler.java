package com.dsy.hangaituangou.handler;

import com.dsy.hangaituangou.annotation.NotControllerResponseAdvice;
import com.dsy.hangaituangou.domain.vo.base.RespBase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice(basePackages = {"com.dsy.hangaituangou.controller.*"}) // 指定需要拦截的包
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    private final ObjectMapper objectMapper;

    public GlobalResponseHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supports(MethodParameter returnType,
                            @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        // 判断是否需要进行封装，如果方法上有 NotControllerResponseAdvice 注解，则不进行封装
        return !returnType.hasMethodAnnotation(NotControllerResponseAdvice.class);
    }

    @Override
    @NonNull
    public Object beforeBodyWrite(@NonNull Object body, @NonNull MethodParameter returnType, @NonNull MediaType selectedContentType,
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