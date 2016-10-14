/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.ntnu.tollefsen.mysite;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import no.ntnu.tollefsen.mysite.CustomerController.Customer;

/**
 *
 * @author mikael
 */
@Path("customer")
@RequestScoped
public class CustomerService {
    @Inject 
    CustomerController controller;
    
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public List<Customer> getHelloWorld() {
        return controller.getCustomers();
    }
    
}
