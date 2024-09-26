package com.example.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.project.model.Product;
import com.example.project.model.ProductManager;
import java.util.List;

public class ProductSuccessAdapter extends RecyclerView.Adapter<ProductSuccessAdapter.ProductViewHolder> {

    private List<Product> productList;
    private ProductSuccessActivity activity; // อ้างถึง Activity เพื่อเรียกใช้ showDeletionMessage

    public ProductSuccessAdapter(List<Product> productList, ProductSuccessActivity activity) {
        this.productList = productList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_success, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.productName.setText(product.getName());
        holder.productPrice.setText("ราคา " + product.getPrice() + " บาท");

        // ใช้ Glide ในการโหลดรูปภาพ
        Glide.with(holder.itemView.getContext())
                .load(product.getImageUrl())
                .placeholder(R.drawable.ic_loading_placeholder)
                .into(holder.productImage);

        // ฟังก์ชันปุ่ม "ลบสินค้า"
        holder.btnDelete.setOnClickListener(v -> {
            // ลบสินค้าออกจาก ProductManager
            ProductManager.getInstance().getProductList().remove(product);

            // อัปเดตรายการสินค้าทั้งหมด
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, productList.size());

            // เรียกใช้ฟังก์ชันแสดงข้อความลบสินค้าใน Activity
            activity.showDeletionMessage(product.getName());
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productPrice;
        ImageView productImage;
        Button btnDelete;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.product_name);
            productPrice = itemView.findViewById(R.id.product_price);
            productImage = itemView.findViewById(R.id.product_image);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
