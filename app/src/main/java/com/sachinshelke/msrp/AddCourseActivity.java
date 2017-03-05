package com.sachinshelke.msrp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sachinshelke.msrp.db.wrapper.CourseWrapper;
import com.sachinshelke.msrp.models.CourseModel;

public class AddCourseActivity extends AppCompatActivity {

    EditText etCourseName, etCourseAbbrivation, etDuration;

    CourseWrapper wrapper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        etCourseName = (EditText) findViewById(R.id.et_CourseName);
        etCourseAbbrivation = (EditText) findViewById(R.id.et_CsrAbbrivation);
        etDuration = (EditText) findViewById(R.id.et_CsrDuration);

        wrapper = new CourseWrapper(this);

    }

    public void onCancel(View view) {
        onBackPressed();
    }

    public void onClearForm(View view) {
        etCourseName.setText("");
        etCourseAbbrivation.setText("");
        etDuration.setText("");
    }

    public void addCourse(View view) {

        String name = etCourseName.getText().toString();
        String abbrivation = etCourseAbbrivation.getText().toString();
        String dur = etDuration.getText().toString();
        int duration = TextUtils.isEmpty(dur) ? 3 : Integer.parseInt(dur);


        CourseModel model = new CourseModel();
        model.setName(name);
        model.setAbbreviation(abbrivation);
        model.setDuration(duration);

        long result = wrapper.addCourse(model);


        if (result == -1) {
            Toast.makeText(this, "Some Error Occured while adding course", Toast.LENGTH_SHORT).show();
            return;
        }

        activityResult = RESULT_OK;
        onClearForm(view);

    }

    @Override
    public void onBackPressed() {
        setResult(activityResult);
        super.onBackPressed();
    }

    int activityResult = RESULT_CANCELED;

}
