package com.pkp.todokeepingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button add;
    private ListView listView;
    private TextView count;
    private Context context;
    private List<ToDo> toDos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        add =findViewById(R.id.addToDo);
        listView = findViewById(R.id.do_list);
        count = findViewById(R.id.count_todo);
        DbHandler db = new DbHandler(context);

        toDos = db.getAllToDos();
        listView = findViewById(R.id.do_list);
        ToDoAdapter adapter = new ToDoAdapter(context,R.layout.single_todo, toDos);
        listView.setAdapter(adapter);


        int numTo = db.countToDo();
        count.setText("You have "+numTo+" DoTos today");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, AddToDo.class));
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ToDo toDo = (ToDo) adapterView.getItemAtPosition(i);

                // create alert box
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Test");
                builder.setMessage("Message");
                builder.setPositiveButton("Finished", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        toDo.setFinished(System.currentTimeMillis());
                        if (db.updateToDo(toDo)){
                            Toast.makeText(context, "You it Finished ..",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(context, MainActivity.class));
                        }else {
                            Toast.makeText(context, "Something Error ..",Toast.LENGTH_LONG).show();
                        }
                        startActivity(new Intent(context, MainActivity.class));
                    }
                });

                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db.deleteToDo(toDo.getId());
                        startActivity(new Intent(context, MainActivity.class));
                    }
                });
                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(context, EditToDo.class);
                        intent.putExtra("id", String.valueOf(toDo.getId()));
                        startActivity(intent);
                    }
                });
                builder.show();
            }
        });
    }
}