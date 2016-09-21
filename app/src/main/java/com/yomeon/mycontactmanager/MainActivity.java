package com.yomeon.mycontactmanager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.yomeon.mycontactmanager.datamodel.DBhandler;
import com.yomeon.mycontactmanager.datamodel.Datamodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView listview;
    DBhandler dBhandler;
    SQLiteDatabase db;
    ListItem dm;
    ArrayList<Object> arrayList;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.toolbar_title);
        getSupportActionBar().setIcon(R.drawable.ic_toolbar_title);
        listview = (ListView) findViewById(R.id.list_view);
        arrayList = new ArrayList<>();
        dBhandler = new DBhandler(this);
        db = dBhandler.getWritableDatabase();
        enterFirstContact();
        cursor = dBhandler.getListViewItems();
        if(cursor!=null) {
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                arrayList.add(" " + cursor.getString(0) + "\n " + cursor.getString(3));
                Log.d("Loop", cursor.getString(0) + "\n" + cursor.getString(3));
            }
        }
        ListAdapter arrayAdapter = new ArrayAdapter(this, R.layout.my_list_view_text_edit, arrayList);
        listview.setAdapter(arrayAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getApplicationContext(),ContactDetails.class);
                intent.putExtra("NUMBER",arrayList.get(i).toString());
                startActivity(intent);

            }
        });
        db.close();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_contact:
                Intent intent = new Intent(this, AddContact.class);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

public void enterFirstContact(){
    Datamodel dm=new Datamodel("Emergency"," "," ","100");
    dBhandler.addContact(dm);
}
}
