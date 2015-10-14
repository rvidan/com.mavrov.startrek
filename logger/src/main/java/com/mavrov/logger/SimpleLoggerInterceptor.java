package com.mavrov.logger;

import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

/**
 * @author serg.mavrov@gmail.com
 */
@Interceptor
@SimpleLogger
public class SimpleLoggerInterceptor implements Serializable {

    private static final int MILLISECONDS = 1000;
    private static final String NULL = "null";
    private static final long serialVersionUID = -3125053202646594241L;

    @Inject
    private transient Logger logger;

    @AroundInvoke
    public Object logRunningCode(InvocationContext ctx) throws Exception {//NOPMD
        // there is a conflict between SONAR/CDI
        final SimpleLogger annotation = ctx.getMethod().getAnnotation(SimpleLogger.class);
        final boolean statistic = annotation.statistic();
        final boolean input = annotation.input();
        final boolean output = annotation.output();
        // -=-=-=-
        if (!annotation.logMessage().trim().isEmpty()) {
            logger.debug(annotation.logMessage());
        }
        // -=-=-=-
        final String clearMethodName = takeClearMethodName(ctx);
        logger.debug("The method name: " + clearMethodName);
        logger.debug("The content data: " + ctx.getContextData().toString());
        // -=-=-=-=-=-
        if (input) {
            logger.info(" -> method {" + ctx.getMethod().getDeclaringClass().getName() +
                    "}" + ctx.getMethod().getName() + " started...");
            Object[] parameters = ctx.getParameters();
            StringBuffer sb = new StringBuffer(" -> parameters : ");
            for (int i = 0; i < parameters.length; i++) {
                final boolean isNull = parameters[i] != null;
                String simpleName = isNull ? parameters[i].getClass().getSimpleName() : NULL;
                String simpleValue = parameters[i] != null ? String.valueOf(parameters[i]) : NULL;
//            for (Object parameter : ctx.getParameters()) {
                sb.append("N").append(i).append(":type[").append(simpleName).append("]=").append(simpleValue).append(";");
            }
            logger.info(sb.toString());
        }
        try {
            long start = System.currentTimeMillis();
            Object result = ctx.proceed();
            long finish = System.currentTimeMillis();
            if (output) {
                logger.info(" <- method " + clearMethodName + " finished...");
                String resultType = (result == null ? null : result.getClass().getSimpleName());
                logger.info(" <- method " + clearMethodName + " result: type[" + resultType + "]=" + result);
            }
            if (statistic) {
                logger.info(" <- method " + clearMethodName + " statistic: time= " +
                        (finish - start) / MILLISECONDS + " sec.");
            }
            return result;
            // -=-=-=-=-=-
        } catch (Exception e) {
            logger.error(" <+ method " + clearMethodName + " crashed...");
            logger.error(" <+ method exception: type[" + e.getClass().getSimpleName() + "]=" + e.getMessage());
            throw e;
        }
    }

    private String takeClearMethodName(InvocationContext ctx) {
        return "{" + ctx.getMethod().getDeclaringClass().getName() + "}" + ctx.getMethod().getName();
    }

}
