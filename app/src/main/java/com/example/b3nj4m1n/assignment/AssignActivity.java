package com.example.b3nj4m1n.assignment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.b3nj4m1n.assignment.Database.TaskListAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class AssignActivity extends AppCompatActivity {

    TaskListAdapter aTaskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign);

        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        Gson gson = new Gson();
        String json = appSharedPrefs.getString("TaskList", "");
        ListView task_list = (ListView) findViewById(R.id.task_list);
        Type type = new TypeToken<ArrayList<TaskItems>>() {}.getType();
        ArrayList<TaskItems> arrayList = new ArrayList<>();
        arrayList = gson.fromJson(json, type);
        if(List.list.isEmpty() && arrayList != null){
            List.list = arrayList;
        }
        //Generate the List during StartUp
        aTaskAdapter = new TaskListAdapter(getApplicationContext(), List.list);
        aTaskAdapter.notifyDataSetChanged();
        task_list.setAdapter(aTaskAdapter);


        registerForContextMenu(task_list);

        FloatingActionButton fabBut = (FloatingActionButton) findViewById(R.id.fab);
        //Go to add task activity
        fabBut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent addTaskIntent = new Intent(AssignActivity.this, AddTask.class);
                startActivity(addTaskIntent);
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = AssignActivity.this.getMenuInflater();
        inflater.inflate(R.menu.task_list_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            //After Delete A Task, It Will Auto Generate Back the List
            case R.id.task_delete:
                ListView task_list = (ListView) findViewById(R.id.task_list);
                List.list.remove(info.position);
                aTaskAdapter = new TaskListAdapter(getApplicationContext(), List.list);
                aTaskAdapter.notifyDataSetChanged();
                task_list.setAdapter(aTaskAdapter);
                final SharedPreferences appSharePrefs  = PreferenceManager.getDefaultSharedPreferences(AssignActivity.this);
                final SharedPreferences.Editor prefsEditor = appSharePrefs.edit();
                final Gson gson = new Gson();
                String json = gson.toJson(List.list);
                prefsEditor.putString("TaskList",json);
                prefsEditor.commit();
                break;
            default:break;
        }

        return super.onContextItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_assign, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
