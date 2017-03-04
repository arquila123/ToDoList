package com.example.b3nj4m1n.assignment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.gson.Gson;


public class AddTask extends AppCompatActivity{


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtask);
        Button add = (Button)findViewById(R.id.addBut);
        final EditText taskName = (EditText)findViewById(R.id.taskName);
        final EditText description = (EditText)findViewById(R.id.Description);
        final Intent i = new Intent(AddTask.this, AssignActivity.class);

        //Adding data into the json database
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List.list.add(new TaskItems(taskName.getText().toString(),description.getText().toString()));
                final SharedPreferences appSharePrefs  = PreferenceManager.getDefaultSharedPreferences(AddTask.this);
                final SharedPreferences.Editor prefsEditor = appSharePrefs.edit();
                final Gson gson = new Gson();
                String json = gson.toJson(List.list);
                prefsEditor.putString("TaskList",json);
                prefsEditor.commit();
                finish();
                //Go back to the main activity
                startActivity(i);
            }
        });


    }
}
