package com.danicc.fitdis.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.danicc.fitdis.Entities.Exercise;
import com.danicc.fitdis.Entities.WorkoutExerciseCrossRef;
import com.danicc.fitdis.R;
import com.danicc.fitdis.ViewModel.FitDisViewModel;

import java.util.List;
/**
 *clase utilizada para abrir un cuadro de dialogo
 * a la hora de añadir un nuevo ejercicio a un workout
 * a traves de la relacion N:M
 *
 * a esta clase se accede al pulsar uno de los workouts
 * insertados en la pantalla de workouts
 * */

public class AddExerciseDialog extends DialogFragment {
    private FitDisViewModel viewModel;
    private Spinner spinner;
    private EditText etWeight, etSets, etReps;
    private int workoutId;
    private List<Exercise> allExercises;

    public static AddExerciseDialog newInstance(int workoutId) {
        AddExerciseDialog fragment = new AddExerciseDialog();
        Bundle args = new Bundle();
        args.putInt("workoutId", workoutId);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        workoutId = getArguments().getInt("workoutId", -1);
        viewModel = new ViewModelProvider(requireActivity()).get(FitDisViewModel.class);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_exercise, null);

        spinner = view.findViewById(R.id.spnExercisesDialog);
        etWeight = view.findViewById(R.id.etWeightDialog);
        etSets = view.findViewById(R.id.etSetsDialog);
        etReps = view.findViewById(R.id.etRepsDialog);
        Button btnAdd = view.findViewById(R.id.btnDialogAddExercise);

        // Cargar ejercicios al spinner
        viewModel.getAllExercises().observe(this, exercises -> {
            allExercises = exercises;
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    getExerciseNames(exercises)
            );
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
        });

        btnAdd.setOnClickListener(v -> {
            int position = spinner.getSelectedItemPosition();
            if (position < 0 || allExercises == null || allExercises.isEmpty()) {
                Toast.makeText(getContext(), "Selecciona un ejercicio válido", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int exerciseId = allExercises.get(position).getId();
                int weight = Integer.parseInt(etWeight.getText().toString());
                int sets = Integer.parseInt(etSets.getText().toString());
                int reps = Integer.parseInt(etReps.getText().toString());

                WorkoutExerciseCrossRef crossRef = new WorkoutExerciseCrossRef(workoutId, exerciseId, weight, sets, reps);
                viewModel.insertWorkoutExerciseCrossRef(crossRef);
                Toast.makeText(getContext(), "Ejercicio añadido", Toast.LENGTH_SHORT).show();
                dismiss();
            } catch (Exception e) {
                Toast.makeText(getContext(), "Completa todos los campos correctamente", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setView(view);
        return builder.create();
    }
    private String[] getExerciseNames(List<Exercise> exercises) {
        String[] names = new String[exercises.size()];
        for (int i = 0; i < exercises.size(); i++) {
            names[i] = exercises.get(i).getName();
        }
        return names;
    }
}
