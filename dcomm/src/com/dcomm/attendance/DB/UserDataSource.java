package com.dcomm.attendance.DB;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.dcomm.attendance.Attendance.User;

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
        long insertId = database.insert(Adapter.TABLE_USERS, null, values);
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

    private void deleteUser(User user)
    {
        String where = "eagle_id LIKE " + user.get_eagle_id() + ";";
        database.delete(Adapter.TABLE_USERS,where,null);
    }
    //Made an oops, createUser returns a new user I didn't set a new user. 
    public void editUser(User user, String new_id)
    {
        createUser(user.getName(),new_id);
        deleteUser(user);
    }
    public boolean checkOnlyUser(Cursor cursor)
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
}
