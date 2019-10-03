package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("list");
        List<Customer> customers = customerService.findAll();
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("{id}")
    public ModelAndView showInformation(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("info");
        Customer customer = customerService.findOne(id);
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @PostMapping
    public String updateCustomer(Customer customer) {
        customerService.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("add")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("create");

        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("create")
    public String addCustomer(Customer customer){
        System.out.println(customer.getName());
        customerService.save(customer);
        return "redirect:/customers";

    }

    @GetMapping("{id}/delete")
    public ModelAndView deleteForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("delete");
        Customer customer = customerService.findOne(id);
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @PostMapping("{id}/del")
    public String deleteCustomer(@PathVariable Long id){
        customerService.delete(id);
        return "redirect:/customers";

    }

    @GetMapping("{id}/edit")
    public ModelAndView editForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        Customer customer = customerService.findOne(id);
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @PostMapping("edit")
    public String editCustomer(Customer customer){
        customerService.save(customer);
        return "redirect:/customers";

    }
}