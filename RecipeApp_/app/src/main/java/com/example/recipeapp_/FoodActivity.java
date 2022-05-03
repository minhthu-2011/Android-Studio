package com.example.recipeapp_;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.recipeapp_.adapter.FoodAdapter;
import com.example.recipeapp_.adapter.FoodCursorAdapter;
import com.example.recipeapp_.enity.FoodModify;
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

public class FoodActivity extends AppCompatActivity {
    ListView listView;
    List<Food> dataList = new ArrayList<>();
    FoodAdapter foodAdapter;
    Food food;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String userID = user.getUid();
    DatabaseReference mDatabase;

    /*FoodCursorAdapter foodCursorAdapter;*/
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        listView = findViewById(R.id.afo_listview);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //position là vi tri tren listview
                Food food = dataList.get(position);
                Intent foodDetail = new Intent(FoodActivity.this,DetailActivity.class);
                //gửi FoodId (ten của Food) và id quán đến activity FoodDetail
                foodDetail.putExtra("FoodId",food.getTitle());
                // mở activity  foodDetail
                startActivity(foodDetail);
            }
        });
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                if(position == 0 ) {
//                    Intent intent = new Intent();
//                    intent.setClass(FoodActivity.this, DetailActivity.class);
//                    startActivity(intent);
//                }
//            }
//        });

        mDatabase = FirebaseDatabase.getInstance().getReference("Food").child(userID);
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    if(dataSnapshot.getValue() != null) {
                        food = ds.getValue(Food.class);
                        dataList.add(new Food(R.drawable.img_1,food.getTitle(),food.getTimecook(),food.getNguyenlieu(),food.getCongthuc()));
                    }
                }
                LoadViewFood();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void LoadViewFood(){
        foodAdapter = new FoodAdapter(this, dataList);
        listView.setAdapter(foodAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1 , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_add_new_item:
                showDialodAdd();
                return true;
            case R.id.menu_exit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showDialodAdd() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.dialog_addfood, null);

            EditText editname = view.findViewById(R.id.editNamefood);
            EditText edittime = view.findViewById(R.id.editTimefood);
            EditText editNguyenLieu = view.findViewById(R.id.edtNguyenLieu);
            EditText editCongThuc = view.findViewById(R.id.edtCongThuc);

            builder.setView(view);
            builder.setTitle("Add Item")
                    .setPositiveButton("Save Item", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String namefood = editname.getText().toString();
                            int timefood = Integer.parseInt(edittime.getText().toString());

                            foodAdapter.notifyDataSetChanged();

                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            Food food = new Food(R.drawable.img_4,namefood,timefood, editNguyenLieu.getText().toString(), editCongThuc.getText().toString());
                            dataList.add(food);
                            DatabaseReference refData = FirebaseDatabase.getInstance().getReference();
                            refData.child("Food").child(userID).child(namefood).setValue(food);
                            Toast.makeText(FoodActivity.this, "Thêm món ăn thành công", Toast.LENGTH_SHORT).show();
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            builder.show();
        } catch (Exception ce) {
//            Toast.makeText(FoodActivity.this, ce.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}