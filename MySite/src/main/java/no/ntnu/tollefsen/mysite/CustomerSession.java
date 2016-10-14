package no.ntnu.tollefsen.mysite;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author mikael
 */
@Stateless
@Path("myservice")
public class CustomerSession {
    @PersistenceContext
    EntityManager em;
    
    @GET
    public List<Customer> getAllCustomers() {
        return em.createQuery("SELECT c from Customer c").getResultList();
    }
    
    public boolean createCustomer(Customer customer) {
        em.persist(customer);
        return true;
    }
}
