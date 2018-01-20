package com.tutexp.student_details.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.tutexp.student_details.fragment.DetailFragment;
import com.tutexp.student_details.R;
import com.tutexp.student_details.adapter.StudentAdapter;
import com.tutexp.student_details.model.Student;
import com.tutexp.student_details.viewmodel.StudentListViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private StudentListViewModel viewModel;
    private StudentAdapter mAdapter;
    private RecyclerView recyclerView;
    private FloatingActionButton addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog();
            }
        });
        recyclerView = findViewById(R.id.students_list);
        mAdapter = new StudentAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        viewModel = ViewModelProviders.of(this).get(StudentListViewModel.class);
        viewModel.getStudentsList().observe(MainActivity.this, new Observer<List<Student>>() {
            @Override
            public void onChanged(@Nullable List<Student> students) {
                mAdapter.addItems(students);
            }
        });

    }

    private void showInputDialog() {
        AlertDialog.Builder builder;

        builder = new AlertDialog.Builder(this);
        builder.setTitle("New Member");

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.add_new_member, null, false);
        final EditText nameInput = (EditText) viewInflated.findViewById(R.id.name_input);
        final EditText rollInput = (EditText) viewInflated.findViewById(R.id.roll_input);
        final EditText addresInput = (EditText) viewInflated.findViewById(R.id.address_input);
        final RadioButton maleRadio = viewInflated.findViewById(R.id.rb_male);
        final RadioButton femaleRadio = viewInflated.findViewById(R.id.rb_female);
        final RadioGroup radioGroup = viewInflated.findViewById(R.id.gender_rad_group);

        builder.setView(viewInflated);

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (isEmpty(nameInput) || isEmpty(rollInput) || isEmpty(addresInput)) {
                    Toast.makeText(MainActivity.this, "No Fields Can Be Empty", Toast.LENGTH_LONG).show();
                } else {

                    String gender = "";
                    if (radioGroup.getCheckedRadioButtonId() == -1) {
                        gender = "none";
                    } else {
                        if (maleRadio.isChecked()) {
                            gender = "male";
                        }
                        if (femaleRadio.isChecked()) {
                            gender = "female";
                        }
                    }

                    if(!gender.equals("none")) {
                        String name = nameInput.getText().toString();
                        String roll = rollInput.getText().toString();
                        String address = addresInput.getText().toString();
                        Student student = new Student(name, roll, address, gender, 101);
                        addItem(student);
                        dialog.dismiss();
                    }else{
                        Toast.makeText(MainActivity.this, "No Fields Can Be Empty", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private void addItem(Student student) {
        viewModel.addItem(student);
    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }

    public void showDetails(Student item) {
        startFragment(DetailFragment.newInstance(item), "student_details");
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to exit?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.super.onBackPressed();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        }
    }

    public void startFragment(Fragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment, tag).addToBackStack(null);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commitAllowingStateLoss();

    }
}
