package com.dwijantojohan.lie.picmicrocontroller;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by dlie on 5/27/2016.
 */
public class CalculatorContextWrapper extends ContextWrapper implements View.OnFocusChangeListener,AdapterView.OnItemSelectedListener,CompoundButton.OnCheckedChangeListener,ActivityConstant {

    Activity act;
    public CheckBox checkBox;
    public EditText editText2;
    public EditText editText3;
    private TextView lowText;
    private TextView highText;
    public Spinner spinner2;
    public Spinner spinner3;
    private int myClock;
    private String TimerErrMsg = "";
    private String PreloadErrMsg= "";

    private int prescallerValue = 1;
    private int clockSourceValue=4;
    private Double MinTime;
    private Double MaxTime;
    public int MaxPreloadValue;
    private String SMinTime;
    private String SMaxTime;
    private String[] spinnerList;
    private int callerActivity;

    SharedPreferences sp;


    public CalculatorContextWrapper(Context base,int MaxPreloadValue,int callerActivity) {
        super(base);
        this.MaxPreloadValue = MaxPreloadValue;
        this.callerActivity = callerActivity;
        act = ((Activity) this.getBaseContext());
        initialized();
    }

    private void initialized(){
        checkBox = (CheckBox) act.findViewById(R.id.checkBox);
        TextView textView = (TextView) act.findViewById(R.id.textView9) ;

        textView.setText(Html.fromHtml("&#8804"));
        textView = (TextView) act.findViewById(R.id.textView11) ;
        textView.setText(Html.fromHtml("&#8804"));
        textView = (TextView) act.findViewById(R.id.textView16) ;
        textView.setText(Html.fromHtml("&#8804"));
        textView = (TextView) act.findViewById(R.id.textView17) ;
        textView.setText(Html.fromHtml("&#8804"));
        textView = (TextView) act.findViewById(R.id.textView12) ;
        textView.setText("0xFFFF");

        spinner2 = (Spinner) act.findViewById(R.id.spinner2);
        spinner2.setEnabled(false);
/*
        switch(MaxPreloadValue) {
            case 65536: //Timer1
                spinnerList = act.getResources().getStringArray(R.array.prescallertimer1);
                break;
            case 256:  //Timer0 and 2
                spinnerList = act.getResources().getStringArray(R.array.prescaller8bits);
                break;
        }*/
        switch (callerActivity){
            case ActivityConstant.TIMER1:
                spinnerList = act.getResources().getStringArray(R.array.prescallertimer1);
                break;
            default: //Timer0 and Timer2
                spinnerList = act.getResources().getStringArray(R.array.prescaller8bits);
                break;
        }
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,spinnerList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(spinnerAdapter);

        spinner3 = (Spinner) act.findViewById(R.id.spinner3);
        editText2=(EditText) act.findViewById(R.id.editText2);
        editText3=(EditText) act.findViewById(R.id.editText3);
        lowText = (TextView) act.findViewById(R.id.textView15);
        highText = (TextView) act.findViewById(R.id.textView18);

        //SharedPreferences sp;
        sp = act.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        myClock = sp.getInt("CurrentClockHz",4000000); //4MHz as default value
        TimerErrMsg = act.getResources().getString(R.string.InvalidTimeValue);
        PreloadErrMsg =  act.getResources().getString(R.string.InvalidPreloadValue);

    }

    public void calculatePreload(int prescallerValue,int clockSourceValue){
        this.prescallerValue = prescallerValue;
        this.clockSourceValue = clockSourceValue;
        int preload;

        String value= editText3.getText().toString();

        if(Helper.validateText((value))){
            if(MinTime <= (Double)Helper.result && MaxTime >= (Double) Helper.result){
                double p = (Double) Helper.result;
                preload = Helper.getPreload(p,myClock,prescallerValue,MaxPreloadValue,clockSourceValue);
                //save value to SharedPreferences
                saveSharedPreference(preload);

                String HexValue = String.format("%02x",preload);
                editText2.setText(String.format("0x%s",HexValue.toUpperCase()));
            }else{
                setDefaultValue((String)TimerErrMsg);
            }
        }else{
            setDefaultValue((String)TimerErrMsg);
        }
    }

    public void calculateTimer(int prescallerValue,int clockSourceValue){
        this.prescallerValue = prescallerValue;
        this.clockSourceValue = clockSourceValue;
        int minReload =0,maxReload=MaxPreloadValue-1,curReload =0;
        String value = editText2.getText().toString();
        if(Helper.validateText(value)){
            curReload = (int) Helper.result;
            saveSharedPreference(curReload);
            SMinTime = Helper.getTout(maxReload,myClock,prescallerValue,MaxPreloadValue,clockSourceValue);
            lowText.setText(SMinTime);
            MinTime = Helper.ToutResult;
            editText3.setText( Helper.getTout(curReload,myClock,prescallerValue,MaxPreloadValue,clockSourceValue));
            SMaxTime = Helper.getTout(minReload,myClock,prescallerValue,MaxPreloadValue,clockSourceValue);
            highText.setText(SMaxTime);
            Helper.validateText(SMaxTime);
            MaxTime = (Double) Helper.result;
        }else{
            setDefaultValue((String)PreloadErrMsg);
        }
    }

    public void setDefaultValue(String message){
        Toast.makeText(this.getBaseContext().getApplicationContext(),String.format("%s",message),Toast.LENGTH_LONG).show();
        editText2.setText("0x00");
        calculateTimer(prescallerValue,clockSourceValue);
    }

    public void saveSharedPreference(int preload) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(String.format("PreloadTimer%d",callerActivity),preload);
        editor.commit();
    }

    /** Listener **/
    @Override
    public void onFocusChange(View v,boolean hasFocus){
        if(!hasFocus){
            switch (v.getId()){
                case R.id.editText2:
                    String s = editText2.getText().toString();
                    if(Helper.validateText(s)){
                        if ((Integer)Helper.result >= 0 && (Integer)Helper.result < MaxPreloadValue){
                            String HexValue = String.format("%02x",Helper.result);
                            editText2.setText(String.format("0x%s",HexValue.toUpperCase()));
                            calculateTimer(prescallerValue,clockSourceValue);
                        }else{
                            setDefaultValue(PreloadErrMsg);
                        }
                    }else{
                        setDefaultValue(PreloadErrMsg);
                    }
                    break;
                case R.id.editText3:
                    calculatePreload(prescallerValue,clockSourceValue);
                    break;
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
        switch (parent.getId()){
            case R.id.spinner2:
                int pos = 0;
                if (position > 0){
                    pos=position+1;
                }
                prescallerValue = (int) Math.pow(2,pos);
                break;
            case R.id.spinner3:
                switch (position){
                    case 0:
                        clockSourceValue=4;
                        break;
                    case 1:
                        clockSourceValue = 1;
                }
                break;
        }
        calculateTimer(prescallerValue,clockSourceValue);
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        spinner2.setEnabled(isChecked);
        spinner2.setSelection(0);
        prescallerValue=1;
        if(isChecked){
            prescallerValue=2;
        }
        calculateTimer(prescallerValue,clockSourceValue);
    }
}
