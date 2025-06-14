package com.danicc.fitdis.Entities;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;
/**
 * clase que representa la relacion entre un workout y sus
 * ejercicios asociados
 *
 * */
public class WorkoutWithExercises {
    @Embedded
    public Workout workout;

    @Relation(
            parentColumn = "id",
            entityColumn = "id",
            associateBy = @Junction(
                    value = WorkoutExerciseCrossRef.class,
                    parentColumn = "workoutId",
                    entityColumn = "exerciseId"
            )
    )
    public List<Exercise> exercises;
}

