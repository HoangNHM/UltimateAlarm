package com.hoangnhm.ultimatealarm.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hoangnhm.ultimatealarm.R;
import com.hoangnhm.ultimatealarm.data.DatabaseHelper;

import java.util.ArrayList;

import static com.hoangnhm.ultimatealarm.data.model.Alarm.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Item> items = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            items.add(new Item.Builder()
                    .create());
        }
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.setAlarms(items);

        ArrayList<Item> result = databaseHelper.getAlarms();
        int a = 0;
        a = 1;
    }
}
