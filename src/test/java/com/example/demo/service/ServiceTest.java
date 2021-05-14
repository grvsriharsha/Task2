package com.example.demo.service;

import com.example.demo.DTO.Response;
import com.example.demo.dao.ItemRepositary;
import com.example.demo.entities.Item;
import com.example.demo.exception.InvalidDataException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

public class ServiceTest {

    @InjectMocks
    private ItemService service;

    @Mock
    private ItemRepositary itemRepositary;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetResponse() throws Exception {

        Item item = new Item();
        item.setId(5);
        given(itemRepositary.findById(eq(item.getId()))).willReturn(java.util.Optional.of(item));
        assertEquals(service.getById(item.getId()),item);

    }

    @Test(expected = InvalidDataException.class)
    public void testGetException()  {

        Item item = new Item();
        item.setId(5);
        given(itemRepositary.findById(eq(item.getId()))).willThrow(new InvalidDataException("No item with such id exists"));
       service.getById(item.getId());

    }


    @Test
    public void testGetAll() throws Exception {

        Path path = Paths.get("src/test/resources/DAOoutput.json");
        FileReader fr = new FileReader(path.toFile());
        ObjectMapper mapper = new ObjectMapper();
        Item[] daooutputArray = (Item [] ) mapper.readValue(fr, Item[].class);
        List<Item> daooutputList = Arrays.asList(daooutputArray);
        given(itemRepositary.findAll()).willReturn(daooutputList);



        Path path1 = Paths.get("src/test/resources/Finaloutput.json");
         fr = new FileReader(path1.toFile());
         mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        Response[] finalOutputArray = (Response[] ) mapper.readValue(fr, Response[].class);
        List<Response> finalOutputList = Arrays.asList(finalOutputArray);

        assertEquals(service.getAll().size(),finalOutputList.size());

    }

}
