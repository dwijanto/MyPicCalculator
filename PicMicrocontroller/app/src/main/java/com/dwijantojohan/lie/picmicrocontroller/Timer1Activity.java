package com.dwijantojohan.lie.picmicrocontroller;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Timer1Activity extends AppCompatActivity {
    private Calculator cal;
    private CheckBox checkBox;
    private EditText editText2;
    private EditText editText3;
    private TextView lowText;
    private TextView highText;
    private Spinner spinner2;
    private Spinner spinner3;
    private int prescallerValue = 1;
    private int clockSourceValue = 4;
    private int myClock;
    private final int MaxPreload = 65536;
    private String TimerErrMsg = "";
    private String PreloadErrMsg= "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.TitleTimer1Activity);
        setContentView(R.layout.activity_timer0);

        initialized();
        listener();

        cal = new Calculator(myClock,MaxPreload,editText2,editText3,lowText,highText,this,TimerErrMsg,PreloadErrMsg);
        cal.calculateTimer(prescallerValue,clockSourceValue);
    }

    private void initialized(){
        setTitle(R.string.TitleTimer1Activity);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        TextView textView = (TextView) findViewById(R.id.textView9) ;

        textView.setText(Html.fromHtml("&#8804"));
        textView = (TextView) findViewById(R.id.textView11) ;
        textView.setText(Html.fromHtml("&#8804"));
        textView = (TextView) findViewById(R.id.textView16) ;
        textView.setText(Html.fromHtml("&#8804"));
        textView = (TextView) findViewById(R.id.textView17) ;
        textView.setText(Html.fromHtml("&#8804"));
        textView = (TextView) findViewById(R.id.textView12) ;
        textView.setText("0xFFFF");
        //Spinner
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setEnabled(false);
        //Spinner
        spinner3 = (Spinner) findViewById(R.id.spinner3);
        editText2=(EditText) findViewById(R.id.editText2);
        editText3=(EditText) findViewById(R.id.editText3);
        lowText = (TextView) findViewById(R.id.textView15);
        highText = (TextView) findViewById(R.id.textView18);
        SharedPreferences sp;
        sp = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        myClock = sp.getInt("CurrentClockHz",4000000); //4MHz as default value
        TimerErrMsg =  getResources().getString(R.string.InvalidTimeValue);//getResources().getString(R.string.InvalidTimeValue);
        PreloadErrMsg =  getResources().getString(R.string.InvalidPreloadValue);

    }
    private void listener(){
        //final Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int pos = 0;
                if (position > 0){
                    pos=position+1;
                }
                prescallerValue = (int) Math.pow(2,pos);
                cal.calculateTimer(prescallerValue,clockSourceValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        clockSourceValue=4;
                        break;
                    case 1:
                        clockSourceValue = 1;
                }
                cal.calculateTimer(prescallerValue,clockSourceValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Spinner spinner = (Spinner) findViewById(R.id.spinner2);
                spinner2.setEnabled(isChecked);
                spinner2.setSelection(0);
                prescallerValue=1;
                if(isChecked){
                    prescallerValue=2;
                }
                cal.calculateTimer(prescallerValue,clockSourceValue);


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
            }
        });
        editText2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){

                    String s = editText2.getText().toString();

                    if(Helper.validateText(s)){
                        if ((Integer)Helper.result >= 0 && (Integer)Helper.result < MaxPreload){
                            String HexValue = String.format("%02x",Helper.result);
                            editText2.setText(String.format("0x%s",HexValue.toUpperCase()));
                            cal.calculateTimer(prescallerValue,clockSourceValue);
                        }else{
                            cal.setDefaultValue("Invalid value. Provide value within the range.Use Integer or Hex value. Reset to default value.");
                        }
                    }else{
                        cal.setDefaultValue("Invalid value. Provide value within the range.Use Integer or Hex value. Reset to default value.");
                    }
                }
            }
        });

        editText3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    cal.calculatePreload(prescallerValue,clockSourceValue);
                }
            }
        });



    }
}
