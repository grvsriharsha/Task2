package com.example.demo.controller;

import com.example.demo.DTO.Response;
import com.example.demo.dao.ItemRepositary;
import com.example.demo.entities.Item;
import com.example.demo.exception.InvalidDataException;
import com.example.demo.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${service.route.path}")
@Api(value = "MicroService4")
public class Task2Controller {


    @Autowired
    private ItemService itemService;

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Gets data by id")
    public Item getItem(@PathVariable(value = "id") int id) {
        return itemService.getById(id);
    }

    @GetMapping(path = "/all")
    @ApiOperation(value = "Gets data by id")
    public List<Response> getAllItems() {
        return itemService.getAll();
    }


}
