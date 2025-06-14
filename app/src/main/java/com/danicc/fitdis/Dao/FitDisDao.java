package com.danicc.fitdis.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.danicc.fitdis.Entities.Exercise;
import com.danicc.fitdis.Entities.Workout;
import com.danicc.fitdis.Entities.WorkoutExerciseCrossRef;
import com.danicc.fitdis.Entities.WorkoutWithExercises;

import java.util.List;

/**
 * Interfaz con las declaraciones de los metodos que interactuando con
 * los elementos de la app y lo que ocurre en caso de error
 *
 * */
@Dao
public interface FitDisDao {
    // -----------------------
    // CRUD para Exercise
    // -----------------------
    @Insert(onConflict = OnConflictStrategy.ABORT)
    long insertExercise(Exercise exercise);

    @Update
    void updateExercise(Exercise exercise);

    @Delete
    void deleteExercise(Exercise exercise);

    @Query("SELECT * FROM exercises ORDER BY name")
    LiveData<List<Exercise>> getAllExercises();

    @Query("SELECT * FROM exercises WHERE id = :id LIMIT 1")
    LiveData<Exercise> getExerciseById(int id);


    // -----------------------
    // CRUD para Workout
    // -----------------------
    @Insert(onConflict = OnConflictStrategy.ABORT)
    long insertWorkout(Workout workout);



    @Query("SELECT * FROM workouts ORDER BY date DESC")
    LiveData<List<Workout>> getAllWorkouts();

    @Query("SELECT * FROM workouts WHERE id = :id LIMIT 1")
    LiveData<Workout> getWorkoutById(int id);

    // -----------------------
    // Relaciones
    // -----------------------

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertWorkoutExerciseCrossRef(WorkoutExerciseCrossRef crossRef);

    @Transaction
    @Query("SELECT * FROM workouts WHERE id = :workoutId")
    LiveData<WorkoutWithExercises> getWorkoutWithExercises(int workoutId);

    @Query("SELECT * FROM workoutexercisecrossref WHERE workoutId = :workoutId")
    LiveData<List<WorkoutExerciseCrossRef>> getCrossRefsForWorkout(int workoutId);

}
