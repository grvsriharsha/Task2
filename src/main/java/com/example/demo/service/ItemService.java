package com.example.demo.service;

import com.example.demo.DTO.Response;
import com.example.demo.LogMethodParam;
import com.example.demo.dao.ItemRepositary;
import com.example.demo.entities.Item;
import com.example.demo.exception.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class ItemService {
    @Autowired
    private ItemRepositary itemRepositary;


    @LogMethodParam
    public Item getById(int id) {
        return Optional.ofNullable(itemRepositary.findById(id)).get().
                orElseThrow(() -> new InvalidDataException("Item with " + id + " doesn't exist"));
    }

    public List<Response> getAll() {

        //all the items with parent id 0 are root parents

        List<Item> items = itemRepositary.findAll();
        List<Item> parentList = items.stream().filter(x -> x.getParentid() == 0).collect(Collectors.toList());

        Map<Integer, List<Item>> map = itemRepositary.findAll().stream().collect(Collectors.groupingBy(x -> x.getParentid()));

        List<Response> responses = new ArrayList<>();
        for (Item parent : parentList) {
            Response response = new Response();
            response.setColor(parent.getColor());
            response.setName(parent.getName());
            responses.add(response);
            addRecursively(map.get(parent.getId()), response, map);
        }

        return responses;
    }


    public void addRecursively(List<Item> children, Response parentResponse, Map<Integer, List<Item>> hashMap) {

        if (children == null || children.isEmpty())
            return;
        for (Item child : children) {
            Response childResponse = new Response();
            childResponse.setColor(child.getColor());
            childResponse.setName(child.getName());
            parentResponse.addResponseToList(childResponse);
            addRecursively(hashMap.get(child.getId()), childResponse, hashMap);
        }
    }
}
