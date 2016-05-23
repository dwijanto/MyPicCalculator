package com.dwijantojohan.lie.picmicrocontroller;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Timer0Activity extends AppCompatActivity {
    private EditText editText2;
    private EditText editText3;
    private TextView lowText;
    private TextView highText;
    private Double MinTime;
    private Double MaxTime;
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

        //EditText
        editText2=(EditText) findViewById(R.id.editText2);
        //EditText
        editText3=(EditText) findViewById(R.id.editText3);
        //TextView
        lowText = (TextView) findViewById(R.id.textView15);
        //TextView
        highText = (TextView) findViewById(R.id.textView18);

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

        //final EditText editText2=(EditText) findViewById(R.id.editText2);
        editText2.addTextChangedListener(new TextWatcher() {
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
                        //Toast.makeText(getApplicationContext(),String.format("%d",Helper.result),Toast.LENGTH_SHORT).show();
                    }
                }else{
                   //Handle Null Value
                }
                //calculateTimer0();
            }
        });
        editText2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    /*
                    String s = editText2.getText().toString();

                    if(Helper.validateText(s)){
                        if ((Integer)Helper.result >= 0 && (Integer)Helper.result <=255){
                            calculateTimer0();
                            //String res = String.format("%s",Integer.toHexString((Integer)Helper.result)));
                            String HexValue = String.format("%02x",Helper.result);
                            //Toast.makeText(getApplicationContext(),String.format("0x%s",HexValue),Toast.LENGTH_SHORT).show();
                            editText2.setText(String.format("0x%s",HexValue.toUpperCase()));
                        }else{
                            setDefaultValue();
                            //Toast.makeText(getApplicationContext(),"Not a valid value.",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                       setDefaultValue();
                    }*/
                    calculateTimer0();

                }
            }
        });

        editText3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    calculatePreload();

                }
            }
        });


    }
    private void setDefaultValue(String message){
        Toast.makeText(getApplicationContext(),String.format("%s",message),Toast.LENGTH_LONG).show();
        editText2.setText("0x00");
        calculateTimer0();

    }

    private void calculatePreload(){
        String message = "Invalid value. Provide value within the range. Use s,ms,us or ns.Reset to default value.";
        String s=editText3.getText().toString();
        int preload;
        SharedPreferences sp;
        sp = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        int myClock = sp.getInt("CurrentClockHz",4000000); //4MHz as default value
        if(Helper.validateText((s))){
            if(MinTime <= (Double) Helper.result && MaxTime >= (Double) Helper.result){
                double p = (Double) Helper.result;
                preload = Helper.getPreload(p,myClock,1,256);
                String HexValue = String.format("%02x",preload);
                editText2.setText(String.format("0x%s",HexValue.toUpperCase()));
                calculateTimer0();
            }else{
                setDefaultValue(message);
            }
        }else{
            setDefaultValue(message);
        }

    }

    private void calculateTimer0(){
        String message = "Invalid value. Provide value within the range.Use Integer or Hex value. Reset to default value.";
        int minReload =0,maxReload=255,curReload =0;

        String str = editText2.getText().toString();

        if(Helper.validateText(str)){
            curReload = (int) Helper.result;
            //Toast.makeText(getApplicationContext(),String.format("%d",maxReload),Toast.LENGTH_SHORT).show();

            SharedPreferences sp;
            sp = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
            int myClock = sp.getInt("CurrentClockHz",4000000); //4MHz as default value

            lowText.setText(Helper.getTout(maxReload,myClock,1,256));
            MinTime = Helper.ToutResult;
            editText3.setText( Helper.getTout(curReload,myClock,1,256));
            highText.setText(Helper.getTout(minReload,myClock,1,256));
            MaxTime = Helper.ToutResult;
        }else{
            setDefaultValue(message);
        }



        //Toast.makeText(getApplicationContext(),String.format("%d",myClock),Toast.LENGTH_SHORT).show();

    }
}
