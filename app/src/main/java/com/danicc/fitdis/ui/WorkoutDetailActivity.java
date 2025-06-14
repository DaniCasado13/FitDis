package com.danicc.fitdis.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.danicc.fitdis.Adapter.WorkoutExerciseAdapter;
import com.danicc.fitdis.R;
import com.danicc.fitdis.ViewModel.FitDisViewModel;
/**
 * clase que añade ejercicios al workout seleccionado
 * recibe el id del workout pulsado a traves de su id
 * e inserta los datos a la hora de pulsar un boton el cual
 * enlaza con un metodo del viewmodel
 * */
public class WorkoutDetailActivity extends AppCompatActivity {
    private FitDisViewModel viewModel;
    private RecyclerView recyclerView;
    private WorkoutExerciseAdapter adapter;
    private Button btnAddExercise;
    private int workoutId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_workout_detail);
        Toolbar toolbar = findViewById(R.id.toolbaraddworkoutdetail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.rvExercisesDetail);
        btnAddExercise = findViewById(R.id.btnAddExercise);

        adapter = new WorkoutExerciseAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(FitDisViewModel.class);

        workoutId = getIntent().getIntExtra("workoutId", -1);
        if (workoutId == -1) {
            Toast.makeText(this, "Workout no válido", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        viewModel.getWorkoutWithExercises(workoutId).observe(this, workoutWithExercises -> {
            if (workoutWithExercises != null) {
                setTitle("Workout " + workoutWithExercises.workout.getDate());
                adapter.setExercises(workoutWithExercises.exercises);
            }
        });

        // Observa crossRefs para mostrar sets, reps y weight
        viewModel.getCrossRefsForWorkout(workoutId).observe(this, adapter::setCrossRefs);

        // Listener para abrir el diálogo de añadir
        btnAddExercise.setOnClickListener(v -> showAddExerciseDialog());
    }
    private void showAddExerciseDialog() {
        AddExerciseDialog dialog = AddExerciseDialog.newInstance(workoutId);
        dialog.show(getSupportFragmentManager(), "AddExerciseDialog");

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}