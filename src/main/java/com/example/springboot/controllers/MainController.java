package com.example.springboot.controllers;

import com.example.springboot.models.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class MainController {

    List<Customer> customers=new ArrayList<>();
    List<Customer> femaleCustomers=new ArrayList<>();

    public MainController(){
        customers.add(new Customer(1,"petro"));
        customers.add(new Customer(5,"vasya"));
        customers.add(new Customer(2,"ivan"));

        femaleCustomers.add(new Customer(2,"masha"));
        femaleCustomers.add(new Customer(4,"ivanka"));
        femaleCustomers.add(new Customer(6,"yulia"));
    }

//    @GetMapping("/customers")
//    public List<Customer> getCustomers(){    //bad solution
//        return customers;
//    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getCustomers(){
        ResponseEntity<List<Customer>> listResponseEntity=new ResponseEntity<>(this.customers, HttpStatus.OK);
        return listResponseEntity;
    }
    @GetMapping("/femaleCustomers")
    public ResponseEntity<List<Customer>> getFemaleCustomers(){
        return new ResponseEntity<>(this.femaleCustomers,HttpStatus.OK);
    }

//    @PostMapping("/customers")
//    public void addCustomer(@RequestBody Customer customer){
//        System.out.println(customer);
//    }
    @PostMapping("/customers")
    public ResponseEntity<List<Customer>> addCustomer(@RequestBody Customer customer){
        this.customers.add(customer);
        System.out.println(customer);
        System.out.println(customers);
        return new ResponseEntity<>(this.customers,HttpStatus.CREATED);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable int id){
        Customer customer=this.customers.get(id-1);
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }

    @DeleteMapping("/customers/{x}")
    public ResponseEntity<List<Customer>> deleteCustomer(@PathVariable("x") int id){
        this.customers.remove(id-1);
        return new ResponseEntity<>(this.customers,HttpStatus.OK);
    }
    @PatchMapping("/customers/{id}")
    public ResponseEntity<Customer> updateName(@PathVariable int id,@RequestBody Customer obj){
        Customer customer1=this.customers.get(id-1);
        System.out.println(obj.getName());
        customer1.setName(obj.getName());
        return new ResponseEntity<>(customer1,HttpStatus.OK);
    }
    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateAllObject(@PathVariable int id,@RequestBody Customer obj){
        Customer customer1=this.customers.get(id-1);
        customer1.setId(obj.getId());
        customer1.setName(obj.getName());
        return new ResponseEntity<>(customer1,HttpStatus.OK);
    }

    @GetMapping("/hello")
    public String hello(){      //bad solution
        System.out.println("hello"); //in console
        return "Hello";  //in postman
    }
}
