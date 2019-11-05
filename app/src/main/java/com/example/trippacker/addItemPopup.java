package com.example.trippacker;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;

public class addItemPopup extends Activity  {

    //ArrayList itemList = (ArrayList) getIntent().getSerializableExtra("itemList");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_popup);

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

        final EditText addTxt = findViewById(R.id.addItemInput);
        Button addItemBtn = findViewById(R.id.addItemBtn);

        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = addTxt.getText().toString();

                Intent intent = new Intent(addItemPopup.this, MainActivity.class);
                intent.putExtra("newItem", input);
                startActivity(intent);
               // saveData();
            }
        });
    }

//    private void saveData() {
//        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        Gson gson = new Gson();
//        String json = gson.toJson(itemList);
//        editor.putString("itemList", json);
//        editor.apply();
//    }
}
