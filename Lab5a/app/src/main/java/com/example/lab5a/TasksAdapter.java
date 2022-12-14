package com.example.lab5a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TasksAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<com.example.lab5a.Task> tasks;

    public TasksAdapter(Context context, ArrayList<com.example.lab5a.Task> tasks) {
        this.context = context;
        this.tasks = tasks;
    }

    public int getCount() {
        return tasks.size();
    }

    public Object getItem(int position) {
        return tasks.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.task, parent, false);
        }

        com.example.lab5a.Task currentTask = (com.example.lab5a.Task) getItem(position);


        TextView textViewItemName = (TextView)
                convertView.findViewById(R.id.name);



        textViewItemName.setText(currentTask.getName());

        if(currentTask.getUrgent() == 1){
            textViewItemName.setBackgroundColor(0xfff00000);
        }else{
            textViewItemName.setBackgroundColor(0x00000000);
        }

        return convertView;
    }
}
