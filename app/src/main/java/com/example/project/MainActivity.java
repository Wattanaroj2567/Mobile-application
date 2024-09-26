package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.model.BeverageProduct;
import com.example.project.model.Product;
import com.example.project.model.SmartphoneProduct;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductMainAdapter productMainAdapter;
    private List<Product> productList;
    private EditText searchBar;
    private Spinner categorySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // กำหนดค่า RecyclerView
        recyclerView = findViewById(R.id.product_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // กำหนดค่า Spinner สำหรับหมวดหมู่สินค้า
        categorySpinner = findViewById(R.id.category_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categories_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        // ค้นหาสินค้า
        searchBar = findViewById(R.id.etSearch);
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // เรียกใช้ฟังก์ชัน filter เพื่อตรวจสอบคำค้นหา
                productMainAdapter.filter(s.toString(), categorySpinner.getSelectedItem().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // เพิ่มข้อมูลสินค้า
        productList = new ArrayList<>();

        //รายละเอียดที่กรอกเก็บข้อมูลทั้งหมด
        //ข้อมูลที่ 1 เครื่องดื่มให้พลังงาน
        productList.add(new BeverageProduct(
                "เรดบูล(Red Bull)",
                "https://st.bigc-cs.com/cdn-cgi/image/format=webp,quality=90/public/media/catalog/product/49/90/9002490221249/9002490221249_5.jpg",
                69,
                "เครื่องดื่มให้พลังงาน",
                "250 มิลลิลิตร",
                "- ช่วยเพิ่มพลังงานและความสดชื่น\n- เพิ่มความตื่นตัวและสมาธิ",
                "แนะนำให้ดื่ม 1 กระป๋องเมื่อรู้สึกเหนื่อยล้าหรือต้องการเพิ่มพลังงานทันที",
                "ไม่เหมาะสำหรับเด็ก, ผู้ตั้งครรภ์, หรือผู้ที่มีความไวต่อคาเฟอีน"
        ));

        //ข้อมูลที่ 2 เครื่องดื่มให้พลังงาน
        productList.add(new BeverageProduct(
                "M-150",
                "https://st.bigc-cs.com/cdn-cgi/image/format=webp,quality=90/public/media/catalog/product/21/88/8851123212021/8851123212021_1-20221104152235-.jpg",
                12,
                "เครื่องดื่มให้พลังงาน",
                "150 มิลลิลิตร",
                "- ช่วยเพิ่มพลังงานและความสดชื่น\n- ช่วยให้สามารถทำกิจกรรมที่ต้องใช้พลังงานได้นานขึ้น เช่น การทำงาน การออกกำลังกาย หรือการขับขี่ระยะไกล",
                "แนะนำให้ดื่ม 1 ขวด เมื่อรู้สึกเหนื่อยล้า หรือก่อนการทำงานหรือออกกำลังกายที่ต้องใช้พลังงานมาก",
                "ไม่ควรดื่มในปริมาณมากเกินไป เนื่องจากมีส่วนผสมของคาเฟอีน ซึ่งอาจทำให้เกิดผลกระทบต่อระบบประสาท\n- ห้ามดื่มเกิน 2 ขวดต่อวัน"
        ));

        //ข้อมูลที่ 3 เครื่องดื่มให้พลังงาน
        productList.add(new BeverageProduct(
                "สปอนเซอร์(Sponsor)",
                "https://st.bigc-cs.com/cdn-cgi/image/format=webp,quality=90/public/media/catalog/product/03/88/8850228000403/8850228000403-20221123110758-.jpg",
                20,
                "เครื่องดื่มให้พลังงาน",
                "250 มิลลิลิตร",
                "- ช่วยทดแทนเกลือแร่ที่สูญเสียไปจากการออกกำลังกาย\n- ให้พลังงานและความสดชื่น\n- เหมาะสำหรับผู้ที่ออกกำลังกายหนักหรือทำกิจกรรมกลางแจ้ง",
                "ดื่มหลังการออกกำลังกายหรือเมื่อรู้สึกอ่อนเพลีย",
                "ควรดื่มตามปริมาณที่แนะนำเพื่อป้องกันการบริโภคเกลือแร่และน้ำตาลมากเกินไป"
        ));

        //ข้อมูลที่ 4 เครื่องดื่มให้พลังงาน
        productList.add(new BeverageProduct(
                "กระทิงแดง(Krating Daeng)",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR6nEWISTygqY6GswHvdpEH9tgFAAnLSpPyhg&s",
                12,
                "เครื่องดื่มให้พลังงาน",
                "150 มิลลิลิตร",
                "เพิ่มพลังงานและความตื่นตัว ช่วยลดความเหนื่อยล้า",
                "ควรดื่มเมื่อจำเป็นต้องใช้พลังงานมาก เช่น ก่อนการทำงานหรือการขับขี่",
                "ห้ามบริโภคมากเกินไป และไม่ควรดื่มก่อนนอน"
        ));

        //ข้อมูลที่ 5 เครื่องดื่มให้พลังงาน
        productList.add(new BeverageProduct(
                "ลิโพ(Lipo)",
                "https://st.bigc-cs.com/cdn-cgi/image/format=webp,quality=90/public/media/catalog/product/24/88/8851123211024/8851123211024.jpg",
                12,
                "เครื่องดื่มให้พลังงาน",
                "100 มิลลิลิตร",
                "ให้พลังงานแก่ร่างกายและสมอง ช่วยลดความเหนื่อยล้า",
                "ดื่มเมื่อต้องการใช้พลังงานมาก เช่น ก่อนทำงานที่ต้องใช้สมาธิ",
                "ไม่ควรดื่มเกินวันละ 2 ขวด และหลีกเลี่ยงการดื่มก่อนนอน"
        ));

        //ข้อมูลที่ 6 เครื่องดื่มให้พลังงาน
        productList.add(new BeverageProduct(
                "เรดดี้(Ready)",
                "https://st.bigc-cs.com/cdn-cgi/image/format=webp,quality=90/public/media/catalog/product/18/88/8850228007518/8850228007518_1-20240708093503-.jpg",
                15,
                "เครื่องดื่มให้พลังงาน",
                "150 มิลลิลิตร",
                "ช่วยเพิ่มพลังและความสดชื่น ช่วยให้สมองตื่นตัว",
                "ดื่มในช่วงเวลาที่รู้สึกเหนื่อยหรือต้องการเพิ่มพลัง",
                "หลีกเลี่ยงการดื่มเกิน 2 ขวดต่อวัน ไม่เหมาะสำหรับผู้ที่ไวต่อคาเฟอีน"
        ));

        //ข้อมูลที่ 7 เครื่องดื่มให้พลังงาน
        productList.add(new BeverageProduct(
                "เปปทีน(Peptein)",
                "https://st.bigc-cs.com/cdn-cgi/image/format=webp,quality=90/public/media/catalog/product/97/88/8851123225397/8851123225397_2-20240605131712-.jpg",
                35,
                "เครื่องดื่มให้พลังงาน",
                "100 มิลลิลิตร",
                "ช่วยเสริมสร้างความจำและการทำงานของสมอง",
                "ดื่มทุกเช้าเพื่อบำรุงสมองและเพิ่มพลังงานให้กับร่างกาย",
                "ห้ามดื่มในปริมาณมากเกินไป"
        ));

        //ข้อมูลที่ 8 เครื่องดื่มให้พลังงาน
        productList.add(new BeverageProduct(
                "คาราบาวแดง(Carabao Dang)",
                "https://st.bigc-cs.com/cdn-cgi/image/format=webp,quality=90/public/media/catalog/product/11/88/8855790000011/8855790000011_1-20240516214331-.jpg",
                10,
                "เครื่องดื่มให้พลังงาน",
                "150 มิลลิลิตร",
                "ช่วยให้ร่างกายกระปรี้กระเปร่า เพิ่มสมรรถภาพการทำงาน",
                "ดื่มเพื่อเติมพลังงานในระหว่างวัน",
                "หลีกเลี่ยงการดื่มในปริมาณมาก และไม่แนะนำสำหรับเด็ก"
        ));

        //ข้อมูลที่ 9 เครื่องดื่มให้พลังงาน
        productList.add(new BeverageProduct(
                "ซี-วิต(C-Vitt)",
                "https://britishop.com/storage/imgcache/663df8b5642e2_74909-1-l__960x960xsquare.jpg",
                16,
                "เครื่องดื่มให้พลังงาน",
                "140 มิลลิลิตร",
                "ช่วยเสริมภูมิคุ้มกันและเพิ่มพลังงาน มีวิตามินซีสูง",
                "ดื่มวันละ 1 ขวดเพื่อเติมวิตามินซีและพลังงานให้กับร่างกาย",
                "ไม่ควรดื่มเกินวันละ 1 ขวด และไม่เหมาะสำหรับผู้ที่แพ้กรดซิตริก"
        ));

        //ข้อมูลที่ 10 เครื่องดื่มให้พลังงาน
        productList.add(new BeverageProduct(
                "บลู(B’lue)",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRqKKDhfCOaz8LdnTlrPNnRjCLYMuEQUekqfA&s",
                20,
                "เครื่องดื่มให้พลังงาน",
                "500 มิลลิลิตร",
                "น้ำแร่ผสมวิตามิน ช่วยเพิ่มความสดชื่นและพลังงาน",
                "ดื่มระหว่างวันเพื่อเติมความสดชื่นและพลังงาน",
                "ไม่ควรดื่มมากเกินไป และหลีกเลี่ยงการดื่มก่อนนอน"
        ));


        //ข้อมูลที่ 1 สมาร์ทโฟน
        productList.add(new SmartphoneProduct(
                "Samsung Galaxy S23 Ultra",
                "https://images.samsung.com/is/image/samsung/p6pim/th/2302/gallery/th-galaxy-s23-s918-sm-s918bzkbthl-534857666?$650_519_PNG$",
                36900,
                "สมาร์ทโฟน",
                "6.8 นิ้ว, Dynamic AMOLED 2X, ความละเอียด 1440 x 3088 พิกเซล",
                "Qualcomm Snapdragon 8 Gen 2",
                "8/12 GB",
                "256 GB / 512 GB / 1 TB",
                "กล้องหลัง 200MP + 12MP + 10MP (2 เลนส์), กล้องหน้า 12MP",
                "5,000 mAh, รองรับชาร์จเร็ว 45W",
                "Android 13, One UI 5.1"
                ));

        //ข้อมูลที่ 2 สมาร์ทโฟน
        productList.add(new SmartphoneProduct(
                "iPhone 14 Pro Max",
                "https://media-cdn.bnn.in.th/234767/iPhone_14_Pro_Max_Deep_Purple_PDP_Image_Position-1A_Deep_Purple_1-square_medium.jpg",
                38700,
                "สมาร์ทโฟน",
                "6.7 นิ้ว, Super Retina XDR OLED, ความละเอียด 1290 x 2796 พิกเซล",
                "Apple A16 Bionic",
                "6 GB",
                "128 GB / 256 GB / 512 GB / 1 TB",
                "กล้องหลัง 48MP + 12MP + 12MP, กล้องหน้า 12MP",
                "4,323 mAh, รองรับชาร์จเร็ว 20W",
                "iOS 16"
        ));

        //ข้อมูลที่ 3 สมาร์ทโฟน
        productList.add(new SmartphoneProduct(
                "Xiaomi 13 Pro",
                "https://media-cdn.bnn.in.th/278581/Xiaomi-13-Pro-Black-1-square_medium.jpg",
                39990,
                "สมาร์ทโฟน",
                "6.73 นิ้ว, LTPO AMOLED, ความละเอียด 1440 x 3200 พิกเซล",
                "Qualcomm Snapdragon 8 Gen 2",
                "12 GB",
                "256 GB / 512 GB",
                "กล้องหลัง 50MP (3 เลนส์), กล้องหน้า 32MP",
                "4,820 mAh, รองรับชาร์จเร็ว 120W",
                "Android 13, MIUI 14"
        ));

        //ข้อมูลที่ 4 สมาร์ทโฟน
        productList.add(new SmartphoneProduct(
                "OnePlus 11",
                "https://oasis.opstatics.com/content/dam/oasis/page/2023/na/oneplus-11/specs/green-img.png",
                29990,
                "สมาร์ทโฟน",
                "6.7 นิ้ว, Fluid AMOLED, ความละเอียด 1440 x 3216 พิกเซล",
                "Qualcomm Snapdragon 8 Gen 2",
                "8/12/16 GB",
                "128 GB / 256 GB / 512 GB",
                "กล้องหลัง 50MP + 48MP + 32MP, กล้องหน้า 16MP",
                "5,000 mAh, รองรับชาร์จเร็ว 100W",
                "Android 13, OxygenOS 13"
        ));

        //ข้อมูลที่ 5 สมาร์ทโฟน
        productList.add(new SmartphoneProduct(
                "Google Pixel 7 Pro",
                "https://ginkotown.store/cdn/shop/products/v9_2048x.jpg?v=1665854347",
                27977,
                "สมาร์ทโฟน",
                "6.7 นิ้ว, LTPO AMOLED, ความละเอียด 1440 x 3120 พิกเซล",
                "Google Tensor G2",
                "12 GB",
                "128 GB / 256 GB / 512 GB",
                "กล้องหลัง 50MP + 48MP + 12MP, กล้องหน้า 10.8MP",
                "5,000 mAh, รองรับชาร์จเร็ว 30W",
                "Android 13"
        ));

        //ข้อมูลที่ 6 สมาร์ทโฟน
        productList.add(new SmartphoneProduct(
                "Oppo Find X6 Pro",
                "https://img.myipadbox.com/sec/product_l/MPH9446G.jpg",
                18299,
                "สมาร์ทโฟน",
                "6.82 นิ้ว, AMOLED, ความละเอียด 1440 x 3168 พิกเซล",
                "Qualcomm Snapdragon 8 Gen 2",
                "12/16 GB",
                "256 GB / 512 GB",
                "กล้องหลัง 50MP (3 เลนส์), กล้องหน้า 32MP",
                " 5,000 mAh, รองรับชาร์จเร็ว 100W",
                "Android 13, ColorOS 13"
        ));

        //ข้อมูลที่ 7 สมาร์ทโฟน
        productList.add(new SmartphoneProduct(
                "Realme GT 3",
                "https://droidsans.com/realme-gt-neo-5-officially-launch-in-china/realme-gt-neo-5-purple/",
                18999,
                "สมาร์ทโฟน",
                "6.74 นิ้ว, AMOLED, ความละเอียด 1240 x 2772 พิกเซล",
                "Qualcomm Snapdragon 8+ Gen 1",
                "8/12/16 GB",
                "256 GB / 512 GB",
                "กล้องหลัง 50MP + 8MP + 2MP, กล้องหน้า 16MP",
                "4,600 mAh, รองรับชาร์จเร็ว 240W",
                "Android 13, Realme UI 4.0"
        ));

        //ข้อมูลที่ 8 สมาร์ทโฟน
        productList.add(new SmartphoneProduct(
                "Vivo X90 Pro",
                "https://asia-exstatic-vivofs.vivo.com/PSee2l50xoirPK7y/1672822347373/32e5861310591744ac06727019f97635.png",
                39999,
                "สมาร์ทโฟน",
                "6.78 นิ้ว, AMOLED, ความละเอียด 1260 x 2800 พิกเซล",
                "MediaTek Dimensity 9200",
                "8/12 GB",
                "256 GB / 512 GB",
                "กล้องหลัง 50MP (3 เลนส์), กล้องหน้า 32MP",
                "4,870 mAh, รองรับชาร์จเร็ว 120W",
                "Android 13, Funtouch 13"
        ));

        //ข้อมูลที่ 9 สมาร์ทโฟน
        productList.add(new SmartphoneProduct(
                "Asus ROG Phone 6D",
                "https://media-cdn.bnn.in.th/311430/Asus-ROG-Phone-6D-Ultimate-(16%2b512GB)-Space-Gray-1-square_medium.jpg",
                30990,
                "สมาร์ทโฟน",
                "6.78 นิ้ว, AMOLED, ความละเอียด 1080 x 2448 พิกเซล",
                "MediaTek Dimensity 9000+",
                "12/16 GB",
                "256 GB / 512 GB",
                "กล้องหลัง 50MP + 13MP + 5MP, กล้องหน้า 12MP",
                "6,000 mAh, รองรับชาร์จเร็ว 65W",
                "Android 12, ROG UI"
        ));

        //ข้อมูลที่ 10 สมาร์ทโฟน
        productList.add(new SmartphoneProduct(
                "Sony Xperia 1 IV",
                "https://sony.scene7.com/is/image/sonyglobalsolutions/747_ProductPrimary_image?$categorypdpnav$&fmt=png-alpha",
                48990,
                "สมาร์ทโฟน",
                "6.5 นิ้ว, OLED, ความละเอียด 1644 x 3840 พิกเซล",
                "Qualcomm Snapdragon 8 Gen 1",
                "12 GB",
                "256 GB / 512 GB",
                "กล้องหลัง 12MP (3 เลนส์), กล้องหน้า 12MP",
                "5,000 mAh, รองรับชาร์จเร็ว 30W",
                "Android 12"
        ));

        // กำหนดค่า Adapter สำหรับ RecyclerView
        productMainAdapter = new ProductMainAdapter(productList, this);
        recyclerView.setAdapter(productMainAdapter);

        // ตั้งค่า Spinner ให้ทำการกรองสินค้าเมื่อมีการเปลี่ยนหมวดหมู่
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // หากเลือก "ทั้งหมด" ให้แสดงสินค้าทั้งหมด
                String selectedCategory = categorySpinner.getSelectedItem().toString();
                if (selectedCategory.equals("ทั้งหมด")) {
                    productMainAdapter.filter(searchBar.getText().toString(), ""); // ส่ง "" เพื่อแสดงทั้งหมด
                } else {
                    productMainAdapter.filter(searchBar.getText().toString(), selectedCategory);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // หากไม่มีการเลือกหมวดหมู่ แสดงสินค้าทั้งหมด
                productMainAdapter.filter(searchBar.getText().toString(), "");
            }
        });
    }


    // ฟังก์ชันเมื่อคลิกที่รูปภาพสินค้า
    public void onProductClick(Product product) {
        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.putExtra("productName", product.getName());
        intent.putExtra("productPrice", product.getPrice());
        intent.putExtra("productImage", product.getImageUrl());
        intent.putExtra("productCategory", product.getCategory());

        // สำหรับข้อมูลเฉพาะ
        if (product instanceof BeverageProduct) {
            BeverageProduct beverageProduct = (BeverageProduct) product;
            intent.putExtra("productSize", beverageProduct.getSize());
            intent.putExtra("productFeatures", beverageProduct.getFeatures());
            intent.putExtra("productConsumption", beverageProduct.getConsumption());
            intent.putExtra("productWarning", beverageProduct.getWarning());
        } else if (product instanceof SmartphoneProduct) {
            SmartphoneProduct smartphoneProduct = (SmartphoneProduct) product;
            intent.putExtra("productScreenSize", smartphoneProduct.getScreenSize ());
            intent.putExtra("productProcessor", smartphoneProduct.getProcessor ());
            intent.putExtra("productRam", smartphoneProduct.getRam ());
            intent.putExtra("productStorage", smartphoneProduct.getStorage ());
            intent.putExtra("productCamera", smartphoneProduct.getCamera ());
            intent.putExtra("productBattery", smartphoneProduct.getBattery());
            intent.putExtra("productOS", smartphoneProduct.getOperatingSystem ());
        }

        startActivity(intent);
    }
}
