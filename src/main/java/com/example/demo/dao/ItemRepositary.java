package com.example.demo.dao;

import com.example.demo.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public
interface ItemRepositary extends JpaRepository<Item,Integer> {

}
