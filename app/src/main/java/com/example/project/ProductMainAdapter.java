package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.project.MainActivity;
import com.example.project.model.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductMainAdapter extends RecyclerView.Adapter<ProductMainAdapter.ProductViewHolder> {

    private List<Product> productList;
    private List<Product> filteredList; // รายการฟิลเตอร์
    private Context context;
    private MainActivity mainActivity;

    // Constructor
    public ProductMainAdapter(List<Product> productList, MainActivity mainActivity) {
        this.productList = productList;
        this.filteredList = new ArrayList<>(productList); // สร้าง filteredList จาก productList
        this.context = mainActivity;
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_main, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = filteredList.get(position); // ใช้ filteredList แทน productList
        holder.productName.setText(product.getName());
        holder.productPrice.setText("ราคา " + product.getPrice() + " บาท");

        Glide.with(holder.itemView.getContext())
                .load(product.getImageUrl())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_loading_placeholder)
                        .error(R.drawable.ic_error)
                        .fallback(R.drawable.ic_fallback))
                .into(holder.productImage);

        // ตั้งค่า OnClickListener สำหรับรูปภาพสินค้า
        holder.productImage.setOnClickListener(v -> {
            mainActivity.onProductClick(product);
        });
    }

    @Override
    public int getItemCount() {
        return filteredList.size(); // ใช้ filteredList
    }

    // ฟังก์ชันฟิลเตอร์สำหรับค้นหาสินค้าและกรองตามหมวดหมู่
    public void filter(String text, String category) {
        filteredList.clear();

        // ถ้าหมวดหมู่คือ "ทั้งหมด" หรือเป็นค่าว่าง ให้ทำการค้นหาทั้งหมด
        if (category.equals("") || category.equals("ทั้งหมด")) {
            // กรองเฉพาะคำค้นหา
            for (Product product : productList) {
                if (product.getName().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(product);
                }
            }
        } else {
            // กรองทั้งคำค้นหาและหมวดหมู่
            for (Product product : productList) {
                boolean matchesCategory = product.getCategory().equals(category);
                boolean matchesSearch = product.getName().toLowerCase().contains(text.toLowerCase());

                if (matchesCategory && matchesSearch) {
                    filteredList.add(product);
                }
            }
        }
        notifyDataSetChanged(); // อัพเดต RecyclerView
    }

    // ViewHolder Class
    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, productPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productName = itemView.findViewById(R.id.product_name);
            productPrice = itemView.findViewById(R.id.product_price);
        }
    }
}
