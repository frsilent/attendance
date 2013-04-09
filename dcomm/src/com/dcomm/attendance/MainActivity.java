package com.dcomm.attendance;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.EditText;
import android.view.View;
import com.dcomm.attendance.DB.*;
import android.content.Context;

public class MainActivity extends Activity {
    private EditText name;
    private EditText id;
    private Adapter dbhelper;
    private UserDataSource database;
    private Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        dbhelper = new Adapter(context);
        database = new UserDataSource(context);
        database.open();
		super.onCreate(savedInstanceState);
        //Check for A created user in the database. If no user, we load the registration activity page
        // If there is a user, we call the method to send a message to the server, but the method
        // can only be called if the NFC tag has been scanned. Otherwise it will just load a basic screen
        // placeholder page if the user clicks on the app. Other features can be added later.
        if(database.checkOnlyUser())
        {
            //Call send message methods and check NFC was called before we set the content view.
            // So may need a loading screen Activity first.
            setContentView(R.layout.activity_main);
        }
        else
        {
            setContentView(R.layout.activity_register);
        }
        database.close();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

    public void onClick(View view)
    {
          if(view.getId() == R.id.button_register)
          {
              name = (EditText)findViewById(R.id.editText_name);
              String s_name = name.getText().toString();
              id = (EditText)findViewById(R.id.editText_eagleid);
              String s_id = id.getText().toString();
              database = new UserDataSource(context);
              database.open();
              if(database.checkOnlyUser())
                    database.createUser(s_name,s_id);
              database.close();


          }

    }
    public boolean userHasInformation()
    {
      return true;
    }
}
