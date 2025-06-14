package com.danicc.fitdis.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.danicc.fitdis.Adapter.ExerciseAdapter;
import com.danicc.fitdis.Entities.Exercise;
import com.danicc.fitdis.R;
import com.danicc.fitdis.ViewModel.FitDisViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
/**
 * clase que gestiona la lista de los ejercicios en un recyclerView
 * a traves del viewModel y el ExerciseAdapter
 * */
public class ExerciseListActivity extends AppCompatActivity {
    private FitDisViewModel viewModel;
    private ExerciseAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exercise_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.rvExercise);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ExerciseAdapter(new ExerciseAdapter.OnItemClickListener() {
            @Override
            public void onEdit(Exercise exercise) {
                Intent intent = new Intent(getApplicationContext(), AddEditExerciseActivity.class);
                intent.putExtra("exercise_id", exercise.getId());
                startActivity(intent);
            }

            @Override
            public void onDelete(Exercise exercise) {
                new AlertDialog.Builder(ExerciseListActivity.this)
                        .setTitle("Eliminar ejercicio")
                        .setMessage("¿Estás seguro de que deseas eliminar el ejercicio "+exercise.getName()+" ?")
                        .setPositiveButton("Sí", (dialog, which) -> {
                            viewModel.deleteExercise(exercise);
                            Toast.makeText(ExerciseListActivity.this, "Ejercicio eliminado", Toast.LENGTH_SHORT).show();
                        })
                        .setNegativeButton("Cancelar", null)
                        .show();
            }
        });
        recyclerView.setAdapter(adapter);

        FloatingActionButton btnAdd = findViewById(R.id.fabAddExercise);
        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddEditExerciseActivity.class);
            startActivity(intent);
        });

        viewModel = new ViewModelProvider(this).get(FitDisViewModel.class);
        viewModel.getAllExercises().observe(this, exercises -> {
            adapter.setExerciseList(exercises);
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}