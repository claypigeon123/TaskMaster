package com.c.taskmaster.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.c.taskmaster.R;
import com.c.taskmaster.db.DatabaseHelper;
import com.c.taskmaster.themes.ThemeController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewCharacterActivity extends AppCompatActivity {

    private Button btnPrev, btnNext, btnCompleteCharacter;
    private EditText txtCharName;
    private ImageView imgChar;

    private int currentResIndex;
    private List<Integer> images;

    private DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new DatabaseHelper(NewCharacterActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_character);

        images = Arrays.asList(R.drawable.char1, R.drawable.char2, R.drawable.char3, R.drawable.char4, R.drawable.char5, R.drawable.char6, R.drawable.char7, R.drawable.char8, R.drawable.char9, R.drawable.char10, R.drawable.char11, R.drawable.char12, R.drawable.char13, R.drawable.char14);

        btnPrev = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);
        btnCompleteCharacter = findViewById(R.id.btnCompleteCharacter);
        txtCharName = findViewById(R.id.txtCharName);
        imgChar = findViewById(R.id.imgChar);
        currentResIndex = 0;
        imgChar.setImageResource(images.get(currentResIndex));

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prevImg();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextImg();
            }
        });
        btnCompleteCharacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtCharName.getText().toString().length() < 3) {
                    Toast.makeText(v.getContext(), "Charater name must be at least 3 characters long!", Toast.LENGTH_LONG).show();
                    return;
                }

                db.insertProf(txtCharName.getText().toString(), 0, 0, currentResIndex + 1, 0);
                Intent i = new Intent(v.getContext(), LoginActivity.class);
                finish();
                startActivity(i);
            }
        });
    }

    private void nextImg() {
        if (currentResIndex == images.size() - 1) {
            currentResIndex = 0;
        } else {
            currentResIndex = currentResIndex + 1;
        }

        imgChar.setImageResource(images.get(currentResIndex));
    }

    private void prevImg() {
        if (currentResIndex == 0) {
            currentResIndex = images.size() - 1;
        } else {
            currentResIndex = currentResIndex - 1;
        }

        imgChar.setImageResource(images.get(currentResIndex));
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, LoginActivity.class);
        finish();
        startActivity(i);
    }
}
