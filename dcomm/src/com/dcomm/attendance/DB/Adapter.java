package com.dcomm.attendance.DB;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * User: Brandon
 * Date: 4/6/13
 * Time: 5:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class Adapter extends SQLiteOpenHelper {

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_EAGLE_ID = "eagle_id";
    public static final String COLUMN_NAME = "name";
    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE =
            "create table " + TABLE_USERS + "(" + COLUMN_ID + " integer primary key autoincrement, " + COLUMN_EAGLE_ID + " text not null, " + COLUMN_NAME
            + " text not null);";

    public Adapter(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //To change body of implemented methods use File | Settings | File Templates.
        sqLiteDatabase.execSQL(DATABASE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        //To change body of implemented methods use File | Settings | File Templates.
        Log.w(Adapter.class.getName(),"Upgrading database from version " + i + " to " + i2 + ", which will destroy all old data");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(sqLiteDatabase);
    }
}
