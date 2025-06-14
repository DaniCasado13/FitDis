package com.danicc.fitdis.Entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
/**
 * clase modelo de ejercicio con propiedades
 * getter setter y constructores
 * */
@Entity(tableName = "exercises",
        indices = {@Index(value = {"name"}, unique = true)})
public class Exercise {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String name;

    @NonNull
    private String muscleGroup;

    @NonNull
    private String description;

    @Ignore
    // Constructor
    public Exercise(@NonNull String name, @NonNull String muscleGroup, String description) {
        this.name = name;
        this.muscleGroup = muscleGroup;
        this.description = description;
    }

    public Exercise(int id, String name, String muscleGroup, String description) {
        this.id = id;
        this.name = name;
        this.muscleGroup = muscleGroup;
        this.description = description;
    }


    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setMuscleGroup(@NonNull String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }
}
