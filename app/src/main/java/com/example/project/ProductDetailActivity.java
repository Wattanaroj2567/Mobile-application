package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.project.model.Product;
import com.example.project.model.ProductManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.graphics.Typeface;

public class ProductDetailActivity extends AppCompatActivity {

    private ImageView productImage;
    private TextView productName, productPrice, productType, productSize, productFeatures, productConsumption, productWarning;
    private TextView productScreenSize, productProcessor, productRam, productStorage, productCamera, productBattery, productOS; // เพิ่มตัวแปรใหม่สำหรับข้อมูลสมาร์ทโฟน
    private Button btnAdd, btnBack;
    private TextView tvTitle; // สำหรับโลโก้ที่คลิกแล้วกลับไปหน้าหลัก

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // เชื่อมต่อกับ View ใน Layout
        btnAdd = findViewById(R.id.btn_add);
        btnBack = findViewById(R.id.btn_back);
        tvTitle = findViewById(R.id.tvTitle);

        // เพิ่มการเชื่อมต่อ View ที่เกี่ยวข้องกับ ภาพ ชื่อ ราคา ประเภท
        productImage = findViewById(R.id.product_image);
        productName = findViewById(R.id.product_name);
        productPrice = findViewById(R.id.product_price);
        productType = findViewById(R.id.product_type);
        // เพิ่มการเชื่อมต่อ View ที่เกี่ยวข้องกับเครื่องดื่มให้พลังงาน
        productSize = findViewById(R.id.product_size);
        productFeatures = findViewById(R.id.product_features);
        productConsumption = findViewById(R.id.product_consumption);
        productWarning = findViewById(R.id.product_warning);
        // เพิ่มการเชื่อมต่อ View ที่เกี่ยวข้องกับสมาร์ทโฟน
        productScreenSize = findViewById(R.id.product_screen_size);
        productProcessor = findViewById(R.id.product_processor);
        productRam = findViewById(R.id.product_ram);
        productStorage = findViewById(R.id.product_storage);
        productCamera = findViewById(R.id.product_camera);
        productBattery = findViewById(R.id.product_battery);
        productOS = findViewById(R.id.product_os);

        // รับข้อมูลจาก Intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("productName");
        int price = intent.getIntExtra("productPrice", 0);
        String imageUrl = intent.getStringExtra("productImage");
        String category = intent.getStringExtra("productCategory");

        // ข้อมูลเฉพาะสำหรับสินค้า
        String size = intent.getStringExtra("productSize");
        String features = intent.getStringExtra("productFeatures");
        String consumption = intent.getStringExtra("productConsumption");
        String warning = intent.getStringExtra("productWarning");

        String screenSize = intent.getStringExtra("productScreenSize");  // ขนาดหน้าจอ
        String processor = intent.getStringExtra("productProcessor");    // ชิปประมวลผล
        String ram = intent.getStringExtra("productRam");                // RAM
        String storage = intent.getStringExtra("productStorage");        // ความจุ
        String camera = intent.getStringExtra("productCamera");          // กล้อง
        String battery = intent.getStringExtra("productBattery");        // แบตเตอรี่
        String os = intent.getStringExtra("productOS");                  // ระบบปฏิบัติการ

        // ตรวจสอบข้อมูลก่อนใช้งาน
        if (name == null || imageUrl == null || category == null) {
            Toast.makeText(this, "ข้อมูลสินค้าบางอย่างไม่ถูกต้อง", Toast.LENGTH_SHORT).show();
            finish(); // ปิด Activity ถ้าข้อมูลไม่ครบ
            return;
        }

        // ตั้งค่าข้อมูลใน View
        productName.setText(name);
        productPrice.setText("ราคา " + price + " บาท");

        // แสดงหมวดหมู่สินค้า
        SpannableString productTypeText = new SpannableString("ประเภท: " + category);
        productTypeText.setSpan(new StyleSpan(Typeface.BOLD), 0, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        productType.setText(productTypeText);

        // ใช้ Glide ในการโหลดรูปภาพจาก URL
        Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.ic_loading_placeholder)
                .error(R.drawable.ic_error)
                .fallback(R.drawable.ic_fallback)
                .into(productImage);

        // แสดงข้อมูลเพิ่มเติมตามประเภทของสินค้า
        if (category.equals("สมาร์ทโฟน")) {
            // แสดงเฉพาะข้อมูลสมาร์ทโฟน
            productScreenSize.setVisibility(View.VISIBLE);
            productProcessor.setVisibility(View.VISIBLE);
            productRam.setVisibility(View.VISIBLE);
            productStorage.setVisibility(View.VISIBLE);
            productCamera.setVisibility(View.VISIBLE);
            productBattery.setVisibility(View.VISIBLE);
            productOS.setVisibility(View.VISIBLE);

            if (screenSize != null) {
                SpannableString screenSizeText = new SpannableString("ขนาดหน้าจอ: " + screenSize);
                screenSizeText.setSpan(new StyleSpan(Typeface.BOLD), 0, 12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                productScreenSize.setText(screenSizeText);
            }

            if (processor != null) {
                SpannableString processorText = new SpannableString("ชิปประมวลผล: " + processor);
                processorText.setSpan(new StyleSpan(Typeface.BOLD), 0, 13, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                productProcessor.setText(processorText);
            }

            if (ram != null) {
                SpannableString ramText = new SpannableString("RAM: " + ram);
                ramText.setSpan(new StyleSpan(Typeface.BOLD), 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                productRam.setText(ramText);
            }

            if (storage != null) {
                SpannableString storageText = new SpannableString("ความจุ: " + storage);
                storageText.setSpan(new StyleSpan(Typeface.BOLD), 0, 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                productStorage.setText(storageText);
            }

            if (camera != null) {
                SpannableString cameraText = new SpannableString("กล้อง: " + camera);
                cameraText.setSpan(new StyleSpan(Typeface.BOLD), 0, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                productCamera.setText(cameraText);
            }

            if (battery != null) {
                SpannableString batteryText = new SpannableString("แบตเตอรี่: " + battery);
                batteryText.setSpan(new StyleSpan(Typeface.BOLD), 0, 9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                productBattery.setText(batteryText);
            }

            if (os != null) {
                SpannableString osText = new SpannableString("ระบบปฏิบัติการ: " + os);
                osText.setSpan(new StyleSpan(Typeface.BOLD), 0, 16, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                productOS.setText(osText);
            }
            // ซ่อนข้อมูลที่เกี่ยวกับเครื่องดื่ม
            productSize.setVisibility(View.GONE);
            productFeatures.setVisibility(View.GONE);
            productConsumption.setVisibility(View.GONE);
            productWarning.setVisibility(View.GONE);

        } else {
            // แสดงข้อมูลที่เกี่ยวกับเครื่องดื่ม
            productSize.setVisibility(View.VISIBLE);
            productFeatures.setVisibility(View.VISIBLE);
            productConsumption.setVisibility(View.VISIBLE);
            productWarning.setVisibility(View.VISIBLE);

            // แสดงข้อมูลเฉพาะสำหรับสินค้าเครื่องดื่ม
            if (size != null) {
                SpannableString sizeText = new SpannableString("ขนาด: " + size);
                sizeText.setSpan(new StyleSpan(Typeface.BOLD), 0, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                productSize.setText(sizeText);
            }

            if (features != null) {
                SpannableString featuresText = new SpannableString("คุณสมบัติ:\n" + features);
                featuresText.setSpan(new StyleSpan(Typeface.BOLD), 0, 9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                productFeatures.setText(featuresText);
            }

            if (consumption != null) {
                SpannableString consumptionText = new SpannableString("วิธีการบริโภค: " + consumption);
                consumptionText.setSpan(new StyleSpan(Typeface.BOLD), 0, 13, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                productConsumption.setText(consumptionText);
            }

            if (warning != null) {
                SpannableString warningText = new SpannableString("คำเตือน: " + warning);
                warningText.setSpan(new StyleSpan(Typeface.BOLD), 0, 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                productWarning.setText(warningText);
            }

            // ซ่อนข้อมูลสมาร์ทโฟนสำหรับสินค้าที่ไม่ใช่สมาร์ทโฟน
            productScreenSize.setVisibility(View.GONE);
            productProcessor.setVisibility(View.GONE);
            productRam.setVisibility(View.GONE);
            productStorage.setVisibility(View.GONE);
            productCamera.setVisibility(View.GONE);
            productBattery.setVisibility(View.GONE);
            productOS.setVisibility(View.GONE);
        }

        // ฟังก์ชันปุ่ม "เพิ่ม"
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product newProduct = new Product(name, imageUrl, price, category);
                ProductManager.getInstance().addProduct(newProduct);
                Intent intent = new Intent(ProductDetailActivity.this, ProductSuccessActivity.class);
                startActivity(intent);
            }
        });

        // ฟังก์ชันปุ่ม "ย้อนกลับ"
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // ปิดหน้า ProductDetailActivity และย้อนกลับไปยังหน้าก่อนหน้า
            }
        });

        // เพิ่มฟังก์ชันคลิกที่โลโก้เพื่อกลับไปหน้าหลัก (MainActivity)
        tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

}
