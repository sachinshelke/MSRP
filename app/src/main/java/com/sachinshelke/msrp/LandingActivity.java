package com.sachinshelke.msrp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
    }

    public void gotoCouseScreen(View view) {
        Intent intent = new Intent(this, CourseActivity.class);
        startActivity(intent);

    }
}
