package com.danicc.fitdis.Entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
/**
 * clase modelo de workout(entrenamiento) con propiedades
 * getter setter y constructores
 * */
@Entity(tableName = "workouts", indices = {@Index(value = {"date"}, unique = true)})
public class Workout {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String date;

    public Workout( @NonNull String date) {
        this.date = date;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDate() { return date; }
    public void setDate(@NonNull String date) {
        this.date = date;
    }
}
