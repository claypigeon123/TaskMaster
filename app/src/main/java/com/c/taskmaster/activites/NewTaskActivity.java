package com.c.taskmaster.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.c.taskmaster.R;
import com.c.taskmaster.db.DatabaseHelper;
import com.c.taskmaster.themes.ThemeController;

public class NewTaskActivity extends AppCompatActivity {

    private EditText txtName, txtDesc, txtExperience, txtGold;
    private Button btnCreateTask;
    private TextView seekBarLabel;
    private SeekBar seekBar;

    private DatabaseHelper db;
    private Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new DatabaseHelper(NewTaskActivity.this);
        id = getIntent().getIntExtra("profid", 0);
        Cursor c = db.viewAllProfs();
        while (c.moveToNext()) {
            if (c.getString(0).equals(id.toString())) {
                break;
            }
        }
        setTheme(ThemeController.getCurrentTheme(c.getInt(5)));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        txtName = findViewById(R.id.txtName);
        txtDesc = findViewById(R.id.txtDesc);
        txtExperience = findViewById(R.id.txtExperience);
        txtGold = findViewById(R.id.txtGold);
        btnCreateTask = findViewById(R.id.btnCreateTask);
        seekBar = findViewById(R.id.seekBar);
        seekBarLabel = findViewById(R.id.seekBarLabel);

        btnCreateTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    db.insertTodo(txtName.getText().toString(), txtDesc.getText().toString(), Integer.parseInt(txtExperience.getText().toString()), Integer.parseInt(txtGold.getText().toString()), id.toString(), seekBar.getProgress());
                    Toast.makeText(getBaseContext(), "Task added!", Toast.LENGTH_LONG).show();
                    clear();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch (progress) {
                    default:
                        seekBarLabel.setText(R.string.txtImportanceLow);
                        seekBarLabel.setCompoundDrawablesWithIntrinsicBounds(R.drawable.low, 0, 0, 0);
                        break;
                    case 1:
                        seekBarLabel.setText(R.string.txtImportanceMed);
                        seekBarLabel.setCompoundDrawablesWithIntrinsicBounds(R.drawable.med, 0, 0, 0);
                        break;
                    case 2:
                        seekBarLabel.setText(R.string.txtImportanceHigh);
                        seekBarLabel.setCompoundDrawablesWithIntrinsicBounds(R.drawable.high, 0, 0, 0);
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void clear() {
        txtName.setText("");
        txtDesc.setText("");
        txtExperience.setText("");
        txtGold.setText("");
        seekBarLabel.setText(R.string.txtImportanceLow);
        seekBar.setProgress(0);
    }
}
