package com.tutexp.student_details.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.tutexp.student_details.model.Student;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface StudentDAO {

    @Query("Select * from Student")
    LiveData<List<Student>> getAllStudents();

    @Query("Select * from Student where id = :id")
    LiveData<List<Student>> getItemsById(String id);

    @Insert(onConflict = REPLACE)
    void addBorrow(Student student);

    @Delete
    void deleteBorrow(Student student);

}