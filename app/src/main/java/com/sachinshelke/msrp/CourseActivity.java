package com.sachinshelke.msrp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.sachinshelke.msrp.db.wrapper.CourseWrapper;
import com.sachinshelke.msrp.models.CourseModel;
import com.sachinshelke.msrp.utilities.CourseListAdapter;

import java.util.ArrayList;
import java.util.List;

public class CourseActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_ADD_COURSE = 123;


    Button btnAddCourse;
    RecyclerView rvCourseList;

    CourseWrapper courseWrapper;

    CourseListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        btnAddCourse = (Button) findViewById(R.id.btn_AddCouse);
        rvCourseList = (RecyclerView) findViewById(R.id.rv_CourseList);
        rvCourseList.setHasFixedSize(false);
        btnAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCourse(v);
            }
        });

        courseWrapper = new CourseWrapper(this);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvCourseList.setLayoutManager(manager);


        adapter = new CourseListAdapter(courseModelList);
        rvCourseList.setAdapter(adapter);
        courseModelList = courseWrapper.getCourse();
        adapter.setCourseModelList(courseModelList);


    }


    List<CourseModel> courseModelList = new ArrayList<>();

    public void addCourse(View view) {
        Intent intent = new Intent(this, AddCourseActivity.class);
        startActivityForResult(intent, REQUEST_CODE_ADD_COURSE);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_ADD_COURSE) {
            if (resultCode == RESULT_OK) {

                adapter.setCourseModelList(courseWrapper.getCourse());
            } else if (resultCode == RESULT_CANCELED) {
                // can perfrom logical operation if required
            }
        }
    }
}
