package com.example.placementpulseapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME="placements";
    private static final String COLUMN_ID="id";
    private static final String COLUMN_NAME="company_name";
    private static final String COLUMN_DATE="last_date";
    private static final String COLUMN_PACKAGE="package";
    private static final String COLUMN_TYPE="type";
    private static final String COLUMN_DESC="description";
    private static final String COLUMN_ELIGIBILITY="eligibility";
    private Context context;

    DBHelper(Context context){
        super(context,"Login.db",null,2);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("create Table users(username Text primary key,password Text)");
        String query=
                "CREATE TABLE " + TABLE_NAME +
                        " ("+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, "+
                        COLUMN_TYPE + " TEXT, "+
                        COLUMN_PACKAGE + " INTEGER, "+
                        COLUMN_ELIGIBILITY + " TEXT, "+
                        COLUMN_DATE +" TEXT, "+
                        COLUMN_DESC + " TEXT);";
        myDB.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int i, int i1) {
        myDB.execSQL("drop Table if exists users");
        myDB.execSQL("drop Table if exists "+TABLE_NAME);
        onCreate(myDB);

    }
    public Boolean insertData(String username,String password){
        SQLiteDatabase myDB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long result=myDB.insert("users",null,contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean checkUsername(String username){
        SQLiteDatabase myDB=this.getWritableDatabase();
        Cursor cursor=myDB.rawQuery("select * from users where username =?",new String[] {username});
        if(cursor.getCount()>0){
            return  true;
        }else{
            return false;
        }
    }

    public Boolean checkUsernamePassword(String username,String password){
        SQLiteDatabase myDB=this.getWritableDatabase();
        Cursor cursor=myDB.rawQuery("select * from users where username =? and password=?",new String[] {username,password});
        if(cursor.getCount()>0){
            return  true;
        }else{
            return false;
        }
    }
    void addPlacement(String companyname,String lastdate,int lpa,String eligibility,String desc,String type){
        SQLiteDatabase myDB=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_NAME,companyname);
        cv.put(COLUMN_DATE,lastdate);
        cv.put(COLUMN_PACKAGE,lpa);
        cv.put(COLUMN_ELIGIBILITY,eligibility);
        cv.put(COLUMN_DESC,desc);
        cv.put(COLUMN_TYPE,type);
        long result=myDB.insert(TABLE_NAME,null,cv);
        if(result==-1)
        {
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context,"Added Successfully",Toast.LENGTH_SHORT).show();
        }

    }
    Cursor readAllData(){
        String query="SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase myDB=this.getReadableDatabase();
        Cursor cursor=null;
        if(myDB!=null)
        {
            cursor=myDB.rawQuery(query,null);

        }
        return cursor;
    }
    public boolean updateData(String id, String name, String description, String eligibility, String lpa, String lastdate, String type)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("company_name", name);
        contentValues.put("last_date", lastdate);
        contentValues.put("package", lpa);
        contentValues.put("eligibility", eligibility);
        contentValues.put("description", description);
        contentValues.put("type", type);

        long result = db.update(TABLE_NAME, contentValues, "id=?", new String[]{id});
        return result != -1; // Returns true if update is successful
    }

    void deleteOneRow(String id){
        SQLiteDatabase myDB=this.getWritableDatabase();
        long result=myDB.delete(TABLE_NAME,"id=?",new String[]{id});
        if(result==-1)
        {
            Toast.makeText(context,"Failed to Delete",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context,"Successfully Deleted",Toast.LENGTH_SHORT).show();
        }
    }


}
