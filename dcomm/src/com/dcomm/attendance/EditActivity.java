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

/**
 * Created with IntelliJ IDEA.
 * User: Brandon
 * Date: 4/28/13
 * Time: 3:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class EditActivity extends Activity {

    private EditText name;
    private Button edit;
    private EditText id;
    private UserDataSource data;
    private Adapter adapter;
    private Context context;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        data = new UserDataSource(getApplicationContext());
        edit = (Button)  findViewById(R.id.Save_Info);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //To change body of implemented methods use File | Settings | File Templates.
                if(view.getId() == R.id.Save_Info)
                {
                    name = (EditText)findViewById(R.id.editText_newname);
                    String s_name = name.getText().toString();
                    id = (EditText)findViewById(R.id.editText_newid);
                    String s_id = id.getText().toString();
                    data.open();
                    data.editUser(s_name,s_id);
                    data.close();
                    Intent myintent = new Intent(EditActivity.this,MainActivity.class);
                    EditActivity.this.startActivity(myintent);


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
            final Button button = (Button) findViewById(R.id.Save_Info);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if(v.getId() == R.id.Save_Info)
                    {
                        name = (EditText)findViewById(R.id.editText_newname);
                        String s_name = name.getText().toString();
                        id = (EditText)findViewById(R.id.editText_newid);
                        String s_id = id.getText().toString();
                        data.open();
                        data.editUser(s_name,s_id);
                        data.close();
                        Intent myintent = new Intent(EditActivity.this,MainActivity.class);
                        EditActivity.this.startActivity(myintent);
                    }
                }
            });
    }

    public void onPause() {
        super.onPause();

    }
}
