package com.dwijantojohan.lie.picmicrocontroller;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

/**
 * Created by dlie on 6/2/2016.
 */
public class CodeCustomAdapter extends BaseAdapter {
    private Context ctx;
    List<CodeModel> data;
    public CodeCustomAdapter(Context ctx,List<CodeModel> data){
        super();
        this.ctx = ctx;
        this.data = data;
    }

    public int getCount(){
        if(data.size()<=0)
            return 1;
        return data.size();
    }

    public View getView(final int position, View view, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowview = inflater.inflate(R.layout.coderowitem,null);
        final CodeModel cm = data.get(position);

        TextView textView = (TextView) rowview.findViewById(R.id.CodeRowtextView);
        textView.setText(cm.getCodeName());
        ImageView img=(ImageView) rowview.findViewById(R.id.CodeRowImage);
        if (cm.getExtension()=="c"){
            img.setImageResource(R.mipmap.ic_c);
        }else{
            img.setImageResource(R.mipmap.ic_h);
        }

        rowview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx,String.format("Selected file: %s",cm.getCodeName()),Toast.LENGTH_SHORT).show();
                //Create Intent, show filename
                //File myfile = new File("/data/data/com.dwijantojohan.lie.picmicrocontroller/app_TimerTmp/project.h");
                /*
                File file = ctx.getDir("TimerTmp",Context.MODE_PRIVATE);
                File myfile = new File(file,"project.h");

                //File myfile= new File("app_TimerTmp/project.h");
                myfile.getAbsolutePath();
                Intent i = new Intent(Intent.ACTION_VIEW);

                i.setDataAndType(Uri.fromFile(myfile),"Text/text");
                ctx.startActivity(i);
                */


                File file = ctx.getDir("TimerTmp",Context.MODE_PRIVATE);
                //File myfile = new File(file,"project.h");
                File myfile = new File(file,cm.getCodeName());
                StringBuilder builder = new StringBuilder("");
                try{
                    //BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(file,"project.h")));
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(file,cm.getCodeName())));
                    String read;
//                    StringBuilder builder = new StringBuilder("");

                    while((read = bufferedReader.readLine()) != null){
                        builder.append(read);
                    }
                    Log.d("Output", builder.toString());
                    bufferedReader.close();
                }catch (Exception e){
                    e.printStackTrace();
                }

                //Intent i = new Intent(Intent.ACTION_VIEW);
                //i.setDataAndType(Uri.fromFile(myfile),"text/plain");


                Intent intent = new Intent(ctx.getApplicationContext(),FileViewerActivity.class);
                intent.putExtra("showContent",builder.toString());
                ctx.startActivity(intent);

            }
        });

        return rowview;
    }
    public void showFilename(Uri uri){

    }
    public long getItemId(int position){
        return position;
    }

    public Object getItem(int position){
        return position;
    }


}
