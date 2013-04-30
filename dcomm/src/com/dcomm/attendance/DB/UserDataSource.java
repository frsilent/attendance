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
    private String[] allcolumns = { Adapter.COLUMN_ID, Adapter.COLUMN_EAGLE_ID, Adapter.COLUMN_NAME};

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

    public User createUser(String name, String eagle_id)
    {
        ContentValues values = new ContentValues();
        values.put(Adapter.COLUMN_EAGLE_ID, eagle_id);
        values.put(Adapter.COLUMN_NAME, name);
        Long insertId = database.insert(Adapter.TABLE_USERS, null, values);
        System.out.println(insertId.toString());
        Cursor cursor = database.query(Adapter.TABLE_USERS, allcolumns, Adapter.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        User newUser = cursorToUser(cursor);
        cursor.close();
        return newUser;
    }

    private User cursorToUser(Cursor cursor) {
        User user = new User();
        user.set_id(cursor.getInt(0));
        user.set_eagle_id(cursor.getString(1));
        user.setName(cursor.getString(2));
        return user;
    }

    private void deleteUser()
    {
        String where = "_id LIKE " + "1" + ";";
        database.delete(Adapter.TABLE_USERS,null,null);
    }
    //Made an oops, createUser returns a new user I didn't set a new user. 
    public void editUser(String new_name, String new_id)
    {
        ContentValues values = new ContentValues();
        values.put(Adapter.COLUMN_EAGLE_ID, new_id);
        values.put(Adapter.COLUMN_NAME,new_name);
        database.update(Adapter.TABLE_USERS,values,"_id = 1",null);

    }
    public boolean checkOnlyUser()
    {
        String countQuery = "SELECT count(*) from users;";
        Cursor tmp =  database.rawQuery(countQuery,null);
        tmp.moveToFirst();
        int count = tmp.getInt(0);
        if(count != 1)
            return false;
        else
            return true;
    }

    public String getUserID()
    {
        String query = "SELECT eagle_id from users where _id = 1";
        Cursor tmp = database.rawQuery(query, null);
        tmp.moveToFirst();
        String id = tmp.getString(0);
        return id;
    }
}
