package org.acme.interceptor;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.ws.rs.Priorities;

@MyInterceptor
@Interceptor
@Priority(Priorities.USER)
public class MyInterceptorImpl {

    @AroundInvoke
    Object myInterceptor(InvocationContext context) {
        Object ret = null;
        try {
            ret = context.proceed();
        } catch (Exception e) {}
        return ret;
    }

}
