package com.danicc.fitdis.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.danicc.fitdis.Entities.Exercise;
import com.danicc.fitdis.Entities.WorkoutExerciseCrossRef;
import com.danicc.fitdis.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 *  Adapter para mostrar cada item de workoutexercise en la lista
 *  y los ejercicios asociado a ella
 *
 * */
public class WorkoutExerciseAdapter extends RecyclerView.Adapter<WorkoutExerciseAdapter.ViewHolder> {
    private List<Exercise> exercises = new ArrayList<>();
    private HashMap<Integer, WorkoutExerciseCrossRef> crossRefs = new HashMap<>();

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
        notifyDataSetChanged();
    }

    public void setCrossRefs(List<WorkoutExerciseCrossRef> crossRefsList) {
        crossRefs.clear();
        for (WorkoutExerciseCrossRef ref : crossRefsList) {
            crossRefs.put(ref.getExerciseId(), ref);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_detail_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Exercise exercise = exercises.get(position);
        holder.tvName.setText(exercise.getName());

        WorkoutExerciseCrossRef ref = crossRefs.get(exercise.getId());
        if (ref != null) {
            holder.tvSets.setText("Sets: " + ref.getSets());
            holder.tvReps.setText("Reps: " + ref.getRepetitions());
            holder.tvWeight.setText("Peso: " + ref.getWeight() + " kg");
        } else {
            holder.tvSets.setText("Sets: -");
            holder.tvReps.setText("Reps: -");
            holder.tvWeight.setText("Peso: -");
        }
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvSets, tvReps, tvWeight;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvExerciseName);
            tvSets = itemView.findViewById(R.id.tvSets);
            tvReps = itemView.findViewById(R.id.tvReps);
            tvWeight = itemView.findViewById(R.id.tvWeight);
        }
    }
}