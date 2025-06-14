package com.danicc.fitdis.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.danicc.fitdis.Dao.FitDisDao;
import com.danicc.fitdis.Entities.Exercise;
import com.danicc.fitdis.Entities.Workout;
import com.danicc.fitdis.Entities.WorkoutExerciseCrossRef;

/**
 * clase para la declaracion de la base de datos
 * e implementacion del patron singleton
 *
 * */
@Database(entities = {Exercise.class, Workout.class, WorkoutExerciseCrossRef.class},
        version = 7,
        exportSchema = false)
public abstract class FitDisDatabase extends RoomDatabase {

    private static volatile FitDisDatabase INSTANCE;

    public abstract FitDisDao fitDisDao();

    public static FitDisDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (FitDisDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    FitDisDatabase.class, "fitdis_database")
                            .fallbackToDestructiveMigration(true)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
