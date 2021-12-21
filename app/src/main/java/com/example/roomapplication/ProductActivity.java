package com.example.roomapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.roomapplication.Model.Adapter.ProductAdapter;
import com.example.roomapplication.Model.DataContract;
import com.example.roomapplication.Model.Entities.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {

    private ProductAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        FloatingActionButton buttonAddProduct = findViewById(R.id.button_add_product);

        buttonAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductActivity.this, AddEditProductActivity.class);
                startActivityForResult(intent, DataContract.ADD_PRODUCT_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new ProductAdapter();
        recyclerView.setAdapter(adapter);
        List<Product> products = new ArrayList<Product>();
        products.add(new Product("product 1",3000,400,40,"","DGFDF"));
        products.add(new Product("product 1",3000,400,40,"","DGFDF"));
        products.add(new Product("product 1",3000,400,40,"","DGFDF"));
        products.add(new Product("product 1",3000,400,40,"","DGFDF"));
        adapter.submitList(products);
        adapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Product product) {

                Intent intent = new Intent(ProductActivity.this, AddEditProductActivity.class);
                intent.putExtra(DataContract.EXTRA_NAME, product.getName());
                intent.putExtra(DataContract.EXTRA_SKU, product.getSku());
                intent.putExtra(DataContract.EXTRA_QUANTITY, product.getQuantity());
                intent.putExtra(DataContract.EXTRA_PRICE, product.getBuying_price());
                intent.putExtra(DataContract.EXTRA_SELLING_PRICE, product.getSelling_price());
                startActivityForResult(intent, DataContract.EDIT_PRODUCT_REQUEST);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.product_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_products:

                Toast.makeText(this, "All notes deleted", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.logout:
                startActivity(new Intent(ProductActivity.this, LoginActivity.class));
                finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == DataContract.ADD_PRODUCT_REQUEST && resultCode == RESULT_OK) {
            String name = data.getStringExtra(DataContract.EXTRA_NAME);
            String sku = data.getStringExtra(DataContract.EXTRA_SKU);
            int quantity = data.getIntExtra(DataContract.EXTRA_QUANTITY,0);
            float price = data.getFloatExtra(DataContract.EXTRA_PRICE,0);
            float selling_price = data.getFloatExtra(DataContract.EXTRA_SELLING_PRICE,0);


            Product product = new Product(name,price,selling_price,quantity,null,sku);

            Toast.makeText(this, "Product saved", Toast.LENGTH_SHORT).show();
        }else if (requestCode == DataContract.EDIT_PRODUCT_REQUEST && resultCode == RESULT_OK){
            int id = data.getIntExtra(DataContract.EXTRA_PRODUCT_ID, -1);

            if (id == -1) {
                Toast.makeText(this, "Product can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String name = data.getStringExtra(DataContract.EXTRA_NAME);
            String sku = data.getStringExtra(DataContract.EXTRA_SKU);
            int quantity = data.getIntExtra(DataContract.EXTRA_QUANTITY,0);
            float price = data.getFloatExtra(DataContract.EXTRA_PRICE,0);
            float selling_price = data.getFloatExtra(DataContract.EXTRA_SELLING_PRICE,0);
            Product product = new Product(name,price,selling_price,quantity,null,sku);

            Toast.makeText(this, "Product updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Product not saved", Toast.LENGTH_SHORT).show();
        }
    }

    private BroadcastReceiver bReceiver = new BroadcastReceiver(){

        @Override
        public void onReceive(Context context, Intent intent) {

            //apdate syncstatus db ....
            Product product = new Product(intent.getStringExtra(DataContract.EXTRA_NAME),
                    intent.getDoubleExtra(DataContract.EXTRA_PRICE,0),
                    intent.getDoubleExtra(DataContract.EXTRA_SELLING_PRICE,0),
                    intent.getIntExtra(DataContract.EXTRA_QUANTITY,0),null,
                    intent.getStringExtra(DataContract.EXTRA_SKU));
            List<Product> products=  new ArrayList<Product>();
            products.add(product);
            adapter.submitList(products);
        }
    };

    protected void onResume(){
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(bReceiver, new IntentFilter("product"));
    }

    protected void onPause (){
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(bReceiver);
    }

}