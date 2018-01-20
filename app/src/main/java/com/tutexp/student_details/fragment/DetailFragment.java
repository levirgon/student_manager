package com.tutexp.student_details.fragment;


import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tutexp.student_details.R;
import com.tutexp.student_details.model.Student;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private Student mStudent;
    private CircleImageView mImageView;
    private TextView nameText;
    private TextView addressText;
    private TextView rollText;
    private TextView genderText;

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(Student student) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, student);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mStudent = (Student) getArguments().getSerializable(ARG_PARAM1);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        mImageView = view.findViewById(R.id.student_image);
        nameText = view.findViewById(R.id.student_name);
        addressText = view.findViewById(R.id.student_address);
        rollText = view.findViewById(R.id.student_roll);
        genderText = view.findViewById(R.id.student_gender);

        updateUI();
        return view;
    }

    private void updateUI() {
        if (mStudent.getGender().equals("male")) {

            Drawable color = new ColorDrawable(getResources().getColor(R.color.cardview_light_background));
            Drawable image = getResources().getDrawable(R.drawable.male_student);

            LayerDrawable ld = new LayerDrawable(new Drawable[]{color, image});
            mImageView.setImageDrawable(ld);

        } else if (mStudent.getGender().equals("female")) {
            Drawable color = new ColorDrawable(getResources().getColor(R.color.cardview_light_background));
            Drawable image = getResources().getDrawable(R.drawable.female_student);

            LayerDrawable ld = new LayerDrawable(new Drawable[]{color, image});
            mImageView.setImageDrawable(ld);

        } else {

            Drawable color = new ColorDrawable(getResources().getColor(R.color.cardview_light_background));
            Drawable image = getResources().getDrawable(R.drawable.baby);

            LayerDrawable ld = new LayerDrawable(new Drawable[]{color, image});
            mImageView.setImageDrawable(ld);

        }

        nameText.setText(mStudent.getName());
        addressText.setText(mStudent.getAddress());
        rollText.setText(mStudent.getRollNo());
        genderText.setText(mStudent.getGender());
    }

}
