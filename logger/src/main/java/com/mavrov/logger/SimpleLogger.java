package com.mavrov.logger;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;
import java.lang.annotation.*;

@Inherited
@InterceptorBinding
@Retention(value = RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface SimpleLogger {

    @Nonbinding boolean statistic() default false;

    @Nonbinding
    boolean input() default false;

    @Nonbinding boolean output() default false;

    @Nonbinding String logMessage() default "";

}
