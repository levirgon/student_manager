package com.tutexp.student_details;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tutexp.student_details.adapter.StudentAdapter;
import com.tutexp.student_details.adapter.model.Student;
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
        builder.setView(viewInflated);

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(isEmpty(nameInput)||isEmpty(rollInput)||isEmpty(addresInput)) {
                    Toast.makeText(MainActivity.this,"No Fields Can Be Empty",Toast.LENGTH_LONG).show();
                }else {

                    String name = nameInput.getText().toString();
                    String roll = rollInput.getText().toString();
                    String address = addresInput.getText().toString();
                    Student student = new Student(name, roll, address, R.drawable.student_icon);
                    addItem(student);
                    dialog.dismiss();
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
}
