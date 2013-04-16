package com.dcomm.attendance;
import android.app.Activity;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.os.Parcelable;
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

    IntentFilter[] intentFiltersArray;
    private NfcAdapter mNfcAdapter;
    private Message message;
    private UserDataSource database;
    private Tag detectedTag;
    private Ndef ndef;
    private Intent intent;
    private NdefMessage[] msgs;
    private NdefMessage nmessage;
    private ProgressDialog mDialog = new ProgressDialog(getApplicationContext());

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDialog.setMessage("Loading...");
        mDialog.setCancelable(false);
        mDialog.show();
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        IntentFilter ndefFilter = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        try {
            ndefFilter.addDataType("text/plain");

        }
        catch (IntentFilter.MalformedMimeTypeException e) {
            throw new RuntimeException("fail", e);
        }
        intentFiltersArray = new IntentFilter[] {ndefFilter, };

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
        mDialog.setMessage("Sending...");
        mDialog.setCancelable(false);
        mDialog.show();
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mNfcAdapter == null) {
            Toast.makeText(this, "NFC is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {

            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            if (rawMsgs != null) {
                msgs = new NdefMessage[rawMsgs.length];
                for (int i = 0; i < rawMsgs.length; i++) {
                    msgs[i] = (NdefMessage) rawMsgs[i];
                }
            }
        }
        message = new Message();
        message.setStudentID(database.getUserID());
        //detectedTag = getIntent().getParcelableExtra(NfcAdapter.EXTRA_TAG);

        mDialog.dismiss();
    }

    public void onPause() {
        super.onPause();
        mNfcAdapter.disableForegroundDispatch(this);
    }

}
