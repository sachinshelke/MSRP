package com.sachinshelke.msrp.utilities;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sachinshelke.msrp.R;
import com.sachinshelke.msrp.models.CourseModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Sachin K. Shelke on 05/03/17.
 */

public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.CourseItemViewHolder> {

    List<CourseModel> courseModelList = new ArrayList<>();

    public CourseListAdapter(List<CourseModel> courseModelList) {

        this.courseModelList = courseModelList;

    }


    public void setCourseModelList(List<CourseModel> courseModelList) {
        this.courseModelList = courseModelList;
        notifyDataSetChanged();
    }

    @Override
    public CourseItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_detail_item, parent, false);

        CourseItemViewHolder viewHolder = new CourseItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CourseItemViewHolder holder, int position) {

        CourseModel model = courseModelList.get(position);
        holder.name.setText(model.getName());
        holder.abbrivation.setText(model.getAbbreviation());
        holder.duration.setText(model.getDuration() + "Yrs");
    }

    @Override
    public int getItemCount() {
        return courseModelList.size();
    }


    public class CourseItemViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView abbrivation;
        public TextView duration;


        public CourseItemViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_CourseName);
            abbrivation = (TextView) itemView.findViewById(R.id.tv_CourseAbbrivation);
            duration = (TextView) itemView.findViewById(R.id.tv_Duration);
        }
    }
}
