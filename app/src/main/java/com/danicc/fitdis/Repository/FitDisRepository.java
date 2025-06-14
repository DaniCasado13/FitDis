package com.danicc.fitdis.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.danicc.fitdis.Dao.FitDisDao;
import com.danicc.fitdis.Database.FitDisDatabase;
import com.danicc.fitdis.Entities.Exercise;
import com.danicc.fitdis.Entities.Workout;
import com.danicc.fitdis.Entities.WorkoutExerciseCrossRef;
import com.danicc.fitdis.Entities.WorkoutWithExercises;

import java.util.List;
import java.util.concurrent.Executors;

/**
 *repositorio para acceder a los metodos del dao, el cual recibe
 * en el constructor un objeto del tipo application
 * */
public class FitDisRepository {

    private final FitDisDao dao;

    public FitDisRepository(Application application) {
        FitDisDatabase db = FitDisDatabase.getInstance(application);
        dao = db.fitDisDao();
    }

    // ------------------- EXERCISES --------------------

    public LiveData<List<Exercise>> getAllExercises() {
        return dao.getAllExercises();
    }

    public LiveData<Exercise> getExerciseById(int id) {
        return dao.getExerciseById(id);
    }

    public void insertExercise(Exercise exercise) {
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                dao.insertExercise(exercise);
            } catch (Exception e) {
            }
        });
    }

    public void updateExercise(Exercise exercise) {
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                dao.updateExercise(exercise);
            } catch (Exception e) {
            }
        });
    }

    public void deleteExercise(Exercise exercise) {
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                dao.deleteExercise(exercise);
            } catch (Exception e) {
            }
        });
    }

    // ------------------- WORKOUTS --------------------

    public LiveData<List<Workout>> getAllWorkouts() {
        return dao.getAllWorkouts();
    }

    public LiveData<Workout> getWorkoutById(int id) {
        return dao.getWorkoutById(id);
    }

    public void insertWorkout(Workout workout) {
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                dao.insertWorkout(workout);
            } catch (Exception e) {
            }
        });
    }



    // ------------------- RELACIÓN WORKOUT - EXERCISE --------------------


    public LiveData<WorkoutWithExercises> getWorkoutWithExercises(int workoutId) {
        return dao.getWorkoutWithExercises(workoutId);
    }

    public LiveData<List<WorkoutExerciseCrossRef>> getCrossRefsForWorkout(int workoutId) {
        return dao.getCrossRefsForWorkout(workoutId);
    }


    public void insertWorkoutExerciseCrossRef(WorkoutExerciseCrossRef crossRef) {
        Executors.newSingleThreadExecutor().execute(() -> {
            dao.insertWorkoutExerciseCrossRef(crossRef);
        });
    }

}
