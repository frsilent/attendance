package com.dcomm.attendance;

/**
 * Created with IntelliJ IDEA.
 * User: Brandon
 * Date: 4/6/13
 * Time: 5:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class User {
    private int id;
    private Integer eagleID;
    private String name;

    public void setID(int id)
    {
           this.id = id;
    }

    public int getId()
    {
        return id;
    }
    public void setEagleID(Integer eagleID)
    {
        this.eagleID = eagleID;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getEagleID()
    {
        return eagleID;
    }

    public String getName()
    {
        return name;
    }

    public String toString()
    {

        return name + "_" + eagleID.toString();
    }
}
