package com.mum.edu.jemaw.controller;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;










import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mum.edu.jemaw.model.Customer;
import com.mum.edu.jemaw.model.Person;
import com.mum.edu.jemaw.service.CustomerService;


@Controller
public class RegistrationController {
	
	@Autowired
    private CustomerService customerService;
	
	@RequestMapping(value="/register")
	public String addCustomer(Customer customer) {
		return "regForm";
	}

	@RequestMapping(value="/addCustomer")
	public String saveEmployee(@Valid @ModelAttribute  Customer customer, BindingResult bindingResult,
			Model model) {
 		
		if (bindingResult.hasErrors()) {
			return "regForm";
		}
		

		//customerService.saveCustomer(customer);
		
	    model.addAttribute("customer", customer);
	    
	   
		return "EmployeeDetails";
	}
	
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
//         binder.setDisallowedFields("id");
//        binder.setRequiredFields("username", "password", "emailAddress");
      
     binder.registerCustomEditor(Person.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
               Date date=new Date();
               SimpleDateFormat sdf=new SimpleDateFormat("mm/dd/yyyy");
                try {
					setValue(sdf.parse(text));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
       
    }
}
