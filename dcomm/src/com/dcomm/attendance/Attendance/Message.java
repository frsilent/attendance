package com.dcomm.attendance.Attendance;

import org.apache.http.HttpClientConnection;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.*;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import org.apache.http.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: Brandon
 * Date: 4/11/13
 * Time: 2:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class Message {
    public static final String URL = "www.cslansing.com/dcomm/upload";
    private String studentID;
    private String ClassID;

    public Message(String sID, String cID)
    {
        studentID = sID;
        ClassID = cID;

    }

    public Message()
    {

    }

    public void setStudentID(String id)
    {
        studentID = id;
    }

    public void setClassID(String ClassID)
    {
        this.ClassID = ClassID;
    }

    public String getStudentID()
    {
        return studentID;
    }

    public String getClassID()
    {
        return ClassID;
    }
    //Post data sends the data to the URL on chris' website. We catch the data by having a view on the site
    // Grab the post data and store it in the database as an object or something. Easily done with django.
    public void postData()
    {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(URL);

        try{
            List<NameValuePair> messagePairs = new ArrayList<NameValuePair>(2);
            messagePairs.add(new BasicNameValuePair("StudentID",studentID));
            messagePairs.add(new BasicNameValuePair("ClassID",ClassID));
            post.setEntity(new UrlEncodedFormEntity(messagePairs));
            HttpResponse response = client.execute(post);
            //Do some reading of data or something.
            BufferedReader red = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        } catch(IOException e)
        {
            //TODO:  Catch exception
        }

    }


}
