package com.example.lab5a;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listview;
    private ArrayList<Task> tasks;
    private Button add;
    private EditText task;
    private Switch urgent;
    DataBaseHandler db = new DataBaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView) findViewById(R.id.listview);
        tasks = new ArrayList<Task>();
        tasks = db.show();
        add = (Button) findViewById(R.id.btnADD);
        task = (EditText) findViewById(R.id.task);
        urgent = (Switch) findViewById(R.id.urgent);
        TasksAdapter adapter = new TasksAdapter(this, db.show());
        listview.setAdapter(adapter);
        String select = "SELECT * FROM " + db.getTableList();
        SQLiteDatabase query = db.getWritableDatabase();
        Cursor c = query.rawQuery(select,null);
        printCursor(c);
        query.close();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                CharSequence text = "Hello toast!";
                int duration = Toast.LENGTH_SHORT;
                String value = task.getText().toString();
                Toast toast = Toast.makeText(context, "Task "+value+" was added succesfully", duration);
                toast.show();
                Task todo;
                if (urgent.isChecked()) {
                    todo = new Task(value, 1);
                } else {
                    todo = new Task(value, 0);
                }
                db.addAct(todo);
                tasks = db.show();
                //Just to see new rows
                String select = "SELECT * FROM " + db.getTableList();
                SQLiteDatabase query = db.getWritableDatabase();
                Cursor c = query.rawQuery(select,null);
                printCursor(c);
                query.close();
                ///
                TasksAdapter adapter = new TasksAdapter(context, db.show());

                listview.setAdapter(adapter);
                task.setText("");
            }
        });


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {AlertDialog.Builder dialogo1 = new AlertDialog.Builder(MainActivity.this);
                dialogo1.setTitle("Do you want to delete this");
                dialogo1.setMessage("Selected row is: "+position);
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        Toast.makeText(MainActivity.this, "You have deleted: "+ tasks.get(position).getName(), Toast.LENGTH_LONG).show();
                        db.deleteAct(tasks.get(position).getId());
                        tasks.remove(position);

                        //Just to see new rows
                        String select = "SELECT * FROM " + db.getTableList();
                        SQLiteDatabase query = db.getWritableDatabase();
                        Cursor c = query.rawQuery(select,null);
                        printCursor(c);
                        query.close();
                        ///

                        TasksAdapter adapter = new TasksAdapter(MainActivity.this, db.show());
                        listview.setAdapter(adapter);
                        task.setText("");
                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        Toast.makeText(MainActivity.this, "Cancelled ", Toast.LENGTH_LONG).show();
                    }
                });
                dialogo1.show();
            }
        });
    }
    private void printCursor(Cursor c){
        Log.d("","Database Version Number: " + db.getDatabaseVersion());
        Log.d("","Number of colums: " + c.getColumnCount());

        Log.d("","Names of the colum 1: " + c.getColumnName(0));
        Log.d("","Names of the colum 2: " + c.getColumnName(1));
        Log.d("","Names of the colum 3: " + c.getColumnName(2));
        Log.d("","Number of results: " + c.getCount());
        if(c.moveToFirst()){
            do {
                String urg = "false";
                if (c.getString(2).equals(1))
                    urg = "true";
                Log.d("","Id: " + c.getString(0) + ", Name: " + c.getString(1) + ", Is Urgent : " + urg);
            } while (c.moveToNext());
        }
    }
}