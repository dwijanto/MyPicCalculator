package com.dwijantojohan.lie.picmicrocontroller;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dlie on 6/2/2016.
 */
public class GenerateCodeContextWrapper extends ContextWrapper {
    Activity act;
    int reloadValue;
    EditText editText2;
    private List<CodeModel> cm = new ArrayList<CodeModel>();
    SharedPreferences sp;
    public static final String MyPreferences = "MyPref";
    private int preload;

    public List<CodeModel> getModel(){
        return cm;
    }

    public  GenerateCodeContextWrapper(Context base){
        super(base);
        act = (Activity) this.getBaseContext();
        initialized();
    }

    private void initialized(){

        sp = getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);
        switch (((ListCodeActivity)act).callerActivity){
            case 0:
                //get Preload value from sharedpreference
                preload = sp.getInt("PreloadTimer0",0);
                Toast.makeText(this.getBaseContext(),String.format("%d",preload),Toast.LENGTH_LONG).show();
                generateTimer0Files();
                break;
            case 1:
                preload = sp.getInt("PreloadTimer1",0);
                generateTimer1Files();
                break;
            case 2:
                preload = sp.getInt("PreloadTimer2",0);
                generateTimer2Files();
                break;

        }

        //String value = editText2.getText().toString();

        //Toast.makeText(this.getBaseContext(),String.format("%s",value),Toast.LENGTH_LONG).show();
    }

    public void generateTimer0Files(){
        //String value = editText2.getText().toString();
        //if(Helper.validateText(value)){

            //Create Files Headers and Implements in temp folder

            //Iterate base on filename in temp folder
            CodeModel m = new CodeModel("Project.h","h");
            cm.add(m);
            m = new CodeModel("Project.c","c");
            cm.add(m);
            m = new CodeModel("Timer.h","h");
            cm.add(m);
            m = new CodeModel("Timer.c","c");
            cm.add(m);
            m = new CodeModel("Main.c","c");
            cm.add(m);
            //end iteration


        //}
    }

    public void generateTimer1Files(){

    }

    public void generateTimer2Files(){

    }
}
