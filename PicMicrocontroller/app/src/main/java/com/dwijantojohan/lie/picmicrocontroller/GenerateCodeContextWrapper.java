package com.dwijantojohan.lie.picmicrocontroller;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
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

            //Create tmp folder
            //File file = new File(this.getBaseContext().getDir("TimerTmp",MODE_PRIVATE));

        PackageManager mg = getPackageManager();
        String s = getPackageName();
        try{
            PackageInfo p = mg.getPackageInfo(s,0);
            s = p.applicationInfo.dataDir;
        } catch (PackageManager.NameNotFoundException e){
            Log.w("DJTag","Error Package name not found ",e);
        }



        File file = getBaseContext().getDir("TimerTmp",MODE_PRIVATE);

        if (!file.exists()){
            file.mkdir();
        }
        File myfile = new File(file,"Project.h");

        //Create Files Headers and Implements in temp folder
        try
        {
            FileWriter writer = new FileWriter(myfile);
            String filename ="Project.h";
            writer.append("this is project.h");
            writer.append(" \n");
            writer.append("Second Line for this is project.h ");
            //writer.append(System.getProperty("line.separator"));
            writer.append(" \n");
            writer.append("Third Line for this is project.h");
            writer.append(" \n");
            writer.flush();
            writer.close();
        }catch (Exception e){

        }

        myfile = new File(file,"Project.c");

        //Create Files Headers and Implements in temp folder
        try
        {
            FileWriter writer = new FileWriter(myfile);
            String filename ="Project.c";
            writer.append("this is project.c");
            writer.append(" \n");
            writer.append("Second Line for this is project.c");
            writer.append(" \n");
            writer.append("Third Line for this is project.c");
            writer.append(" \n");
            writer.flush();
            writer.close();
        }catch (Exception e){

        }

        /*String filename = "myfile";
        String string = "Hello world!";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/


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
