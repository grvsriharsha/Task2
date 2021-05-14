package com.example.demo.controller;

import com.example.demo.entities.Item;
import com.example.demo.exception.InvalidDataException;
import com.example.demo.service.ItemService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {Task2Controller.class})
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    private MockMvc mvc;

    @Value("${service.route.path}")
    private String apiUrl;

    protected Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();


    @MockBean
    ItemService service;


    @BeforeClass
    public static void setUp() {

    }

    @Test
    public void testGetById() throws Exception {

        Item item = new Item();
        item.setId(5);
        given(this.service.getById(eq(item.getId()))).willReturn(item);
        mvc.perform(get("http://localhost:8084/" + apiUrl + "/5")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).
                andExpect(jsonPath("$.id", is(item.getId())));

    }


    @Test
    public void testWithWrongId() throws Exception {

        int id=5;
        Item item = new Item();
        item.setId(id);
        given(this.service.getById(eq(item.getId()))).willThrow(new InvalidDataException("Item with" +id +"doesn't exist"));
        MvcResult mvcResult = mvc.perform(get("http://localhost:8084/" + apiUrl + "/5")
                .contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().is4xxClientError()).andReturn();


        assertTrue(mvcResult.getResolvedException() instanceof InvalidDataException);
        assertTrue(mvcResult.getResolvedException().getMessage().contains("doesn't exist"));

    }

    @Test
    public void testGetAll() throws Exception {

        Item item = new Item();
        item.setId(5);
        given(this.service.getById(eq(item.getId()))).willReturn(item);
        mvc.perform(get("http://localhost:8084/" + apiUrl + "/all")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }

}