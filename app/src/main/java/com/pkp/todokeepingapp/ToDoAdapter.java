package com.pkp.todokeepingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ToDoAdapter extends ArrayAdapter<ToDo> {
    private Context context;
    private int resource;
    private List<ToDo> toDos;

    public ToDoAdapter(@NonNull Context context, int resource, List<ToDo> toDos) {
        super(context, resource, toDos);
        this.context = context;
        this.resource = resource;
        this.toDos = toDos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource, parent, false);

        TextView title = row.findViewById(R.id.title);
        TextView des = row.findViewById(R.id.description);
        ImageView imageView = row.findViewById(R.id.onGoing);

        ToDo toDo = toDos.get(position);
        title.setText(toDo.getTitle());
        des.setText(toDo.getDescription());
        imageView.setVisibility(View.INVISIBLE);

        if (toDo.getFinished() > 0) {
            imageView.setVisibility(View.VISIBLE);
        }
        return row;
    }
}
