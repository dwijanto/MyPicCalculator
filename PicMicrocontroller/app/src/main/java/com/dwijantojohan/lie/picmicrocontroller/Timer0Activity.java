package com.dwijantojohan.lie.picmicrocontroller;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Timer0Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_timer0);
        initialized();
        listener();
    }
    private void initialized(){
        setTitle(R.string.TitleTimer0Activity);
        TextView textView = (TextView) findViewById(R.id.textView9) ;
        textView.setText(Html.fromHtml("&#8804"));
        textView = (TextView) findViewById(R.id.textView11) ;
        textView.setText(Html.fromHtml("&#8804"));
        textView = (TextView) findViewById(R.id.textView16) ;
        textView.setText(Html.fromHtml("&#8804"));
        textView = (TextView) findViewById(R.id.textView17) ;
        textView.setText(Html.fromHtml("&#8804"));
        Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        spinner.setEnabled(false);
        calculateTimer0();
    }


    private void listener(){
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Spinner spinner = (Spinner) findViewById(R.id.spinner2);
                spinner.setEnabled(isChecked);
            }
        });

        EditText editText=(EditText) findViewById(R.id.editText2);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()!=0){
                    if(Helper.validateText(s.toString())){
                        Toast.makeText(getApplicationContext(),String.format("%d",Helper.result),Toast.LENGTH_SHORT).show();
                    }
                }else{
                   //Handle Null Value
                }
                calculateTimer0();
            }
        });
    }
    private void calculateTimer0(){
        int minReload =0,maxReload=255;
        float minPeriod=0,maxPeriod=0;

        EditText editText=(EditText) findViewById(R.id.editText2);
        String str = editText.getText().toString();
        //Helper h = new Helper();
        if(Helper.validateText(str)){
            maxReload = (int) Helper.result;
            Toast.makeText(getApplicationContext(),String.format("%d",maxReload),Toast.LENGTH_SHORT).show();
        }
        SharedPreferences sp;
        sp = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        int myClock = sp.getInt("CurrentClockHz",4000000); //4MHz as default value
        Toast.makeText(getApplicationContext(),String.format("%d",myClock),Toast.LENGTH_SHORT).show();

    }
}
