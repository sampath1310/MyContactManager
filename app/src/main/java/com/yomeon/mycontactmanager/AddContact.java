package com.yomeon.mycontactmanager;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import com.yomeon.mycontactmanager.datamodel.*;

public class AddContact extends AppCompatActivity {

    private static final String TAG="ADD CONTACT CLASS";
    private EditText first_name, last_name, email, phone_number;
    private TextInputLayout first_name_layout, last_name_layout, email_layout, phone_number_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        Toolbar   toolbar = (Toolbar) findViewById(R.id.my_toolbar_add_contact);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.add_contact);
        getSupportActionBar().setIcon(R.drawable.ic_add_contact);
        first_name = (EditText) findViewById(R.id.add_contact_first_name);
        last_name = (EditText) findViewById(R.id.add_contact_Last_name);
        email = (EditText) findViewById(R.id.add_contact_Email);
        phone_number = (EditText) findViewById(R.id.add_contact_phonenumber);
        first_name_layout = (TextInputLayout) findViewById(R.id.first_layout_name);
        last_name_layout = (TextInputLayout) findViewById(R.id.Last_layout_name);
        email_layout = (TextInputLayout) findViewById(R.id.Email_layout_name);
        phone_number_layout = (TextInputLayout) findViewById(R.id.phonenumber_layout_name);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_contact_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tick:
                saveContact();
                Toast.makeText(this, "Contact Added", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void saveContact() {
        String fname=null,lname = null,semail=null,phno=null;
        if (validFirstName()) {
           fname=first_name.getText().toString();
            Log.d(TAG,"firstname"+first_name.getText().toString());
        }
        if(validLasttName()){
            lname=new String(last_name.getText().toString());
            Log.d(TAG,"lastname:"+last_name.getText().toString());
        }
        if(validEmail()){
            semail=email.getText().toString();
            Log.d(TAG,"email:"+email.getText().toString());
        }
        if(validPhoneNumber()){
            phno=phone_number.getText().toString();
            Log.d(TAG,"phonenumber"+phone_number.getText().toString());
        }
        Datamodel dm=new Datamodel(fname,lname,semail,phno);
        Log.d(TAG,"Datamodel created");
        DBhandler db=new DBhandler(getApplicationContext());
       db.addContact(dm);
        Log.d(TAG,"db handeled contact");

    }

    private boolean validFirstName() {
        if (first_name.getText().toString().trim().isEmpty()) {
            first_name_layout.setError(getString(R.string.err_first_name));
            first_name_layout.requestFocus();
            return false;
        } else {
            first_name_layout.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validLasttName() {
        if (last_name.getText().toString().trim().isEmpty()) {
            last_name_layout.setError(getString(R.string.err_last_name));
            last_name_layout.requestFocus();
            return false;
        } else {
            last_name_layout.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validEmail() {
           String c_email=email.getText().toString().trim();
        if (c_email.isEmpty() || !isValidEmail(c_email)) {
         email_layout.setError(getString(R.string.err_email));
            email_layout.requestFocus();
            return false;
        }else {
            email_layout.setErrorEnabled(false);
        }
     return true;
    }
    private static boolean isValidEmail(String email){
        return !email.isEmpty()&& Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private boolean validPhoneNumber(){
        if(!PhoneNumberUtils.isGlobalPhoneNumber(phone_number.getText().toString().trim())){
            phone_number_layout.setError(getString(R.string.err_phone_number));
            phone_number_layout.requestFocus();
            return false;
        }else{
            phone_number_layout.setErrorEnabled(false);
             }
        return true;
    }
}