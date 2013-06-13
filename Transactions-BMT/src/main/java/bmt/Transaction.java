package bmt;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.RollbackException;
import javax.transaction.Status;
import javax.transaction.UserTransaction;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class Transaction {

    @PersistenceUnit(unitName = "primary")
    private EntityManagerFactory entityManagerFactory;

    @Inject
    private UserTransaction userTransaction;

    public String updateKeyValueDatabase(String key, String value) {
    	EntityManager entityManager = entityManagerFactory.createEntityManager();
    	try {
            
        	userTransaction.begin();           
            
        	entityManager.joinTransaction();
            String result = decipherInputValues(entityManager, key, value);
            
            userTransaction.commit();
            return result;
            
        } catch (RollbackException e) {
            // We tried to commit the transaction but it has already been rolled back
            Throwable t = e.getCause();
            if (key.equals("R")) return "You have used special rollback key. " +
            		"The transaction have been rolledback after adding Pair to database.";
            return t != null ? t.getMessage() :  e.getMessage();
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            try {
                if (userTransaction.getStatus() == Status.STATUS_ACTIVE)
                    userTransaction.rollback();
            } catch (Throwable e) {
                // ignore
            }
            entityManager.close();
        }    
    }

    @SuppressWarnings("unchecked")
	private String printOutDatabase(EntityManager entityManager) {
    	StringBuilder sb = new StringBuilder();
    	
    	final List<Pair> list = entityManager.createQuery("select k from Pair k").getResultList();

        for (Pair pair : list)
            sb.append(pair.getKey()).append("=").append(pair.getValue()).append(',');
    	return sb.toString();
	}

	private String decipherInputValues(EntityManager entityManager, String key, String value) {
		String result;
		if (key == null || key.length() == 0) {
            result = printOutDatabase(entityManager);
        } 
		else {
			result = updateDatabase(entityManager, key, value);
        }
		return result;
	}

	public String updateDatabase(EntityManager entityManager, String key, String value) {
        Pair pair = entityManager.find(Pair.class, key);
        if (value == null ) value = "";
        if (pair == null) pair = new Pair(key,value);
        pair.setValue(value);
        entityManager.merge(pair);
        
        if (key.equals("R"))
			try {
				// if key is "R" initiates rollback after adding Pair to database so setRollbackOnly has something to rollback
				userTransaction.setRollbackOnly();
			} catch (Exception e) {
				// ignore
			} 
        
        return pair.getKey()+"="+pair.getValue();
    }
}
