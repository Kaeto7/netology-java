package ru.netology.ilyapodoinikov.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.ilyapodoinikov.controller.dto.CustomerDTO;
import ru.netology.ilyapodoinikov.controller.dto.CustomersGetResponse;
import ru.netology.ilyapodoinikov.domain.Customer;
import ru.netology.ilyapodoinikov.services.CustomerService;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public CustomersGetResponse getCustomers(){
        List<Customer> customers = customerService.getCustomers();
        List<CustomerDTO> customerDTOS = customers
                .stream()
                .map(customer -> new CustomerDTO(customer.getId(), customer.getName()))
                .collect(Collectors.toList());
        return new CustomersGetResponse(customerDTOS);
    }

    @GetMapping("{id}")
    public CustomerDTO getCustomer(@PathVariable int id){
        return customerService
                .getCustomers()
                .stream()
                .filter(customer -> customer.getId() == id)
                .map(customer -> new CustomerDTO(customer.getId(), customer.getName()))
                .findFirst().orElse(null);
    }

    @DeleteMapping("{customerId}")
    public ResponseEntity<Object> removeCustomer(@PathVariable int customerId) {
        customerService.removeCustomer(customerId);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> addCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer newCustomer = new Customer(customerDTO.getId(), customerDTO.getName());
        Customer addedCustomer = customerService.addCustomer(newCustomer);
        if (addedCustomer == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else {
            CustomerDTO addedCustomerDTO = new CustomerDTO(addedCustomer.getId(), addedCustomer.getName());
            return new ResponseEntity<>(addedCustomerDTO, HttpStatus.CREATED);
        }
    }


}