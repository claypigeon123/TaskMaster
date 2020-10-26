package com.c.taskmaster.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.c.taskmaster.R;
import com.c.taskmaster.db.DatabaseHelper;
import com.c.taskmaster.level.LevelCalculator;
import com.c.taskmaster.listviewtasks.TaskListItem;
import com.c.taskmaster.listviewtasks.TaskListViewAdapter;
import com.c.taskmaster.themes.ThemeController;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import static com.c.taskmaster.notifications.NotificationWrapper.CHANNEL_1_ID;

public class TaskListActivity extends AppCompatActivity {

    private ListView listTasks;
    private DatabaseHelper db;
    private Integer id;
    private Cursor user;

    private NotificationManagerCompat manager;

    private List<TaskListItem> itemList;
    private TaskListViewAdapter listViewDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new DatabaseHelper(TaskListActivity.this);
        id = getIntent().getIntExtra("profid", 0);
        user = db.viewAllProfs();
        while (user.moveToNext()) {
            if (user.getString(0).equals(id.toString())) {
                break;
            }
        }
        setTheme(ThemeController.getCurrentTheme(user.getInt(5)));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        manager = NotificationManagerCompat.from(this);

        listTasks = findViewById(R.id.listTasks);

        updateData();

        listTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get user selected item.
                Object itemObject = parent.getAdapter().getItem(position);

                // Translate the selected item to DTO object.
                TaskListItem item = (TaskListItem)itemObject;

                // Get the checkbox.
                CheckBox itemCheckbox = view.findViewById(R.id.list_view_item_checkbox);

                // Reverse the checkbox and clicked item check state.
                if(item.isChecked())
                {
                    itemCheckbox.setChecked(false);
                    item.setChecked(false);
                }else
                {
                    itemCheckbox.setChecked(true);
                    item.setChecked(true);
                }
            }
        });

        findViewById(R.id.btnCompleteTask).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int xp = 0, gold = 0;
                boolean isChecked = false;
                for (int i = 0; i < itemList.size(); i++) {
                    TaskListItem item = itemList.get(i);
                    if (item.isChecked()) {
                        isChecked = true;
                        xp += Integer.parseInt(item.getItemExp());
                        gold += Integer.parseInt(item.getItemGold());

                        db.deleteTodo(item.getId());
                    }
                }

                if (!isChecked) {
                    Toast.makeText(v.getContext(), "No items selected.", Toast.LENGTH_LONG).show();
                    return;
                }

                Snackbar.make(v, "Earned " + xp + " XP and " + gold + " gold!", Snackbar.LENGTH_LONG).show();

                int currentExp = Integer.parseInt(user.getString(2));
                xp += currentExp;
                int currentGold = Integer.parseInt(user.getString(3));
                gold += currentGold;

                db.updateProf(id.toString(), user.getString(1), xp, gold, user.getInt(4), user.getInt(5));

                if (LevelCalculator.hasLeveledUp(currentExp, xp)) {
                    sendLevelupNotification(LevelCalculator.calculateLevel(xp), user.getString(1));
                }

                updateData();
            }
        });

        findViewById(R.id.btnShare).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int count = 0;
                TaskListItem selected = null;
                for (int i = 0; i < itemList.size(); i++) {
                    TaskListItem item = itemList.get(i);
                    if (item.isChecked()) {
                        count++;
                        selected = item;
                    }
                }
                if (count == 0) {
                    Toast.makeText(v.getContext(), "No items selected.", Toast.LENGTH_LONG).show();
                    return;
                }
                if (count > 1) {
                    Toast.makeText(v.getContext(), "Only select one item to share.", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                String body = "Sharing my task from TaskMaster:\n\n" + selected.getItemText() + "\n" + selected.getItemDesc() + "\nEXP Value: " + selected.getItemExp() + "\nGold Value: " + selected.getItemGold();
                String sub = "TaskMaster";

                i.putExtra(Intent.EXTRA_SUBJECT, sub);
                i.putExtra(Intent.EXTRA_TEXT, body);

                startActivity(Intent.createChooser(i, "Share using"));
            }
        });
    }


    private List<TaskListItem> getInitViewItemList()
    {
        Cursor c = db.viewAllTodos(id.toString());

        List<TaskListItem> ret = new ArrayList<TaskListItem>();

        if (c.getCount() == 0) {
            return ret;
        }

        while(c.moveToNext()) {
            System.out.println(c.getString(6));
            TaskListItem item = new TaskListItem(false, c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(6));
            ret.add(item);
        }

        return ret;
    }

    private void sendLevelupNotification(int level, String name) {
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.exp).setTicker("TaskMaster").setWhen(0)
                .setContentTitle("Level up!")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Congratulations! Your character " + name + " has reached level " + level))
                .build();

        manager.notify(0, notification);
    }

    private void updateData() {
        itemList = getInitViewItemList();
        listViewDataAdapter = new TaskListViewAdapter(itemList, getApplicationContext());
        listViewDataAdapter.notifyDataSetChanged();
        listTasks.setAdapter(listViewDataAdapter);
    }
}
