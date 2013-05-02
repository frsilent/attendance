package com.dcomm.attendance;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import com.dcomm.attendance.DB.*;


public class MainActivity extends Activity {
    private EditText name;
    private Button register;
    private EditText id;
    private UserDataSource data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        data = new UserDataSource(getApplicationContext());
        register = (Button)  findViewById(R.id.button_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(view.getId() == R.id.button_register)
                {
                    name = (EditText)findViewById(R.id.RegisterName);
                    String studentName = name.getText().toString();
                    id = (EditText)findViewById(R.id.RegisterID);
                    Integer studentID = Integer.parseInt(id.getText().toString());
                    data.open();
                    if(!data.checkOnlyUser())
                        data.createUser(studentName,studentID);
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
            final Button button = (Button) findViewById(R.id.EditInfo);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if(v.getId() == R.id.EditInfo)
                    {
                    Intent myIntent = new Intent(MainActivity.this,EditActivity.class);
                    MainActivity.this.startActivity(myIntent);
                    }
                }
            });
        }
        else
        {
            setContentView(R.layout.activity_register);
            data = new UserDataSource(getApplicationContext());
            register = (Button)  findViewById(R.id.button_register);
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //To change body of implemented methods use File | Settings | File Templates.
                    if(view.getId() == R.id.button_register)
                    {
                        name = (EditText)findViewById(R.id.RegisterName);
                        String studentName = name.getText().toString();
                        id = (EditText)findViewById(R.id.RegisterID);
                        Integer studentID = Integer.parseInt(id.getText().toString());
                        data.open();
                        if(!data.checkOnlyUser())
                            data.createUser(studentName,studentID);
                            System.out.println(data.checkOnlyUser());
                        data.close();
                        setContentView(R.layout.activity_overview);

                    }
                }
            });



        }
        data.close();

    }

    public void onPause() {
        super.onPause();

    }
}
