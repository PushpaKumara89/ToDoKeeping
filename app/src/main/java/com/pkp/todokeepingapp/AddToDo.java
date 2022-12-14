package com.pkp.todokeepingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddToDo extends AppCompatActivity {
    private EditText title, desc;
    private Button add;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);

        context = this;

        title =findViewById(R.id.addToDoTitle);
        desc = findViewById(R.id.addToDoDescription);
        add = findViewById(R.id.addToDoBtn);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // close database connection
                String userTitle = title.getText().toString();
                String userDesc = desc.getText().toString();
                long started = System.currentTimeMillis();

                ToDo toDo = new ToDo(userTitle, userDesc , started,0, 0);
                new DbHandler(context).addToDo(toDo);

                startActivity(new Intent(context,MainActivity.class));

            }
        });
    }
}