package no.ntnu.tollefsen.mysite;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author mikael
 */
@Named
@RequestScoped
public class CustomerController {
    @Resource(name = "jdbc/sample")
    DataSource ds;
    
    List<Customer> customers;
    
    public List<Customer> getCustomers() {
        if(customers == null) {
            customers = new ArrayList<>();
            try(
                Connection c = ds.getConnection();
                Statement s = c.createStatement();
            ) {
                ResultSet rs = s.executeQuery("select customer_id,name,phone from customer order by name");
                while(rs.next()) {
                    customers.add(
                        new Customer(rs.getInt(1), rs.getString(2), rs.getString(3))
                    );
                }
            } catch(SQLException e) {
                Logger.getLogger(CustomerController.class.getName()).log(Level.INFO, "message");
            }
        }
        
        return customers;
    }
    
    @Data @AllArgsConstructor @NoArgsConstructor
    @XmlRootElement @XmlAccessorType(XmlAccessType.FIELD)
    public static class Customer implements Serializable {
        long id;
        String name;
        String phone;
    }
}
