package com.yomeon.mycontactmanager;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.yomeon.mycontactmanager.datamodel.DBhandler;
import com.yomeon.mycontactmanager.datamodel.Datamodel;

public class ContactDetails extends AppCompatActivity {
    String message,name,number;
    String[] str;
    Datamodel dm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar_add_contact);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.info);
        Bundle bundle = getIntent().getExtras();
        message = bundle.getString("NUMBER");
        getvalue(message);
        Log.d("After getvalue",dm.getFirstName());
        TextView txtname=(TextView)findViewById(R.id.text_first);
        TextView txtlast=(TextView)findViewById(R.id.text_last);
        TextView txtemail=(TextView)findViewById(R.id.text_email);
        TextView txtView = (TextView) findViewById(R.id.text_number);
        txtname.setText("FirstName:"+dm.getFirstName());
        txtlast.setText("LastName:"+dm.getLastname());
        txtemail.setText("Email:"+dm.getEmail());
        txtView.setText("Number:"+dm.getPhonenumber());

    }
    public void getvalue(String message){
        str=message.trim().split("\\n");
        name=str[0].trim();
        number=str[1].trim();
        DBhandler dBhandler=new DBhandler(this);
        Cursor cursor=dBhandler.getContactDetails(name,number);
        dm=new Datamodel(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));

    }
}
