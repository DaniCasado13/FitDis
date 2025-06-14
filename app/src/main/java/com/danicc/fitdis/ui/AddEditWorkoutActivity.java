package com.danicc.fitdis.ui;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.danicc.fitdis.Entities.Workout;
import com.danicc.fitdis.R;
import com.danicc.fitdis.ViewModel.FitDisViewModel;

import java.time.LocalDate;
/**
 * Activity que gestiona el añadido de un nuevo
 * workout en la lista de workouts a traves
 * del viewmodel.
 *
 * A esta clase se accede a traves de un intent en la lista de workouts
 *
 * */
public class AddEditWorkoutActivity extends AppCompatActivity {
    private Button btnSelectDate, btnSaveWorkout;
    private FitDisViewModel viewModel;
    private LocalDate selectedDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_edit_workout);

        Toolbar toolbar = findViewById(R.id.toolbaraddworkout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnSelectDate = findViewById(R.id.btnSelectDate);
        btnSaveWorkout = findViewById(R.id.btnSaveWorkout);

        viewModel = new ViewModelProvider(this).get(FitDisViewModel.class);

        // DatePickerDialog
        btnSelectDate.setOnClickListener(v -> showDatePicker());

        btnSaveWorkout.setOnClickListener(v -> {
            if (selectedDate == null) {
                Toast.makeText(this, "Selecciona una fecha", Toast.LENGTH_SHORT).show();
                return;
            }

            Workout newWorkout = new Workout(selectedDate.toString());
            viewModel.insertWorkout(newWorkout);

            Toast.makeText(this, "Workout guardado", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
    private void showDatePicker() {
        LocalDate today = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            today = LocalDate.now();
        }
        DatePickerDialog dialog = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            dialog = new DatePickerDialog(
                    this,
                    (view, year, month, dayOfMonth) -> {
                        selectedDate = LocalDate.of(year, month + 1, dayOfMonth);
                        btnSelectDate.setText(selectedDate.toString());
                    },
                    today.getYear(), today.getMonthValue() - 1, today.getDayOfMonth()
            );
        }
        dialog.show();
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}