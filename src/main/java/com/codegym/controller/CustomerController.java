package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.Province;
import com.codegym.service.ICustomerService;
import com.codegym.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IProvinceService provinceService;
    @ModelAttribute("provinces")
    public Iterable<Province> provinces(){
        return provinceService.findAll();
    }
    @GetMapping("/create-customer")
    private ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("customers/create");
        modelAndView.addObject("customer",new Customer());
        return modelAndView;
    }
    @PostMapping("/create-customer")
    private ModelAndView saveCustomer(@ModelAttribute ("customer") Customer customer){
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("customers/create");
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("message","New customer create successfully");
        return modelAndView;
    }
    @GetMapping("/customers")
//
    public ModelAndView listCustomers(@RequestParam("s") Optional<String> s, Pageable pageable){
        Page<Customer> customers;
        if(s.isPresent()){
            customers = customerService.findAllByFirstNameContaining(s.get(), pageable);
        } else {
            customers = customerService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("customers/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

}
