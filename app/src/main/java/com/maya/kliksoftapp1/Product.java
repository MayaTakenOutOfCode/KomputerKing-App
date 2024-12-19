package com.maya.kliksoftapp1;

import java.util.Arrays;
import java.util.Dictionary;

public class Product {
    public String productName;
    public String productDesc;
    public String productPrice;
    public int id;

    public Product(String name, String desc, int price, int id){
        super();
        this.productName = name;
        this.productDesc = desc;
        this.productPrice = String.valueOf(price);
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public String getProductName() {
        return productName;
    }
    public String getProductDesc() {
        return productDesc;
    }
    public String getProductPrice() {
        return productPrice;
    }

}
