package com.hoangnhm.ultimatealarm.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hoangnhm.ultimatealarm.R;
import com.hoangnhm.ultimatealarm.data.DatabaseHelper;
import com.hoangnhm.ultimatealarm.data.model.Alarm;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Alarm> alarms = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            alarms.add(new Alarm(i + "", "title", "des", "time"));
        }
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.setAlarms(alarms);

        ArrayList<Alarm> result = databaseHelper.getAlarms();
    }
}
