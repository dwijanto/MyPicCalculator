package com.dwijantojohan.lie.picmicrocontroller;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
            }
        });

        return rowview;
    }

    public long getItemId(int position){
        return position;
    }

    public Object getItem(int position){
        return position;
    }


}
