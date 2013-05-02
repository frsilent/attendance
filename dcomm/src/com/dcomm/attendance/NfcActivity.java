package com.dcomm.attendance;
import android.app.Activity;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.dcomm.attendance.DB.UserDataSource;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Brandon
 * Date: 4/15/13
 * Time: 7:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class NfcActivity extends Activity {

    private NfcAdapter mNfcAdapter;
    private Message message;
    private Nfc nfc;
    private Tag detectedTag;
    private Ndef ndef;
    private NdefMessage nmessage;
    private UserDataSource data;
    private Button ok;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = new UserDataSource(getApplicationContext());
        nfc = new Nfc(mNfcAdapter);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

         ok = (Button) findViewById(R.id.button);
         ok.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 NfcActivity.this.finish();
             }
         });


    }

    public void onNewIntent(Intent intent) {
        detectedTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        ndef = Ndef.get(detectedTag);
        try {
            nmessage = ndef.getNdefMessage();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (FormatException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        //do something with tagFromIntent
    }

    public void onResume()
    {
        super.onResume();
        nfc = new Nfc();
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if (mNfcAdapter == null) {
            Toast.makeText(this, "NFC is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        //Set up message parameters
        detectedTag = getIntent().getParcelableExtra(NfcAdapter.EXTRA_TAG);
        message = new Message();
        data.open();
        Integer studentID = data.getUserID();
        message.setStudentID(studentID);
        message.setClassID(nfc.readTag(detectedTag,getIntent()));    //Set Class ID from the nfc tags content

        //Set up task and send message
        MessageTask task = new MessageTask();
        task.setMessage(message);
        task.execute();
        data.close();
    }

    public void onPause() {
        super.onPause();
    }

}
