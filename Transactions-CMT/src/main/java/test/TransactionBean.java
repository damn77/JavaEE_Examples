package test;

import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;

import java.util.concurrent.Callable;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

@Stateless
@TransactionAttribute(REQUIRES_NEW)
public class TransactionBean implements Caller {
	
    public <V> V call(Callable<V> callable) throws Exception {
        return callable.call();
    }
}
