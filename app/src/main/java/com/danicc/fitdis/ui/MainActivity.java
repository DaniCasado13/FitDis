package com.danicc.fitdis.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.danicc.fitdis.R;

public class MainActivity extends AppCompatActivity {
    Button btnExercises, btnWorkouts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnExercises = findViewById(R.id.btnExercises);
        btnWorkouts = findViewById(R.id.btnWorkouts);

        btnExercises.setOnClickListener(v -> {
            Intent intent = new Intent(com.danicc.fitdis.ui.MainActivity.this, ExerciseListActivity.class);
            startActivity(intent);
        });

        btnWorkouts.setOnClickListener(v -> {
            Intent intent = new Intent(com.danicc.fitdis.ui.MainActivity.this, WorkoutListActivity.class);
            startActivity(intent);
        });

    }
}