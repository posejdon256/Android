package com.example.annabujak.listazakupow;

/**
 * Created by pawel.bujak on 03.03.2017.
 */

public class Product {
    private int id;
    private String name;
    private int count;
    private String unit;
    public Product(){}
    public Product(int id, String name, int count, String unit){
        this.id = id;
        this.name = name;
        this.count = count;
        this.unit = unit;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setCount(int count){
        this.count = count;
    }
    public void setUnit(String unit){
        this.unit = unit;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public int getCount(){
        return count;
    }
    public String getUnit(){
        return unit;
    }
}
