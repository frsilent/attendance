package com.dcomm.attendance;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import com.dcomm.attendance.DB.*;
import android.content.Context;
import android.nfc.NfcAdapter;
import android.nfc.*;
import android.os.Parcelable;
import android.widget.Toast;
import android.app.PendingIntent;

import static java.lang.System.exit;

public class MainActivity extends Activity {
    private EditText name;
    private Button register;
    private EditText id;
    private UserDataSource data;
    private Adapter adapter;
    private Context context;
    private Intent intent;
    //private NdefMessage[] msgs;
    //private NfcAdapter mNfcAdapter;
    //private Message message;
    //private Tag detectedTag;
    //private IntentFilter[] intentFiltersArray;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        data = new UserDataSource(getApplicationContext());
        register = (Button)  findViewById(R.id.button_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //To change body of implemented methods use File | Settings | File Templates.
                if(view.getId() == R.id.button_register)
                {
                    name = (EditText)findViewById(R.id.editText_name);
                    String s_name = name.getText().toString();
                    id = (EditText)findViewById(R.id.editText_eagleid);
                    String s_id = id.getText().toString();
                    data.open();
                    if(!data.checkOnlyUser())
                        data.createUser(s_name,s_id);
                    data.close();


                }
            }
        });


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

    //Check to see if the app started from a NFC Tag.
    public void onResume()
    {
        super.onResume();
        //database = new UserDataSource(context);
        data.open();
        if(data.checkOnlyUser())
        {
            setContentView(R.layout.activity_overview);
            System.out.println(data.getUserID());
        }
        else
        {
            setContentView(R.layout.activity_register);
        }
        data.close();

    }

    public void onPause() {
        super.onPause();

    }
}
