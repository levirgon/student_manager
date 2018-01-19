package com.tutexp.student_details.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.tutexp.student_details.adapter.model.Student;
import com.tutexp.student_details.dao.StudentDAO;

/**
 * Created by noushad on 12/28/17.
 */
@Database(entities = {Student.class}, version = 1)
public abstract class AppDB extends RoomDatabase {

    public static AppDB INSTANCE;

    public static AppDB getINSTANCE(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDB.class, "borrow_db")
                            .build();
        }
        return INSTANCE;
    }

    public abstract StudentDAO access();

}
