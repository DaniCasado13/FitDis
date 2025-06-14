package com.danicc.fitdis.ui;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.danicc.fitdis.Entities.Exercise;
import com.danicc.fitdis.R;
import com.danicc.fitdis.ViewModel.FitDisViewModel;
/**
 * Activity que gestiona el añadido edicion y eliminacion
 * de un ejercicio en la lista de ejercicios a traves
 * del viewmodel.
 *
 * A esta clase se accede a traves de un intent en la lista de ejercicios
 *
 * */
public class AddEditExerciseActivity extends AppCompatActivity {
    private EditText etName, etDescription;
      private Spinner      spnMuscleGroup;
    private Button btnSave;
    private FitDisViewModel viewModel;
    private int exerciseId = -1; // por defecto: modo insertar
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_edit_exercise);

        Toolbar toolbar = findViewById(R.id.toolbaraddexercise);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        etName = findViewById(R.id.etExerciseName);
        spnMuscleGroup = findViewById(R.id.spnMuscles);
        etDescription = findViewById(R.id.etDescription);
        btnSave = findViewById(R.id.btnSaveExercise);
        viewModel = new ViewModelProvider(this).get(FitDisViewModel.class);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.muscle_groups_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMuscleGroup.setAdapter(adapter);

        // Ver si es modo edición
        exerciseId = getIntent().getIntExtra("exercise_id", -1);
        if (exerciseId != -1) {
            // Modo edición, cargar ejercicio
            viewModel.getExerciseById(exerciseId).observe(this, exercise -> {
                if (exercise != null) {
                    etName.setText(exercise.getName());
                    etDescription.setText(exercise.getDescription());

                    int position = ((ArrayAdapter)spnMuscleGroup.getAdapter()).getPosition(exercise.getMuscleGroup());
                    if(position >= 0) spnMuscleGroup.setSelection(position);

                }
            });
        }

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String description = etDescription.getText().toString().trim();
            String muscleGroup = spnMuscleGroup.getSelectedItem().toString();

            if (name.isEmpty()) {
                etName.setError("El nombre no puede estar vacío");
                return;
            }

            if (exerciseId != -1) {
                // Actualizar
                Exercise updated = new Exercise((int) exerciseId, name,muscleGroup, description);
                viewModel.updateExercise(updated);
                Toast.makeText(this, "Ejercicio actualizado", Toast.LENGTH_SHORT).show();
            } else {
                // Insertar nuevo
                Exercise newExercise = new Exercise(name,  muscleGroup,description);
                viewModel.insertExercise(newExercise);
                Toast.makeText(this, "Ejercicio creado", Toast.LENGTH_SHORT).show();
            }

            finish(); // Cierra la pantalla
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}