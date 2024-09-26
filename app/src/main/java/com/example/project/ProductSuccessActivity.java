package com.example.project;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.project.model.Product;
import com.example.project.model.ProductManager;
import java.util.List;

public class ProductSuccessActivity extends AppCompatActivity {

    private TextView tvSuccessMessage, tvTitle;
    private ImageView successIcon, deleteIcon;
    private RecyclerView recyclerViewProducts;
    private ProductSuccessAdapter productSuccessAdapter;
    private Button btnOrder, btnBackToHome; // เพิ่มปุ่มกลับไปหน้าแรก
    private List<Product> productList; // เก็บรายการสินค้าทั้งหมด

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_success);

        // เชื่อมต่อกับ View ใน Layout
        tvSuccessMessage = findViewById(R.id.tvSuccessMessage);
        successIcon = findViewById(R.id.success_icon);
        deleteIcon = findViewById(R.id.delete_icon);
        recyclerViewProducts = findViewById(R.id.recycler_view_products);
        tvTitle = findViewById(R.id.tvTitle);
        btnOrder = findViewById(R.id.btnOrder);
        btnBackToHome = findViewById(R.id.btnBackToHome); // เชื่อมต่อกับปุ่มกลับไปหน้าแรก

        // ดึงรายการสินค้าจาก ProductManager
        productList = ProductManager.getInstance().getProductList();

        // ตั้งค่า RecyclerView และ Adapter
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(this));
        productSuccessAdapter = new ProductSuccessAdapter(productList, this);
        recyclerViewProducts.setAdapter(productSuccessAdapter);

        // แสดงข้อความสำหรับสินค้าล่าสุด
        if (!productList.isEmpty()) {
            Product lastAddedProduct = productList.get(productList.size() - 1); // ดึงสินค้าล่าสุด
            tvSuccessMessage.setText("เพิ่มสินค้า " + lastAddedProduct.getName() + " เรียบร้อยแล้ว!");
            successIcon.setVisibility(View.VISIBLE); // แสดงไอคอนถูก
            deleteIcon.setVisibility(View.GONE); // ซ่อนไอคอนผิด
        } else {
            tvSuccessMessage.setText("ยังไม่มีสินค้าเพิ่ม");
            successIcon.setVisibility(View.GONE); // ซ่อนไอคอนถูก
            deleteIcon.setVisibility(View.VISIBLE); // แสดงไอคอนผิด
        }

        // ฟังก์ชันคลิกที่โลโก้เพื่อกลับไปที่ MainActivity
        tvTitle.setOnClickListener(v -> {
            Intent mainActivityIntent = new Intent(ProductSuccessActivity.this, MainActivity.class);
            mainActivityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(mainActivityIntent);
            finish(); // ปิดหน้า ProductSuccessActivity ทันที
        });

        // ฟังก์ชันปุ่ม "สั่งซื้อ"
        btnOrder.setOnClickListener(v -> {
            Intent orderResultIntent = new Intent(ProductSuccessActivity.this, OrderResultActivity.class);
            if (!productList.isEmpty()) {
                // มีสินค้าอยู่ ส่งค่าสถานะการสั่งซื้อสำเร็จ
                orderResultIntent.putExtra(OrderResultActivity.EXTRA_ORDER_STATUS, true);
            } else {
                // ไม่มีสินค้า ส่งค่าสถานะการสั่งซื้อไม่สำเร็จ
                orderResultIntent.putExtra(OrderResultActivity.EXTRA_ORDER_STATUS, false);
            }
            startActivity(orderResultIntent);
            finish(); // ปิดหน้า ProductSuccessActivity
        });

        // ฟังก์ชันปุ่มกลับไปหน้าแรก
        btnBackToHome.setOnClickListener(v -> {
            Intent mainActivityIntent = new Intent(ProductSuccessActivity.this, MainActivity.class);
            mainActivityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(mainActivityIntent);
            finish(); // ปิดหน้า ProductSuccessActivity
        });

        // Animation สำหรับข้อความ
        animateSuccessMessage();
    }

    // ฟังก์ชันแสดงข้อความเมื่อรายการสินค้าถูกลบ
    public void showDeletionMessage(String productName) {
        tvSuccessMessage.setText("ลบสินค้า " + productName);
        successIcon.setVisibility(View.GONE); // ซ่อนไอคอนถูก
        deleteIcon.setVisibility(View.VISIBLE); // แสดงไอคอนผิด
        animateSuccessMessage(); // แสดง Animation ให้ข้อความเด้งขึ้น
    }

    // ฟังก์ชันลบสินค้า
    public void deleteProduct(Product product) {
        // ลบสินค้าจากรายการ
        productList.remove(product);

        // อัปเดต RecyclerView และข้อความ
        productSuccessAdapter.notifyDataSetChanged();
        updateSuccessMessage();
    }

    // อัปเดตข้อความเมื่อมีการลบหรือเพิ่มสินค้า
    private void updateSuccessMessage() {
        if (!productList.isEmpty()) {
            Product lastAddedProduct = productList.get(productList.size() - 1);
            tvSuccessMessage.setText("เพิ่มสินค้า " + lastAddedProduct.getName() + " เรียบร้อยแล้ว!");
            successIcon.setVisibility(View.VISIBLE); // แสดงไอคอนถูก
            deleteIcon.setVisibility(View.GONE); // ซ่อนไอคอนผิด
        } else {
            tvSuccessMessage.setText("ไม่มีสินค้าในรายการ");
            successIcon.setVisibility(View.GONE); // ซ่อนไอคอนถูก
            deleteIcon.setVisibility(View.VISIBLE); // แสดงไอคอนผิด
        }
    }

    // Animation สำหรับข้อความ
    private void animateSuccessMessage() {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(tvSuccessMessage, "scaleX", 0.5f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(tvSuccessMessage, "scaleY", 0.5f, 1f);
        scaleX.setDuration(500);
        scaleY.setDuration(500);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleX, scaleY);
        animatorSet.start();
    }
}
