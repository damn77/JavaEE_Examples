package interceptors;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

@Interceptor
@Log
public class ExampleInterceptor implements Serializable {
    private static final long serialVersionUID = 8139854519874743530L;

    @Inject
    MessageBoard mb;
    
    @AroundInvoke
    public Object logMethodEntry(InvocationContext ctx) throws Exception {
        mb.setMessage("Intercepting entered method: " + ctx.getMethod().getName());
        return ctx.proceed();
    }
}
