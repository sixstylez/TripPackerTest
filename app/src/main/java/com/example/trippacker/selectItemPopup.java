package com.example.trippacker;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class selectItemPopup extends Activity {

    ArrayList<Item> itemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_item_popup);
        //Window Stylings
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.7), (int)(height*.6));
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));



        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);


        TextView selectedItemTxt = findViewById(R.id.selectedItemTxt);




        final Intent intent = getIntent();
        if (intent.hasExtra("selectedItem")) {
            String selectedItem = intent.getStringExtra("selectedItem");
            selectedItemTxt.setText(selectedItem);
        }



        ImageButton deleteBtn = findViewById(R.id.deleteBtn);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override   //Delete Button - Delete by index
            public void onClick(View v) {
                loadData();
                int position = intent.getIntExtra("position", 0);
                itemList.remove(position);
                saveData();
                Intent intent = new Intent(selectItemPopup.this, MainActivity.class);
                startActivity(intent);



            }
        });


    }
        //update sharedPref
    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(itemList);
        editor.putString("item list", json);
        editor.apply();
    }
        //load sharedPref
    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("item list", "add items");
        Type type = new TypeToken<ArrayList<Item>>() {
        }.getType();
        itemList = gson.fromJson(json, type);
    }
}
