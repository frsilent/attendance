package com.dcomm.attendance;
import android.content.Intent;
import android.nfc.Tag;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.Ndef;
import android.os.Parcelable;
import android.util.Log;
import java.io.IOException;
import java.nio.charset.Charset;
import android.nfc.*;

/**
 * Created with IntelliJ IDEA.
 * User: Brandon
 * Date: 4/9/13
 * Time: 2:39 PM
 * To change this template use File | Settings | File Templates.
 */


public class Nfc {
    private User user;
    private String classID;
    public NfcAdapter adapter;
    private static final String TAG = Nfc.class.getSimpleName();
    private NdefMessage[] msgs;

    public Nfc(NfcAdapter adapter)
    {
        this.adapter = adapter;

    }

    public Nfc()
    {

    }

    public void setClassID(String classID)
    {
        this.classID = classID;
    }

    public void setUser(User u)
    {
        this.user = u;
    }

    public String getClassID()
    {
        return this.classID;
    }

    public String getUserName()
    {
        return this.user.getName();
    }

    public String getUserID()
    {
        return this.user.get_eagle_id();
    }

    public String readTag(Tag tag,Intent intent) {
        Ndef ndef = Ndef.get(tag);

        try {
            ndef.connect();
            NdefMessage payload = ndef.getNdefMessage();
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage msg = (NdefMessage) rawMsgs[0];
            NdefRecord record = msg.getRecords()[0];
            String id = new String(record.getPayload());
            id = id.substring(3,id.length());
            setClassID(id);

            return new String(id);
        } catch (IOException e) {
            Log.e(TAG, "IOException while reading message...", e);
        } catch (FormatException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            if (ndef != null) {
                try {
                    ndef.close();
                }
                catch (IOException e) {
                    Log.e(TAG, "Error closing tag...", e);
                }
            }
        }
        return null;
    }
}
