package ru.netology.ilyapodoinikov;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.ilyapodoinikov.domain.Customer;
import ru.netology.ilyapodoinikov.services.CustomerService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomerServiceTest extends OperationHistoryApiApplicationTest {
    @Autowired
    CustomerService customerService;

    @Test
    void addCustomerTest() {
        Customer customer = new Customer(3, "Alex");

        Customer addedCustomer = customerService.addCustomer(customer);

        assertEquals(customer, addedCustomer);
        assertTrue(customerService.getCustomers().contains(customer));
    }

    @Test
    void removeCustomerTest() {
        Customer customer1 = new Customer(1, "Spring");
        Customer customer2 = new Customer(2, "Boot");
        customerService.getCustomers().add(customer1);
        customerService.getCustomers().add(customer2);

        customerService.removeCustomer(1);

        assertTrue(customerService.getCustomers().contains(customer2));
    }

    @Test
    void removeCustomerNonExistingIdTest() {
        Customer customer1 = new Customer(1, "Spring");
        Customer customer2 = new Customer(2, "Boot");
        customerService.getCustomers().add(customer1);
        customerService.getCustomers().add(customer2);

        customerService.removeCustomer(3);

        assertTrue(customerService.getCustomers().contains(customer1));
        assertTrue(customerService.getCustomers().contains(customer2));
    }
}