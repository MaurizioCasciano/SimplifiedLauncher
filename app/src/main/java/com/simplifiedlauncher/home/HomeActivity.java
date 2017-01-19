package com.simplifiedlauncher.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.maurizio.simplifiedlauncher.R;
import com.simplifiedlauncher.applist.AppsListActivity;
import com.simplifiedlauncher.sms.SMSListActivity;

public class HomeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void showApps(View v) {
        Intent i = new Intent(this, AppsListActivity.class);
        startActivity(i);
    }

    public void showSMS(View v) {
        Intent i = new Intent(this, SMSListActivity.class);
        startActivity(i);
    }
}
