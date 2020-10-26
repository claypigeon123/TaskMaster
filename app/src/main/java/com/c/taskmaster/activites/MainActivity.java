package com.c.taskmaster.activites;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.c.taskmaster.R;
import com.c.taskmaster.db.DatabaseHelper;
import com.c.taskmaster.themes.ThemeController;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private Integer id;

    private boolean backPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new DatabaseHelper(MainActivity.this);
        id = getIntent().getIntExtra("profid", 0);
        Cursor c = db.viewAllProfs();
        while (c.moveToNext()) {
            if (c.getString(0).equals(id.toString())) {
                break;
            }
        }
        setTheme(ThemeController.getCurrentTheme(c.getInt(5)));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnTaskList).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), TaskListActivity.class);
                i.putExtra("profid", id);
                startActivity(i);
            }
        });
        findViewById(R.id.btnAddTask).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), NewTaskActivity.class);
                i.putExtra("profid", id);
                startActivity(i);
            }
        });
        findViewById(R.id.btnStats).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), StatsActivity.class);
                i.putExtra("profid", id);
                startActivityForResult(i, 1313);
            }
        });
        findViewById(R.id.btnSettings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), SettingsActivity.class);
                i.putExtra("profid", id);
                finish();
                startActivity(i);
            }
        });
        findViewById(R.id.btnChangeCharacter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), LoginActivity.class);
                finish();
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (!backPressed) {
            backPressed = true;
            Toast.makeText(this, "Press again to return to character selection", Toast.LENGTH_SHORT).show();
        } else {
            Intent i = new Intent(this, LoginActivity.class);
            finish();
            startActivity(i);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1313) {
            if (resultCode == RESULT_OK) {
                Intent i = new Intent(this, LoginActivity.class);
                finish();
                startActivity(i);
            }
        }
    }
}
