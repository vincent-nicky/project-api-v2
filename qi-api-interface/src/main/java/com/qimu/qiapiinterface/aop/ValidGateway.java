package com.qimu.qiapiinterface.aop;

import com.qimu.qiapiinterface.exception.BusinessException;
import com.qimu.qiapiinterface.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 验证请求是否通过了网关的验证
 */
@Aspect
@Component
public class ValidGateway {

    @Around("execution(* com.qimu.qiapiinterface.controller.*.*(..))")
    public Object doInterceptor(ProceedingJoinPoint point) throws Throwable {
        // 获取 session
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
        // 先判断是否通过网关
        String isPass = httpServletRequest.getHeader("X-ValidGateway-Header");
        if (isPass == null || !isPass.equals("passwsjdegateway")) {
            throw new BusinessException(ErrorCode.FORBIDDEN_ERROR, "非法请求，未通过网关验证！");
        }
        // 执行原方法
        return point.proceed();
    }
}
