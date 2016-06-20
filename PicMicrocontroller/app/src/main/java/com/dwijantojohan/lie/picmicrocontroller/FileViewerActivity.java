package com.dwijantojohan.lie.picmicrocontroller;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class FileViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_viewer);

        Intent i = getIntent();
        String s = i.getExtras().getString("showContent");
        TextView textView = (TextView) this.findViewById(R.id.textView13) ;
        textView.setText(s);
    }
}
