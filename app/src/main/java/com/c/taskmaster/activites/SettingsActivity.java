package com.c.taskmaster.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;
import android.view.Window;

import com.c.taskmaster.R;
import com.c.taskmaster.db.DatabaseHelper;
import com.c.taskmaster.themes.ThemeController;

public class SettingsActivity extends AppCompatActivity {

    private Integer id;

    private DatabaseHelper db;

    private Cursor currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new DatabaseHelper(SettingsActivity.this);
        id = getIntent().getIntExtra("profid", 0);
        if (getIntent().getBooleanExtra("anim", false)) {
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        }
        final Cursor c = db.viewAllProfs();
        while (c.moveToNext()) {
            if (c.getString(0).equals(id.toString())) {
                break;
            }
        }
        setTheme(ThemeController.getCurrentTheme(c.getInt(5)));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        currentUser = c;

        id = getIntent().getIntExtra("profid", 0);

        findViewById(R.id.btnThemeOne).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.updateProf(currentUser.getString(0), currentUser.getString(1), currentUser.getInt(2), currentUser.getInt(3), currentUser.getInt(4), 0);
                Intent i = getIntent();
                i.putExtra("anim", true);
                finish();
                startActivity(i);
            }
        });

        findViewById(R.id.btnThemeTwo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.updateProf(currentUser.getString(0), currentUser.getString(1), currentUser.getInt(2), currentUser.getInt(3), currentUser.getInt(4), 1);
                Intent i = getIntent();
                i.putExtra("anim", true);
                finish();
                startActivity(getIntent());
            }
        });

        findViewById(R.id.btnThemeThree).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.updateProf(currentUser.getString(0), currentUser.getString(1), currentUser.getInt(2), currentUser.getInt(3), currentUser.getInt(4), 2);
                Intent i = getIntent();
                i.putExtra("anim", true);
                finish();
                startActivity(getIntent());
            }
        });

        findViewById(R.id.btnThemeFour).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.updateProf(currentUser.getString(0), currentUser.getString(1), currentUser.getInt(2), currentUser.getInt(3), currentUser.getInt(4), 3);
                Intent i = getIntent();
                i.putExtra("anim", true);
                finish();
                startActivity(getIntent());
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("profid", id);
        finish();
        startActivity(i);
    }
}
