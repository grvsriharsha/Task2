package com.example.demo.intgTests;

import com.example.demo.controller.Task2Controller;
import com.example.demo.entities.Item;
import com.example.demo.exception.InvalidDataException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {


    @Autowired
    TestRestTemplate testRestTemplate;

    @Value("${service.route.path}")
    private String apiUrl;


    @Test
    public void testGetById() throws Exception {

        ResponseEntity<Item> itemResponseEntity = testRestTemplate.exchange("/" + apiUrl + "/5",
                HttpMethod.GET,
                new HttpEntity<String>(null, new HttpHeaders())
                , Item.class);

        Item item = itemResponseEntity.getBody();
        assertEquals(5, item.getId());

    }


    @Test
    public void testWithWrongId() throws Exception {

        ResponseEntity<Item> itemResponseEntity = testRestTemplate.exchange("/" + apiUrl + "/555",
                HttpMethod.GET,
                new HttpEntity<String>(null, new HttpHeaders())
                , Item.class);
        assertEquals("Bad Request",itemResponseEntity.getStatusCode().getReasonPhrase());
        assertEquals(400,itemResponseEntity.getStatusCodeValue());
    }

    @Test
    public void testGetAll() throws Exception {

        ResponseEntity<Item[]> itemsResponseEntity = testRestTemplate.exchange("/" + apiUrl + "/all",
                HttpMethod.GET,
                new HttpEntity<String>(null, new HttpHeaders())
                , Item[].class);

        Item[] items = itemsResponseEntity.getBody();
        assertEquals(4, items.length);


    }

}