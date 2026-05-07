package com.example.todoapp;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    EditText taskInput;
    Button addBtn;
    ListView listView;
    DatabaseHelper db;

    ArrayList<String> tasks = new ArrayList<>();
    ArrayList<Integer> ids = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskInput = findViewById(R.id.taskInput);
        addBtn = findViewById(R.id.addBtn);
        listView = findViewById(R.id.listView);

        db = new DatabaseHelper(this);

        loadTasks();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);
        listView.setAdapter(adapter);

        addBtn.setOnClickListener(v -> {
            db.addTask(taskInput.getText().toString());
            taskInput.setText("");
            loadTasks();
            adapter.notifyDataSetChanged();
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            db.deleteTask(ids.get(position));
            loadTasks();
            adapter.notifyDataSetChanged();
        });
    }

    private void loadTasks() {
        tasks.clear();
        ids.clear();

        Cursor c = db.getTasks();

        while (c.moveToNext()) {
            ids.add(c.getInt(0));
            tasks.add(c.getString(1));
        }
    }
}