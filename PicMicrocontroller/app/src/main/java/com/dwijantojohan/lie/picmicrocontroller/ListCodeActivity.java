package com.dwijantojohan.lie.picmicrocontroller;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class ListCodeActivity extends AppCompatActivity implements ShareActionProvider.OnShareTargetSelectedListener {
    public int callerActivity;
    private ShareActionProvider share=null;
    private Intent shareIntent = new Intent(Intent.ACTION_SEND);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_code);
        initialized();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.listcodemenu,menu);
        return true;
    }
    private Intent createShareIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,
                "this text will be shared");
        return shareIntent;
    }
    @Override
    public boolean onShareTargetSelected(ShareActionProvider source,Intent intent){
        Toast.makeText(this,intent.getComponent().toString(),Toast.LENGTH_LONG).show();
        return(false);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_codeshare:
                share = new ShareActionProvider(this);
                share.setShareIntent(createShareIntent());
                MenuItemCompat.setActionProvider(item,share);

                //Toast.makeText(this.getBaseContext(),String.format("%s","Share"),Toast.LENGTH_LONG).show();

                return true;
            case R.id.action_codemail:
                Toast.makeText(this.getBaseContext(),String.format("%s","Mail"),Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void initialized(){
        setTitle(R.string.TitleListCodeActivity);
        callerActivity = getIntent().getIntExtra("calling_activity",0);
        GenerateCodeContextWrapper gccw = new GenerateCodeContextWrapper(this);
        ListView l = (ListView) findViewById(R.id.listView);
        l.setAdapter(new CodeCustomAdapter(this,gccw.getModel()));



    }
}
