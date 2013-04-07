package com.dcomm.attendance.Attendance;

/**
 * Created with IntelliJ IDEA.
 * User: Brandon
 * Date: 4/6/13
 * Time: 5:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class User {
    private int id;
    private String _eagle_id;
    private String name;

    public void set_id(int id)
    {
           this.id = id;
    }

    public int getId()
    {
        return id;
    }
    public void set_eagle_id(String id)
    {
        _eagle_id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String get_eagle_id()
    {
        return _eagle_id;
    }

    public String getName()
    {
        return name;
    }

    public String toString()
    {

        return name + "_" + _eagle_id;
    }
}
