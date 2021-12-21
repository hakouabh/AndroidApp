package com.example.roomapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.roomapplication.Model.DataContract;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static com.example.roomapplication.Model.DataContract.RESULT_LOAD_IMAGE;

public class AddEditProductActivity extends AppCompatActivity {


    private EditText editTextName;
    private EditText editTextBuyingPrice;
    private EditText editTextSKU;
    private EditText editTextSellingPrice;
    private EditText editTextQuantity;
    private ImageView imageViewProduct;
    OutputStream outputStream;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_product);

        editTextName = findViewById(R.id.edit_text_name);
        editTextSKU = findViewById(R.id.edit_text_sku);
        editTextQuantity = findViewById(R.id.edit_text_quantity);
        editTextBuyingPrice = findViewById(R.id.edit_text_price);
        editTextSellingPrice = findViewById(R.id.edit_text_selling_price);
        imageViewProduct = (ImageView) findViewById(R.id.image_product);


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();

        if (intent.hasExtra(DataContract.EXTRA_PRODUCT_ID)) {
            setTitle("Edit Product");
            editTextName.setText(intent.getStringExtra(DataContract.EXTRA_NAME));
            editTextSKU.setText(intent.getStringExtra(DataContract.EXTRA_SKU));
            editTextSellingPrice.setText(String.valueOf(intent.getDoubleExtra(DataContract.EXTRA_SELLING_PRICE,0)));
            editTextBuyingPrice.setText(String.valueOf(intent.getDoubleExtra(DataContract.EXTRA_PRICE,0)));
            editTextQuantity.setText(String.valueOf(intent.getIntExtra(DataContract.EXTRA_QUANTITY, 0)));
        } else {
            setTitle("Add Product");
        }

        imageViewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

    }

    private void saveProduct(){

        String name = editTextName.getText().toString();
        String sku = editTextSKU.getText().toString();
        float buying_price = Float.valueOf(editTextBuyingPrice.getText().toString());
        float selling_price = Float.valueOf(editTextSellingPrice.getText().toString());
        int quantity = Integer.valueOf(editTextQuantity.getText().toString());

        if (name.trim().isEmpty() || sku.trim().isEmpty()) {
            Toast.makeText(this, "Please insert all", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(DataContract.EXTRA_NAME,name);
        data.putExtra(DataContract.EXTRA_SKU,sku);
        data.putExtra(DataContract.EXTRA_PRICE,buying_price);
        data.putExtra(DataContract.EXTRA_SELLING_PRICE,selling_price);
        data.putExtra(DataContract.EXTRA_QUANTITY,quantity);
        int id = getIntent().getIntExtra(DataContract.EXTRA_PRODUCT_ID, -1);
        if (id != -1) {
            data.putExtra(DataContract.EXTRA_PRODUCT_ID, id);
        }
        if (ContextCompat.checkSelfPermission(AddEditProductActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(AddEditProductActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            //saveImage();

        }else {


            ActivityCompat.requestPermissions(AddEditProductActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
            ActivityCompat.requestPermissions(AddEditProductActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);


        }

        setResult(RESULT_OK, data);
        finish();
    }

    private void saveImage(){

        File dir = new File(Environment.getExternalStorageDirectory(),"SaveImage");

        if (!dir.exists()){

            dir.mkdir();

        }

        Bitmap bitmap  = BitmapFactory.decodeResource(getResources(), R.drawable.product);

        File file = new File(dir,System.currentTimeMillis()+".jpg");
        try {
            outputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
        try {
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_product_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_product:
                saveProduct();
                //saveImage();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            imageViewProduct.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }
}