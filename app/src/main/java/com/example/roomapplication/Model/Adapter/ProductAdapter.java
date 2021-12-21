package com.example.roomapplication.Model.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.roomapplication.Model.Entities.Product;
import com.example.roomapplication.R;

public class ProductAdapter  extends ListAdapter<Product, ProductAdapter.ProductHolder> {
    private ProductAdapter.OnItemClickListener listener;

    public ProductAdapter(){
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Product> DIFF_CALLBACK = new DiffUtil.ItemCallback<Product>() {
        @Override
        public boolean areItemsTheSame(Product oldItem, Product newItem) {
            return oldItem.getName() == newItem.getName();
        }

        @Override
        public boolean areContentsTheSame(Product oldItem, Product newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getSku().equals(newItem.getSku()) &&
                    oldItem.getQuantity() == newItem.getQuantity();
        }
    };

    @NonNull
    @Override
    public ProductAdapter.ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item, parent, false);
        return new ProductAdapter.ProductHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductHolder holder, int position) {
        Product currentProduct = getItem(position);
        holder.textViewName.setText(currentProduct.getName());
        holder.textViewSku.setText(currentProduct.getSku());
        holder.textViewQuantity.setText(String.valueOf(currentProduct.getQuantity()));
        holder.textViewPrice.setText(String.valueOf(currentProduct.getBuying_price()));

        //image view
       /* if (currentProduct.getImage()!=null){
            Glide.with(holder.itemView)
                    .load(RetrofitBuilder.URL+currentProduct.getImage())
                    .fitCenter()
                    .into(holder.imageViewProduct);
        }*/
    }

    public Product getProductAt(int position) {
        return getItem(position);
    }

    class ProductHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewSku;
        private TextView textViewQuantity;
        private TextView textViewPrice;
        private ImageView imageViewProduct ;


        public ProductHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewSku = itemView.findViewById(R.id.text_view_sku);
            textViewQuantity = itemView.findViewById(R.id.text_view_quantity);
            textViewPrice = itemView.findViewById(R.id.text_view_price);
            imageViewProduct = itemView.findViewById(R.id.image_product);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(Product product);
    }

    public void setOnItemClickListener(ProductAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
}
