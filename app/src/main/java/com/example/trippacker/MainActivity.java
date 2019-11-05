package com.example.trippacker;
//Derek Ferguson 3/22/19
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    private ListView lv;
    ArrayList<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create array list
        loadData();

        //mock entries
         Item item1= new Item("item1",false,0);
            Item item2= new Item("item2",true,1);
            Item item3= new Item("item3",false,0);
             itemList.add(item1);
            itemList.add(item2);
            itemList.add(item3);

        //register add item button
        FloatingActionButton newItem =  findViewById(R.id.addItemBtn);

        newItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, addItemPopup.class);
                startActivity(intent);

            }
        });






        lv = (ListView) findViewById(R.id.item_list_view);

        ArrayAdapter<Item> arrayAdapter = new ArrayAdapter<Item>(this,
                android.R.layout.simple_list_item_1,itemList);

        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, selectItemPopup.class);
                String selectedItem = itemList.get(position).itemName;
                intent.putExtra("selectedItem", selectedItem);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        if (intent.hasExtra("newItem")) {
            String newItemStr = intent.getStringExtra("newItem");
            Item addedItem = new Item(newItemStr, false, 0);
            itemList.add(addedItem);
            saveData();

        }



    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(itemList);
        editor.putString("item list", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("item list", "add items");
        Type type = new TypeToken<ArrayList<Item>>(){}.getType();
        itemList = gson.fromJson(json, type);

        if (itemList == null) {
            itemList = new ArrayList<>();
        }

    }


}
