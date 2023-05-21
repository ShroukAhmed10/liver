package com.example.liver;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class myDbAdapter {
    myDbHelper myhelper;
    public myDbAdapter(Context context)
    {
        myhelper = new myDbHelper(context);
    }

    public long insertData(String Total_Bilirubin, String Direct_Bilirubin, String Alkaline_Phosphotase , String Alamine_Aminotransferase, String Aspartate_Aminotransferase,String Total_Protiens, String Albumin, String Albumin_and_Globulin_Ratio)
    {

        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.Total_Bilirubin, Total_Bilirubin);
        contentValues.put(myDbHelper.Direct_Bilirubin, Direct_Bilirubin);
        contentValues.put(myDbHelper.Alkaline_Phosphotase, Alkaline_Phosphotase);
        contentValues.put(myDbHelper.Alamine_Aminotransferase, Alamine_Aminotransferase);
        contentValues.put(myDbHelper.Aspartate_Aminotransferase, Aspartate_Aminotransferase);
        contentValues.put(myDbHelper.Total_Protiens, Total_Protiens);
        contentValues.put(myDbHelper.Albumin, Albumin);
        contentValues.put(myDbHelper.Albumin_and_Globulin_Ratio, Albumin_and_Globulin_Ratio);

        long id = dbb.insert(myDbHelper.TABLE_NAME, null , contentValues);
        return id;
    }


    public String getData()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID, myDbHelper.Total_Bilirubin, myDbHelper.Direct_Bilirubin,
                myDbHelper.Alkaline_Phosphotase, myDbHelper.Alamine_Aminotransferase, myDbHelper.Aspartate_Aminotransferase,
        myDbHelper.Total_Protiens , myDbHelper.Albumin ,myDbHelper.Albumin_and_Globulin_Ratio};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndexOrThrow(myDbHelper.UID));
            String Total_Bilirubin =cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.Total_Bilirubin));
            String  Direct_Bilirubin =cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.Direct_Bilirubin));
            String  Alkaline_Phosphotase =cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.Alkaline_Phosphotase));
            String  Alamine_Aminotransferase =cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.Alamine_Aminotransferase));
            String  Aspartate_Aminotransferase =cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.Aspartate_Aminotransferase));
            String  Total_Protiens =cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.Total_Protiens));
            String  Albumin =cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.Albumin));
            String  Albumin_and_Globulin_Ratio =cursor.getString(cursor.getColumnIndexOrThrow(myDbHelper.Albumin_and_Globulin_Ratio));

            buffer.append(cid+ "  bi  " + Total_Bilirubin + "  di " + Direct_Bilirubin  +"   alk  " +
                    Alkaline_Phosphotase + "   ala  " +  Alamine_Aminotransferase  +"   asp  " + Aspartate_Aminotransferase +" pro " +Total_Protiens + "  alb " +
                    Albumin +" alg " +  Albumin_and_Globulin_Ratio  );
        }
        return buffer.toString();
    }

    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "myDatabase";
        public static final String TABLE_NAME = "myTable";
        private static final int DATABASE_Version = 1;
        private static final String UID="_id";
        private static final String Total_Bilirubin = "Total_Bilirubin";
        private static final String Direct_Bilirubin= "Direct_Bilirubin";
        private static final String Alkaline_Phosphotase= "Alkaline_Phosphotase";
        private static final String Alamine_Aminotransferase= "Alamine_Aminotransferase";
        private static final String Aspartate_Aminotransferase= "Aspartate_Aminotransferase";
        private static final String Total_Protiens= "Total_Protiens";
        private static final String Albumin= "Albumin";
        private static final String Albumin_and_Globulin_Ratio= "Albumin_and_Globulin_Ratio";


        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+ UID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+   Total_Bilirubin   +" VARCHAR(255) ,"+   Direct_Bilirubin  +" VARCHAR(225),"+   Alkaline_Phosphotase  +" VARCHAR(225) ,"+   Alamine_Aminotransferase  +"  VARCHAR(225) ,"+   Aspartate_Aminotransferase  +" VARCHAR(225),"+   Total_Protiens  +" VARCHAR(225),"+   Albumin  +" VARCHAR(225),"+   Albumin_and_Globulin_Ratio  +" VARCHAR(225))";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;

        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                Message.message(context,""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context,"OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
                Message.message(context,""+e);
            }
        }

    }

}



