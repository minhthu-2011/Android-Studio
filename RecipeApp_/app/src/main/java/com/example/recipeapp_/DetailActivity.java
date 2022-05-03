package com.example.recipeapp_;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.recipeapp_.adapter.DetailAdapter;
import com.example.recipeapp_.adapter.FoodAdapter;
import com.example.recipeapp_.models.Food;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String userID = user.getUid();
    DatabaseReference mDatabase;
    Food food;
    TextView nguyenLieu;
    TextView congThuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        String foodID = intent.getStringExtra("FoodId");

        LoadDataFoodDetail(foodID);

        Button btnback = findViewById(R.id.btn_detail_back);
        nguyenLieu = findViewById(R.id.txtNguyenlieu);
        congThuc = findViewById(R.id.txtCongthuc);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * Hàm lấy dữ liệu chi tiết món ăn
     * @param foodID
     */
    private void LoadDataFoodDetail(final String foodID) {
        try {
            mDatabase = FirebaseDatabase.getInstance().getReference("Food").child(userID).child(foodID);
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                    if(dataSnapshot.getValue() != null) {
                        food = dataSnapshot.getValue(Food.class);
                        nguyenLieu.setText(food.getNguyenlieu());
                        congThuc.setText(food.getCongthuc());
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        } catch (Exception ce){

        }

    }

}