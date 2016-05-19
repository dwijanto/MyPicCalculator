package com.dwijantojohan.lie.picmicrocontroller;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get Listview from Activity
        ListView lv = (ListView) findViewById(R.id.listViewMenu);;

        //Define Array of string
        String[] strArr = new String[]{"Timer"};

        //Create List
        final ArrayList<String> list = new ArrayList<String>();
        for (int i=0;i< strArr.length;++i){
            list.add(strArr[i]);
        }
        //Create ArrayAdapter For List (define the layout of your item list)
        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id){
                final String item = (String) parent.getItemAtPosition(position);
                Log.i("Selected Item in list",item);
                //Toast toast = Toast.makeText(getApplicationContext(),item,Toast.LENGTH_SHORT);
                //toast.show();
                Intent intent = new Intent(getApplicationContext(),TimerActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });





    }

}
