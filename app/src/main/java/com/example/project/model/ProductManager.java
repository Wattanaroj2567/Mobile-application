package com.example.project.model;

import com.example.project.model.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    private static ProductManager instance;
    private List<Product> productList;

    private ProductManager() {
        productList = new ArrayList<>();
    }

    // ใช้ Singleton Pattern เพื่อให้คลาสมีเพียง instance เดียวในแอป
    public static ProductManager getInstance() {
        if (instance == null) {
            instance = new ProductManager();
        }
        return instance;
    }

    // เพิ่มสินค้า
    public void addProduct(Product product) {
        productList.add(product);
    }

    // คืนค่ารายการสินค้าทั้งหมด
    public List<Product> getProductList() {
        return productList;
    }

    // ล้างข้อมูลสินค้า (ถ้าจำเป็น)
    public void clearProductList() {
        productList.clear();
    }
}
