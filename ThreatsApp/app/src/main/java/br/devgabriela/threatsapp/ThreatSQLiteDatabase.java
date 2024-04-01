package br.devgabriela.threatsapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class ThreatSQLiteDatabase {
    Context ctx;
    public static final String DATABASE_NAME = "threats.db";
    public static final Integer DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    public ThreatSQLiteDatabase(Context ctx){
        this.ctx = ctx;
        db = new ThreatSQLiteDatabaseHelper().getWritableDatabase();
    }

    public static class ThreatTable implements BaseColumns{
        public static final String TABLE_NAME = "threat";
        public static final String COL_ADDRESS = "address";
        public static final String COL_DATE = "date";
        public static final String COL_DESCRIPTION = "description";

        public static String getSQL(){
            String sql = "CREATE TABLE " + TABLE_NAME + " ("+
                    _ID             + " INTEGER PRIMARY KEY, "+
                    COL_ADDRESS + " TEXT, "+
                    COL_DATE + " TEXT, "+
                    COL_DESCRIPTION + " TEXT)";

            return sql;
        }
    }

    public Long addThreat(Threat s){
        ContentValues values = new ContentValues();
        values.put(ThreatTable.COL_ADDRESS, s.getAddress());
        values.put(ThreatTable.COL_DATE, s.getDescription());
        values.put(ThreatTable.COL_DESCRIPTION, s.getDate());

        return db.insert(ThreatTable.TABLE_NAME, null, values);
    }

    public Threat getThreat(Long id){
        String cols[] = {ThreatTable._ID, ThreatTable.COL_ADDRESS, ThreatTable.COL_DATE, ThreatTable.COL_DESCRIPTION};
        String args[] = {id.toString()};
        Cursor cursor = db.query(ThreatTable.TABLE_NAME, cols, ThreatTable._ID+"=?", args, null, null, ThreatTable._ID);

        if(cursor.getCount() != 1){
            return null;
        }

        cursor.moveToNext();
        Threat s = new Threat();
        s.setId(cursor.getLong(cursor.getColumnIndex(ThreatTable._ID)));
        s.setAddress(cursor.getString(cursor.getColumnIndex(ThreatTable.COL_ADDRESS)));
        s.setDate(cursor.getString(cursor.getColumnIndex(ThreatTable.COL_DESCRIPTION)));
        s.setDescription(cursor.getString(cursor.getColumnIndex(ThreatTable.COL_DATE)));
        return s;
    }

    public List<Threat> getThreats(){
        String cols[] = {ThreatTable._ID, ThreatTable.COL_ADDRESS, ThreatTable.COL_DATE, ThreatTable.COL_DESCRIPTION};
        Cursor cursor = db.query(ThreatTable.TABLE_NAME, cols, null, null, null, null, ThreatTable.COL_DATE);
        List<Threat> threats = new ArrayList<>();
        Threat s;

        while(cursor.moveToNext()){
            s = new Threat();
            s.setId(cursor.getLong(cursor.getColumnIndex(ThreatTable._ID)));
            s.setAddress(cursor.getString(cursor.getColumnIndex(ThreatTable.COL_ADDRESS)));
            s.setDate(cursor.getString(cursor.getColumnIndex(ThreatTable.COL_DESCRIPTION)));
            s.setDescription(cursor.getString(cursor.getColumnIndex(ThreatTable.COL_DATE)));
            threats.add(s);
        }

        return threats;
    }

    public Integer removeThreat(Threat s){
        String args[] = {s.getId().toString()};
        return db.delete(ThreatTable.TABLE_NAME, ThreatTable._ID + "=?", args);
    }

    public Integer updateThreat(Threat s){
        String args[] = {s.getId().toString()};
        ContentValues values = new ContentValues();
        values.put(ThreatTable.COL_ADDRESS, s.getAddress());
        values.put(ThreatTable.COL_DATE, s.getDescription());
        values.put(ThreatTable.COL_DESCRIPTION, s.getDate());
        return db.update(ThreatTable.TABLE_NAME, values, ThreatTable._ID + "=?", args);
    }

    private class ThreatSQLiteDatabaseHelper extends SQLiteOpenHelper{
        public ThreatSQLiteDatabaseHelper(){super(ctx, DATABASE_NAME, null, DATABASE_VERSION);}

        public void onCreate(SQLiteDatabase db) {db.execSQL(ThreatTable.getSQL());}

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL("DROP TABLE IF EXISTS " + ThreatTable.TABLE_NAME );
            onCreate(db);
        }
    }
}
