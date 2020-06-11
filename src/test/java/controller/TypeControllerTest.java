package controller;

import com.education.controllers.TypeController;
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
import service.mock.MockTypeService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TypeController.class, MockTypeService.class})
@Transactional
@EnableAutoConfiguration
public class TypeControllerTest {
    @Autowired
    private TypeController typeController;

    MockMvc mockMvc;

    private final static String URL = "http://localhost:8080/api/v1/type";

    ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {
        this.mockMvc = standaloneSetup(typeController).build();
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
        Type type = new Type(4, "typeTest");
        String requestJson = mapper.writeValueAsString(type);
        mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON_UTF8).content(requestJson)
        ).andExpect(status().isCreated());
    }

    @Test
    public void updateTest() throws Exception {
        Type type = new Type(1, "typeTest");
        String requestJson = mapper.writeValueAsString(type);
        mockMvc.perform(put(URL).contentType(MediaType.APPLICATION_JSON_UTF8).content(requestJson)
        ).andExpect(status().isOk());
    }

    @Test
    public void deleteTest() throws Exception {
        mockMvc.perform(delete(String.format("%s/1", URL))).andExpect(status().isNoContent());
    }
}
