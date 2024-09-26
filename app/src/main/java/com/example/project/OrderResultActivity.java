package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project.model.ProductManager;

public class OrderResultActivity extends AppCompatActivity {

    private TextView tvOrderSuccessMessage;
    private TextView tvOrderFailedMessage;
    private Button btnBackToHome;
    public static final String EXTRA_ORDER_STATUS = "order_status"; // สถานะการสั่งซื้อ

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_result);

        // เชื่อมต่อกับ View ใน Layout
        tvOrderSuccessMessage = findViewById(R.id.tvOrderSuccessMessage);
        tvOrderFailedMessage = findViewById(R.id.tvOrderFailedMessage);
        btnBackToHome = findViewById(R.id.btnBackToHome);

        // รับสถานะการสั่งซื้อจาก Intent
        Intent intent = getIntent();
        boolean orderStatus = intent.getBooleanExtra(EXTRA_ORDER_STATUS, false);

        // แสดงข้อความตามสถานะการสั่งซื้อ
        if (orderStatus) {
            // แสดงข้อความการสั่งซื้อสำเร็จ
            tvOrderSuccessMessage.setVisibility(View.VISIBLE);
            tvOrderFailedMessage.setVisibility(View.GONE);
        } else {
            // แสดงข้อความการสั่งซื้อไม่สำเร็จ
            tvOrderSuccessMessage.setVisibility(View.GONE);
            tvOrderFailedMessage.setVisibility(View.VISIBLE);
        }

        // ปุ่มกลับไปยังหน้าแรกและลบข้อมูลที่เพิ่ม
        btnBackToHome.setOnClickListener(v -> {
            // ลบข้อมูลสินค้าทั้งหมดที่เพิ่มไว้
            ProductManager.getInstance().clearProductList(); // เรียกฟังก์ชันล้างข้อมูล
            Intent mainActivityIntent = new Intent(OrderResultActivity.this, MainActivity.class);
            mainActivityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(mainActivityIntent);
            finish();
        });
    }
}
