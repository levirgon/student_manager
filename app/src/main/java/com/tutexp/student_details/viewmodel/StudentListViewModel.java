package com.tutexp.student_details.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.tutexp.student_details.adapter.model.Student;
import com.tutexp.student_details.database.AppDB;

import java.util.List;

/**
 * Created by noushad on 1/20/18.
 */

public class StudentListViewModel extends AndroidViewModel {

    private final LiveData<List<Student>> studentsList;
    private AppDB appDB;

    public StudentListViewModel(@NonNull Application application) {
        super(application);
        appDB = AppDB.getINSTANCE(this.getApplication());
        studentsList = appDB.access().getAllStudents();
    }

    public LiveData<List<Student>> getStudentsList() {
        return studentsList;
    }

    public void deleteItem(Student item) {
        new deleteTask(appDB).execute(item);
    }

    public void addItem(Student item) {
        new addTask(appDB).execute(item);
    }

    private static class deleteTask extends AsyncTask<Student, Void, Void> {

        private AppDB db;

        deleteTask(AppDB appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(Student... students) {
            db.access().deleteBorrow(students[0]);
            return null;
        }
    }

    private static class addTask extends AsyncTask<Student, Void, Void> {

        private AppDB db;

        addTask(AppDB appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(Student... students) {
            db.access().addBorrow(students[0]);
            return null;
        }
    }


}
