package com.example.b3nj4m1n.assignment.Database;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.b3nj4m1n.assignment.R;
import com.example.b3nj4m1n.assignment.TaskItems;

import java.util.List;

public class TaskListAdapter extends BaseAdapter {
    private Context mContext;
    private List<TaskItems> items;

    public TaskListAdapter(Context mContext, List<TaskItems> items) {
        this.mContext = mContext;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.task_item, null);
        LinearLayout tasklist = (LinearLayout) v.findViewById(R.id.task_layout);
        TextView taskName = (TextView) v.findViewById(R.id.task_name);
        TextView taskDescription = (TextView)v.findViewById(R.id.task_description);
        //Display data from database into List
        taskName.setText(items.get(position).getTask().toString());
        taskDescription.setText(String.valueOf(items.get(position).getDescription()));

        return v;
    }
}
