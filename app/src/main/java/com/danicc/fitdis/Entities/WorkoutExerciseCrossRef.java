package com.danicc.fitdis.Entities;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(
        primaryKeys = {"workoutId", "exerciseId"},
        foreignKeys = {
                @ForeignKey(entity = Workout.class, parentColumns = "id", childColumns = "workoutId", onDelete = CASCADE),
                @ForeignKey(entity = Exercise.class, parentColumns = "id", childColumns = "exerciseId", onDelete = CASCADE)
        },
        indices = {
                @Index("workoutId"),
                @Index("exerciseId")
        }
)
/**
 * Clase para la relacion entre workouts y ejercicios
 * con sus detalles(peso, series y repeticiones)
 * cuenta con un constructor ,getter y setter
 * */
public class WorkoutExerciseCrossRef {
    public int workoutId;
    public int exerciseId;
    public int weight;
    public int sets;
    public int repetitions;


    public WorkoutExerciseCrossRef(int workoutId, int exerciseId, int weight, int sets, int repetitions) {
        this.workoutId = workoutId;
        this.exerciseId = exerciseId;
        this.weight = weight;
        this.sets = sets;
        this.repetitions = repetitions;
    }

    public WorkoutExerciseCrossRef() {

    }

    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }
}
