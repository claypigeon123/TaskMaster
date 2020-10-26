package com.c.taskmaster.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.c.taskmaster.R;
import com.c.taskmaster.db.DatabaseHelper;
import com.c.taskmaster.listviewprofs.ProfListItem;
import com.c.taskmaster.listviewprofs.ProfListViewAdapter;
import com.c.taskmaster.themes.ThemeController;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private ListView listProfs;
    private List<ProfListItem> initItemList;

    private boolean backPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(LoginActivity.this);

        listProfs = findViewById(R.id.listProfs);

        // Initiate listview data.
        initItemList = this.getInitViewItemDtoList();

        // Create a custom list view adapter with checkbox control.
        final ProfListViewAdapter listViewDataAdapter = new ProfListViewAdapter(initItemList, getApplicationContext());

        listViewDataAdapter.notifyDataSetChanged();

        // Set data adapter to list view.
        listProfs.setAdapter(listViewDataAdapter);

        listProfs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(view.getContext(), MainActivity.class);
                i.putExtra("profid", Integer.parseInt(initItemList.get(position).getId()));
                finish();
                startActivity(i);
            }
        });

        findViewById(R.id.btnCreateChar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), NewCharacterActivity.class);
                finish();
                startActivity(i);
            }
        });
    }

    private List<ProfListItem> getInitViewItemDtoList()
    {
        Cursor c = db.viewAllProfs();

        List<ProfListItem> ret = new ArrayList<>();

        if (c.getCount() == 0) {
            return ret;
        }

        while(c.moveToNext()) {
            System.out.println(c.getInt(3));
            ProfListItem item = new ProfListItem(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4));
            ret.add(item);
        }

        return ret;
    }

    @Override
    public void onBackPressed() {
        if (!backPressed) {
            backPressed = true;
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
        } else {
            super.onBackPressed();
        }

    }
}
