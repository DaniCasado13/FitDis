package com.danicc.fitdis.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.danicc.fitdis.Adapter.WorkoutAdapter;
import com.danicc.fitdis.Entities.Workout;
import com.danicc.fitdis.R;
import com.danicc.fitdis.ViewModel.FitDisViewModel;

/**
 * clase que gestiona la lista de los workouts en un recyclerView
 * a traves del viewModel y el workoutAdapter
 * */
public class WorkoutListActivity extends AppCompatActivity {
    private FitDisViewModel viewModel;
    private WorkoutAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_workout_list);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        RecyclerView recyclerView = findViewById(R.id.rvWorkouts);
        Button btnAddWorkout = findViewById(R.id.btnAddWorkout);
        adapter = new WorkoutAdapter(new WorkoutAdapter.OnWorkoutClickListener() {
            @Override
            public void onWorkoutClick(Workout workout) {
                Intent intent = new Intent(WorkoutListActivity.this, WorkoutDetailActivity.class);
                intent.putExtra("workoutId", workout.getId());  // Envía el ID del workout
                startActivity(intent);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(FitDisViewModel.class);
        viewModel.getAllWorkouts().observe(this, workouts -> adapter.setWorkouts(workouts));

        btnAddWorkout.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddEditWorkoutActivity.class);
            startActivity(intent);
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}