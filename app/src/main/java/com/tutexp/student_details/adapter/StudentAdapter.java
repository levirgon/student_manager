package com.tutexp.student_details.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tutexp.student_details.activity.MainActivity;
import com.tutexp.student_details.R;
import com.tutexp.student_details.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noushad on 1/20/18.
 */

public class StudentAdapter extends RecyclerView.Adapter {


    private List<Student> students;
    private Context mContext;

    public StudentAdapter(Context context) {
        mContext = context;
        this.students = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewItem = inflater.inflate(R.layout.student_item, parent, false);
        viewHolder = new StudentVH(viewItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Student item = students.get(position);
        ((StudentVH) holder).updateUI(item);
    }

    @Override
    public int getItemCount() {
        return students == null ? 0 : students.size();
    }

    private class StudentVH extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView nameText;
        TextView rollText;
        ImageView profileImage;
        private Student mItem;

        public StudentVH(View view) {
            super(view);
            nameText = view.findViewById(R.id.item_name);
            rollText = view.findViewById(R.id.item_roll);
            profileImage = view.findViewById(R.id.item_image);
            view.setOnClickListener(this);
        }

        public void updateUI(Student item) {
            mItem = item;
            if (item.getGender().equals("male")){
                profileImage.setImageResource(R.drawable.male_student);
            }else if (item.getGender().equals("female")){
                profileImage.setImageResource(R.drawable.female_student);
            }else{
                profileImage.setImageResource(R.drawable.baby);

            }
            nameText.setText(item.getName());
            rollText.setText(item.getRollNo());
        }

        @Override
        public void onClick(View v) {
            ((MainActivity)mContext).showDetails(mItem);
        }
    }

    public void addItems(List<Student> borrowModelList) {
        this.students = borrowModelList;
        notifyDataSetChanged();
    }
}
