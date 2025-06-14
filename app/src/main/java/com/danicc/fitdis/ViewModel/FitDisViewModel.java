package com.danicc.fitdis.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.danicc.fitdis.Entities.Exercise;
import com.danicc.fitdis.Entities.Workout;
import com.danicc.fitdis.Entities.WorkoutExerciseCrossRef;
import com.danicc.fitdis.Entities.WorkoutWithExercises;
import com.danicc.fitdis.Repository.FitDisRepository;

import java.util.List;

/**
 * Viewmodel el cual enlaza con el repository
 * para la gestion de ambas listas, tanto la de ejercicios
 * como la de workouts, tambien los metodos de la insercion
 * en la tabla workoutExerciseCrossRef.
 * */
public class FitDisViewModel extends AndroidViewModel {
    private final FitDisRepository repository;
    private final LiveData<List<Exercise>> allExercises;
    private final LiveData<List<Workout>> allWorkouts;

    public FitDisViewModel(@NonNull Application application) {
        super(application);
        repository = new FitDisRepository(application);
        allExercises = repository.getAllExercises();
        allWorkouts = repository.getAllWorkouts();
    }

    // ----------- EXERCISES --------------
    public LiveData<List<Exercise>> getAllExercises() {
        return allExercises;
    }

    public LiveData<Exercise> getExerciseById(int exerciseId) {
        return repository.getExerciseById(exerciseId);
    }

    public void insertExercise(Exercise exercise) {
        repository.insertExercise(exercise);
    }

    public void updateExercise(Exercise exercise) {
        repository.updateExercise(exercise);
    }

    public void deleteExercise(Exercise exercise) {
        repository.deleteExercise(exercise);
    }

    // ----------- WORKOUTS --------------
    public LiveData<List<Workout>> getAllWorkouts() {
        return allWorkouts;
    }

    public void insertWorkout(Workout workout) {
        repository.insertWorkout(workout);
    }



    // ----------- RELACIÓN EJERCICIO-WORKOUT --------------

    public LiveData<WorkoutWithExercises> getWorkoutWithExercises(int workoutId) {
        return repository.getWorkoutWithExercises(workoutId);
    }

    public LiveData<List<WorkoutExerciseCrossRef>> getCrossRefsForWorkout(int workoutId) {
        return repository.getCrossRefsForWorkout(workoutId);
    }

    public void insertWorkoutExerciseCrossRef(WorkoutExerciseCrossRef crossRef) {
        repository.insertWorkoutExerciseCrossRef(crossRef);
    }

}
