package com.dcomm.attendance;

import android.os.AsyncTask;

import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: Brandon
 * Date: 4/16/13
 * Time: 3:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class MessageTask extends AsyncTask<String, Void, Message> {
    private Message mes;
    private URL url;

    @Override
    protected Message doInBackground(String... strings) {
        mes.postData();
        return null;

    }

    public void setMessage(Message message)
    {
        mes = message;
    }

    public void setURL(URL url)
    {
       this.url = url;
    }
}
