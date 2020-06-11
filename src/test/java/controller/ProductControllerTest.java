package controller;

import com.education.controllers.ProductController;
import com.education.entity.Product;
import com.education.entity.Type;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import service.mock.MockProductService;

import java.sql.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProductController.class, MockProductService.class})
@Transactional
@EnableAutoConfiguration
public class ProductControllerTest {
    @Autowired
    private ProductController productController;

    MockMvc mockMvc;

    private final static String URL = "http://localhost:8080/api/v1/product";

    ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {
        this.mockMvc = standaloneSetup(productController).build();
    }

    @Test
    public void findAllTest() throws Exception {
        mockMvc.perform(get(URL)).andExpect(status().isOk());
    }

    @Test
    public void findByIdTest() throws Exception {
        mockMvc.perform(get(String.format("%s/1", URL))).andExpect(status().isOk());
    }

    @Test
    public void createTest() throws Exception {
        Product product = new Product(4, "testProduct",
                Date.valueOf("2020-03-25"), 0, new Type(3, "Мороженое"));
        String requestJson = mapper.writeValueAsString(product);
        mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON_UTF8).content(requestJson)
        ).andExpect(status().isCreated());
    }

    @Test
    public void updateTest() throws Exception {
        Product product = new Product(1, "testProduct",
                Date.valueOf("2020-03-25"), 0, new Type(3, "Мороженое"));
        String requestJson = mapper.writeValueAsString(product);
        mockMvc.perform(put(URL).contentType(MediaType.APPLICATION_JSON_UTF8).content(requestJson)
        ).andExpect(status().isOk());
    }

    @Test
    public void deleteTest() throws Exception {
        mockMvc.perform(delete(String.format("%s/1", URL))).andExpect(status().isNoContent());
    }
}
