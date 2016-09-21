package com.yomeon.mycontactmanager.datamodel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sampa on 18-09-2016.
 */
public class DBhandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="ContactManager";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME="contacts";
    private static final String KEY_FISRT_NAME="firstname";
    private static final String KEY_LAST_NAME="lastname";
    private static final String KEY_EMAIL="email";
    private static final String KEY_PHONE_NUMBER="phonenumber";

    public DBhandler(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREAT_TABLE="CREATE TABLE "+TABLE_NAME+"("+KEY_FISRT_NAME+" TEXT,"+KEY_LAST_NAME+" TEXT,"+KEY_EMAIL+" TEXT,"+KEY_PHONE_NUMBER+" TEXT PRIMARY KEY  "+")";
        sqLiteDatabase.execSQL(CREAT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    //Inserting contact in database
    public void addContact(Datamodel dm){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_FISRT_NAME,dm.getFirstName());
        values.put(KEY_LAST_NAME,dm.getLastname());
        values.put(KEY_EMAIL,dm.getEmail());
        values.put(KEY_PHONE_NUMBER,dm.getPhonenumber());

        db.insert(TABLE_NAME,null,values);
        db.close();

    }
    //GET ALL CONTACTS
    public List<Datamodel> getAllContacts(){
        SQLiteDatabase db=getReadableDatabase();
        String query="SELECT * FROM "+TABLE_NAME;
        List<Datamodel> contactlist = new ArrayList<Datamodel>();
        Cursor cursor=db.rawQuery(query,null);
        if(cursor!=null){
            cursor.moveToFirst();
            while(cursor.moveToNext()){
                Datamodel prelist=new Datamodel(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
                contactlist.add(prelist);
            }
        }
        return contactlist;
    }
    //Number of contacts in database
    public int getCount(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM"+TABLE_NAME,null);
        cursor.close();
        return cursor.getCount();
    }
    //Update the number using names
    public String updateNumber(Datamodel datamodel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(KEY_FISRT_NAME,datamodel.getFirstName());
        values.put(KEY_PHONE_NUMBER,datamodel.getPhonenumber());
        return String.valueOf(db.update(TABLE_NAME,values,KEY_PHONE_NUMBER+"=?",new String[]{datamodel.getPhonenumber()}));
    }
    //Delete contact from contact database
    public void deleteContact(Datamodel datamodel){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME,KEY_PHONE_NUMBER+"=?",new String[]{datamodel.getPhonenumber()});
        db.close();
    }
    public Cursor getListViewItems(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor list_cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        if(list_cursor!=null){
            list_cursor.moveToFirst();
        }else{
            Log.d("database empty","Extablish");
        }
        return  list_cursor;
    }
    public Cursor getContactDetails(String name,String number){
        SQLiteDatabase db=this.getReadableDatabase();
                Cursor contact_cursor=db.query(TABLE_NAME,new String[]{KEY_FISRT_NAME,KEY_LAST_NAME,KEY_EMAIL,KEY_PHONE_NUMBER},KEY_PHONE_NUMBER+"=?", new String[] { number },null,null,null);
        if(contact_cursor!=null){
            contact_cursor.moveToFirst();
        }else{
            Log.d("database empty","Extablish");
        }
        return contact_cursor;
    }
}
