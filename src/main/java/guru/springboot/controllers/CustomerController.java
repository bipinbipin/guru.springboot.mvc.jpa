package guru.springboot.controllers;

import guru.springboot.domain.Customer;
import guru.springboot.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Bipin on 6/14/2016.
 */
@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("customer/new")
    public String newCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/customerform";
    }

    @RequestMapping(value = "customer", method = RequestMethod.POST)
    public String saveCustomer(Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/customer/" + customer.getId();
    }

    @RequestMapping("customer/{id}")
    public String showCustomer(@PathVariable Integer id, Model model) {
        model.addAttribute("customer", customerService.getCustomerById(id));
        return "customer/show";
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("customers", customerService.listAllCustomer());
        return "customer/list";
    }

    @RequestMapping("customer/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("customer", customerService.getCustomerById(id));
        return "customer/form";
    }

    @RequestMapping("customer/delete/{id}")
    public String delete(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }
}
