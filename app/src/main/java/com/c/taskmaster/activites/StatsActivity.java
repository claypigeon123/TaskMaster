package com.c.taskmaster.activites;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.c.taskmaster.R;
import com.c.taskmaster.db.DatabaseHelper;
import com.c.taskmaster.level.LevelCalculator;
import com.c.taskmaster.themes.ThemeController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StatsActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private ImageView imgProf;
    private TextView txtProfName, txtProfLevel, txtProfExp, txtProfGold, txtProfTasks;
    private Button btnDeleteCharacter;

    private Integer id;
    private List<Integer> images;

    private Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new DatabaseHelper(StatsActivity.this);
        id = getIntent().getIntExtra("profid", 0);
        c = db.viewAllProfs();
        while (c.moveToNext()) {
            if (c.getString(0).equals(id.toString())) {
                break;
            }
        }
        setTheme(ThemeController.getCurrentTheme(c.getInt(5)));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        images = Arrays.asList(R.drawable.char1, R.drawable.char2, R.drawable.char3, R.drawable.char4, R.drawable.char5, R.drawable.char6, R.drawable.char7, R.drawable.char8, R.drawable.char9, R.drawable.char10, R.drawable.char11, R.drawable.char12, R.drawable.char13, R.drawable.char14);


        imgProf = findViewById(R.id.imgProf);
        txtProfName = findViewById(R.id.txtProfName);
        txtProfLevel = findViewById(R.id.txtProfLevel);
        txtProfExp = findViewById(R.id.txtProfExp);
        txtProfGold = findViewById(R.id.txtProfGold);
        txtProfTasks = findViewById(R.id.txtProfTasks);
        btnDeleteCharacter = findViewById(R.id.btnDeleteCharacter);

        int xp = c.getInt(2);
        int level = LevelCalculator.calculateLevel(xp);

        imgProf.setImageResource(images.get(c.getInt(4) - 1));
        txtProfName.setText(c.getString(1));
        txtProfLevel.setText("Level " + level);
        txtProfExp.setText(xp + "/" + LevelCalculator.nextLevelAt(level, xp) + " XP");
        txtProfGold.setText(c.getString(3) + " Gold");

        Cursor tasks = db.viewAllTodos(id.toString());
        int count = tasks.getCount();
        if (count == 0) {
            txtProfTasks.setText("No Active Quests");
        } else if (count == 1) {
            txtProfTasks.setText(count + " Active Quest");
        } else {
            txtProfTasks.setText(count + " Active Quests");
        }

        btnDeleteCharacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(StatsActivity.this)
                        .setMessage("Are you sure you want to delete your character " + c.getString(1) + "?")
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Deleting associated tasks
                                List<String> userTaskIds = new ArrayList<>();
                                Cursor tasks = db.viewAllTodos(id.toString());
                                if (tasks.getCount() != 0) {
                                    while (tasks.moveToNext()) {
                                        userTaskIds.add(tasks.getString(0));
                                    }
                                    for (int i = 0; i < userTaskIds.size(); i++) {
                                        db.deleteTodo(userTaskIds.get(i));
                                    }
                                }
                                // Deleting profile
                                System.out.println(id);
                                db.deleteProf(id.toString());
                                setResult(RESULT_OK, null);
                                finish();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create();
                dialog.show();

            }
        });
    }
}
