package com.dcomm.attendance;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.dcomm.attendance.DB.Adapter;
import com.dcomm.attendance.DB.UserDataSource;

public class EditActivity extends Activity {

    private EditText name;
    private Button edit;
    private EditText id;
    private UserDataSource data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        data = new UserDataSource(getApplicationContext());
        edit = (Button)  findViewById(R.id.SaveInfo);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(view.getId() == R.id.SaveInfo)
                {
                    name = (EditText)findViewById(R.id.EditedName);
                    String studentName = name.getText().toString();
                    id = (EditText)findViewById(R.id.EditedID);
                    Integer studentID = Integer.parseInt(id.getText().toString());
                    data.open();
                    data.editUser(studentName,studentID);
                    data.close();
                    Intent myIntent = new Intent(EditActivity.this,MainActivity.class);
                    EditActivity.this.startActivity(myIntent);


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
            setContentView(R.layout.activity_edit);
            final Button button = (Button) findViewById(R.id.SaveInfo);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if(v.getId() == R.id.SaveInfo)
                    {
                        name = (EditText)findViewById(R.id.EditedName);
                        String studentName = name.getText().toString();
                        id = (EditText)findViewById(R.id.EditedID);
                        Integer studentID = Integer.parseInt(id.getText().toString());
                        data.open();
                        data.editUser(studentName,studentID);
                        data.close();
                        Intent myIntent = new Intent(EditActivity.this,MainActivity.class);
                        EditActivity.this.startActivity(myIntent);
                    }
                }
            });
    }

    public void onPause() {
        super.onPause();

    }
}
