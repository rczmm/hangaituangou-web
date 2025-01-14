package com.dsy.hangaituangou.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD}) // 注解作用在方法上
@Retention(RetentionPolicy.RUNTIME) // 运行时有效
@Documented
public @interface NotControllerResponseAdvice {

}
