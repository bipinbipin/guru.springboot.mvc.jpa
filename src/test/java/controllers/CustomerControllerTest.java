package controllers;

import guru.springboot.controllers.CustomerController;
import guru.springboot.domain.Customer;
import guru.springboot.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bipin on 6/17/2016.
 */
public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testList() throws Exception {

        List<Customer> customers = new ArrayList<Customer>();
        customers.add(new Customer());
        customers.add(new Customer());

        when(customerService.listAllCustomer()).thenReturn((List) customers);

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/list"))
                .andExpect(model().attribute("customers", hasSize(2)));

    }

    @Test
    public void testShow() throws Exception {
        Integer id = 1;

        when(customerService.getCustomerById(id)).thenReturn(new Customer());

        mockMvc.perform(get("/customer/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/show"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void testNewCustomer() throws Exception {
        verifyZeroInteractions(customerService);

        mockMvc.perform(get("/customer/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customerform"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void testSaveOrUpdate() throws Exception {

        Integer id = 1;
        String firstName = "Bipin";
        String lastName = "Butala";
        String email = "bipin@gmail.com";
        String phoneNumber = "612.231.4214";
        String address1 = "10404 Morgan Ave S";
        String address2 = "apt 2";
        String city = "Minneapolis";
        String state = "MN";
        String zipCode = "55431";

        Customer returnCustomer = new Customer();
        returnCustomer.setId(id);
        returnCustomer.setFirstName(firstName);
        returnCustomer.setLastName(lastName);
        returnCustomer.setEmail(email);
        returnCustomer.setPhoneNumber(phoneNumber);
        returnCustomer.setAddress1(address1);
        returnCustomer.setAddress2(address2);
        returnCustomer.setCity(city);
        returnCustomer.setState(state);
        returnCustomer.setZipCode(zipCode);

        when(customerService.saveCustomer(Matchers.<Customer>any())).thenReturn(returnCustomer);

        mockMvc.perform(post("/customer")
                .param("id", "1")
                .param("firstName", firstName)
                .param("lastName", lastName)
                .param("email", email)
                .param("phoneNumber", phoneNumber)
                .param("address1", address1)
                .param("address2", address2)
                .param("city", city)
                .param("state", state)
                .param("zipCode", zipCode))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(view().name("redirect:/customer/1"))
                    .andExpect(model().attribute("customer", instanceOf(Customer.class)))
                    .andExpect(model().attribute("customer", hasProperty("id", is(id))))
                    .andExpect(model().attribute("customer", hasProperty("firstName", is(firstName))))
                    .andExpect(model().attribute("customer", hasProperty("lastName", is(lastName))))
                    .andExpect(model().attribute("customer", hasProperty("email", is(email))))
                    .andExpect(model().attribute("customer", hasProperty("phoneNumber", is(phoneNumber))))
                    .andExpect(model().attribute("customer", hasProperty("address1", is(address1))))
                    .andExpect(model().attribute("customer", hasProperty("address2", is(address2))))
                    .andExpect(model().attribute("customer", hasProperty("city", is(city))))
                    .andExpect(model().attribute("customer", hasProperty("state", is(state))))
                    .andExpect(model().attribute("customer", hasProperty("zipCode", is(zipCode))));

        ArgumentCaptor<Customer> boundCustomer = ArgumentCaptor.forClass(Customer.class);
        verify(customerService).saveCustomer(boundCustomer.capture());

        assertEquals(id, boundCustomer.getValue().getId());
        assertEquals(firstName, boundCustomer.getValue().getFirstName());
        assertEquals(lastName, boundCustomer.getValue().getLastName());
        assertEquals(email, boundCustomer.getValue().getEmail());
        assertEquals(phoneNumber, boundCustomer.getValue().getPhoneNumber());
        assertEquals(address1, boundCustomer.getValue().getAddress1());
        assertEquals(address2, boundCustomer.getValue().getAddress2());
        assertEquals(city, boundCustomer.getValue().getCity());
        assertEquals(state, boundCustomer.getValue().getState());
        assertEquals(zipCode, boundCustomer.getValue().getZipCode());

    }

    @Test
    public void testDelete() throws Exception {
        Integer id = 1;

        mockMvc.perform(get("/customer/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customers"));

        verify(customerService, times(1)).deleteCustomer(id);
    }

}
