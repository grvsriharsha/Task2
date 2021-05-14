package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Item")
@DynamicInsert
@JsonIgnoreProperties
public class Item implements Serializable {

    @Id
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private int id;

    @Column(name = "parentid")
    private int parentid;


    @Column(columnDefinition = "nvarchar")
    private String name;

    @Column(columnDefinition = "nvarchar")
    private String color;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }
}
