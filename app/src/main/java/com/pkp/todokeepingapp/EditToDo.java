package com.pkp.todokeepingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditToDo extends AppCompatActivity {
    private EditText title, desc;
    private Button update;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_to_do);
        context = this;
        title =findViewById(R.id.editToDoTitle);
        desc = findViewById(R.id.editToDoDescription);
        update = findViewById(R.id.editUpdate);

        DbHandler db = new DbHandler(context);

        final String id = getIntent().getStringExtra("id");
        System.out.println(id);

        ToDo toDo = db.getSingleToDo(Integer.parseInt(id));
        System.out.println(toDo);
        title.setText(toDo.getTitle());
        desc.setText(toDo.getDescription());

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toDo.setTitle(title.getText().toString());
                toDo.setDescription(desc.getText().toString());
                toDo.setUpdate_date(System.currentTimeMillis());
                if (db.updateToDo(toDo)){
                    Toast.makeText(context, "Updated Success ..",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(context, MainActivity.class));
                }else {
                    Toast.makeText(context, "Something Error ..",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}