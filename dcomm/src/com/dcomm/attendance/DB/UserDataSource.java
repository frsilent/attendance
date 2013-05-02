package com.dcomm.attendance.DB;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.dcomm.attendance.User;

/**
 * Created with IntelliJ IDEA.
 * User: Brandon
 * Date: 4/6/13
 * Time: 6:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserDataSource {
    private SQLiteDatabase database;
    private Adapter dbHelper;
    private String[] allColumns = { Adapter.COLUMN_ID, Adapter.COLUMN_EAGLE_ID, Adapter.COLUMN_NAME};

    public UserDataSource(Context context)
    {
        dbHelper = new Adapter(context);
    }

    public void open() throws SQLException
    {
        database = dbHelper.getWritableDatabase();
    }

    public void close()
    {
        dbHelper.close();
    }

    public User createUser(String name, int eagleID)
    {
        ContentValues values = new ContentValues();
        values.put(Adapter.COLUMN_EAGLE_ID, eagleID);
        values.put(Adapter.COLUMN_NAME, name);
        Long insertId = database.insert(Adapter.TABLE_USERS, null, values);
        System.out.println(insertId.toString());
        Cursor cursor = database.query(Adapter.TABLE_USERS, allColumns, Adapter.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        User newUser = cursorToUser(cursor);
        cursor.close();
        return newUser;
    }

    private User cursorToUser(Cursor cursor) {
        User user = new User();
        user.setID(cursor.getInt(0));
        user.setEagleID(cursor.getInt(1));
        user.setName(cursor.getString(2));
        return user;
    }

    public void editUser(String newName, int newID)
    {
        ContentValues values = new ContentValues();
        values.put(Adapter.COLUMN_EAGLE_ID, newID);
        values.put(Adapter.COLUMN_NAME,newName);
        database.update(Adapter.TABLE_USERS,values,"_id = 1",null);

    }
    public boolean checkOnlyUser()
    {
        String countQuery = "SELECT count(*) from users;";
        Cursor tmp =  database.rawQuery(countQuery,null);
        tmp.moveToFirst();
        int count = tmp.getInt(0);
        return (count == 1);
    }

    public int getUserID()
    {
        String query = "SELECT eagle_id from users where _id = 1";
        Cursor tmp = database.rawQuery(query, null);
        tmp.moveToFirst();
        //return tmp.getString(0);
        return tmp.getInt(0);
    }
}
